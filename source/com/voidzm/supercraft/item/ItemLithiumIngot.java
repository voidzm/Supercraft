//////////////////////////////////////
//*      ItemLithiumIngot.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemLithiumIngot extends Item {

	public ItemLithiumIngot(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("lithiumIngot");
	}
	
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon("supercraft:ingotlithium");
	}
	
}
