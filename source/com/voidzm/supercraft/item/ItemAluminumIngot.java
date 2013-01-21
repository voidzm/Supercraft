//////////////////////////////////////
//*     ItemAluminumIngot.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemAluminumIngot extends Item {

	public ItemAluminumIngot(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setIconIndex(2);
		this.setItemName("aluminumIngot");
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
}
