package com.voidzm.supercraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

public class ItemSupercraftHoe extends ItemHoe implements IRegisterable {

	private EnumToolMaterial material;
	private RegisterData rdata = new RegisterData();
	private String icon;
	
	public ItemSupercraftHoe(int par1, EnumToolMaterial par2EnumToolMaterial, String unlocalized, String localized, String iconPath) {
		super(par1, par2EnumToolMaterial);
		material = par2EnumToolMaterial;
		this.setUnlocalizedName(unlocalized);
		this.setCreativeTab(CreativeTabs.tabTools);
		rdata.internalName = unlocalized;
		rdata.externalName = localized;
		icon = iconPath;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(this.icon);
	}
	
	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}

}
