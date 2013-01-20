//////////////////////////////////////
//*     ItemSupercraftLog.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSupercraftLog extends ItemBlock {

	private final static String[] names = {"oliveLog"};
	
	public ItemSupercraftLog(int par1) {
		super(par1);
		setHasSubtypes(true);
		setItemName("supercraftLog");
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
