package com.voidzm.supercraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSupercraft extends Item {

	private String iconString;
	private boolean shimmering = false;
	
	public ItemSupercraft(int par1, String unlocalized, String icon, CreativeTabs tab) {
		super(par1);
		this.setMaxStackSize(64);
		this.setUnlocalizedName(unlocalized);
		this.iconString = icon;
		this.setCreativeTab(tab);
	}
	
	public ItemSupercraft(int par1, String unlocalized, String icon) {
		this(par1, unlocalized, icon, CreativeTabs.tabMaterials);
	}
	
	@Override
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon(this.iconString);
	}
	
	public ItemSupercraft setHasShimmerEffect(boolean value) {
		this.shimmering = value;
		return this;
	}
	
	@Override
	public boolean hasEffect(ItemStack par1) {
		return this.shimmering;
	}

}
