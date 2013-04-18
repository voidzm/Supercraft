package com.voidzm.supercraft.util;

import net.minecraft.item.ItemStack;

public class ElectroplationEngineMap {

	private int catalystID, metalID;
	private ItemStack outputStack;
	
	public ElectroplationEngineMap(ItemStack stack1, ItemStack stack2, ItemStack output) {
		this.catalystID = stack1.itemID;
		this.metalID = stack2.itemID;
		this.outputStack = output;
	}
	
	public int getCatalystID() {
		return this.catalystID;
	}
	
	public int getMetalID() {
		return this.metalID;
	}
	
	public ItemStack getOutput() {
		return this.outputStack;
	}
	
}
