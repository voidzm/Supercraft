//////////////////////////////////////
//*    ItemSupercraftSlab2.java    *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSupercraftSlab2 extends ItemBlock {

	private final static String[] names = {"palestoneBricksSlab"};
	
	public ItemSupercraftSlab2(int par1) {
		super(par1);
		setHasSubtypes(true);
		setItemName("supercraftSlab2");
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
