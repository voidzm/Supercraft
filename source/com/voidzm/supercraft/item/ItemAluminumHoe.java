//////////////////////////////////////
//*      ItemAluminumHoe.java      *//
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

public class ItemAluminumHoe extends ItemHoe implements IRegisterable {

	private RegisterData rdata = new RegisterData();
	
	public ItemAluminumHoe(int par1) {
		super(par1, Supercraft.aluminumTool);
		this.setUnlocalizedName("aluminumHoe");
		this.setCreativeTab(CreativeTabs.tabTools);
		this.rdata.internalName = "hoealuminum";
		this.rdata.externalName = "Aluminum Hoe";
	}

	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("supercraft:hoealuminum");
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}
	
}
