//**
//**  BlockHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import java.util.ArrayList;

import com.voidzm.supercraft.block.*;
import com.voidzm.supercraft.block.BlockStorage.StorageType;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.item.*;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumPlantType;

public class BlockHandler {
	
	protected LanguageHandler languageHandler = new LanguageHandler();
	protected SupercraftConfiguration config;
	
	public static Block reinforcedGlass;
	public static Block temperedGlass;
	public static Block ornateGlass;
	public static Block impactGlass;
	
	public static Block aluminumOre;
	public static Block copperOre;
	public static Block silverOre;
	public static Block electrumOre;
	public static Block tantalumOre;
	public static Block nisilOre;
	public static Block platinumOre;
	public static Block lithiumOre;
	
	public static Block elinvarOre;
	public static Block cobaltOre;
	
	public static Block draconiumOre;
	
	public static Block incendiumOre;
	public static Block luxificenOre;
	public static Block jadeOre;
	public static Block voltasniaOre;
	
	public static Block coalBlock;
	public static Block aluminumBlock;
	public static Block copperBlock;
	public static Block silverBlock;
	public static Block electrumBlock;
	public static Block tantalumBlock;
	public static Block nisilBlock;
	public static Block platinumBlock;
	public static Block lithiumBlock;
	
	public static Block elinvarBlock;
	public static Block cobaltBlock;
	public static Block cobaltBlockOff;

	public static Block draconiumBlock;
	
	public static Block conduit;
	
	public static Block redstoneGenerator;
	public static Block radiantSolarGenerator;
	public static Block waveringLunarGenerator;
	public static Block celestialBalanceGenerator;
	
	public static Block ironboundStone;
	public static Block copperboundStone;
	public static Block silverboundStone;
	public static Block goldboundStone;
	
	public static Block essentialReducer;
	public static Block alloyInductor;
	public static Block electroplationEngine;

	public static Block gravenStone;
	
	public static Block monolithDemission;
	public static Block monolithDemissionActivated;
	public static Block monolithInception;
	public static Block monolithInceptionActivated;
	public static Block monolithAscension;
	public static Block monolithAscensionActivated;
	public static Block monolithTermination;
	public static Block monolithTerminationActivated;
	
	
	
	
	public static Block supercraftLog1;
	public static Block supercraftLog2;
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
	public static Block inisiaStairs;
	public static Block valensienStairs;
	public static Block mortaliaStairs;
	
	public static Block refinedCraftingTable;
	
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

	public static Block cobaltTorch;

	public static Block ghostlyVaporFlowing;
	public static Block ghostlyVaporStill;

	public static Block inisiaMushroom;
	public static Block valensienMushroom;
	public static Block mortaliaMushroom;

	public static Block radiantLeaves;

