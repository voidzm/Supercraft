//////////////////////////////////////
//*     ItemSupercraftLog.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSupercraftLog extends ItemBlock {

	private final static String[] names = {"oliveLog", "goldenwoodLog", "tenebriaLog", "tenebriaCrystalLog"};
	
	public ItemSupercraftLog(int par1) {
		super(par1);
		setHasSubtypes(true);
		setUnlocalizedName("supercraftLog");
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	} 
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}
	
}
