//////////////////////////////////////
//* ItemRefinedCraftingTable.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemRefinedCraftingTable extends ItemBlock {

	private final static String[] names = {"stoneTable", "stoneTable", "stoneTable", "netherTable", "endTable"};
	
	public ItemRefinedCraftingTable(int par1) {
		super(par1);
		setHasSubtypes(true);
		setItemName("refinedCraftingTable");
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	} 
	
	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return getItemName() + "." + names[itemstack.getItemDamage()];
	}
	
}
