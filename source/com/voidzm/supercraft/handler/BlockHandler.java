//////////////////////////////////////
//*        BlockHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all blocks.

package com.voidzm.supercraft.handler;

import java.util.ArrayList;

import com.voidzm.supercraft.block.*;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.item.ItemConduit;
import com.voidzm.supercraft.item.ItemRefinedCraftingTable;
import com.voidzm.supercraft.item.ItemSupercraftLeaves1;
import com.voidzm.supercraft.item.ItemSupercraftLeaves2;
import com.voidzm.supercraft.item.ItemSupercraftLog;
import com.voidzm.supercraft.item.ItemSupercraftPlanks;
import com.voidzm.supercraft.item.ItemSupercraftSapling1;
import com.voidzm.supercraft.item.ItemSupercraftSapling2;
import com.voidzm.supercraft.item.ItemSupercraftSlab1;
import com.voidzm.supercraft.item.ItemSupercraftSlab2;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockHandler {
	
	protected LanguageHandler languageHandler = new LanguageHandler();
	protected SupercraftConfiguration config;
	
	public static Block reinforcedGlass;
	public static Block temperedGlass;
	public static Block ornateGlass;
	public static Block impactGlass;
	
	public static Block coalBlock;
	
	public static Block supercraftLog;
	public static Block supercraftLeaves1;
	public static Block supercraftLeaves2;
	public static Block supercraftPlanks;
	public static Block supercraftSapling1;
	public static Block supercraftSapling2;
	public static Block supercraftSlab1;
	public static Block supercraftSlab2;
	
	public static Block oliveStairs;
	public static Block goldenwoodStairs;
	public static Block tenebriaStairs;
	
	public static Block aluminumOre;
	public static Block aluminumBlock;
	
	public static Block tantalumOre;
	public static Block tantalumBlock;
	
	public static Block copperOre;
	public static Block copperBlock;
	
	public static Block elinvarOre;
	public static Block elinvarBlock;
	
	public static Block silverOre;
	public static Block silverBlock;
	
	public static Block electrumOre;
	public static Block electrumBlock;
	
	public static Block conduit;
	
	public static Block refinedCraftingTable;
	
	public static Block redstoneGenerator;
	
	public static Block bluebells;
	public static Block daisies;
	public static Block snapdragon;
	
	public static Block palestone;
	public static Block palestoneBricks;
	public static Block palestoneStairs;
	public static Block inscribedPalestone;
	public static Block blockOfGoldenwood;
	public static Block overgrownPalestone;
	public static Block burnedPalestone;
	
	public static Block nightrock;
	public static Block nightrockBricks;
	public static Block nightrockStairs;
	public static Block inscribedNightrock;
	public static Block blockOfTenebral;
	public static Block burnedNightrock;
	
	public static Block nisilOre;
	public static Block nisilBlock;
	
	public static Block ironboundStone;
	public static Block essentialReducer;
	public static Block copperboundStone;
	public static Block radiantSolarGenerator;
	public static Block alloyInductor;
	public static Block silverboundStone;
	public static Block waveringLunarGenerator;
	
	public static Block cobaltOre;
	public static Block cobaltTorch;
	public static Block cobaltBlock;
	public static Block cobaltBlockOff;
	
	public static Block platinumOre;
	public static Block platinumBlock;
	
	public static Block lithiumOre;
	public static Block lithiumBlock;
	
	public static Block ghostlyVaporFlowing;
	public static Block ghostlyVaporStill;
	
	public void populateAllAndInitialize(SupercraftConfiguration configObject) {
		if(this.config != null) {
			throw new RuntimeException("Block handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for block handler initialization!");
		}
		this.config = configObject;
		this.createBlocks();
		this.populateLanguage();
		this.registerBlocks();
		this.initializeLanguage();
		System.out.println("[Supercraft] " + languageHandler.count() + " blocks added.");
	}
	
	private void populateLanguage() {
		
		// Advanced Glass
		
		languageHandler.add(reinforcedGlass, "Reinforced Glass");
		languageHandler.add(temperedGlass, "Tempered Glass");
		languageHandler.add(ornateGlass, "Ornate Glass");
		languageHandler.add(impactGlass, "Impact Glass");
		
		// Material Extensions
		
		languageHandler.add(coalBlock, "Block of Coal");
		
		// Supercraft Trees
		
		ArrayList<String> supercraftLogList = new ArrayList<String>();
		supercraftLogList.add("Olive Wood");
		supercraftLogList.add("Goldenwood Wood");
		supercraftLogList.add("Tenebria Wood");
		supercraftLogList.add("Crystalline Tenebria Wood");
		languageHandler.add(supercraftLog, supercraftLogList);
		
		ArrayList<String> supercraftLeaves1List = new ArrayList<String>();
		supercraftLeaves1List.add("Olive Leaves");
		languageHandler.add(supercraftLeaves1, supercraftLeaves1List);
		
		ArrayList<String> supercraftLeaves2List = new ArrayList<String>();
		supercraftLeaves2List.add("Goldenwood Leaves");
		supercraftLeaves2List.add("Tenebria Leaves");
		languageHandler.add(supercraftLeaves2, supercraftLeaves2List);
		
		ArrayList<String> supercraftPlanksList = new ArrayList<String>();
		supercraftPlanksList.add("Olive Wood Planks");
		supercraftPlanksList.add("Goldenwood Wood Planks");
		supercraftPlanksList.add("Tenebria Wood Planks");
		languageHandler.add(supercraftPlanks, supercraftPlanksList);
		
		ArrayList<String> supercraftSapling1List = new ArrayList<String>();
		supercraftSapling1List.add("Olive Sapling");
		languageHandler.add(supercraftSapling1, supercraftSapling1List);
		
		ArrayList<String> supercraftSapling2List = new ArrayList<String>();
		supercraftSapling2List.add("Goldenwood Sapling");
		supercraftSapling2List.add("Tenebria Sapling");
		languageHandler.add(supercraftSapling2, supercraftSapling2List);
		
		ArrayList<String> supercraftSlab1List = new ArrayList<String>();
		supercraftSlab1List.add("Olive Wood Slab");
		supercraftSlab1List.add("Goldenwood Wood Slab");
		supercraftSlab1List.add("Tenebria Wood Slab");
		languageHandler.add(supercraftSlab1, supercraftSlab1List);
		
		ArrayList<String> supercraftSlab2List = new ArrayList<String>();
		supercraftSlab2List.add("Palestone Bricks Slab");
		supercraftSlab2List.add("Nightrock Bricks Slab");
		languageHandler.add(supercraftSlab2, supercraftSlab2List);
		
		languageHandler.add(oliveStairs, "Olive Wood Stairs");
		languageHandler.add(goldenwoodStairs, "Goldenwood Wood Stairs");
		languageHandler.add(tenebriaStairs, "Tenebria Wood Stairs");
		
		// Aluminum
		
		languageHandler.add(aluminumOre, "Aluminum Ore");
		languageHandler.add(aluminumBlock, "Block of Aluminum");
		
		// Tantalum
		
		languageHandler.add(tantalumOre, "Tantalum Ore");
		languageHandler.add(tantalumBlock, "Block of Tantalum");
		
		// Copper
		
		languageHandler.add(copperOre, "Copper Ore");
		languageHandler.add(copperBlock, "Block of Copper");
		
		// Silver
		
		languageHandler.add(silverOre, "Silver Ore");
		languageHandler.add(silverBlock, "Block of Silver");
		
		// Electrum
		
		languageHandler.add(electrumOre, "Electrum Ore");
		languageHandler.add(electrumBlock, "Block of Electrum");
		
		// Elinvar
		
		languageHandler.add(elinvarOre, "Elinvar Ore");
		languageHandler.add(elinvarBlock, "Block of Elinvar");
		
		// Conduits
		
		ArrayList<String> conduitList = new ArrayList<String>();
		conduitList.add("Wooden Conduit");
		conduitList.add("Stone Conduit");
		conduitList.add("Iron Conduit");
		conduitList.add("Copper Conduit");
		conduitList.add("Aluminum Conduit");
		conduitList.add("Silver Conduit");
		conduitList.add("Golden Conduit");
		conduitList.add("Electrum Conduit");
		conduitList.add("Diamond Conduit");
		conduitList.add("Cobalt Conduit");
		conduitList.add("Platinum Conduit");
		conduitList.add("Tantalum Conduit");
		conduitList.add("Lithium Conduit");
		languageHandler.add(conduit, conduitList);
		
		// Refined Crafting Tables
		
		ArrayList<String> refinedCraftingTableList = new ArrayList<String>();
		refinedCraftingTableList.add("Stone Crafting Table");
		refinedCraftingTableList.add("Stone Crafting Table");
		refinedCraftingTableList.add("Stone Crafting Table");
		refinedCraftingTableList.add("Nether Crafting Table");
		refinedCraftingTableList.add("End Crafting Table");
		refinedCraftingTableList.add("Palestone Crafting Table");
		refinedCraftingTableList.add("Nightrock Crafting Table");
		languageHandler.add(refinedCraftingTable, refinedCraftingTableList);
		
		// Elinvar Generators
		
		languageHandler.add(redstoneGenerator, "Redstone Conversion Generator");
		
		// Supercraft Flowers
		
		languageHandler.add(bluebells, "Bluebells");
		languageHandler.add(daisies, "Daisies");
		languageHandler.add(snapdragon, "Snapdragon");
		
		// Palestone
		
		languageHandler.add(palestone, "Palestone");
		languageHandler.add(palestoneBricks, "Palestone Bricks");
		languageHandler.add(palestoneStairs, "Palestone Brick Stairs");
		languageHandler.add(inscribedPalestone, "Inscribed Palestone");
		languageHandler.add(blockOfGoldenwood, "Block of the Goldenwood");
		languageHandler.add(overgrownPalestone, "Overgrown Palestone Bricks");
		languageHandler.add(burnedPalestone, "Burned Palestone Bricks");
		
		// Nightrock
		
		languageHandler.add(nightrock, "Nightrock");
		languageHandler.add(nightrockBricks, "Nightrock Bricks");
		languageHandler.add(nightrockStairs, "Nightrock Brick Stairs");
		languageHandler.add(inscribedNightrock, "Inscribed Nightrock");
		languageHandler.add(blockOfTenebral, "Block of the Tenebral");
		languageHandler.add(burnedNightrock, "Burned Nightrock Bricks");
		
		// Nisil
		
		languageHandler.add(nisilOre, "Nisil Ore");
		languageHandler.add(nisilBlock, "Block of Nisil");
		
		// Machines
		
		languageHandler.add(ironboundStone, "Ironbound Stone");
		languageHandler.add(essentialReducer, "Essential Reducer");
		languageHandler.add(copperboundStone, "Copperbound Stone");
		languageHandler.add(radiantSolarGenerator, "Radiant Solar Generator");
		languageHandler.add(alloyInductor, "Alloy Inductor");
		languageHandler.add(silverboundStone, "Silverbound Stone");
		languageHandler.add(waveringLunarGenerator, "Wavering Lunar Generator");
		
		// Cobalt
		
		languageHandler.add(cobaltOre, "Cobalt Ore");
		languageHandler.add(cobaltTorch, "Cobalt Torch");
		languageHandler.add(cobaltBlock, "Block of Cobalt");
		languageHandler.add(cobaltBlockOff, "Block of Cobalt");
		
		// Platinum
		
		languageHandler.add(platinumOre, "Platinum Ore");
		languageHandler.add(platinumBlock, "Block of Platinum");
		
		// Lithium
		
		languageHandler.add(lithiumOre, "Lithium Ore");
		languageHandler.add(lithiumBlock, "Block of Lithium");
		
		// The Deep
		
		languageHandler.add(ghostlyVaporFlowing, "Ghostly Vapor");
		languageHandler.add(ghostlyVaporStill, "Ghostly Vapor");
		
	}
	
	private void createBlocks() {
		reinforcedGlass = new BlockReinforcedGlass(this.config.reinforcedglassID);
		temperedGlass = new BlockTemperedGlass(this.config.temperedglassID);
		ornateGlass = new BlockOrnateGlass(this.config.ornateglassID);
		impactGlass = new BlockImpactGlass(this.config.impactglassID);
		
		coalBlock = new BlockCoal(this.config.blockcoalID);

		supercraftLog = new BlockSupercraftLog(this.config.woodsupercraft1ID);
		supercraftLeaves1 = new BlockSupercraftLeaves1(this.config.leavessupercraft1ID);
		supercraftLeaves2 = new BlockSupercraftLeaves2(this.config.leavessupercraft2ID);
		supercraftPlanks = new BlockSupercraftPlanks(this.config.plankssupercraft1ID);
		supercraftSapling1 = new BlockSupercraftSapling1(this.config.saplingsupercraft1ID);
		supercraftSapling2 = new BlockSupercraftSapling2(this.config.saplingsupercraft2ID);
		supercraftSlab1 = new BlockSupercraftSlab1(this.config.slabsupercraft1ID);
		supercraftSlab2 = new BlockSupercraftSlab2(this.config.slabsupercraft2ID);
		oliveStairs = new BlockSupercraftStairs(this.config.stairsoliveID, supercraftPlanks, 0).setUnlocalizedName("oliveStairs");
		goldenwoodStairs = new BlockSupercraftStairs(this.config.stairsgoldenwoodID, supercraftPlanks, 1).setUnlocalizedName("goldenwoodStairs");
		tenebriaStairs = new BlockSupercraftStairs(this.config.stairstenebriaID, supercraftPlanks, 2).setUnlocalizedName("tenebriaStairs");
		
		aluminumOre = new BlockAluminumOre(this.config.orealuminumID);
		aluminumBlock = new BlockAluminum(this.config.blockaluminumID);
		tantalumOre = new BlockTantalumOre(this.config.oretantalumID);
		tantalumBlock = new BlockTantalum(this.config.blocktantalumID);
		copperOre = new BlockCopperOre(this.config.orecopperID);
		copperBlock = new BlockCopper(this.config.blockcopperID);
		silverOre = new BlockSilverOre(this.config.oresilverID);
		silverBlock = new BlockSilver(this.config.blocksilverID);
		electrumOre = new BlockElectrumOre(this.config.oreelectrumID);
		electrumBlock = new BlockElectrum(this.config.blockelectrumID);
		elinvarOre = new BlockElinvarOre(this.config.oreelinvarID);
		elinvarBlock = new BlockElinvar(this.config.blockelinvarID);
		
		conduit = new BlockConduit(this.config.conduitID);
		
		refinedCraftingTable = new BlockRefinedCraftingTable(this.config.refinedcraftingtableID);

		redstoneGenerator = new BlockRedstoneGenerator(this.config.redstoneconversiongeneratorID);
		radiantSolarGenerator = new BlockRadiantSolarGenerator(this.config.radiantsolargeneratorID);
		waveringLunarGenerator = new BlockWaveringLunarGenerator(this.config.waveringlunargeneratorID);
		
		bluebells = new BlockSupercraftFlower(this.config.bluebellsID, "supercraft:bluebells").setUnlocalizedName("bluebells");
		daisies = new BlockSupercraftFlower(this.config.daisiesID, "supercraft:daisies").setUnlocalizedName("daisies");
		snapdragon = new BlockSupercraftFlower(this.config.snapdragonID, "supercraft:snapdragon").setUnlocalizedName("snapdragon");

		palestone = new BlockPalestone(this.config.palestoneID);
		palestoneBricks = new BlockPalestoneBricks(this.config.palestonebricksID);
		palestoneStairs = new BlockSupercraftStairs(this.config.stairspalestoneID, palestoneBricks, 0).setUnlocalizedName("palestoneStairs");
		inscribedPalestone = new BlockInscribedPalestone(this.config.inscribedpalestoneID);
		blockOfGoldenwood = new BlockGoldenwood(this.config.blockgoldenwoodID);
		overgrownPalestone = new BlockOvergrownPalestone(this.config.overgrownpalestonebricksID);
		burnedPalestone = new BlockBurnedPalestone(this.config.burnedpalestonebricksID);

		nightrock = new BlockNightrock(this.config.nightrockID);
		nightrockBricks = new BlockNightrockBricks(this.config.nightrockbricksID);
		nightrockStairs = new BlockSupercraftStairs(this.config.stairsnightrockID, nightrockBricks, 0).setUnlocalizedName("nightrockStairs");
		inscribedNightrock = new BlockInscribedNightrock(this.config.inscribednightrockID);
		blockOfTenebral = new BlockTenebral(this.config.blocktenebralID);
		burnedNightrock = new BlockBurnedNightrock(this.config.burnednightrockbricksID);

		nisilOre = new BlockNisilOre(this.config.orenisilID);
		nisilBlock = new BlockNisil(this.config.blocknisilID);

		essentialReducer = new BlockEssentialReducer(this.config.essentialreducerID);
		alloyInductor = new BlockAlloyInductor(this.config.alloyinductorID);
		
		ironboundStone = new BlockIronboundStone(this.config.ironboundstoneID);
		copperboundStone = new BlockCopperboundStone(this.config.copperboundstoneID);
		silverboundStone = new BlockSilverboundStone(this.config.silverboundstoneID);

		cobaltOre = new BlockCobaltOre(this.config.orecobaltID);
		cobaltTorch = new BlockCobaltTorch(this.config.torchcobaltID);
		cobaltBlock = new BlockCobalt(this.config.blockcobaltID, true);
		cobaltBlockOff = new BlockCobalt(this.config.blockcobaltoffID, false);

		platinumOre = new BlockPlatinumOre(this.config.oreplatinumID);
		platinumBlock = new BlockPlatinum(this.config.blockplatinumID);

		lithiumOre = new BlockLithiumOre(this.config.orelithiumID);
		lithiumBlock = new BlockLithium(this.config.blocklithiumID);
		
		ghostlyVaporFlowing = new BlockGhostlyVaporFlowing(this.config.ghostlyvaporflowingID);
		ghostlyVaporStill = new BlockGhostlyVaporStill(this.config.ghostlyvaporstillID);
	}
	
	private void registerBlocks() {
		
		// Advanced Glass
		
		GameRegistry.registerBlock(reinforcedGlass, "reinforcedGlass");
		GameRegistry.registerBlock(temperedGlass, "temperedGlass");
		GameRegistry.registerBlock(ornateGlass, "ornateGlass");
		GameRegistry.registerBlock(impactGlass, "impactGlass");
		
		// Material Extensions
		
		GameRegistry.registerBlock(coalBlock, "coalBlock");
	
		// Supercraft Trees
		
		GameRegistry.registerBlock(supercraftLog, ItemSupercraftLog.class, "supercraftLog");
		GameRegistry.registerBlock(supercraftLeaves1, ItemSupercraftLeaves1.class, "supercraftLeaves1");
		GameRegistry.registerBlock(supercraftLeaves2, ItemSupercraftLeaves2.class, "supercraftLeaves2");
		GameRegistry.registerBlock(supercraftPlanks, ItemSupercraftPlanks.class, "supercraftPlanks");
		GameRegistry.registerBlock(supercraftSapling1, ItemSupercraftSapling1.class, "supercraftSapling1");
		GameRegistry.registerBlock(supercraftSapling2, ItemSupercraftSapling2.class, "supercraftSapling2");
		GameRegistry.registerBlock(supercraftSlab1, ItemSupercraftSlab1.class, "supercraftSlab1");
		GameRegistry.registerBlock(supercraftSlab2, ItemSupercraftSlab2.class, "supercraftSlab2");
		
		GameRegistry.registerBlock(oliveStairs, "oliveStairs");
		GameRegistry.registerBlock(goldenwoodStairs, "goldenwoodStairs");
		GameRegistry.registerBlock(tenebriaStairs, "tenebriaStairs");
		
		// Aluminum
		
		GameRegistry.registerBlock(aluminumOre, "aluminumOre");
		GameRegistry.registerBlock(aluminumBlock, "aluminumBlock");
		
		// Tantalum
		
		GameRegistry.registerBlock(tantalumOre, "tantalumOre");
		GameRegistry.registerBlock(tantalumBlock, "tantalumBlock");
		
		// Copper
		
		GameRegistry.registerBlock(copperOre, "copperOre");
		GameRegistry.registerBlock(copperBlock, "copperBlock");
		
		// Silver
		
		GameRegistry.registerBlock(silverOre, "silverOre");
		GameRegistry.registerBlock(silverBlock, "silverBlock");
		
		// Electrum
		
		GameRegistry.registerBlock(electrumOre, "electrumOre");
		GameRegistry.registerBlock(electrumBlock, "electrumBlock");
		
		// Elinvar
		
		GameRegistry.registerBlock(elinvarOre, "elinvarOre");
		GameRegistry.registerBlock(elinvarBlock, "elinvarBlock");
		
		// Elinvar Conduits
		
		GameRegistry.registerBlock(conduit, ItemConduit.class, "conduit");
		
		// Refined Crafting Tables
		
		GameRegistry.registerBlock(refinedCraftingTable, ItemRefinedCraftingTable.class, "refinedCraftingTable");
		
		// Elinvar Generators
		
		GameRegistry.registerBlock(redstoneGenerator, "redstoneGenerator");
		
		// Supercraft Flowers
		
		GameRegistry.registerBlock(bluebells, "bluebells");
		GameRegistry.registerBlock(daisies, "daisies");
		GameRegistry.registerBlock(snapdragon, "snapdragon");
		
		// Palestone
		
		GameRegistry.registerBlock(palestone, "palestone");
		GameRegistry.registerBlock(palestoneBricks, "palestoneBricks");
		GameRegistry.registerBlock(palestoneStairs, "palestoneStairs");
		GameRegistry.registerBlock(inscribedPalestone, "inscribedPalestone");
		GameRegistry.registerBlock(blockOfGoldenwood, "blockOfGoldenwood");
		GameRegistry.registerBlock(overgrownPalestone, "overgrownPalestone");
		GameRegistry.registerBlock(burnedPalestone, "burnedPalestone");
		
		// Nightrock

		GameRegistry.registerBlock(nightrock, "nightrock");
		GameRegistry.registerBlock(nightrockBricks, "nightrockBricks");
		GameRegistry.registerBlock(nightrockStairs, "nightrockStairs");
		GameRegistry.registerBlock(inscribedNightrock, "inscribedNightrock");
		GameRegistry.registerBlock(blockOfTenebral, "blockOfTenebral");
		GameRegistry.registerBlock(burnedNightrock, "burnedNightrock");
		
		// Nisil
		
		GameRegistry.registerBlock(nisilOre, "nisilOre");
		GameRegistry.registerBlock(nisilBlock, "nisilBlock");
		
		// Machines
		
		GameRegistry.registerBlock(ironboundStone, "ironboundStone");
		GameRegistry.registerBlock(essentialReducer, "essentialReducer");
		GameRegistry.registerBlock(copperboundStone, "copperboundStone");
		GameRegistry.registerBlock(radiantSolarGenerator, "radiantSolarGenerator");
		GameRegistry.registerBlock(alloyInductor, "alloyInductor");
		GameRegistry.registerBlock(silverboundStone, "silverboundStone");
		GameRegistry.registerBlock(waveringLunarGenerator, "waveringLunarGenerator");
		
		// Cobalt
		
		GameRegistry.registerBlock(cobaltOre, "cobaltOre");
		GameRegistry.registerBlock(cobaltTorch, "cobaltTorch");
		GameRegistry.registerBlock(cobaltBlock, "cobaltBlock");
		GameRegistry.registerBlock(cobaltBlockOff, "cobaltBlockOff");
		
		// Platinum
		
		GameRegistry.registerBlock(platinumOre, "platinumOre");
		GameRegistry.registerBlock(platinumBlock, "platinumBlock");
		
		// Lithium
		
		GameRegistry.registerBlock(lithiumOre, "lithiumOre");
		GameRegistry.registerBlock(lithiumBlock, "lithiumBlock");
		
		GameRegistry.registerBlock(ghostlyVaporFlowing, "ghostlyVaporFlowing");
		GameRegistry.registerBlock(ghostlyVaporStill, "ghostlyVaporStill");
		
	}
	
	private void initializeLanguage() {
		
		// Advanced Glass
		
		LanguageRegistry.addName(reinforcedGlass, (String)this.languageHandler.getString(reinforcedGlass));
		LanguageRegistry.addName(temperedGlass, (String)this.languageHandler.getString(temperedGlass));
		LanguageRegistry.addName(ornateGlass, (String)this.languageHandler.getString(ornateGlass));
		LanguageRegistry.addName(impactGlass, (String)this.languageHandler.getString(impactGlass));
		
		// Material Extensions
		
		LanguageRegistry.addName(coalBlock, (String)this.languageHandler.getString(coalBlock));
		
		// Supercraft Trees
	
		int i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftLog))  {
			ItemStack stack = new ItemStack(supercraftLog, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftLeaves1))  {
			ItemStack stack = new ItemStack(supercraftLeaves1, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftLeaves2))  {
			ItemStack stack = new ItemStack(supercraftLeaves2, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftPlanks))  {
			ItemStack stack = new ItemStack(supercraftPlanks, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftSapling1))  {
			ItemStack stack = new ItemStack(supercraftSapling1, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftSapling2))  {
			ItemStack stack = new ItemStack(supercraftSapling2, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftSlab1))  {
			ItemStack stack = new ItemStack(supercraftSlab1, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftSlab2))  {
			ItemStack stack = new ItemStack(supercraftSlab2, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		
		LanguageRegistry.addName(oliveStairs, (String)this.languageHandler.getString(oliveStairs));
		LanguageRegistry.addName(goldenwoodStairs, (String)this.languageHandler.getString(goldenwoodStairs));
		LanguageRegistry.addName(tenebriaStairs, (String)this.languageHandler.getString(tenebriaStairs));
		
		// Aluminum
		
		LanguageRegistry.addName(aluminumOre, (String)this.languageHandler.getString(aluminumOre));
		LanguageRegistry.addName(aluminumBlock, (String)this.languageHandler.getString(aluminumBlock));
		
		// Tantalum
		
		LanguageRegistry.addName(tantalumOre, (String)this.languageHandler.getString(tantalumOre));
		LanguageRegistry.addName(tantalumBlock, (String)this.languageHandler.getString(tantalumBlock));
		
		// Copper
		
		LanguageRegistry.addName(copperOre, (String)this.languageHandler.getString(copperOre));
		LanguageRegistry.addName(copperBlock, (String)this.languageHandler.getString(copperBlock));
		
		// Silver
		
		LanguageRegistry.addName(silverOre, (String)this.languageHandler.getString(silverOre));
		LanguageRegistry.addName(silverBlock, (String)this.languageHandler.getString(silverBlock));
		
		// Electrum
		
		LanguageRegistry.addName(electrumOre, (String)this.languageHandler.getString(electrumOre));
		LanguageRegistry.addName(electrumBlock, (String)this.languageHandler.getString(electrumBlock));
		
		// Elinvar
		
		LanguageRegistry.addName(elinvarOre, (String)this.languageHandler.getString(elinvarOre));
		LanguageRegistry.addName(elinvarBlock, (String)this.languageHandler.getString(elinvarBlock));
		
		// Elinvar Conduits
		
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(conduit))  {
			ItemStack stack = new ItemStack(conduit, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		
		// Refined Crafting Tables
		
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(refinedCraftingTable))  {
			ItemStack stack = new ItemStack(refinedCraftingTable, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		
		// Elinvar Generators
		
		LanguageRegistry.addName(redstoneGenerator, (String)this.languageHandler.getString(redstoneGenerator));
		
		// Supercraft Flowers
		
		LanguageRegistry.addName(bluebells, (String)this.languageHandler.getString(bluebells));
		LanguageRegistry.addName(daisies, (String)this.languageHandler.getString(daisies));
		LanguageRegistry.addName(snapdragon, (String)this.languageHandler.getString(snapdragon));
		
		// Palestone
		
		LanguageRegistry.addName(palestone, (String)this.languageHandler.getString(palestone));
		LanguageRegistry.addName(palestoneBricks, (String)this.languageHandler.getString(palestoneBricks));
		LanguageRegistry.addName(palestoneStairs, (String)this.languageHandler.getString(palestoneStairs));
		LanguageRegistry.addName(inscribedPalestone, (String)this.languageHandler.getString(inscribedPalestone));
		LanguageRegistry.addName(blockOfGoldenwood, (String)this.languageHandler.getString(blockOfGoldenwood));
		LanguageRegistry.addName(overgrownPalestone, (String)this.languageHandler.getString(overgrownPalestone));
		LanguageRegistry.addName(burnedPalestone, (String)this.languageHandler.getString(burnedPalestone));
		
		// Nightrock
		
		LanguageRegistry.addName(nightrock, (String)this.languageHandler.getString(nightrock));
		LanguageRegistry.addName(nightrockBricks, (String)this.languageHandler.getString(nightrockBricks));
		LanguageRegistry.addName(nightrockStairs, (String)this.languageHandler.getString(nightrockStairs));
		LanguageRegistry.addName(inscribedNightrock, (String)this.languageHandler.getString(inscribedNightrock));
		LanguageRegistry.addName(blockOfTenebral, (String)this.languageHandler.getString(blockOfTenebral));
		LanguageRegistry.addName(burnedNightrock, (String)this.languageHandler.getString(burnedNightrock));
		
		// Nisil
		
		LanguageRegistry.addName(nisilOre, (String)this.languageHandler.getString(nisilOre));
		LanguageRegistry.addName(nisilBlock, (String)this.languageHandler.getString(nisilBlock));
		
		// Machines
		
		LanguageRegistry.addName(ironboundStone, (String)this.languageHandler.getString(ironboundStone));
		LanguageRegistry.addName(essentialReducer, (String)this.languageHandler.getString(essentialReducer));
		LanguageRegistry.addName(copperboundStone, (String)this.languageHandler.getString(copperboundStone));
		LanguageRegistry.addName(radiantSolarGenerator, (String)this.languageHandler.getString(radiantSolarGenerator));
		LanguageRegistry.addName(alloyInductor, (String)this.languageHandler.getString(alloyInductor));
		LanguageRegistry.addName(silverboundStone, (String)this.languageHandler.getString(silverboundStone));
		LanguageRegistry.addName(waveringLunarGenerator, (String)this.languageHandler.getString(waveringLunarGenerator));
		
		// Cobalt
		
		LanguageRegistry.addName(cobaltOre, (String)this.languageHandler.getString(cobaltOre));
		LanguageRegistry.addName(cobaltTorch, (String)this.languageHandler.getString(cobaltTorch));
		LanguageRegistry.addName(cobaltBlock, (String)this.languageHandler.getString(cobaltBlock));
		LanguageRegistry.addName(cobaltBlockOff, (String)this.languageHandler.getString(cobaltBlockOff));
		
		// Platinum
		
		LanguageRegistry.addName(platinumOre, (String)this.languageHandler.getString(platinumOre));
		LanguageRegistry.addName(platinumBlock, (String)this.languageHandler.getString(platinumBlock));
		
		// Lithium
		
		LanguageRegistry.addName(lithiumOre, (String)this.languageHandler.getString(lithiumOre));
		LanguageRegistry.addName(lithiumBlock, (String)this.languageHandler.getString(lithiumBlock));
		
		LanguageRegistry.addName(ghostlyVaporFlowing, (String)this.languageHandler.getString(ghostlyVaporFlowing));
		LanguageRegistry.addName(ghostlyVaporStill, (String)this.languageHandler.getString(ghostlyVaporStill));
		
	}

}
