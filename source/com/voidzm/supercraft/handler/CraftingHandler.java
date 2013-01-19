//////////////////////////////////////
//*       CraftingHandler.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all crafting recipes.

package com.voidzm.supercraft.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingHandler {

	// Official vanilla Minecraft
	
	private static ItemStack glass;
	private static ItemStack coal;
	private static ItemStack coalBlock;
	private static ItemStack ironScrap;
	private static ItemStack ironIngot;
	private static ItemStack goldNugget;
	private static ItemStack diamond;
	private static ItemStack diamondShard;
	private static ItemStack obsidian;
	
	// Supercraft
	
	public void populateAllAndInitialize() {
		this.initializeCraftingRefs();
		this.addRecipes();
		this.addShapelessRecipes();
		System.out.println("[Supercraft] 10 recipes added.");
	}
	
	private void initializeCraftingRefs() {
		glass = new ItemStack(Block.glass);
		coal = new ItemStack(Item.coal);
		coalBlock = new ItemStack(BlockHandler.coalBlock);
		ironScrap = new ItemStack(ItemHandler.ironScrap);
		ironIngot = new ItemStack(Item.ingotIron);
		goldNugget = new ItemStack(Item.goldNugget);
		diamond = new ItemStack(Item.diamond);
		diamondShard = new ItemStack(ItemHandler.diamondShard);
		obsidian = new ItemStack(Block.obsidian);
	}
	
	private void addRecipes() {
		
		// Advanced Glass
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.reinforcedGlass), "a a", " b ", "a a", 'a', ironScrap, 'b', glass);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.temperedGlass), "a a", " b ", "a a", 'a', goldNugget, 'b', glass);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.ornateGlass), "a a", " b ", "a a", 'a', diamondShard, 'b', glass);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.impactGlass), "a a", " b ", "a a", 'a', obsidian, 'b', glass);
		
		// Material Extensions
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.coalBlock), "aaa", "aaa", "aaa", 'a', coal);
		GameRegistry.addRecipe(new ItemStack(Item.ingotIron), "aaa", "aaa", "aaa", 'a', ironScrap);
		GameRegistry.addRecipe(new ItemStack(Item.diamond), "aaa", "aaa", "aaa", 'a', diamondShard);
		
	}
	
	private void addShapelessRecipes() {
		
		// Material Extensions
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.coal, 9), coalBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.ironScrap, 9), ironIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.diamondShard, 9), diamond);
		
	}
	
}
