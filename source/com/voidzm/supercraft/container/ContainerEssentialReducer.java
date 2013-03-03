package com.voidzm.supercraft.container;

import com.voidzm.supercraft.entity.TileEntityEssentialReducer;
import com.voidzm.supercraft.misc.SlotOutputOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEssentialReducer extends Container {

	protected TileEntityEssentialReducer te;
	private int invSize;
	
	public ContainerEssentialReducer(InventoryPlayer player, TileEntityEssentialReducer tile) {
		this.te = tile;
		this.invSize = tile.getSizeInventory();
		this.addSlotToContainer(new Slot(this.te, 0, 45, 22));
		this.addSlotToContainer(new Slot(this.te, 1, 45, 48));
		this.addSlotToContainer(new SlotOutputOnly(this.te, 2, 110, 35));
		this.bindPlayerInventory(player);
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
	
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return this.te.isUseableByPlayer(var1);
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
				if(TileEntityEssentialReducer.reducable.contains(new Integer(stackInSlot.itemID))) {
					if(!this.mergeItemStack(stackInSlot, 0, 1, false)) {
						return null;
					}
				}
				else if(TileEntityEssentialReducer.getCatalystType(stackInSlot.itemID) != null) {
					if(!this.mergeItemStack(stackInSlot, 1, 2, false)) {
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
