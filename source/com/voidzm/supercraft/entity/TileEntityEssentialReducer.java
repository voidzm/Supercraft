package com.voidzm.supercraft.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.google.common.collect.ArrayListMultimap;
import com.voidzm.supercraft.block.BlockConduit;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.ItemHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEssentialReducer extends TileEntity implements IInventory {

	public static int t = 20;
	
	public enum CatalystType {
		EARTH, AIR, FIRE, WATER, SURFACE, NETHER, END, METAL, GEM, LIFE, DEATH, UNKNOWN
	}
	
	public static ArrayList<Integer> reducable = new ArrayList<Integer>();
	
	private ItemStack[] inventory;
	private boolean reducing;
	public int timeUntilReduction;
	
	public boolean doUpdateCheck;
	public boolean powered;
	
	public TileEntityEssentialReducer() {
		inventory = new ItemStack[3];
		this.populateReducable();
		this.setReducing(false);
		this.timeUntilReduction = t;
		this.powered = false;
		this.doUpdateCheck = true;
	}
	
	public void updateClients() {
		if(this.worldObj == null) return;
		if(this.worldObj.isRemote) return;
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream(18);
		DataOutputStream dataStream = new DataOutputStream(byteArray);
		try {
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
		packet.channel = "SCMachineUpdates";
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
		if(toReduce == null || !this.reducable.contains(new Integer(toReduce.itemID))) {
			return;
		}
		ItemStack toCatalyze = inventory[1];
		if(toCatalyze == null || this.getCatalystType(toCatalyze.itemID) == null) {
			return;
		}
		ItemStack targetOutput = this.resultForInputAndCatalyst(toReduce, toCatalyze);
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
		if(this.reducable.contains(new Integer(toBeReduced.itemID))) {
			if(this.getCatalystType(toBeCatalyzed.itemID) != null) {
				ItemStack result = this.resultForInputAndCatalyst(toBeReduced, toBeCatalyzed);
				if(result == null) return;
				ItemStack output = inventory[2];
				if(output != null && !output.isItemEqual(result)) {
					if(this.reducing == true) {
						System.out.println("Stopping!");
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

	private void populateReducable() {
		reducable.add(new Integer(Item.coal.itemID));
		reducable.add(new Integer(Item.diamond.itemID));
		reducable.add(new Integer(Item.ingotIron.itemID));
		reducable.add(new Integer(Item.ingotGold.itemID));
		reducable.add(new Integer(Item.silk.itemID));
		reducable.add(new Integer(Item.feather.itemID));
		reducable.add(new Integer(Item.gunpowder.itemID));
		reducable.add(new Integer(Item.seeds.itemID));
		reducable.add(new Integer(Item.wheat.itemID));
		reducable.add(new Integer(Item.flint.itemID));
		reducable.add(new Integer(Item.leather.itemID));
		reducable.add(new Integer(Item.brick.itemID));
		reducable.add(new Integer(Item.clay.itemID));
		reducable.add(new Integer(Item.reed.itemID));
		reducable.add(new Integer(Item.egg.itemID));
		reducable.add(new Integer(Item.lightStoneDust.itemID));
		reducable.add(new Integer(Item.sugar.itemID));
		reducable.add(new Integer(Item.pumpkinSeeds.itemID));
		reducable.add(new Integer(Item.melonSeeds.itemID));
		reducable.add(new Integer(Item.blazeRod.itemID));
		reducable.add(new Integer(Item.goldNugget.itemID));
		reducable.add(new Integer(Item.netherStalkSeeds.itemID));
		reducable.add(new Integer(Item.emerald.itemID));
		reducable.add(new Integer(Item.netherStar.itemID));
		reducable.add(new Integer(ItemHandler.ironScrap.itemID));
		reducable.add(new Integer(ItemHandler.diamondShard.itemID));
		reducable.add(new Integer(ItemHandler.aluminumIngot.itemID));
		reducable.add(new Integer(ItemHandler.tantalumCrystal.itemID));
		reducable.add(new Integer(ItemHandler.copperIngot.itemID));
		reducable.add(new Integer(ItemHandler.copperChunk.itemID));
		reducable.add(new Integer(ItemHandler.silverIngot.itemID));
		reducable.add(new Integer(ItemHandler.electrumIngot.itemID));
		reducable.add(new Integer(ItemHandler.electrumBit.itemID));
		reducable.add(new Integer(ItemHandler.bloodAmber.itemID));
		reducable.add(new Integer(ItemHandler.nisilIngot.itemID));
		reducable.add(new Integer(ItemHandler.nisilShard.itemID));
		reducable.add(new Integer(Item.redstone.itemID));
		reducable.add(new Integer(Item.snowball.itemID));
		reducable.add(new Integer(Item.paper.itemID));
		reducable.add(new Integer(Item.book.itemID));
		reducable.add(new Integer(Item.slimeBall.itemID));
		reducable.add(new Integer(Item.bone.itemID));
		reducable.add(new Integer(Item.enderPearl.itemID));
		reducable.add(new Integer(Item.eyeOfEnder.itemID));
		reducable.add(new Integer(Item.fireballCharge.itemID));
		reducable.add(new Integer(ItemHandler.elinvarDust.itemID));
		reducable.add(new Integer(Block.waterlily.blockID));
		reducable.add(new Integer(Item.glassBottle.itemID));
	}
	
	public static CatalystType getCatalystType(int id) {
		CatalystType type = null;
		if(isBlock(id)) {
			if(id == Block.dirt.blockID) {
				type = CatalystType.EARTH;
			}
			else if(id == Block.stone.blockID) {
				type = CatalystType.EARTH;
			}
			else if(id == Block.gravel.blockID) {
				type = CatalystType.EARTH;
			}
			else if(id == BlockHandler.palestone.blockID) {
				type = CatalystType.EARTH;
			}
			else if(id == BlockHandler.nightrock.blockID) {
				type = CatalystType.EARTH;
			}
			else if(id == Block.grass.blockID) {
				type = CatalystType.SURFACE;
			}
			else if(id == Block.cobblestone.blockID) {
				type = CatalystType.EARTH;
			}
			else if(id == Block.planks.blockID) {
				type = CatalystType.SURFACE;
			}
			else if(id == Block.sand.blockID) {
				type = CatalystType.EARTH;
			}
			else if(id == Block.oreGold.blockID) {
				type = CatalystType.METAL;
			}
			else if(id == Block.oreIron.blockID) {
				type = CatalystType.METAL;
			}
			else if(id == Block.oreCoal.blockID) {
				type = CatalystType.EARTH;
			}
			else if(id == Block.wood.blockID) {
				type = CatalystType.SURFACE;
			}
			else if(id == Block.glass.blockID) {
				type = CatalystType.AIR;
			}
			else if(id == Block.oreLapis.blockID) {
				type = CatalystType.GEM;
			}
			else if(id == Block.blockLapis.blockID) {
				type = CatalystType.GEM;
			}
			else if(id == Block.sandStone.blockID) {
				type = CatalystType.EARTH;
			}
			else if(id == Block.cloth.blockID) {
				type = CatalystType.SURFACE;
			}
			else if(id == Block.blockGold.blockID) {
				type = CatalystType.METAL;
			}
			else if(id == Block.blockSteel.blockID) {
				type = CatalystType.METAL;
			}
		}
		return type;
	}
	
	private ItemStack resultForInputAndCatalyst(ItemStack in, ItemStack cat) {
		CatalystType type = null;
		type = this.getCatalystType(cat.itemID);
		if(type == null) return null;
		if(in.isItemEqual(new ItemStack(Item.coal))) {
			if(type == CatalystType.FIRE || type == CatalystType.NETHER) {
				return new ItemStack(ItemHandler.essence, 1, 2);
			}
			else return new ItemStack(ItemHandler.essence, 1, 0);
		}
		else if(in.isItemEqual(new ItemStack(Item.diamond))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 8);
		}
		else if(in.isItemEqual(new ItemStack(Item.ingotIron))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 7);
		}
		else if(in.isItemEqual(new ItemStack(Item.ingotGold))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 7);
		}
		else if(in.isItemEqual(new ItemStack(Item.silk))) {
			if(type == CatalystType.DEATH) {
				return new ItemStack(ItemHandler.essence, 1, 10);
			}
			else return new ItemStack(ItemHandler.essence, 1, 9);
		}
		else if(in.isItemEqual(new ItemStack(Item.feather))) {
			if(type == CatalystType.LIFE) {
				return new ItemStack(ItemHandler.essence, 1, 9);
			}
			else return new ItemStack(ItemHandler.essence, 1, 4);
		}
		else if(in.isItemEqual(new ItemStack(Item.gunpowder))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 10);
		}
		else if(in.isItemEqual(new ItemStack(Item.seeds))) {
			if(type == CatalystType.SURFACE) {
				return new ItemStack(ItemHandler.essence, 1, 4);
			}
			else return new ItemStack(ItemHandler.essence, 1, 9);
		}
		else if(in.isItemEqual(new ItemStack(Item.wheat))) {
			if(type == CatalystType.SURFACE) {
				return new ItemStack(ItemHandler.essence, 1, 4);
			}
			else return new ItemStack(ItemHandler.essence, 1, 9);
		}
		else if(in.isItemEqual(new ItemStack(Item.flint))) {
			if(type == CatalystType.FIRE) {
				return new ItemStack(ItemHandler.essence, 1, 2);
			}
			else return new ItemStack(ItemHandler.essence, 1, 0);
		}
		else if(in.isItemEqual(new ItemStack(Item.leather))) {
			if(type == CatalystType.LIFE) {
				return new ItemStack(ItemHandler.essence, 1, 9);
			}
			else return new ItemStack(ItemHandler.essence, 1, 4);
		}
		else if(in.isItemEqual(new ItemStack(Item.brick))) {
			if(type == CatalystType.SURFACE) {
				return new ItemStack(ItemHandler.essence, 1, 4);
			}
			else return new ItemStack(ItemHandler.essence, 1, 0);
		}
		else if(in.isItemEqual(new ItemStack(Item.clay))) {
			if(type == CatalystType.SURFACE) {
				return new ItemStack(ItemHandler.essence, 1, 4);
			}
			else return new ItemStack(ItemHandler.essence, 1, 0);
		}
		else if(in.isItemEqual(new ItemStack(Item.reed))) {
			if(type == CatalystType.SURFACE) {
				return new ItemStack(ItemHandler.essence, 1, 4);
			}
			else return new ItemStack(ItemHandler.essence, 1, 9);
		}
		else if(in.isItemEqual(new ItemStack(Item.egg))) {
			if(type == CatalystType.LIFE) {
				return new ItemStack(ItemHandler.essence, 1, 9);
			}
			else return new ItemStack(ItemHandler.essence, 1, 4);
		}
		else if(in.isItemEqual(new ItemStack(Item.lightStoneDust))) {
			if(type == CatalystType.GEM) {
				return new ItemStack(ItemHandler.essence, 1, 8);
			}
			else return new ItemStack(ItemHandler.essence, 1, 5);
		}
		else if(in.isItemEqual(new ItemStack(Item.sugar))) {
			if(type == CatalystType.LIFE) {
				return new ItemStack(ItemHandler.essence, 1, 9);
			}
			else return new ItemStack(ItemHandler.essence, 1, 4);
		}
		else if(in.isItemEqual(new ItemStack(Item.pumpkinSeeds))) {
			if(type == CatalystType.SURFACE) {
				return new ItemStack(ItemHandler.essence, 1, 4);
			}
			else return new ItemStack(ItemHandler.essence, 1, 9);
		}
		else if(in.isItemEqual(new ItemStack(Item.melonSeeds))) {
			if(type == CatalystType.SURFACE) {
				return new ItemStack(ItemHandler.essence, 1, 4);
			}
			else return new ItemStack(ItemHandler.essence, 1, 9);
		}
		else if(in.isItemEqual(new ItemStack(Item.blazeRod))) {
			if(type == CatalystType.FIRE) {
				return new ItemStack(ItemHandler.essence, 1, 2);
			}
			else return new ItemStack(ItemHandler.essence, 1, 5);
		}
		else if(in.isItemEqual(new ItemStack(Item.goldNugget))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 7);
		}
		else if(in.isItemEqual(new ItemStack(Item.netherStalkSeeds))) {
			if(type == CatalystType.LIFE) {
				return new ItemStack(ItemHandler.essence, 1, 9);
			}
			else return new ItemStack(ItemHandler.essence, 1, 5);
		}
		else if(in.isItemEqual(new ItemStack(Item.emerald))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 8);
		}
		else if(in.isItemEqual(new ItemStack(Item.netherStar))) {
			if(type == CatalystType.GEM) {
				return new ItemStack(ItemHandler.essence, 1, 8);
			}
			else return new ItemStack(ItemHandler.essence, 1, 5);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.ironScrap))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 7);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.diamondShard))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 8);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.aluminumIngot))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 7);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.tantalumCrystal))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 8);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.copperIngot))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 7);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.copperChunk))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 7);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.silverIngot))) {
			if(type == CatalystType.METAL) {
				return new ItemStack(ItemHandler.essence, 1, 7);
			}
			else return new ItemStack(ItemHandler.essence, 1, 1);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.electrumIngot))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 7);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.electrumBit))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 7);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.bloodAmber))) {
			if(type == CatalystType.SURFACE) {
				return new ItemStack(ItemHandler.essence, 1, 4);
			}
			else return new ItemStack(ItemHandler.essence, 1, 10);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.nisilIngot))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 10);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.nisilShard))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 10);
		}
		else if(in.isItemEqual(new ItemStack(Item.redstone))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 8);
		}
		else if(in.isItemEqual(new ItemStack(Item.snowball))) {
			if(type == CatalystType.AIR) {
				return new ItemStack(ItemHandler.essence, 1, 1);
			}
			else return new ItemStack(ItemHandler.essence, 1, 4);
		}
		else if(in.isItemEqual(new ItemStack(Item.paper))) {
			if(type == CatalystType.AIR) {
				return new ItemStack(ItemHandler.essence, 1, 1);
			}
			else return new ItemStack(ItemHandler.essence, 1, 4);
		}
		else if(in.isItemEqual(new ItemStack(Item.book))) {
			if(type == CatalystType.AIR) {
				return new ItemStack(ItemHandler.essence, 1, 1);
			}
			else return new ItemStack(ItemHandler.essence, 1, 4);
		}
		else if(in.isItemEqual(new ItemStack(Item.slimeBall))) {
			if(type == CatalystType.DEATH) {
				return new ItemStack(ItemHandler.essence, 1, 10);
			}
			else return new ItemStack(ItemHandler.essence, 1, 4);
		}
		else if(in.isItemEqual(new ItemStack(Item.bone))) {
			if(type == CatalystType.EARTH) {
				return new ItemStack(ItemHandler.essence, 1, 0);
			}
			else return new ItemStack(ItemHandler.essence, 1, 10);
		}
		else if(in.isItemEqual(new ItemStack(Item.enderPearl))) {
			if(type == CatalystType.AIR) {
				return new ItemStack(ItemHandler.essence, 1, 1);
			}
			else return new ItemStack(ItemHandler.essence, 1, 6);
		}
		else if(in.isItemEqual(new ItemStack(Item.eyeOfEnder))) {
			if(type == CatalystType.AIR) {
				return new ItemStack(ItemHandler.essence, 1, 1);
			}
			else return new ItemStack(ItemHandler.essence, 1, 6);
		}
		else if(in.isItemEqual(new ItemStack(Item.fireballCharge))) {
			if(type == CatalystType.NETHER) {
				return new ItemStack(ItemHandler.essence, 1, 5);
			}
			else return new ItemStack(ItemHandler.essence, 1, 2);
		}
		else if(in.isItemEqual(new ItemStack(ItemHandler.elinvarDust))) {
			if(type == CatalystType.GEM) {
				return new ItemStack(ItemHandler.essence, 1, 8);
			}
			else return new ItemStack(ItemHandler.essence, 1, 5);
		}
		else if(in.isItemEqual(new ItemStack(Block.waterlily))) {
			if(type == CatalystType.SURFACE) {
				return new ItemStack(ItemHandler.essence, 1, 4);
			}
			else return new ItemStack(ItemHandler.essence, 1, 3);
		}
		else if(in.isItemEqual(new ItemStack(Item.glassBottle))) {
			if(type == CatalystType.AIR) {
				return new ItemStack(ItemHandler.essence, 1, 1);
			}
			else return new ItemStack(ItemHandler.essence, 1, 3);
		}
		else return null;
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
	
}