	public void init(SupercraftConfiguration configObject) {
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
		ArrayList<String> supercraftLog1List = new ArrayList<String>();
		supercraftLog1List.add("Olive Wood");
		supercraftLog1List.add("Goldenwood Wood");
		supercraftLog1List.add("Tenebria Wood");
		supercraftLog1List.add("Crystalline Tenebria Wood");
		languageHandler.add(supercraftLog1, supercraftLog1List);
		
		ArrayList<String> supercraftLog2List = new ArrayList<String>();
		supercraftLog2List.add("Inisia Wood");
		supercraftLog2List.add("Valensien Wood");
		supercraftLog2List.add("Mortalia Wood");
		languageHandler.add(supercraftLog2, supercraftLog2List);
		
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
		supercraftPlanksList.add("Inisia Wood Planks");
		supercraftPlanksList.add("Valensien Wood Planks");
		supercraftPlanksList.add("Mortalia Wood Planks");
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
		supercraftSlab1List.add("Inisia Wood Slab");
		supercraftSlab1List.add("Valensien Wood Slab");
		supercraftSlab1List.add("Mortalia Wood Slab");
		languageHandler.add(supercraftSlab1, supercraftSlab1List);
		
		ArrayList<String> supercraftSlab2List = new ArrayList<String>();
		supercraftSlab2List.add("Palestone Bricks Slab");
		supercraftSlab2List.add("Nightrock Bricks Slab");
		languageHandler.add(supercraftSlab2, supercraftSlab2List);
		
		languageHandler.add(oliveStairs, "Olive Wood Stairs");
		languageHandler.add(goldenwoodStairs, "Goldenwood Wood Stairs");
		languageHandler.add(tenebriaStairs, "Tenebria Wood Stairs");
		languageHandler.add(inisiaStairs, "Inisia Wood Stairs");
		languageHandler.add(valensienStairs, "Valensien Wood Stairs");
		languageHandler.add(mortaliaStairs, "Mortalia Wood Stairs");
		
		ArrayList<String> refinedCraftingTableList = new ArrayList<String>();
		refinedCraftingTableList.add("Stone Crafting Table");
		refinedCraftingTableList.add("Stone Crafting Table");
		refinedCraftingTableList.add("Stone Crafting Table");
		refinedCraftingTableList.add("Nether Crafting Table");
		refinedCraftingTableList.add("End Crafting Table");
		refinedCraftingTableList.add("Palestone Crafting Table");
		refinedCraftingTableList.add("Nightrock Crafting Table");
		languageHandler.add(refinedCraftingTable, refinedCraftingTableList);

		languageHandler.add(bluebells, "Bluebells");
		languageHandler.add(daisies, "Daisies");
		languageHandler.add(snapdragon, "Snapdragon");
		
		languageHandler.add(palestone, "Palestone");
		languageHandler.add(palestoneBricks, "Palestone Bricks");
		languageHandler.add(palestoneStairs, "Palestone Brick Stairs");
		languageHandler.add(inscribedPalestone, "Inscribed Palestone");
		languageHandler.add(blockOfGoldenwood, "Block of the Goldenwood");
		languageHandler.add(overgrownPalestone, "Overgrown Palestone Bricks");
		languageHandler.add(burnedPalestone, "Burned Palestone Bricks");
		
		languageHandler.add(nightrock, "Nightrock");
		languageHandler.add(nightrockBricks, "Nightrock Bricks");
		languageHandler.add(nightrockStairs, "Nightrock Brick Stairs");
		languageHandler.add(inscribedNightrock, "Inscribed Nightrock");
		languageHandler.add(blockOfTenebral, "Block of the Tenebral");
		languageHandler.add(burnedNightrock, "Burned Nightrock Bricks");
	
		languageHandler.add(cobaltTorch, "Cobalt Torch");

		languageHandler.add(ghostlyVaporFlowing, "Ghostly Vapor");
		languageHandler.add(ghostlyVaporStill, "Ghostly Vapor");

		languageHandler.add(inisiaMushroom, "Inisia Mushroom");
		languageHandler.add(valensienMushroom, "Valensien Mushroom");
		languageHandler.add(mortaliaMushroom, "Mortalia Mushroom");
		
		languageHandler.add(radiantLeaves, "Radiant Leaves");
	}
	
