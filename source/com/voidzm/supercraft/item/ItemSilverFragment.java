//////////////////////////////////////
//*     ItemSilverFragment.java    *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSilverFragment extends Item {

	public ItemSilverFragment(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setUnlocalizedName("silverFragment");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon("supercraft:silverfragment");
	}
	
}