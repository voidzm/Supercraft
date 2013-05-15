package com.voidzm.supercraft.misc;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.handler.ItemHandler;

public class SlotVenianRod extends Slot {

	public SlotVenianRod(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}
	
	public boolean isItemValid(ItemStack par1ItemStack) {
		return (par1ItemStack.itemID == ItemHandler.venianRod.itemID);
	}

}
