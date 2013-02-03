//////////////////////////////////////
//*      ItemElectrumBit.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemElectrumBit extends Item {

	public ItemElectrumBit(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setIconIndex(9);
		this.setItemName("electrumBit");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
}
