//////////////////////////////////////
//*        BlockHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all blocks.

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
	
	public static Block coalBlock;
	
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
	
	public static Block monolithDemission;
	public static Block monolithDemissionActivated;
	
	public static Block gravenStone;
	
	public static Block monolithInception;
	public static Block monolithInceptionActivated;
	
	public static Block incendiumOre;
	public static Block luxificenOre;
	public static Block jadeOre;
	public static Block voltasniaOre;
	
	public static Block inisiaMushroom;
	public static Block valensienMushroom;
	public static Block mortaliaMushroom;
	
	public static Block monolithAscension;
	public static Block monolithAscensionActivated;
	
	public static Block radiantLeaves;
	
	public static Block draconiumOre;
	public static Block draconiumBlock;

	public static Block monolithTermination;
	public static Block monolithTerminationActivated;
	
	public static Block celestialBalanceGenerator;
	
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
		
		// Advanced Glass
		
		languageHandler.add(reinforcedGlass, "Reinforced Glass");
		languageHandler.add(temperedGlass, "Tempered Glass");
		languageHandler.add(ornateGlass, "Ornate Glass");
		languageHandler.add(impactGlass, "Impact Glass");
		
		// Material Extensions
		
		languageHandler.add(coalBlock, "Block of Coal");
		
		// Supercraft Trees
		
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
		
		languageHandler.add(monolithDemission, "Monolith of Demission");
		languageHandler.add(monolithDemissionActivated, "Monolith of Demission");
		
		ArrayList<String> gravenStoneList = new ArrayList<String>();
		gravenStoneList.add("Graven Stone of Darkness");
		gravenStoneList.add("Graven Stone of Shadow");
		gravenStoneList.add("Graven Stone of Gleaming");
		gravenStoneList.add("Graven Stone of Brilliance");
		languageHandler.add(gravenStone, gravenStoneList);
		
		languageHandler.add(monolithInception, "Monolith of Inception");
		languageHandler.add(monolithInceptionActivated, "Monolith of Inception");
		
		languageHandler.add(incendiumOre, "Incendium Ore");
		languageHandler.add(luxificenOre, "Luxificen Ore");
		languageHandler.add(jadeOre, "Jade Ore");
		languageHandler.add(voltasniaOre, "Voltasnia Ore");
		
		languageHandler.add(inisiaMushroom, "Inisia Mushroom");
		languageHandler.add(valensienMushroom, "Valensien Mushroom");
		languageHandler.add(mortaliaMushroom, "Mortalia Mushroom");
		
		languageHandler.add(monolithAscension, "Monolith of Ascension");
		languageHandler.add(monolithAscensionActivated, "Monolith of Ascension");
		
		languageHandler.add(radiantLeaves, "Radiant Leaves");
		
		languageHandler.add(draconiumOre, "Draconium Ore");
		languageHandler.add(draconiumBlock, "Block of Draconium");
		
		languageHandler.add(monolithTermination, "Monolith of Termination");
		languageHandler.add(monolithTerminationActivated, "Monolith of Termination");
		
		languageHandler.add(celestialBalanceGenerator, "Celestial Balance Generator");
	}
	
	private void createBlocks() {
		reinforcedGlass = new BlockReinforcedGlass(this.config.reinforcedglassID);
		temperedGlass = new BlockTemperedGlass(this.config.temperedglassID);
		ornateGlass = new BlockOrnateGlass(this.config.ornateglassID);
		impactGlass = new BlockImpactGlass(this.config.impactglassID);
		
		coalBlock = new BlockStorage(this.config.blockcoalID, "coalBlock", "supercraft:blockcoal", StorageType.DUST);

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
		
		aluminumOre = new BlockStoneOre(this.config.orealuminumID, "aluminumOre", "supercraft:orealuminum");
		tantalumOre = new BlockStoneOre(this.config.oretantalumID, "tantalumOre", "supercraft:oretantalum");
		copperOre = new BlockStoneOre(this.config.orecopperID, "copperOre", "supercraft:orecopper");
		silverOre = new BlockStoneOre(this.config.oresilverID, "silverOre", "supercraft:oresilver");
		electrumOre = new BlockStoneOre(this.config.oreelectrumID, "electrumOre", "supercraft:oreelectrum");
		nisilOre = new BlockStoneOre(this.config.orenisilID, "nisilOre", "supercraft:orenisil");
		platinumOre = new BlockStoneOre(this.config.oreplatinumID, "platinumOre", "supercraft:oreplatinum");
		lithiumOre = new BlockStoneOre(this.config.orelithiumID, "lithiumOre", "supercraft:orelithium");
		elinvarOre = new BlockNetherrackOre(this.config.oreelinvarID, "elinvarOre", "supercraft:oreelinvar", this.config.elinvardustID);
		cobaltOre = new BlockNetherrackOre(this.config.orecobaltID, "cobaltOre", "supercraft:orecobalt", this.config.cobaltdustID).setExtraDrop(3);
		draconiumOre = new BlockEndStoneOre(this.config.oredraconiumID, "draconiumOre", "supercraft:oredraconium", this.config.draconiumID);
		
		aluminumBlock = new BlockStorage(this.config.blockaluminumID, "aluminumBlock", "supercraft:blockaluminum");
		tantalumBlock = new BlockStorage(this.config.blocktantalumID, "tantalumBlock", "supercraft:blocktantalum", StorageType.CRYSTAL);
		copperBlock = new BlockStorage(this.config.blockcopperID, "copperBlock", "supercraft:blockcopper");
		silverBlock = new BlockStorage(this.config.blocksilverID, "silverBlock", "supercraft:blocksilver");
		electrumBlock = new BlockStorage(this.config.blockelectrumID, "electrumBlock", "supercraft:blockelectrum");
		nisilBlock = new BlockStorage(this.config.blocknisilID, "nisilBlock", "supercraft:blocknisil");
		platinumBlock = new BlockStorage(this.config.blockplatinumID, "platinumBlock", "supercraft:blockplatinum");
		lithiumBlock = new BlockStorage(this.config.blocklithiumID, "lithiumBlock", "supercraft:blocklithium");
		elinvarBlock = new BlockStorage(this.config.blockelinvarID, "elinvarBlock", "supercraft:blockelinvar", StorageType.DUST);
		draconiumBlock = new BlockStorage(this.config.blockdraconiumID, "draconiumBlock", "supercraft:blockdraconium", StorageType.CRYSTAL);
		
		conduit = new BlockConduit(this.config.conduitID);
		
		refinedCraftingTable = new BlockRefinedCraftingTable(this.config.refinedcraftingtableID);

		redstoneGenerator = new BlockRedstoneGenerator(this.config.redstoneconversiongeneratorID);
		radiantSolarGenerator = new BlockRadiantSolarGenerator(this.config.radiantsolargeneratorID);
		waveringLunarGenerator = new BlockWaveringLunarGenerator(this.config.waveringlunargeneratorID);
		celestialBalanceGenerator = new BlockCelestialBalanceGenerator(this.config.celestialbalancegeneratorID);
		
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

		essentialReducer = new BlockEssentialReducer(this.config.essentialreducerID);
		alloyInductor = new BlockAlloyInductor(this.config.alloyinductorID);
		
		ironboundStone = new BlockIronboundStone(this.config.ironboundstoneID);
		copperboundStone = new BlockCopperboundStone(this.config.copperboundstoneID);
		silverboundStone = new BlockSilverboundStone(this.config.silverboundstoneID);

		cobaltTorch = new BlockCobaltTorch(this.config.torchcobaltID);
		cobaltBlock = new BlockCobalt(this.config.blockcobaltID, true);
		cobaltBlockOff = new BlockCobalt(this.config.blockcobaltoffID, false);
		
		ghostlyVaporFlowing = new BlockGhostlyVaporFlowing(this.config.ghostlyvaporflowingID);
		ghostlyVaporStill = new BlockGhostlyVaporStill(this.config.ghostlyvaporstillID);
		
		monolithDemission = new BlockMonolithDemission(this.config.monolithdemissionID, false);
		monolithDemissionActivated = new BlockMonolithDemission(this.config.monolithdemissiononID, true);
		
		gravenStone = new BlockGravenStone(this.config.gravenStoneID);
		
		monolithInception = new BlockMonolithInception(this.config.monolithinceptionID, false);
		monolithInceptionActivated = new BlockMonolithInception(this.config.monolithinceptiononID, true);
		
		incendiumOre = new BlockStoneOre(this.config.oreincendiumID, "incendiumOre", "supercraft:oreincendium", this.config.incendiumdustID).setExtraDrop(2);
		luxificenOre = new BlockStoneOre(this.config.oreluxificenID, "luxificenOre", "supercraft:oreluxificen", this.config.luxificendustID).setExtraDrop(3);
		jadeOre = new BlockStoneOre(this.config.orejadeID, "jadeOre", "supercraft:orejade");
		voltasniaOre = new BlockStoneOre(this.config.orevoltasniaID, "voltasniaOre", "supercraft:orevoltasnia", this.config.voltasniarodID);
	
		inisiaMushroom = new BlockSupercraftMushroom(this.config.mushroominisiaID, "supercraft:mushroominisia", EnumPlantType.Cave).setUnlocalizedName("inisiaMushroom");
		valensienMushroom = new BlockSupercraftMushroom(this.config.mushroomvalensienID, "supercraft:mushroomvalensien", EnumPlantType.Cave).setUnlocalizedName("valensienMushroom");
		mortaliaMushroom = new BlockSupercraftMushroom(this.config.mushroommortaliaID, "supercraft:mushroommortalia", EnumPlantType.Cave).setUnlocalizedName("mortaliaMushroom");
	
		monolithAscension = new BlockMonolithAscension(this.config.monolithascensionID, false);
		monolithAscensionActivated = new BlockMonolithAscension(this.config.monolithascensiononID, true);
	
		radiantLeaves = new BlockLeavesRadiant(this.config.radiantleavesID);
		
		monolithTermination = new BlockMonolithTermination(this.config.monolithterminationID, false);
		monolithTerminationActivated = new BlockMonolithTermination(this.config.monolithterminationonID, true);
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
		GameRegistry.registerBlock(celestialBalanceGenerator, "celestialBalanceGenerator");
		
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
		
		GameRegistry.registerBlock(monolithDemission, "monolithDemission");
		GameRegistry.registerBlock(monolithDemissionActivated, "monolithDemissionActivated");
		
		GameRegistry.registerBlock(gravenStone, ItemGravenStone.class, "gravenStone");
		
		GameRegistry.registerBlock(monolithInception, "monolithInception");
		GameRegistry.registerBlock(monolithInceptionActivated, "monolithInceptionActivated");
		
		GameRegistry.registerBlock(incendiumOre, "incendiumOre");
		GameRegistry.registerBlock(luxificenOre, "luxificenOre");
		GameRegistry.registerBlock(jadeOre, "jadeOre");
		GameRegistry.registerBlock(voltasniaOre, "voltasniaOre");
		
		GameRegistry.registerBlock(inisiaMushroom, "inisiaMushroom");
		GameRegistry.registerBlock(valensienMushroom, "valensienMushroom");
		GameRegistry.registerBlock(mortaliaMushroom, "mortaliaMushroom");
		
		GameRegistry.registerBlock(monolithAscension, "monolithAscension");
		GameRegistry.registerBlock(monolithAscensionActivated, "monolithAscensionActivated");
		
		GameRegistry.registerBlock(radiantLeaves, "radiantLeaves");
		
		GameRegistry.registerBlock(draconiumOre, "draconiumOre");
		GameRegistry.registerBlock(draconiumBlock, "draconiumBlock");
		
		GameRegistry.registerBlock(monolithTermination, "monolithTermination");
		GameRegistry.registerBlock(monolithTerminationActivated, "monolithTerminationActivated");
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
		LanguageRegistry.addName(celestialBalanceGenerator, (String)this.languageHandler.getString(celestialBalanceGenerator));
		
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
		
		LanguageRegistry.addName(monolithDemission, (String)this.languageHandler.getString(monolithDemission));
		LanguageRegistry.addName(monolithDemissionActivated, (String)this.languageHandler.getString(monolithDemissionActivated));
	
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(gravenStone))  {
			ItemStack stack = new ItemStack(gravenStone, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		
		LanguageRegistry.addName(monolithInception, (String)this.languageHandler.getString(monolithInception));
		LanguageRegistry.addName(monolithInceptionActivated, (String)this.languageHandler.getString(monolithInceptionActivated));
	
		LanguageRegistry.addName(incendiumOre, (String)this.languageHandler.getString(incendiumOre));
		LanguageRegistry.addName(luxificenOre, (String)this.languageHandler.getString(luxificenOre));
		LanguageRegistry.addName(jadeOre, (String)this.languageHandler.getString(jadeOre));
		LanguageRegistry.addName(voltasniaOre, (String)this.languageHandler.getString(voltasniaOre));
		
		LanguageRegistry.addName(inisiaMushroom, (String)this.languageHandler.getString(inisiaMushroom));
		LanguageRegistry.addName(valensienMushroom, (String)this.languageHandler.getString(valensienMushroom));
		LanguageRegistry.addName(mortaliaMushroom, (String)this.languageHandler.getString(mortaliaMushroom));
		
		LanguageRegistry.addName(monolithAscension, (String)this.languageHandler.getString(monolithAscension));
		LanguageRegistry.addName(monolithAscensionActivated, (String)this.languageHandler.getString(monolithAscensionActivated));
		
		LanguageRegistry.addName(radiantLeaves, (String)this.languageHandler.getString(radiantLeaves));
	
		LanguageRegistry.addName(draconiumOre, (String)this.languageHandler.getString(draconiumOre));
		LanguageRegistry.addName(draconiumBlock, (String)this.languageHandler.getString(draconiumBlock));
		
		LanguageRegistry.addName(monolithTermination, (String)this.languageHandler.getString(monolithTermination));
		LanguageRegistry.addName(monolithTerminationActivated, (String)this.languageHandler.getString(monolithTerminationActivated));
	}

}
