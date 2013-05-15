package com.voidzm.supercraft.util;

import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.util.EssentialReducerRecipes.EssentialAspect;

public class EssentialReducerCatalystMap {

	private int catalystID;
	private EssentialAspect aspect;
	
	public EssentialReducerCatalystMap(ItemStack item, EssentialAspect aspect1) {
		this.catalystID = item.itemID;
		this.aspect = aspect1;
	}
	
	public int getID() {
		return this.catalystID;
	}
	
	public EssentialAspect getAspect() {
		return this.aspect;
	}
	
}
