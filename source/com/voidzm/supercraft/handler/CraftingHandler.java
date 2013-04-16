//////////////////////////////////////
//*       CraftingHandler.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all crafting recipes.

package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.util.SupercraftConfiguration;
import com.voidzm.supercraft.util.EssentialReducerRecipes.EssentialAspect;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingHandler {

	protected SupercraftConfiguration config;
	
	// Official vanilla Minecraft
	
	private ItemStack glass;
	private ItemStack coal;
	private ItemStack coalBlock;
	private ItemStack ironScrap;
	private ItemStack ironIngot;
	private ItemStack goldNugget;
	private ItemStack diamond;
	private ItemStack diamondShard;
	private ItemStack obsidian;
	private ItemStack stick;
	private ItemStack blueWool;
	private ItemStack greenWool;
	private ItemStack redWool;
	private ItemStack purpleWool;
	private ItemStack darkGrayWool;
	private ItemStack lightGrayWool;
	private ItemStack stone;
	private ItemStack craftingTable;
	private ItemStack endStone;
	private ItemStack netherBrick;
	private ItemStack redstone;
	private ItemStack stoneBrick;
	private ItemStack flint;
	
	// Supercraft
	
	private ItemStack oliveWood;
	private ItemStack olivePlanks;
	private ItemStack aluminum;
	private ItemStack aluminumBlock;
	private ItemStack copperChunk;
	private ItemStack copper;
	private ItemStack copperBlock;
	private ItemStack silverFragment;
	private ItemStack silver;
	private ItemStack silverBlock;
	private ItemStack electrumBit;
	private ItemStack electrum;
	private ItemStack electrumBlock;
	private ItemStack elinvarDust;
	private ItemStack elinvarBlock;
	private ItemStack goldenwoodWood;
	private ItemStack goldenwoodPlanks;
	private ItemStack bluebells;
	private ItemStack daisies;
	private ItemStack palestone;
	private ItemStack palestoneBricks;
	private ItemStack palestoneBrickSlab;
	private ItemStack tenebriaWood;
	private ItemStack tenebriaPlanks;
	private ItemStack nightrock;
	private ItemStack nightrockBricks;
	private ItemStack nightrockBrickSlab;
	private ItemStack nisil;
	private ItemStack nisilShard;
	private ItemStack nisilBlock;
	private ItemStack snapdragon;
	private ItemStack ironboundStone;
	private ItemStack copperboundStone;
	private ItemStack aeronicEssence;
	private ItemStack radantisEssence;
	private ItemStack cobaltDust;
	private ItemStack cobaltBlock;
	private ItemStack metallicCobalt;
	private ItemStack tantalum;
	private ItemStack tantalumBlock;
	private ItemStack platinum;
	private ItemStack platinumBlock;
	private ItemStack lithium;
	private ItemStack lithiumBlock;
	private ItemStack silverboundStone;
	private ItemStack ferricEssence;
	private ItemStack gravenStoneDarkness;
	private ItemStack gravenStoneGleaming;
	private ItemStack jade;
	private ItemStack voltasnia;
	private ItemStack inisiaWood;
	private ItemStack valensienWood;
	private ItemStack mortaliaWood;
	private ItemStack inisiaPlanks;
	private ItemStack valensienPlanks;
	private ItemStack mortaliaPlanks;
	private ItemStack draconium;
	private ItemStack draconiumBlock;
	private ItemStack gravenStoneBrilliance;
	
	public void init(SupercraftConfiguration configObject) {
		if(this.config != null) {
			throw new RuntimeException("Crafting handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for crafting handler initialization!");
		}
		this.config = configObject;
		this.initializeCraftingRefs();
		this.addRecipes();
		this.addShapelessRecipes();
		this.addSmelting();
		this.initOreDictionary();
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
		purpleWool = new ItemStack(Block.cloth, 1, 10);
		darkGrayWool = new ItemStack(Block.cloth, 1, 7);
		lightGrayWool = new ItemStack(Block.cloth, 1, 8);
		stone = new ItemStack(Block.stone);
		craftingTable = new ItemStack(Block.workbench);
		endStone = new ItemStack(Block.whiteStone);
		netherBrick = new ItemStack(Block.netherBrick);
		redstone = new ItemStack(Item.redstone);
		stoneBrick = new ItemStack(Block.stoneBrick);
		flint = new ItemStack(Item.flint);
		
		oliveWood = new ItemStack(BlockHandler.supercraftLog1, 1, 0);
		olivePlanks = new ItemStack(BlockHandler.supercraftPlanks, 1, 0);
		aluminum = new ItemStack(ItemHandler.aluminumIngot);
		aluminumBlock = new ItemStack(BlockHandler.aluminumBlock);
		copperChunk = new ItemStack(ItemHandler.copperChunk);
		copper = new ItemStack(ItemHandler.copperIngot);
		copperBlock = new ItemStack(BlockHandler.copperBlock);
		silverFragment = new ItemStack(ItemHandler.silverFragment);
		silver = new ItemStack(ItemHandler.silverIngot);
		silverBlock = new ItemStack(BlockHandler.silverBlock);
		electrumBit = new ItemStack(ItemHandler.electrumBit);
		electrum = new ItemStack(ItemHandler.electrumIngot);
		electrumBlock = new ItemStack(BlockHandler.electrumBlock);
		elinvarDust = new ItemStack(ItemHandler.elinvarDust);
		elinvarBlock = new ItemStack(BlockHandler.elinvarBlock);
		goldenwoodWood = new ItemStack(BlockHandler.supercraftLog1, 1, 1);
		goldenwoodPlanks = new ItemStack(BlockHandler.supercraftPlanks, 1, 1);
		bluebells = new ItemStack(BlockHandler.bluebells);
		daisies = new ItemStack(BlockHandler.daisies);
		palestone = new ItemStack(BlockHandler.palestone);
		palestoneBricks = new ItemStack(BlockHandler.palestoneBricks);
		palestoneBrickSlab = new ItemStack(BlockHandler.supercraftSlab2, 1, 0);
		tenebriaWood = new ItemStack(BlockHandler.supercraftLog1, 1, 2);
		tenebriaPlanks = new ItemStack(BlockHandler.supercraftPlanks, 1, 2);
		nightrock = new ItemStack(BlockHandler.nightrock);
		nightrockBricks = new ItemStack(BlockHandler.nightrockBricks);
		nightrockBrickSlab = new ItemStack(BlockHandler.supercraftSlab2, 1, 1);
		nisil = new ItemStack(ItemHandler.nisilIngot);
		nisilShard = new ItemStack(ItemHandler.nisilShard);
		nisilBlock = new ItemStack(BlockHandler.nisilBlock);
		snapdragon = new ItemStack(BlockHandler.snapdragon);
		ironboundStone = new ItemStack(BlockHandler.ironboundStone);
		copperboundStone = new ItemStack(BlockHandler.copperboundStone);
		aeronicEssence = new ItemStack(ItemHandler.essence, 1, EssentialAspect.AERONIC.index);
		radantisEssence = new ItemStack(ItemHandler.essence, 1, EssentialAspect.RADANTIS.index);
		cobaltDust = new ItemStack(ItemHandler.cobaltDust);
		cobaltBlock = new ItemStack(BlockHandler.cobaltBlock);
		metallicCobalt = new ItemStack(ItemHandler.metallicCobaltIngot);
		tantalum = new ItemStack(ItemHandler.tantalumCrystal);
		tantalumBlock = new ItemStack(BlockHandler.tantalumBlock);
		platinum = new ItemStack(ItemHandler.platinumIngot);
		platinumBlock = new ItemStack(BlockHandler.platinumBlock);
		lithium = new ItemStack(ItemHandler.lithiumIngot);
		lithiumBlock = new ItemStack(BlockHandler.lithiumBlock);
		silverboundStone = new ItemStack(BlockHandler.silverboundStone);
		ferricEssence = new ItemStack(ItemHandler.essence, 1, EssentialAspect.FERRIC.index);
		gravenStoneDarkness = new ItemStack(BlockHandler.gravenStone, 1, 0);
		gravenStoneGleaming = new ItemStack(BlockHandler.gravenStone, 1, 2);
		jade = new ItemStack(ItemHandler.jadeCrystal);
		voltasnia = new ItemStack(ItemHandler.voltasniaRod);
		inisiaWood = new ItemStack(BlockHandler.supercraftLog2, 1, 0);
		valensienWood = new ItemStack(BlockHandler.supercraftLog2, 1, 1);
		mortaliaWood = new ItemStack(BlockHandler.supercraftLog2, 1, 2);
		inisiaPlanks = new ItemStack(BlockHandler.supercraftPlanks, 1, 3);
		valensienPlanks = new ItemStack(BlockHandler.supercraftPlanks, 1, 4);
		mortaliaPlanks = new ItemStack(BlockHandler.supercraftPlanks, 1, 5);
		draconium = new ItemStack(ItemHandler.draconium);
		draconiumBlock = new ItemStack(BlockHandler.draconiumBlock);
		gravenStoneBrilliance = gravenStoneGleaming = new ItemStack(BlockHandler.gravenStone, 1, 3);
	}
	
	private void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(BlockHandler.reinforcedGlass), "a a", " b ", "a a", 'a', ironScrap, 'b', glass);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.temperedGlass), "a a", " b ", "a a", 'a', goldNugget, 'b', glass);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.ornateGlass), "a a", " b ", "a a", 'a', diamondShard, 'b', glass);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.impactGlass), "a a", " b ", "a a", 'a', obsidian, 'b', glass);
		
		if(this.config.docoalblockrecipe) GameRegistry.addRecipe(new ItemStack(BlockHandler.coalBlock), "aaa", "aaa", "aaa", 'a', coal);
		GameRegistry.addRecipe(new ItemStack(Item.ingotIron), "aaa", "aaa", "aaa", 'a', ironScrap);
		GameRegistry.addRecipe(new ItemStack(Item.diamond), "aaa", "aaa", "aaa", 'a', diamondShard);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 0), "aaa", 'a', olivePlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.oliveStairs, 4), "a  ", "aa ", "aaa", 'a', olivePlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.oliveStairs, 4), "  a", " aa", "aaa", 'a', olivePlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 1), "aaa", 'a', goldenwoodPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.goldenwoodStairs, 4), "a  ", "aa ", "aaa", 'a', goldenwoodPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.goldenwoodStairs, 4), "  a", " aa", "aaa", 'a', goldenwoodPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 2), "aaa", 'a', tenebriaPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.tenebriaStairs, 4), "a  ", "aa ", "aaa", 'a', tenebriaPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.tenebriaStairs, 4), "  a", " aa", "aaa", 'a', tenebriaPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 3), "aaa", 'a', inisiaPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inisiaStairs, 4), "a  ", "aa ", "aaa", 'a', inisiaPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inisiaStairs, 4), "  a", " aa", "aaa", 'a', inisiaPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 4), "aaa", 'a', valensienPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.valensienStairs, 4), "a  ", "aa ", "aaa", 'a', valensienPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.valensienStairs, 4), "  a", " aa", "aaa", 'a', valensienPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 5), "aaa", 'a', mortaliaPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.mortaliaStairs, 4), "a  ", "aa ", "aaa", 'a', mortaliaPlanks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.mortaliaStairs, 4), "  a", " aa", "aaa", 'a', mortaliaPlanks);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.aluminumBlock), "aaa", "aaa", "aaa", 'a', aluminum);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumPickaxe), "aaa", " b ", " b ", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumSaber), "a", "a", "b", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumShovel), "a", "b", "b", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumAxe), "aa", "ab", " b", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumAxe), "aa", "ba", "b ", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumHoe), "aa", " b", " b", 'a', aluminum, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumHoe), "aa", "b ", "b ", 'a', aluminum, 'b', stick);

		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperIngot), "aaa", "aaa", "aaa", 'a', copperChunk);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.copperBlock), "aaa", "aaa", "aaa", 'a', copper);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperPickaxe), "aaa", " b ", " b ", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperSword), "a", "a", "b", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperShovel), "a", "b", "b", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperAxe), "aa", "ab", " b", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperAxe), "aa", "ba", "b ", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperHoe), "aa", " b", " b", 'a', copper, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperHoe), "aa", "b ", "b ", 'a', copper, 'b', stick);

		GameRegistry.addRecipe(new ItemStack(ItemHandler.silverIngot), "aaa", "aaa", "aaa", 'a', silverFragment);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.silverBlock), "aaa", "aaa", "aaa", 'a', silver);

		GameRegistry.addRecipe(new ItemStack(ItemHandler.electrumIngot), "aaa", "aaa", "aaa", 'a', electrumBit);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.electrumBlock), "aaa", "aaa", "aaa", 'a', electrum);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.elinvarBlock), "aaa", "aaa", "aaa", 'a', elinvarDust);

		this.addConduitRecipes();

		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 0), " a ", "bcb", " b ", 'a', blueWool, 'b', stone, 'c', craftingTable);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 1), " a ", "bcb", " b ", 'a', greenWool, 'b', stone, 'c', craftingTable);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 2), " a ", "bcb", " b ", 'a', redWool, 'b', stone, 'c', craftingTable);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 3), " a ", "bcb", " b ", 'a', redWool, 'b', netherBrick, 'c', craftingTable);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 4), " a ", "bcb", " b ", 'a', purpleWool, 'b', endStone, 'c', craftingTable);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 5), " a ", "bcb", " b ", 'a', darkGrayWool, 'b', palestone, 'c', craftingTable);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 6), " a ", "bcb", " b ", 'a', lightGrayWool, 'b', nightrock, 'c', craftingTable);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.redstoneGenerator), "aba", "cac", "ddd", 'a', ironboundStone, 'b', redstone, 'c', obsidian, 'd', aluminum);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.palestoneBricks, 4), "aa", "aa", 'a', palestone);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab2, 6, 0), "aaa", 'a', palestoneBricks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.palestoneStairs, 4), "a  ", "aa ", "aaa", 'a', palestoneBricks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.palestoneStairs, 4), "  a", " aa", "aaa", 'a', palestoneBricks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inscribedPalestone), "a", "a", 'a', palestoneBrickSlab);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.nightrockBricks, 4), "aa", "aa", 'a', nightrock);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab2, 6, 1), "aaa", 'a', nightrockBricks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.nightrockStairs, 4), "a  ", "aa ", "aaa", 'a', nightrockBricks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.nightrockStairs, 4), "  a", " aa", "aaa", 'a', nightrockBricks);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inscribedNightrock), "a", "a", 'a', nightrockBrickSlab);

		GameRegistry.addRecipe(new ItemStack(ItemHandler.nisilIngot), "aaa", "aaa", "aaa", 'a', nisilShard);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.nisilBlock), "aaa", "aaa", "aaa", 'a', nisil);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.ironboundStone), "a a", " b ", "a a", 'a', ironScrap, 'b', stone);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.essentialReducer), "aba", "cdc", "aaa", 'a', ironboundStone, 'b', flint, 'c', elinvarDust, 'd', diamond);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.copperboundStone), "a a", " b ", "a a", 'a', copperChunk, 'b', stone);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.radiantSolarGenerator), "aba", "cdc", "eee", 'a', aeronicEssence, 'b', radantisEssence, 'c', obsidian, 'd', copper, 'e', copperboundStone);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.silverboundStone), "a a", " b ", "a a", 'a', silverFragment, 'b', stone);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.waveringLunarGenerator), "aba", "cdc", "eee", 'a', aeronicEssence, 'b', ferricEssence, 'c', obsidian, 'd', silver, 'e', silverboundStone);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.cobaltTorch, 4), "a", "b", 'a', cobaltDust, 'b', stick);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.cobaltBlock), "aaa", "aaa", "aaa", 'a', cobaltDust);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.tantalumBlock), "aaa", "aaa", "aaa", 'a', tantalum);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.platinumBlock), "aaa", "aaa", "aaa", 'a', platinum);

		GameRegistry.addRecipe(new ItemStack(BlockHandler.lithiumBlock), "aaa", "aaa", "aaa", 'a', lithium);
	
		GameRegistry.addRecipe(new ItemStack(BlockHandler.gravenStone, 1, 0), " a ", "aaa", " a ", 'a', nightrock);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.gravenStone, 1, 1), " a ", "aba", " a ", 'a', nightrock, 'b', stone);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.gravenStone, 1, 2), " a ", "aba", " a ", 'a', palestone, 'b', stone);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.gravenStone, 1, 3), " a ", "aaa", " a ", 'a', palestone);
	
		GameRegistry.addRecipe(new ItemStack(BlockHandler.monolithDemission), "aaa", "bcb", "aaa", 'a', gravenStoneDarkness, 'b', silver, 'c', cobaltBlock);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.monolithAscension), "aaa", "bcb", "aaa", 'a', gravenStoneGleaming, 'b', voltasnia, 'c', jade);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.monolithTermination), "aaa", "bcb", "aaa", 'a', gravenStoneBrilliance, 'b', silver, 'c', draconiumBlock);
	
		GameRegistry.addRecipe(new ItemStack(BlockHandler.draconiumBlock), "aaa", "aaa", "aaa", 'a', draconium);
	}
	
	private void addShapelessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(Item.coal, 9), coalBlock);
		if(this.config.doironscraprecipe) GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.ironScrap, 9), ironIngot);
		if(this.config.dodiamondshardrecipe) GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.diamondShard, 9), diamond);

		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks, 4, 0), oliveWood);
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks, 4, 1), goldenwoodWood);
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks, 4, 2), tenebriaWood);
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks, 4, 3), inisiaWood);
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks, 4, 4), valensienWood);
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks, 4, 5), mortaliaWood);

		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.aluminumIngot, 9), aluminumBlock);

		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.copperChunk, 9), copper);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.copperIngot, 9), copperBlock);

		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.silverFragment, 9), silver);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.silverIngot, 9), silverBlock);

		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.electrumBit, 9), electrum);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.electrumIngot, 9), electrumBlock);

		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.elinvarDust, 9), elinvarBlock);
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), bluebells);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 7), daisies);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 14), snapdragon);
		
		// TEMPORARY - CRYSTALLINE EXTRACTOR
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.bloodAmber), new ItemStack(BlockHandler.supercraftLog1, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.nisilShard, 9), nisil);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.nisilIngot, 9), nisilBlock);

		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.cobaltDust, 9), cobaltBlock);

		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.tantalumCrystal, 9), tantalumBlock);
	
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.lithiumIngot, 9), lithiumBlock);
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.draconium, 9), draconiumBlock);
	}
	
	private void addSmelting() {
		GameRegistry.addSmelting(BlockHandler.aluminumOre.blockID, new ItemStack(ItemHandler.aluminumIngot, 1), 0.4F);
		GameRegistry.addSmelting(BlockHandler.copperOre.blockID, new ItemStack(ItemHandler.copperIngot, 1), 0.4F);
		GameRegistry.addSmelting(BlockHandler.silverOre.blockID, new ItemStack(ItemHandler.silverIngot, 1), 0.5F);
		GameRegistry.addSmelting(BlockHandler.electrumOre.blockID, new ItemStack(ItemHandler.electrumIngot, 1), 0.6F);
		
		// Tantalum -- TEMPORARY until Crystalline Extracter	
		GameRegistry.addSmelting(BlockHandler.tantalumOre.blockID, new ItemStack(ItemHandler.tantalumCrystal, 1), 1.0F); // Temporary until Elinvar machines.

		GameRegistry.addSmelting(BlockHandler.nisilOre.blockID, new ItemStack(ItemHandler.nisilIngot, 1), 0.4F);
		GameRegistry.addSmelting(BlockHandler.platinumOre.blockID, new ItemStack(ItemHandler.platinumIngot, 1), 0.8F);
		GameRegistry.addSmelting(BlockHandler.lithiumOre.blockID, new ItemStack(ItemHandler.lithiumIngot, 1), 2.0F);
	}
	
	private void addConduitRecipes() {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 0), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "plankWood", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 1), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), Block.stone, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 2), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), Item.ingotIron, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 3), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "ingotCopper", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 4), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "ingotAluminum", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 5), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "ingotSilver", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 6), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), Item.ingotGold, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 7), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), "ingotElectrum", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 8), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), Item.diamond, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 9), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), ItemHandler.metallicCobaltIngot, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 10), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), ItemHandler.platinumIngot, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 11), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), ItemHandler.tantalumCrystal, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 12), new Object[]{"aba", "ccc", "aba", Character.valueOf('a'), ItemHandler.lithiumIngot, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
	}
	
	private void initOreDictionary() {
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog1, 1, 0));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog1, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog1, 1, 2));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog2, 1, 0));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog2, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog2, 1, 2));
		
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks, 1, 0));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks, 1, 1));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks, 1, 2));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks, 1, 3));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks, 1, 4));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks, 1, 5));
		
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 0));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 1));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 2));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 3));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 4));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 5));
		
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.oliveStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.goldenwoodStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.tenebriaStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.inisiaStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.valensienStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.mortaliaStairs));
		
		OreDictionary.registerOre("treeSapling", new ItemStack(BlockHandler.supercraftSapling1, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(BlockHandler.supercraftSapling2, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(BlockHandler.supercraftSapling2, 1, 1));
		
		OreDictionary.registerOre("treeLeaves", new ItemStack(BlockHandler.supercraftLeaves1, 1, 0));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BlockHandler.supercraftLeaves2, 1, 0));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BlockHandler.supercraftLeaves2, 1, 1));
		
		OreDictionary.registerOre("ingotAluminum", new ItemStack(ItemHandler.aluminumIngot));
		OreDictionary.registerOre("ingotCopper", new ItemStack(ItemHandler.copperIngot));
		OreDictionary.registerOre("ingotSilver", new ItemStack(ItemHandler.silverIngot));
		OreDictionary.registerOre("ingotElectrum", new ItemStack(ItemHandler.electrumIngot));
	}
	
}
