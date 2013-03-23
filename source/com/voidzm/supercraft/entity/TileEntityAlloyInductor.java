package com.voidzm.supercraft.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import com.voidzm.supercraft.block.BlockConduit;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.handler.PacketHandler.SCMachinePacketType;
import com.voidzm.supercraft.util.AlloyInductorComboMap;
import com.voidzm.supercraft.util.AlloyInductorRecipes;
import com.voidzm.supercraft.util.EssentialReducerRecipes;
import com.voidzm.supercraft.util.EssentialReducerRecipes.EssentialAspect;

import cpw.mods.fml.common.network.PacketDispatcher;
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

public class TileEntityAlloyInductor extends TileEntity implements IInventory {

	public static int t = 20;
	public static final int e = 4;
	private static ItemStack targetEssence = new ItemStack(ItemHandler.essence, 1, EssentialAspect.FERRIC.index);
	
	private ItemStack[] inventory;
	private boolean inducting;
	public int timeUntilInduction;
	public int essenceRemaining;
	
	public boolean doUpdateCheck;
	public boolean powered;
	public boolean needsEssenceDecrement;
	
	public TileEntityAlloyInductor() {
		inventory = new ItemStack[4];
		this.setInducting(false);
		this.timeUntilInduction = t;
		this.essenceRemaining = 0;
		this.powered = false;
		this.doUpdateCheck = true;
		this.needsEssenceDecrement = false;
	}
	
	public void updateClients() {
		if(this.worldObj == null) return;
		if(this.worldObj.isRemote) return;
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream(26);
		DataOutputStream dataStream = new DataOutputStream(byteArray);
		try {
			dataStream.writeInt(SCMachinePacketType.ALLOYINDUCTOR.index);
			dataStream.writeInt(this.xCoord);
			dataStream.writeInt(this.yCoord);
			dataStream.writeInt(this.zCoord);
			dataStream.writeBoolean(this.inducting);
			dataStream.writeBoolean(this.powered);
			dataStream.writeInt(this.timeUntilInduction);
			dataStream.writeInt(this.essenceRemaining);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "SCMachineUpdates";
		packet.data = byteArray.toByteArray();
		packet.length = byteArray.size();
		packet.isChunkDataPacket = true;
		PacketDispatcher.sendPacketToAllInDimension(packet, this.worldObj.provider.dimensionId);
	}
	
	public boolean isInducting() {
		return this.inducting;
	}
	
	public void setInducting(boolean val) {
		this.inducting = val;
	}
	
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
	}
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.customParam1);
	}
	
	public int getTimeLeft() {
		return this.timeUntilInduction;
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
		return "supercraft.alloyinductor";
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
		this.setInducting(tagCompound.getBoolean("Inducting"));
		this.timeUntilInduction = tagCompound.getInteger("TimeLeft");
		this.essenceRemaining = tagCompound.getInteger("EssenceLeft");
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
		tagCompound.setBoolean("Inducting", this.inducting);
		tagCompound.setInteger("TimeLeft", this.timeUntilInduction);
		tagCompound.setInteger("EssenceLeft", this.essenceRemaining);
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
			if(highestFound >= 8) {
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
		if(this.needsEssenceDecrement) {
			ItemStack essence = inventory[2];
			--essence.stackSize;
			if(essence.stackSize <= 0) inventory[2] = null;
			this.needsEssenceDecrement = false;
		}
		if(this.essenceRemaining > 0) {
			this.essenceRemaining--;
		}
		if(this.inducting) {
			if(this.timeUntilInduction > 1) {
				this.timeUntilInduction--;
			}
			else {
				this.timeUntilInduction = 0;
				if(!this.worldObj.isRemote) {
					doConversion();
					this.timeUntilInduction = t;
					checkForStateChange(false);
					this.updateClients();
				}
			}
		}
	}
	
	private void tryShutdown(boolean packet) {
		if(this.inducting == true) {
			this.setInducting(false);
			this.timeUntilInduction = t;
			if(packet) this.updateClients();
		}
	}
	
	private void tryStartup(boolean packet) {
		this.setInducting(true);
		this.timeUntilInduction = t;
		if(packet) this.updateClients();
	}
	
	private boolean canProvideEssence() {
		if(inventory[2] != null && inventory[2].isItemEqual(targetEssence)) return true;
		else return false;
	}
	
	private void doConversion() {
		if(this.worldObj.isRemote) return;
		ItemStack metal1 = inventory[0];
		if(metal1 == null || !AlloyInductorRecipes.isValidAlloyInput(metal1)) {
			return;
		}
		ItemStack metal2 = inventory[1];
		if(metal2 == null || !AlloyInductorRecipes.isValidInputForOther(metal2, metal1)) {
			return;
		}
		ItemStack targetOutput = AlloyInductorRecipes.outputForMetals(metal1, metal2);
		if(targetOutput == null) {
			return;
		}
		--metal1.stackSize;
		if(metal1.stackSize <= 0) inventory[0] = null;
		--metal2.stackSize;
		if(metal2.stackSize <= 0) inventory[1] = null;
		if(inventory[3] == null) {
			inventory[3] = targetOutput.copy();
		}
		else {
			if(inventory[3].isItemEqual(targetOutput) && inventory[3].stackSize < 64) {
				inventory[3].stackSize += targetOutput.stackSize;
			}
		}
	}
	
	private void checkForStateChange() {
		checkForStateChange(true);
	}
	
	private void checkForStateChange(boolean doPackets) {
		if(this.worldObj.isRemote) return;
		if(!this.powered) {
			tryShutdown(doPackets);
			return;
		}
		ItemStack metal1 = inventory[0];
		if(metal1 == null) {
			tryShutdown(doPackets);
			return;
		}
		ItemStack metal2 = inventory[1];
		if(metal2 == null) {
			tryShutdown(doPackets);
			return;
		}
		if(AlloyInductorRecipes.isValidAlloyInput(metal1)) {
			if(AlloyInductorRecipes.isValidInputForOther(metal2, metal1)) {
				ItemStack result = AlloyInductorRecipes.outputForMetals(metal1, metal2);
				if(result == null) {
					tryShutdown(doPackets);
					return;
				}
				if(this.essenceRemaining <= 0) {
					if(canProvideEssence()) {
						this.essenceRemaining = t*e;
						this.needsEssenceDecrement = true;
						this.updateClients();
					}
					else { 
						tryShutdown(doPackets);
						return;
					}
				}
				ItemStack output = inventory[3];
				if(output != null && !output.isItemEqual(result)) {
					tryShutdown(doPackets);
					return;
				}
				if(output != null && output.stackSize >= 64) {
					tryShutdown(doPackets);
					return;
				}
				if(this.inducting == false) {
					tryStartup(doPackets);
				}
			}
			else {
				tryShutdown(doPackets);
				return;
			}
		}
		else {
			tryShutdown(doPackets);
			return;
		}
	}
	
	public int progressInductionScaled(int par1) {
		return par1 - ((this.timeUntilInduction * par1) / t);
	}
	
	public int progressEssenceScaled(int par1) {
		return par1 - ((this.essenceRemaining * par1) / (t*e));
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		switch(i) {
		case 0:
			return AlloyInductorRecipes.isValidAlloyInput(itemstack);
		case 1:
			return AlloyInductorRecipes.isValidAlloyInput(itemstack);
		case 2:
			return targetEssence.isItemEqual(itemstack);
		case 3:
			return false;
		}
		return false;
	}
	
}
