//////////////////////////////////////
//*      ItemElinvarDust.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
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
		this.setUnlocalizedName("elinvarDust");
	}
	
	public void func_94581_a(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.func_94245_a("supercraft:elinvardust");
	}
	
}
