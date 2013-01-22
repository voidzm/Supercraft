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
	private static ItemStack stick;
	
	// Supercraft
	
	private static ItemStack oliveWood;
	private static ItemStack olivePlanks;
	private static ItemStack aluminum;
	private static ItemStack aluminumBlock;
	
	public void populateAllAndInitialize() {
		this.initializeCraftingRefs();
		this.addRecipes();
		this.addShapelessRecipes();
		this.addSmelting();
		System.out.println("[Supercraft] 14 recipes added.");
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
		stick = new ItemStack(Item.stick);
		
		oliveWood = new ItemStack(BlockHandler.supercraftLog, 1, 0);
		olivePlanks = new ItemStack(BlockHandler.supercraftPlanks, 1, 0);
		aluminum = new ItemStack(ItemHandler.aluminumIngot);
		aluminumBlock = new ItemStack(BlockHandler.aluminumBlock);
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
		
		// Supercraft Trees
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftWoodSlab, 6, 0), "aaa", 'a', olivePlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.oliveStairs, 4), "a  ", "aa ", "aaa", 'a', olivePlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.oliveStairs, 4), "  a", " aa", "aaa", 'a', olivePlanks);
		
		// Aluminum Material
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.aluminumBlock), "aaa", "aaa", "aaa", 'a', aluminum);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumPickaxe), "aaa", " b ", " b ", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumSaber), "a", "a", "b", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumShovel), "a", "b", "b", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumAxe), "aa", "ab", " b", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumAxe), "aa", "ba", "b ", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumHoe), "aa", " b", " b", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumHoe), "aa", "b ", "b ", 'a', aluminum, 'b', stick);
		
	}
	
	private void addShapelessRecipes() {
		
		// Material Extensions
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.coal, 9), coalBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.ironScrap, 9), ironIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.diamondShard, 9), diamond);
		
		// Supercraft Trees
		
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks, 4, 0), oliveWood);
		
		// Aluminum Material
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.aluminumIngot, 9), aluminumBlock);
		
	}
	
	private void addSmelting() {
		
		// Aluminum Material
		
		GameRegistry.addSmelting(BlockHandler.aluminumOre.blockID, new ItemStack(ItemHandler.aluminumIngot, 1), 0.4F);
		
		GameRegistry.addSmelting(BlockHandler.tantalumOre.blockID, new ItemStack(ItemHandler.tantalumCrystal, 1), 1.0F); // Temporary until Elivar machines.
		
	}
	
}
