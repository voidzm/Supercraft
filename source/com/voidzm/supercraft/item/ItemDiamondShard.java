//////////////////////////////////////
//*      ItemDiamondShard.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemDiamondShard extends Item {

	public ItemDiamondShard(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setIconIndex(1);
		this.setItemName("diamondShard");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
}
