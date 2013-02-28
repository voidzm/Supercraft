package com.voidzm.supercraft.misc;

import com.voidzm.supercraft.handler.ItemHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabElinvar extends CreativeTabs {

	public CreativeTabElinvar(String label) {
		super(label);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemHandler.elinvarDust);
	}

}
