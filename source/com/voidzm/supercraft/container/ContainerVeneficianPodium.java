package com.voidzm.supercraft.container;

import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.inventory.InventoryVeneficianPodium;
import com.voidzm.supercraft.misc.SlotVenianRod;
import com.voidzm.supercraft.util.ElectroplationEngineRecipes;
import com.voidzm.supercraft.util.VenianProperties;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerVeneficianPodium extends Container {

	private IInventory slot = new InventoryVeneficianPodium(this);
	
	private World world;
	private int x;
	private int y;
	private int z;
	
	private EntityPlayer player;
	
	private int powerVal = 0;
	private int rangeVal = 0;
	private int drainVal = 0;
	
	private int invSize = 1;
	
	public ContainerVeneficianPodium(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
		this.world = par2World;
		this.x = par3;
		this.y = par4;
		this.z = par5;
		this.player = par6EntityPlayer;
		this.addSlotToContainer(new SlotVenianRod(this.slot, 0, 26, 35));
		this.bindPlayerInventory(par1InventoryPlayer);
	}
	
	protected void bindPlayerInventory(InventoryPlayer player) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
		}
	}
	
	public int getPower() {
		return this.powerVal;
	}
	
	public int getRange() {
		return this.rangeVal;
	}
	
	public int getDrain() {
		return this.drainVal;
	}
	
	public void setPower(int par1) {
		this.powerVal = par1;
		this.updateRodProperties();
	}
	
	public void setRange(int par1) {
		this.rangeVal = par1;
		this.updateRodProperties();
	}
	
	public void setDrain(int par1) {
		this.drainVal = par1;
		this.updateRodProperties();
	}
	
	public void setField(int field, int val) {
		switch(field) {
		case 0:
			this.setPower(val);
			break;
		case 1:
			this.setRange(val);
			break;
		case 2:
			this.setDrain(val);
			break;
		default:
			break;
		}
	}
	
	private void updateRodProperties() {
		ItemStack stack = this.getItemStack();
		if(stack == null) return;
		VenianProperties oldProp = VenianProperties.readFromItemStack(stack);
		VenianProperties newProp = new VenianProperties(oldProp.aspect, oldProp.material, this.powerVal, this.rangeVal, this.drainVal);
		newProp.applyProperties(stack);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory) {
		super.onCraftMatrixChanged(par1IInventory);
		if(par1IInventory.getStackInSlot(0) == null) {
			this.powerVal = 0;
			this.rangeVal = 0;
			this.drainVal = 0;
		}
		else {
			VenianProperties prop = VenianProperties.readFromItemStack(par1IInventory.getStackInSlot(0));
			this.powerVal = prop.power;
			this.rangeVal = prop.range;
			this.drainVal = prop.drain;
		}
	}
	
	public ItemStack getItemStack() {
		return this.slot.getStackInSlot(0);
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.powerVal);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.rangeVal);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.drainVal);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		switch(par1) {
		case 0:
			this.powerVal = par2;
			break;
		case 1:
			this.rangeVal = par2;
			break;
		case 2:
			this.drainVal = par2;
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer){
		super.onCraftGuiClosed(par1EntityPlayer);
		if(!this.world.isRemote){
			ItemStack itemstack = this.slot.getStackInSlotOnClosing(0);
			if(itemstack != null){
				par1EntityPlayer.dropPlayerItem(itemstack);
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer){
		return this.world.getBlockId(this.x, this.y, this.z) != BlockHandler.veneficianPodium.blockID ? false : par1EntityPlayer.getDistanceSq((double)this.x + 0.5D, (double)this.y + 0.5D, (double)this.z + 0.5D) <= 64.0D;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot)inventorySlots.get(slot);
		if(slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();
			if(slot < this.invSize) {
				if(!this.mergeItemStack(stackInSlot, this.invSize, this.invSize + 36, true)) {
					return null;
				}
			}
			else {
				if(stackInSlot.itemID == ItemHandler.venianRod.itemID) {
					if(!this.mergeItemStack(stackInSlot, 0, 1, false)) {
						return null;
					}
				}
				else if(slot >= this.invSize && slot < this.invSize + 27) {
					if(!this.mergeItemStack(stackInSlot, this.invSize + 27, this.invSize + 36, false)) {
						return null;
					}
				}
				else if(slot >= this.invSize + 27 && slot < this.invSize + 36) {
					if(!this.mergeItemStack(stackInSlot, this.invSize, this.invSize + 27, false)) {
						return null;
					}
				}
			}
			if(stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			}
			else {
				slotObject.onSlotChanged();
			}
			if(stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}
	

}
