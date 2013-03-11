//////////////////////////////////////
//*        ItemConduit.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemConduit extends ItemBlock {

	private final static String[] names = {"wood", "stone", "iron", "copper", "aluminum", "silver", "gold", "electrum", "diamond", "cobalt", "platinum", "tantalum", "lithium"};
	
	public ItemConduit(int par1) {
		super(par1);
		setHasSubtypes(true);
		setItemName("conduit");
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
