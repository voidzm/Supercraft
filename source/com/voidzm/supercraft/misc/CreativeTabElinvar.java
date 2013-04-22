package com.voidzm.supercraft.misc;

import com.voidzm.supercraft.handler.ItemHandler;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabElinvar extends CreativeTabs {

	public CreativeTabElinvar(String label) {
		super(label);
		LanguageRegistry.instance().addStringLocalization("itemGroup.elinvarTab", "Elinvar");
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemHandler.elinvarDust);
	}

}
