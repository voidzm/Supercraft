//////////////////////////////////////
//*    ItemSupercraftLeaves2.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSupercraftLeaves2 extends ItemBlock {

	private final static String[] names = {"goldenwoodLeaves", "tenebriaLeaves"};
	
	public ItemSupercraftLeaves2(int par1) {
		super(par1);
		setHasSubtypes(true);
		setUnlocalizedName("supercraftLeaves2");
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
