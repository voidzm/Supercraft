//////////////////////////////////////
//*        ItemHandler.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all items.

package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.item.ItemDiamondShard;
import com.voidzm.supercraft.item.ItemIronScrap;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.item.Item;

public class ItemHandler {

	protected LanguageHandler languageHandler = new LanguageHandler();
	
	public static Item ironScrap;
	public static Item diamondShard;
	
	public void populateAllAndInitialize() {
		this.createItems();
		this.populateLanguage();
		this.registerItems();
		this.initializeLanguage();
		System.out.println("[Supercraft] " + languageHandler.count() + " items added.");
	}
	
	private void populateLanguage() {
		languageHandler.add(ironScrap, "Iron Scrap");
		languageHandler.add(diamondShard, "Diamond Shard");
	}
	
	private void createItems() {
		ironScrap = new ItemIronScrap(23000);
		diamondShard = new ItemDiamondShard(23001);
	}
	
	private void registerItems() {
		GameRegistry.registerItem(ironScrap, "ironScrap");
		GameRegistry.registerItem(diamondShard, "diamondShard");
	}
	
	private void initializeLanguage() {
		LanguageRegistry.addName(ironScrap, this.languageHandler.getString(ironScrap));
		LanguageRegistry.addName(diamondShard, this.languageHandler.getString(diamondShard));
	}
	
}
