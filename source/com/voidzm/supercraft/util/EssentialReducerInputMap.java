package com.voidzm.supercraft.util;

import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.util.EssentialReducerRecipes.EssentialAspect;

public class EssentialReducerInputMap {
	
	private int sourceID;
	private EssentialAspect primaryAspect;
	private EssentialAspect secondaryAspect;
	
	public EssentialReducerInputMap(ItemStack item, EssentialAspect aspect1, EssentialAspect aspect2) {
		this.sourceID = item.itemID;
		this.primaryAspect = aspect1;
		this.secondaryAspect = aspect2;
	}
	
	public int getID() {
		return this.sourceID;
	}
	
	public EssentialAspect getPrimary() {
		return this.primaryAspect;
	}
	
	public EssentialAspect getSecondary() {
		return this.secondaryAspect;
	}
	
}
