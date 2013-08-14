package com.voidzm.supercraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.inventory.InventoryVeneficianPodium;
import com.voidzm.supercraft.misc.SlotVeneficianRod;
import com.voidzm.supercraft.util.VeneficianProperties;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerVeneficianPodium extends Container {

	private IInventory slot = new InventoryVeneficianPodium(this);
	
	private World world;
	private int x;
	private int y;
	private int z;
	
	private int vitality = 0;
	private int perception = 0;
	private int energy = 0;
	
	private int invSize = 1;
	
	public ContainerVeneficianPodium(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
		this.world = par2World;
		this.x = par3;
		this.y = par4;
		this.z = par5;
		this.addSlotToContainer(new SlotVeneficianRod(this.slot, 0, 30, 45));
		this.bindPlayerInventory(par1InventoryPlayer);
	}
	
	protected void bindPlayerInventory(InventoryPlayer player) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 104 + i * 18));
			}
		}
		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 162));
		}
	}
	
	public int getPower() {
		return this.vitality;
	}
	
	public int getRange() {
		return this.perception;
	}
	
	public int getDrain() {
		return this.energy;
	}
	
	public void setPower(int par1) {
		this.vitality = par1;
		this.updateRodProperties();
	}
	
	public void setRange(int par1) {
		this.perception = par1;
		this.updateRodProperties();
	}
	
	public void setDrain(int par1) {
		this.energy = par1;
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
		VeneficianProperties oldProp = VeneficianProperties.readFromItemStack(stack);
		VeneficianProperties newProp = new VeneficianProperties(oldProp.aspect, oldProp.material, this.vitality, this.perception, this.energy, oldProp.stability);
		newProp.applyProperties(stack);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory) {
		super.onCraftMatrixChanged(par1IInventory);
		if(par1IInventory.getStackInSlot(0) == null) {
			this.vitality = 0;
			this.perception = 0;
			this.energy = 0;
		}
		else {
			VeneficianProperties prop = VeneficianProperties.readFromItemStack(par1IInventory.getStackInSlot(0));
			this.vitality = prop.vitality;
			this.perception = prop.perception;
			this.energy = prop.energy;
		}
	}
	
	public ItemStack getItemStack() {
		return this.slot.getStackInSlot(0);
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.vitality);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.perception);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.energy);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		switch(par1) {
		case 0:
			this.vitality = par2;
			break;
		case 1:
			this.perception = par2;
			break;
		case 2:
			this.energy = par2;
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer){
		super.onContainerClosed(par1EntityPlayer);
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
				if(stackInSlot.itemID == ItemHandler.veneficianRod.itemID) {
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
