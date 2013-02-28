//////////////////////////////////////
//*      ItemElinvarDust.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;

public class ItemElinvarDust extends Item {

	public ItemElinvarDust(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setCreativeTab(Supercraft.elinvarTab);
		this.setIconIndex(4);
		this.setItemName("elinvarDust");
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
}
