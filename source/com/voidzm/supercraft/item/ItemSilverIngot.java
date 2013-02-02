//////////////////////////////////////
//*      ItemSilverIngot.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSilverIngot extends Item {

	public ItemSilverIngot(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setIconIndex(5);
		this.setItemName("silverIngot");
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
}