	private void createBlocks() {
		reinforcedGlass = new BlockReinforcedGlass(this.config.reinforcedglassID).register();
		temperedGlass = new BlockTemperedGlass(this.config.temperedglassID).register();
		ornateGlass = new BlockOrnateGlass(this.config.ornateglassID).register();
		impactGlass = new BlockImpactGlass(this.config.impactglassID).register();
		
		aluminumOre = new BlockStoneOre(this.config.orealuminumID, "supercraft:orealuminum").setInternalName("orealuminum").setExternalName("Aluminum Ore").register();
		copperOre = new BlockStoneOre(this.config.orecopperID, "supercraft:orecopper").setInternalName("orecopper").setExternalName("Copper Ore").register();
		silverOre = new BlockStoneOre(this.config.oresilverID, "supercraft:oresilver").setInternalName("oresilver").setExternalName("Silver Ore").register();
		electrumOre = new BlockStoneOre(this.config.oreelectrumID, "supercraft:oreelectrum").setInternalName("oreelectrum").setExternalName("Electrum Ore").register();
		tantalumOre = new BlockStoneOre(this.config.oretantalumID, "supercraft:oretantalum").setInternalName("oretantalum").setExternalName("Tantalum Ore").register();
		nisilOre = new BlockStoneOre(this.config.orenisilID, "supercraft:orenisil").setInternalName("orenisil").setExternalName("Nisil Ore").register();
		platinumOre = new BlockStoneOre(this.config.oreplatinumID, "supercraft:oreplatinum").setInternalName("oreplatinum").setExternalName("Platinum Ore").register();
		lithiumOre = new BlockStoneOre(this.config.orelithiumID, "supercraft:orelithium").setInternalName("orelithium").setExternalName("Lithium Ore").register();

		elinvarOre = new BlockNetherrackOre(this.config.oreelinvarID, "supercraft:oreelinvar", this.config.elinvardustID).setInternalName("oreelinvar").setExternalName("Elinvar Ore").register();
		cobaltOre = new BlockNetherrackOre(this.config.orecobaltID, "supercraft:orecobalt", this.config.cobaltdustID).setExtraDrop(3).setInternalName("orecobalt").setExternalName("Cobalt Ore").register();
		draconiumOre = new BlockEndStoneOre(this.config.oredraconiumID, "supercraft:oredraconium", this.config.draconiumID).setInternalName("oredraconium").setExternalName("Draconium Ore").register();
		
		incendiumOre = new BlockStoneOre(this.config.oreincendiumID, "supercraft:oreincendium", this.config.incendiumdustID).setExtraDrop(2).setInternalName("oreincendium").setExternalName("Incendium Ore").register();
		luxificenOre = new BlockStoneOre(this.config.oreluxificenID, "supercraft:oreluxificen", this.config.luxificendustID).setExtraDrop(3).setInternalName("oreluxificen").setExternalName("Luxificen Ore").register();
		jadeOre = new BlockStoneOre(this.config.orejadeID, "supercraft:orejade").setInternalName("orejade").setExternalName("Jade Ore").register();
		voltasniaOre = new BlockStoneOre(this.config.orevoltasniaID, "supercraft:orevoltasnia", this.config.voltasniarodID).setInternalName("orevoltasnia").setExternalName("Voltasnia Ore").register();
		
		coalBlock = new BlockStorage(this.config.blockcoalID, "supercraft:blockcoal", StorageType.DUST).setInternalName("blockcoal").setExternalName("Block of Coal").register();
		aluminumBlock = new BlockStorage(this.config.blockaluminumID, "supercraft:blockaluminum").setInternalName("blockaluminum").setExternalName("Block of Aluminum").register();
		copperBlock = new BlockStorage(this.config.blockcopperID, "supercraft:blockcopper").setInternalName("blockcopper").setExternalName("Block of Copper").register();
		silverBlock = new BlockStorage(this.config.blocksilverID, "supercraft:blocksilver").setInternalName("blocksilver").setExternalName("Block of Silver").register();
		electrumBlock = new BlockStorage(this.config.blockelectrumID, "supercraft:blockelectrum").setInternalName("blockelectrum").setExternalName("Block of Electrum").register();
		tantalumBlock = new BlockStorage(this.config.blocktantalumID, "supercraft:blocktantalum", StorageType.CRYSTAL).setInternalName("blocktantalum").setExternalName("Block of Tantalum").register();
		nisilBlock = new BlockStorage(this.config.blocknisilID, "supercraft:blocknisil").setInternalName("blocknisil").setExternalName("Block of Nisil").register();
		platinumBlock = new BlockStorage(this.config.blockplatinumID, "supercraft:blockplatinum").setInternalName("blockplatinum").setExternalName("Block of Platinum").register();
		lithiumBlock = new BlockStorage(this.config.blocklithiumID, "supercraft:blocklithium").setInternalName("blocklithium").setExternalName("Block of Lithium").register();
		
		elinvarBlock = new BlockStorage(this.config.blockelinvarID, "supercraft:blockelinvar", StorageType.DUST).setInternalName("blockelinvar").setExternalName("Block of Elinvar").register();
		cobaltBlock = new BlockCobalt(this.config.blockcobaltID, true).register();
		cobaltBlockOff = new BlockCobalt(this.config.blockcobaltoffID, false).register();
		
		draconiumBlock = new BlockStorage(this.config.blockdraconiumID, "supercraft:blockdraconium", StorageType.CRYSTAL).setInternalName("blockdraconium").setExternalName("Block of Draconium").register();
		
		conduit = new BlockConduit(this.config.conduitID).register();
		
		redstoneGenerator = new BlockRedstoneGenerator(this.config.redstoneconversiongeneratorID).register();
		radiantSolarGenerator = new BlockRadiantSolarGenerator(this.config.radiantsolargeneratorID).register();
		waveringLunarGenerator = new BlockWaveringLunarGenerator(this.config.waveringlunargeneratorID).register();
		celestialBalanceGenerator = new BlockCelestialBalanceGenerator(this.config.celestialbalancegeneratorID).register();
		
		ironboundStone = new BlockBoundStone(this.config.ironboundstoneID, "supercraft:ironboundstone").setInternalName("ironboundstone").setExternalName("Ironbound Stone").register();
		copperboundStone = new BlockBoundStone(this.config.copperboundstoneID, "supercraft:copperboundstone").setInternalName("copperboundstone").setExternalName("Copperbound Stone").register();
		silverboundStone = new BlockBoundStone(this.config.silverboundstoneID, "supercraft:silverboundstone").setInternalName("silverboundstone").setExternalName("Silverbound Stone").register();
		goldboundStone = new BlockBoundStone(this.config.goldboundstoneID, "supercraft:goldboundstone").setInternalName("goldboundstone").setExternalName("Goldbound Stone").register();
		
		essentialReducer = new BlockEssentialReducer(this.config.essentialreducerID).register();
		alloyInductor = new BlockAlloyInductor(this.config.alloyinductorID).register();
		electroplationEngine = new BlockElectroplationEngine(this.config.electroplationengineID).register();
		
		gravenStone = new BlockGravenStone(this.config.gravenStoneID).register();
		
		monolithDemission = new BlockMonolithDemission(this.config.monolithdemissionID, false).register();
		monolithDemissionActivated = new BlockMonolithDemission(this.config.monolithdemissiononID, true).register();
		monolithInception = new BlockMonolithInception(this.config.monolithinceptionID, false).register();
		monolithInceptionActivated = new BlockMonolithInception(this.config.monolithinceptiononID, true).register();
		monolithAscension = new BlockMonolithAscension(this.config.monolithascensionID, false).register();
		monolithAscensionActivated = new BlockMonolithAscension(this.config.monolithascensiononID, true).register();
		monolithTermination = new BlockMonolithTermination(this.config.monolithterminationID, false).register();
		monolithTerminationActivated = new BlockMonolithTermination(this.config.monolithterminationonID, true).register();
		
		
		
		
		
		supercraftLog1 = new BlockSupercraftLog1(this.config.woodsupercraft1ID);
		supercraftLog2 = new BlockSupercraftLog2(this.config.woodsupercraft2ID);
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
		inisiaStairs = new BlockSupercraftStairs(this.config.stairsinisiaID, supercraftPlanks, 3).setUnlocalizedName("inisiaStairs");
		valensienStairs = new BlockSupercraftStairs(this.config.stairsvalensienID, supercraftPlanks, 4).setUnlocalizedName("valensienStairs");
		mortaliaStairs = new BlockSupercraftStairs(this.config.stairsmortaliaID, supercraftPlanks, 5).setUnlocalizedName("mortaliaStairs");

		refinedCraftingTable = new BlockRefinedCraftingTable(this.config.refinedcraftingtableID);

		bluebells = new BlockSupercraftFlower(this.config.bluebellsID, "supercraft:bluebells", EnumPlantType.Plains).setUnlocalizedName("bluebells");
		daisies = new BlockSupercraftFlower(this.config.daisiesID, "supercraft:daisies", EnumPlantType.Plains).setUnlocalizedName("daisies");
		snapdragon = new BlockSupercraftFlower(this.config.snapdragonID, "supercraft:snapdragon", EnumPlantType.Plains).setUnlocalizedName("snapdragon");

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

		cobaltTorch = new BlockCobaltTorch(this.config.torchcobaltID);
		
		ghostlyVaporFlowing = new BlockGhostlyVaporFlowing(this.config.ghostlyvaporflowingID);
		ghostlyVaporStill = new BlockGhostlyVaporStill(this.config.ghostlyvaporstillID);

		inisiaMushroom = new BlockSupercraftMushroom(this.config.mushroominisiaID, "supercraft:mushroominisia", EnumPlantType.Cave).setUnlocalizedName("inisiaMushroom");
		valensienMushroom = new BlockSupercraftMushroom(this.config.mushroomvalensienID, "supercraft:mushroomvalensien", EnumPlantType.Cave).setUnlocalizedName("valensienMushroom");
		mortaliaMushroom = new BlockSupercraftMushroom(this.config.mushroommortaliaID, "supercraft:mushroommortalia", EnumPlantType.Cave).setUnlocalizedName("mortaliaMushroom");

		radiantLeaves = new BlockLeavesRadiant(this.config.radiantleavesID);
	}
	
