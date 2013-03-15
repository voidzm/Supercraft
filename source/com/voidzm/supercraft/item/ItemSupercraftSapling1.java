//////////////////////////////////////
//*   ItemSupercraftSapling1.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSupercraftSapling1 extends ItemBlock {

	private final static String[] names = {"oliveSapling"};
	
	public ItemSupercraftSapling1(int par1) {
		super(par1);
		setHasSubtypes(true);
		setUnlocalizedName("supercraftSapling1");
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
