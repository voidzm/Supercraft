package com.voidzm.supercraft.misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.handler.ItemHandler;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class CreativeTabVeneficia extends CreativeTabs {

	public CreativeTabVeneficia(String label) {
		super(label);
		LanguageRegistry.instance().addStringLocalization("itemGroup.veneficiaTab", "Veneficia");
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemHandler.arcaneRod, 1, 0);
	}

}
