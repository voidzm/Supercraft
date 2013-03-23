//////////////////////////////////////
//*    ItemTantalumCrystal.java    *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTantalumCrystal extends Item {

	public ItemTantalumCrystal(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("tantalumCrystal");
	}
	
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon("supercraft:tantalumcrystal");
	}
	
	public boolean hasEffect(ItemStack par1) {
		return true;
	}
	
}
