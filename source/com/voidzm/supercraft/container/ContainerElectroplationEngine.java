package com.voidzm.supercraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.misc.SlotOutputOnly;
import com.voidzm.supercraft.tileentity.TileEntityAlloyInductor;
import com.voidzm.supercraft.tileentity.TileEntityElectroplationEngine;
import com.voidzm.supercraft.tileentity.TileEntityEssentialReducer;
import com.voidzm.supercraft.util.AlloyInductorRecipes;
import com.voidzm.supercraft.util.ElectroplationEngineRecipes;
import com.voidzm.supercraft.util.EssentialReducerRecipes;
import com.voidzm.supercraft.util.EssentialReducerRecipes.EssentialAspect;

public class ContainerElectroplationEngine extends Container {
	
	protected TileEntityElectroplationEngine te;
	private int invSize;
	
	public ContainerElectroplationEngine(InventoryPlayer player, TileEntityElectroplationEngine tile) {
		this.te = tile;
		this.invSize = tile.getSizeInventory();
		this.addSlotToContainer(new Slot(this.te, 0, 26, 31));
		this.addSlotToContainer(new SlotOutputOnly(this.te, 1, 80, 31));
		this.addSlotToContainer(new Slot(this.te, 2, 134, 31));
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
				if(ElectroplationEngineRecipes.isValidCatalyst(stackInSlot)) {
					if(!this.mergeItemStack(stackInSlot, 0, 1, false)) {
						return null;
					}
				}
				else if(ElectroplationEngineRecipes.isValidMetal(stackInSlot)) {
					if(!this.mergeItemStack(stackInSlot, 2, 3, false)) {
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
