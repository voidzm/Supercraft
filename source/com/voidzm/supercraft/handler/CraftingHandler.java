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
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
	private static ItemStack blueWool;
	private static ItemStack greenWool;
	private static ItemStack redWool;
	private static ItemStack stone;
	private static ItemStack craftingTable;
	
	// Supercraft
	
	private static ItemStack oliveWood;
	private static ItemStack olivePlanks;
	private static ItemStack aluminum;
	private static ItemStack aluminumBlock;
	private static ItemStack copperChunk;
	private static ItemStack copper;
	private static ItemStack copperBlock;
	private static ItemStack silver;
	private static ItemStack silverBlock;
	private static ItemStack electrumBit;
	private static ItemStack electrum;
	private static ItemStack electrumBlock;
	private static ItemStack elinvarDust;
	private static ItemStack elinvarBlock;
	
	public void populateAllAndInitialize() {
		this.initializeCraftingRefs();
		this.addRecipes();
		this.addShapelessRecipes();
		this.addSmelting();
		System.out.println("[Supercraft] Recipes added.");
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
		blueWool = new ItemStack(Block.cloth, 1, 11);
		greenWool = new ItemStack(Block.cloth, 1, 13);
		redWool = new ItemStack(Block.cloth, 1, 14);
		stone = new ItemStack(Block.stone);
		craftingTable = new ItemStack(Block.workbench);
		
		oliveWood = new ItemStack(BlockHandler.supercraftLog, 1, 0);
		olivePlanks = new ItemStack(BlockHandler.supercraftPlanks, 1, 0);
		aluminum = new ItemStack(ItemHandler.aluminumIngot);
		aluminumBlock = new ItemStack(BlockHandler.aluminumBlock);
		copperChunk = new ItemStack(ItemHandler.copperChunk);
		copper = new ItemStack(ItemHandler.copperIngot);
		copperBlock = new ItemStack(BlockHandler.copperBlock);
		silver = new ItemStack(ItemHandler.silverIngot);
		silverBlock = new ItemStack(BlockHandler.silverBlock);
		electrumBit = new ItemStack(ItemHandler.electrumBit);
		electrum = new ItemStack(ItemHandler.electrumIngot);
		electrumBlock = new ItemStack(BlockHandler.electrumBlock);
		elinvarDust = new ItemStack(ItemHandler.elinvarDust);
		elinvarBlock = new ItemStack(BlockHandler.elinvarBlock);
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
		
		// Copper Material
		
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperIngot), "aaa", "aaa", "aaa", 'a', copperChunk);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.copperBlock), "aaa", "aaa", "aaa", 'a', copper);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperPickaxe), "aaa", " b ", " b ", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperSword), "a", "a", "b", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperShovel), "a", "b", "b", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperAxe), "aa", "ab", " b", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperAxe), "aa", "ba", "b ", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperHoe), "aa", " b", " b", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperHoe), "aa", "b ", "b ", 'a', copper, 'b', stick);
		
		// Aluminum Material
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.silverBlock), "aaa", "aaa", "aaa", 'a', silver);
		
		// Electrum Material
		
		GameRegistry.addRecipe(new ItemStack(ItemHandler.electrumIngot), "aaa", "aaa", "aaa", 'a', electrumBit);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.electrumBlock), "aaa", "aaa", "aaa", 'a', electrum);
		
		// Elinvar Material
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.elinvarBlock), "aaa", "aaa", "aaa", 'a', elinvarDust);
		
		// Elinvar Conduits
		
		this.addConduitRecipes();
		
		// Refined Crafting Tables
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 0), " a ", "bcb", " b ", 'a', blueWool, 'b', stone, 'c', craftingTable);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 1), " a ", "bcb", " b ", 'a', greenWool, 'b', stone, 'c', craftingTable);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 2), " a ", "bcb", " b ", 'a', redWool, 'b', stone, 'c', craftingTable);
		
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
		
		// Copper Material
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.copperChunk, 9), copper);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.copperIngot, 9), copperBlock);
		
		// Silver Material
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.silverIngot, 9), silverBlock);
		
		// Electrum Material
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.electrumBit, 9), electrum);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.electrumIngot, 9), electrumBlock);
		
		// Elinvar Material
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.elinvarDust, 9), elinvarBlock);
		
	}
	
	private void addSmelting() {
		
		// Aluminum Material
		
		GameRegistry.addSmelting(BlockHandler.aluminumOre.blockID, new ItemStack(ItemHandler.aluminumIngot, 1), 0.4F);
		
		// Copper Material
		
		GameRegistry.addSmelting(BlockHandler.copperOre.blockID, new ItemStack(ItemHandler.copperIngot, 1), 0.4F);
		
		// Silver Material
		
		GameRegistry.addSmelting(BlockHandler.silverOre.blockID, new ItemStack(ItemHandler.silverIngot, 1), 0.5F);
		
		// Electrum Material
		
		GameRegistry.addSmelting(BlockHandler.electrumOre.blockID, new ItemStack(ItemHandler.electrumIngot, 1), 0.6F);
		
		// Tantalum Material -- TEMPORARY until Crystalline Extracter
		
		GameRegistry.addSmelting(BlockHandler.tantalumOre.blockID, new ItemStack(ItemHandler.tantalumCrystal, 1), 1.0F); // Temporary until Elinvar machines.
		
	}
	
	private void addConduitRecipes() {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.woodenConduit, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "plankWood", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.stoneConduit, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), Block.stone, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.ironConduit, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), Item.ingotIron, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.copperConduit, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "ingotCopper", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.aluminumConduit, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "ingotAluminum", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.silverConduit, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "ingotSilver", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.goldenConduit, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), Item.ingotGold, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.electrumConduit, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "ingotElectrum", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.diamondConduit, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), Item.diamond, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
	}
	
}
