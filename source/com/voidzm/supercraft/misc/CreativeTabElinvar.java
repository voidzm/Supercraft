package com.voidzm.supercraft.misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.handler.ItemHandler;

import cpw.mods.fml.common.registry.LanguageRegistry;

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
