//////////////////////////////////////
//*    ItemSupercraftPlanks.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSupercraftPlanks extends ItemBlock {

	private final static String[] names = {"olivePlanks", "goldenwoodPlanks"};
	
	public ItemSupercraftPlanks(int par1) {
		super(par1);
		setHasSubtypes(true);
		setItemName("supercraftPlanks");
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
