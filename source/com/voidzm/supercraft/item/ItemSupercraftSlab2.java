//////////////////////////////////////
//*    ItemSupercraftSlab2.java    *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSupercraftSlab2 extends ItemBlock {

	private final static String[] names = {"palestoneBricksSlab", "nightrockBricksSlab"};
	
	public ItemSupercraftSlab2(int par1) {
		super(par1);
		setHasSubtypes(true);
		setUnlocalizedName("supercraftSlab2");
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
