//////////////////////////////////////
//*       ItemCopperHoe.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

public class ItemCopperHoe extends ItemHoe implements IRegisterable {

	private RegisterData rdata = new RegisterData();
	
	public ItemCopperHoe(int par1) {
		super(par1, Supercraft.copperTool);
		this.setUnlocalizedName("copperHoe");
		this.setCreativeTab(CreativeTabs.tabTools);
		this.rdata.internalName = "hoecopper";
		this.rdata.externalName = "Copper Hoe";
	}

	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("supercraft:hoecopper");
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}
	
}