	private void registerBlocks() {
		GameRegistry.registerBlock(supercraftLog1, ItemSupercraftLog1.class, "supercraftLog1");
		GameRegistry.registerBlock(supercraftLog2, ItemSupercraftLog2.class, "supercraftLog2");
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
		GameRegistry.registerBlock(inisiaStairs, "inisiaStairs");
		GameRegistry.registerBlock(valensienStairs, "valensienStairs");
		GameRegistry.registerBlock(mortaliaStairs, "mortaliaStairs");

		GameRegistry.registerBlock(refinedCraftingTable, ItemRefinedCraftingTable.class, "refinedCraftingTable");

		GameRegistry.registerBlock(bluebells, "bluebells");
		GameRegistry.registerBlock(daisies, "daisies");
		GameRegistry.registerBlock(snapdragon, "snapdragon");

		GameRegistry.registerBlock(palestone, "palestone");
		GameRegistry.registerBlock(palestoneBricks, "palestoneBricks");
		GameRegistry.registerBlock(palestoneStairs, "palestoneStairs");
		GameRegistry.registerBlock(inscribedPalestone, "inscribedPalestone");
		GameRegistry.registerBlock(blockOfGoldenwood, "blockOfGoldenwood");
		GameRegistry.registerBlock(overgrownPalestone, "overgrownPalestone");
		GameRegistry.registerBlock(burnedPalestone, "burnedPalestone");
		
		GameRegistry.registerBlock(nightrock, "nightrock");
		GameRegistry.registerBlock(nightrockBricks, "nightrockBricks");
		GameRegistry.registerBlock(nightrockStairs, "nightrockStairs");
		GameRegistry.registerBlock(inscribedNightrock, "inscribedNightrock");
		GameRegistry.registerBlock(blockOfTenebral, "blockOfTenebral");
		GameRegistry.registerBlock(burnedNightrock, "burnedNightrock");

		GameRegistry.registerBlock(cobaltTorch, "cobaltTorch");

		GameRegistry.registerBlock(ghostlyVaporFlowing, "ghostlyVaporFlowing");
		GameRegistry.registerBlock(ghostlyVaporStill, "ghostlyVaporStill");
		
		GameRegistry.registerBlock(inisiaMushroom, "inisiaMushroom");
		GameRegistry.registerBlock(valensienMushroom, "valensienMushroom");
		GameRegistry.registerBlock(mortaliaMushroom, "mortaliaMushroom");
		
		GameRegistry.registerBlock(radiantLeaves, "radiantLeaves");
	}
	
