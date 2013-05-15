package com.voidzm.supercraft.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

import com.voidzm.supercraft.block.BlockConduit;
import com.voidzm.supercraft.handler.PacketHandler.SCClientElinvarType;
import com.voidzm.supercraft.util.EssentialReducerRecipes;

import cpw.mods.fml.common.network.PacketDispatcher;

public class TileEntityEssentialReducer extends TileEntity implements IInventory {

	public static int t = 20;
	
	private ItemStack[] inventory;
	private boolean reducing;
	public int timeUntilReduction;
	
	public boolean doUpdateCheck;
	public boolean powered;
	
	public TileEntityEssentialReducer() {
		inventory = new ItemStack[3];
		this.setReducing(false);
		this.timeUntilReduction = t;
		this.powered = false;
		this.doUpdateCheck = true;
	}
	
	public void updateClients() {
		if(this.worldObj == null) return;
		if(this.worldObj.isRemote) return;
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream(22);
		DataOutputStream dataStream = new DataOutputStream(byteArray);
		try {
			dataStream.writeInt(SCClientElinvarType.ESSENTIALREDUCER.index);
			dataStream.writeInt(this.xCoord);
			dataStream.writeInt(this.yCoord);
			dataStream.writeInt(this.zCoord);
			dataStream.writeBoolean(this.reducing);
			dataStream.writeBoolean(this.powered);
			dataStream.writeInt(this.timeUntilReduction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "SCC|Elinvar";
		packet.data = byteArray.toByteArray();
		packet.length = byteArray.size();
		packet.isChunkDataPacket = true;
		PacketDispatcher.sendPacketToAllInDimension(packet, this.worldObj.provider.dimensionId);
	}
	
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
	}
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.customParam1);
	}
	
	public boolean isReducing() {
		return this.reducing;
	}
	
	public void setReducing(boolean val) {
		this.reducing = val;
	}
	
	public int getTimeLeft() {
		return this.timeUntilReduction;
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return inventory[var1];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		ItemStack cont = this.getStackInSlot(var1);
		if(cont != null) {
			if(cont.stackSize <= var2) {
				this.setInventorySlotContents(var1, null);
			}
			else {
				cont = cont.splitStack(var2);
				if(cont.stackSize == 0) {
					this.setInventorySlotContents(var1, null);
				}
			}
			return cont;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		ItemStack cont = this.getStackInSlot(var1);
		if(cont != null) {
			this.setInventorySlotContents(var1, null);
		}
		return cont;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		inventory[var1] = var2;
		if(var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
			var2.stackSize = this.getInventoryStackLimit();
		}
		this.checkForStateChange();
	}

	@Override
	public String getInvName() {
		return "supercraft.essentialreducer";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && var1.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for(int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound)tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if(slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		this.setReducing(tagCompound.getBoolean("Reducing"));
		this.timeUntilReduction = tagCompound.getInteger("TimeLeft");
		this.powered = tagCompound.getBoolean("Powered");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		NBTTagList tagList = new NBTTagList();
		for(int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if(stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				stack.writeToNBT(tag);
				tagList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", tagList);
		tagCompound.setBoolean("Reducing", this.reducing);
		tagCompound.setInteger("TimeLeft", this.timeUntilReduction);
		tagCompound.setBoolean("Powered", this.powered);
	}
	
	public void updateEntity() {
		if(doUpdateCheck && !this.worldObj.isRemote) {
			int[] neighbors = new int[6];
			neighbors[0] = this.worldObj.getBlockId(this.xCoord+1, this.yCoord, this.zCoord);
			neighbors[1] = this.worldObj.getBlockId(this.xCoord-1, this.yCoord, this.zCoord);
			neighbors[2] = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord+1);
			neighbors[3] = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord-1);
			neighbors[4] = this.yCoord < 255 ? this.worldObj.getBlockId(this.xCoord, this.yCoord+1, this.zCoord) : 0;
			neighbors[5] = this.yCoord > 0 ? this.worldObj.getBlockId(this.xCoord, this.yCoord-1, this.zCoord) : 0;
			int i = 0;
			int highestFound = 0;
			for(int val : neighbors) {
				if(BlockConduit.isConduit(val)) {
					TileEntityConduit te = (TileEntityConduit)this.worldObj.getBlockTileEntity(xCoord + (i == 0 ? 1 : (i == 1 ? -1 : 0)), yCoord + (i == 4 ? 1 : (i == 5 ? -1 : 0)), zCoord + (i == 2 ? 1 : (i == 3 ? -1 : 0)));
					if(te.powerLevel() > highestFound) highestFound = te.powerLevel();
				}
				i++;
			}
			if(highestFound >= 1) {
				if(!this.powered) {
					this.powered = true;
					this.updateClients();
					checkForStateChange();
				}
			}
			else if(this.powered) {
				this.powered = false;
				this.updateClients();
				checkForStateChange();
			}
			doUpdateCheck = false;
		}
		if(this.reducing) {
			if(this.timeUntilReduction > 1) {
				this.timeUntilReduction--;
			}
			else {
				this.timeUntilReduction = 0;
				if(!this.worldObj.isRemote) {
					doConversion();
					this.timeUntilReduction = t;
					checkForStateChange(false);
					this.updateClients();
				}
			}
		}
	}
	
	private void doConversion() {
		if(this.worldObj.isRemote) return;
		ItemStack toReduce = inventory[0];
		if(toReduce == null || !EssentialReducerRecipes.isStackReducable(toReduce)) {
			return;
		}
		ItemStack toCatalyze = inventory[1];
		if(toCatalyze == null || !EssentialReducerRecipes.isStackCatalytic(toCatalyze)) {
			return;
		}
		ItemStack targetOutput = EssentialReducerRecipes.outputForInputAndCatalyst(toReduce, toCatalyze);
		if(targetOutput == null) {
			return;
		}
		--toReduce.stackSize;
		if(toReduce.stackSize <= 0) inventory[0] = null;
		--toCatalyze.stackSize;
		if(toCatalyze.stackSize <= 0) inventory[1] = null;
		if(inventory[2] == null) {
			inventory[2] = targetOutput;
		}
		else {
			if(inventory[2].isItemEqual(targetOutput) && inventory[2].stackSize < 64) {
				inventory[2].stackSize++;
			}
			else {
				return;
			}
		}
	}
	
	private void checkForStateChange() {
		checkForStateChange(true);
	}
	
	private void checkForStateChange(boolean doPackets) {
		if(this.worldObj.isRemote) return;
		if(!this.powered) {
			if(this.reducing == true) {
				this.setReducing(false);
				this.timeUntilReduction = t;
				if(doPackets) this.updateClients();
			}
			return;
		}
		ItemStack toBeReduced = inventory[0];
		if(toBeReduced == null) {
			if(this.reducing == true) {
				this.setReducing(false);
				this.timeUntilReduction = t;
				if(doPackets) this.updateClients();
			}
			return;
		}
		ItemStack toBeCatalyzed = inventory[1];
		if(toBeCatalyzed == null) {
			if(this.reducing == true) {
				this.setReducing(false);
				this.timeUntilReduction = t;
				if(doPackets) this.updateClients();
			}
			return;
		}
		if(EssentialReducerRecipes.isStackReducable(toBeReduced)) {
			if(EssentialReducerRecipes.isStackCatalytic(toBeCatalyzed)) {
				ItemStack result = EssentialReducerRecipes.outputForInputAndCatalyst(toBeReduced, toBeCatalyzed);
				if(result == null) return;
				ItemStack output = inventory[2];
				if(output != null && !output.isItemEqual(result)) {
					if(this.reducing == true) {
						this.setReducing(false);
						this.timeUntilReduction = t;
						if(doPackets) this.updateClients();
					}
					return;
				}
				if(output != null && output.stackSize >= 64) {
					if(this.reducing == true) {
						this.setReducing(false);
						this.timeUntilReduction = t;
						if(doPackets) this.updateClients();
					}
					return;
				}
				else if(this.reducing == false) {
					this.setReducing(true);
					this.timeUntilReduction = t;
					if(doPackets) this.updateClients();
				}
			}
			else {
				if(this.reducing == true) {
					this.setReducing(false);
					this.timeUntilReduction = t;
					if(doPackets) this.updateClients();
				}
				return;
			}
		}
		else {
			if(this.reducing == true) {
				this.setReducing(false);
				this.timeUntilReduction = t;
				if(doPackets) this.updateClients();
			}
			return;
		}
	}
	
	public int progressScaled(int par1) {
		return par1 - ((this.timeUntilReduction * par1) / t);
	}
	
	public static boolean isBlock(int id) {
		if(id > 4095) return false;
		if(Block.blocksList[id] == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		switch(i) {
		case 0:
			return EssentialReducerRecipes.isStackReducable(itemstack);
		case 1:
			return EssentialReducerRecipes.isStackCatalytic(itemstack);
		case 2:
			return false;
		}
		return false;
	}

}
