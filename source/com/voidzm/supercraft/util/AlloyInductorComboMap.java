package com.voidzm.supercraft.util;

import net.minecraft.item.ItemStack;

public class AlloyInductorComboMap {

	private int metal1ID, metal2ID;
	private ItemStack outputStack;

	public AlloyInductorComboMap(ItemStack stack1, ItemStack stack2, ItemStack output) {
		this.metal1ID = stack1.itemID;
		this.metal2ID = stack2.itemID;
		this.outputStack = output;
	}
	
	public int getID1() {
		return this.metal1ID;
	}
	
	public int getID2() {
		return this.metal2ID;
	}
	
	public ItemStack getOutput() {
		return this.outputStack;
	}
	
}
