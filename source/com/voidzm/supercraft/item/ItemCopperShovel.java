//////////////////////////////////////
//*      ItemCopperShovel.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;
import net.minecraftforge.common.MinecraftForge;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

public class ItemCopperShovel extends ItemSpade implements IRegisterable {

	private RegisterData rdata = new RegisterData();
	
	public ItemCopperShovel(int par1) {
		super(par1, Supercraft.copperTool);
		this.setUnlocalizedName("copperShovel");
		this.setCreativeTab(CreativeTabs.tabTools);
		MinecraftForge.setToolClass(this, "shovel", 2);
		this.rdata.internalName = "shovelcopper";
		this.rdata.externalName = "Copper Shovel";
	}

	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("supercraft:shovelcopper");
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}
	
}