	private void initializeLanguage() {
		int i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftLog1))  {
			ItemStack stack = new ItemStack(supercraftLog1, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftLog2))  {
			ItemStack stack = new ItemStack(supercraftLog2, 1, i);
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
		LanguageRegistry.addName(inisiaStairs, (String)this.languageHandler.getString(inisiaStairs));
		LanguageRegistry.addName(valensienStairs, (String)this.languageHandler.getString(valensienStairs));
		LanguageRegistry.addName(mortaliaStairs, (String)this.languageHandler.getString(mortaliaStairs));

		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(refinedCraftingTable))  {
			ItemStack stack = new ItemStack(refinedCraftingTable, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		

		LanguageRegistry.addName(bluebells, (String)this.languageHandler.getString(bluebells));
		LanguageRegistry.addName(daisies, (String)this.languageHandler.getString(daisies));
		LanguageRegistry.addName(snapdragon, (String)this.languageHandler.getString(snapdragon));

		LanguageRegistry.addName(palestone, (String)this.languageHandler.getString(palestone));
		LanguageRegistry.addName(palestoneBricks, (String)this.languageHandler.getString(palestoneBricks));
		LanguageRegistry.addName(palestoneStairs, (String)this.languageHandler.getString(palestoneStairs));
		LanguageRegistry.addName(inscribedPalestone, (String)this.languageHandler.getString(inscribedPalestone));
		LanguageRegistry.addName(blockOfGoldenwood, (String)this.languageHandler.getString(blockOfGoldenwood));
		LanguageRegistry.addName(overgrownPalestone, (String)this.languageHandler.getString(overgrownPalestone));
		LanguageRegistry.addName(burnedPalestone, (String)this.languageHandler.getString(burnedPalestone));

		LanguageRegistry.addName(nightrock, (String)this.languageHandler.getString(nightrock));
		LanguageRegistry.addName(nightrockBricks, (String)this.languageHandler.getString(nightrockBricks));
		LanguageRegistry.addName(nightrockStairs, (String)this.languageHandler.getString(nightrockStairs));
		LanguageRegistry.addName(inscribedNightrock, (String)this.languageHandler.getString(inscribedNightrock));
		LanguageRegistry.addName(blockOfTenebral, (String)this.languageHandler.getString(blockOfTenebral));
		LanguageRegistry.addName(burnedNightrock, (String)this.languageHandler.getString(burnedNightrock));

		LanguageRegistry.addName(cobaltTorch, (String)this.languageHandler.getString(cobaltTorch));
		
		LanguageRegistry.addName(ghostlyVaporFlowing, (String)this.languageHandler.getString(ghostlyVaporFlowing));
		LanguageRegistry.addName(ghostlyVaporStill, (String)this.languageHandler.getString(ghostlyVaporStill));

		LanguageRegistry.addName(inisiaMushroom, (String)this.languageHandler.getString(inisiaMushroom));
		LanguageRegistry.addName(valensienMushroom, (String)this.languageHandler.getString(valensienMushroom));
		LanguageRegistry.addName(mortaliaMushroom, (String)this.languageHandler.getString(mortaliaMushroom));
		
		LanguageRegistry.addName(radiantLeaves, (String)this.languageHandler.getString(radiantLeaves));
	}

}
