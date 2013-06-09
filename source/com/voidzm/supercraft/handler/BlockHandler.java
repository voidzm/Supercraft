//**
//**  BlockHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.block.Block;
import net.minecraftforge.common.EnumPlantType;

import com.voidzm.supercraft.block.BlockAlloyInductor;
import com.voidzm.supercraft.block.BlockBoundStone;
import com.voidzm.supercraft.block.BlockCelestialBalanceGenerator;
import com.voidzm.supercraft.block.BlockCobalt;
import com.voidzm.supercraft.block.BlockCobaltTorch;
import com.voidzm.supercraft.block.BlockConduit;
import com.voidzm.supercraft.block.BlockDeadSpawner;
import com.voidzm.supercraft.block.BlockElectroplationEngine;
import com.voidzm.supercraft.block.BlockEndStoneOre;
import com.voidzm.supercraft.block.BlockEssentialReducer;
import com.voidzm.supercraft.block.BlockGhostlyVaporFlowing;
import com.voidzm.supercraft.block.BlockGhostlyVaporStill;
import com.voidzm.supercraft.block.BlockGravenStone;
import com.voidzm.supercraft.block.BlockImpactGlass;
import com.voidzm.supercraft.block.BlockLeavesRadiant;
import com.voidzm.supercraft.block.BlockMonolithAscension;
import com.voidzm.supercraft.block.BlockMonolithDemission;
import com.voidzm.supercraft.block.BlockMonolithInception;
import com.voidzm.supercraft.block.BlockMonolithTermination;
import com.voidzm.supercraft.block.BlockNetherrackOre;
import com.voidzm.supercraft.block.BlockOrnateGlass;
import com.voidzm.supercraft.block.BlockRadiantSolarGenerator;
import com.voidzm.supercraft.block.BlockRedstoneGenerator;
import com.voidzm.supercraft.block.BlockRefinedCraftingTable;
import com.voidzm.supercraft.block.BlockReinforcedGlass;
import com.voidzm.supercraft.block.BlockStoneOre;
import com.voidzm.supercraft.block.BlockStorage;
import com.voidzm.supercraft.block.BlockStorage.StorageType;
import com.voidzm.supercraft.block.BlockSupercraft;
import com.voidzm.supercraft.block.BlockSupercraftFlower;
import com.voidzm.supercraft.block.BlockSupercraftLeaves1;
import com.voidzm.supercraft.block.BlockSupercraftLeaves2;
import com.voidzm.supercraft.block.BlockSupercraftLog1;
import com.voidzm.supercraft.block.BlockSupercraftLog2;
import com.voidzm.supercraft.block.BlockSupercraftMushroom;
import com.voidzm.supercraft.block.BlockSupercraftPlanks1;
import com.voidzm.supercraft.block.BlockSupercraftSapling1;
import com.voidzm.supercraft.block.BlockSupercraftSapling2;
import com.voidzm.supercraft.block.BlockSupercraftSlab1;
import com.voidzm.supercraft.block.BlockSupercraftSlab2;
import com.voidzm.supercraft.block.BlockSupercraftStairs;
import com.voidzm.supercraft.block.BlockSupercraftStone;
import com.voidzm.supercraft.block.BlockSupercraftStone.SupercraftStoneType;
import com.voidzm.supercraft.block.BlockTemperedGlass;
import com.voidzm.supercraft.block.BlockVeneficianPodium;
import com.voidzm.supercraft.block.BlockWaveringLunarGenerator;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;

public class BlockHandler {
	
	private static SupercraftConfiguration config;
	
	//**  Glass  **//
	
	public static Block reinforcedGlass;
	public static Block temperedGlass;
	public static Block ornateGlass;
	public static Block impactGlass;
	
	//**  Ores  **//
	
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
	
	//**  Storage Blocks  **//
	
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

	//**  Decor  **//
	
	public static Block refinedCraftingTable;
	public static Block cobaltTorch;
	
	//**  Elinvar  **//
	
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

	//**  Monoliths  **//
	
	public static Block gravenStone;
	
	public static Block monolithDemission;
	public static Block monolithDemissionActivated;
	public static Block monolithInception;
	public static Block monolithInceptionActivated;
	public static Block monolithAscension;
	public static Block monolithAscensionActivated;
	public static Block monolithTermination;
	public static Block monolithTerminationActivated;
	
	//**  Trees  **//
	
	public static Block supercraftLog1;
	public static Block supercraftLog2;
	public static Block supercraftLeaves1;
	public static Block supercraftLeaves2;
	public static Block radiantLeaves;
	public static Block supercraftSapling1;
	public static Block supercraftSapling2;
	public static Block supercraftPlanks1;
	
	//**  Building Blocks  **//
	
	public static Block palestone;
	public static Block palestoneBricks;
	public static Block overgrownPalestoneBricks;
	public static Block burnedPalestoneBricks;
	public static Block inscribedPalestone;
	public static Block blockOfGoldenwood;
	
	public static Block nightrock;
	public static Block nightrockBricks;
	public static Block burnedNightrockBricks;
	public static Block inscribedNightrock;
	public static Block blockOfTenebral;
	
	public static Block sandstoneBricks;
	public static Block inscribedSandstone;
	public static Block blockOfDesert;
	
	public static Block endstoneBricks;
	public static Block inscribedEndstone;
	public static Block blockOfSky;
	
	public static Block deadSpawner;
	
	//**  Slabs  **//
	
	public static Block supercraftSlab1;
	public static Block supercraftSlab2;
	
	//**  Stairs  **//
	
	public static Block oliveStairs;
	public static Block goldenwoodStairs;
	public static Block tenebriaStairs;
	public static Block inisiaStairs;
	public static Block valensienStairs;
	public static Block mortaliaStairs;
	public static Block palestoneStairs;
	public static Block nightrockStairs;
	public static Block sandstonebrickStairs;
	public static Block endstonebrickStairs;
	
	//**  Plants  **//
	
	public static Block bluebells;
	public static Block daisies;
	public static Block snapdragon;
	public static Block inisiaMushroom;
	public static Block valensienMushroom;
	public static Block mortaliaMushroom;

	//**  Liquids  **//
	
	public static Block ghostlyVaporFlowing;
	public static Block ghostlyVaporStill;
	
	//**  Veneficia  **//

	public static Block veneficianPodium;
	
	public static void init(SupercraftConfiguration configObject) {
		if(config != null) {
			throw new RuntimeException("Block handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for block handler initialization!");
		}
		config = configObject;
		createBlocks();
		StartupStats.outputBlockStats();
	}
	
	private static void createBlocks() {
		createGlass();
		createOres();
		createStorageBlocks();
		createDecor();
		createElinvar();
		createMonoliths();
		createTrees();
		createBuildingBlocks();
		createSlabs();
		createStairs();
		createPlants();
		createLiquids();
		createVeneficia();
	}
	
	private static void createGlass() {
		reinforcedGlass = new BlockReinforcedGlass(config.reinforcedglassID).register();
		temperedGlass = new BlockTemperedGlass(config.temperedglassID).register();
		ornateGlass = new BlockOrnateGlass(config.ornateglassID).register();
		impactGlass = new BlockImpactGlass(config.impactglassID).register();
	}
	
	private static void createOres() {
		aluminumOre = new BlockStoneOre(config.orealuminumID, "supercraft:orealuminum").setInternalName("orealuminum").setExternalName("Aluminum Ore").register();
		copperOre = new BlockStoneOre(config.orecopperID, "supercraft:orecopper").setInternalName("orecopper").setExternalName("Copper Ore").register();
		silverOre = new BlockStoneOre(config.oresilverID, "supercraft:oresilver").setInternalName("oresilver").setExternalName("Silver Ore").register();
		electrumOre = new BlockStoneOre(config.oreelectrumID, "supercraft:oreelectrum").setInternalName("oreelectrum").setExternalName("Electrum Ore").register();
		tantalumOre = new BlockStoneOre(config.oretantalumID, "supercraft:oretantalum").setInternalName("oretantalum").setExternalName("Tantalum Ore").register();
		nisilOre = new BlockStoneOre(config.orenisilID, "supercraft:orenisil").setInternalName("orenisil").setExternalName("Nisil Ore").register();
		platinumOre = new BlockStoneOre(config.oreplatinumID, "supercraft:oreplatinum").setInternalName("oreplatinum").setExternalName("Platinum Ore").register();
		lithiumOre = new BlockStoneOre(config.orelithiumID, "supercraft:orelithium").setInternalName("orelithium").setExternalName("Lithium Ore").register();

		elinvarOre = new BlockNetherrackOre(config.oreelinvarID, "supercraft:oreelinvar", config.elinvardustID).setInternalName("oreelinvar").setExternalName("Elinvar Ore").register();
		cobaltOre = new BlockNetherrackOre(config.orecobaltID, "supercraft:orecobalt", config.cobaltdustID).setExtraDrop(3).setInternalName("orecobalt").setExternalName("Cobalt Ore").register();
		draconiumOre = new BlockEndStoneOre(config.oredraconiumID, "supercraft:oredraconium", config.draconiumID).setInternalName("oredraconium").setExternalName("Draconium Ore").register();
		
		incendiumOre = new BlockStoneOre(config.oreincendiumID, "supercraft:oreincendium", config.incendiumdustID).setExtraDrop(2).setInternalName("oreincendium").setExternalName("Incendium Ore").register();
		luxificenOre = new BlockStoneOre(config.oreluxificenID, "supercraft:oreluxificen", config.luxificendustID).setExtraDrop(3).setInternalName("oreluxificen").setExternalName("Luxificen Ore").register();
		jadeOre = new BlockStoneOre(config.orejadeID, "supercraft:orejade").setInternalName("orejade").setExternalName("Jade Ore").register();
		voltasniaOre = new BlockStoneOre(config.orevoltasniaID, "supercraft:orevoltasnia", config.voltasniarodID).setInternalName("orevoltasnia").setExternalName("Voltasnia Ore").register();
	}
	
	private static void createStorageBlocks() {
		coalBlock = new BlockStorage(config.blockcoalID, "supercraft:blockcoal", StorageType.DUST).setInternalName("blockcoal").setExternalName("Block of Coal").register();
		aluminumBlock = new BlockStorage(config.blockaluminumID, "supercraft:blockaluminum").setInternalName("blockaluminum").setExternalName("Block of Aluminum").register();
		copperBlock = new BlockStorage(config.blockcopperID, "supercraft:blockcopper").setInternalName("blockcopper").setExternalName("Block of Copper").register();
		silverBlock = new BlockStorage(config.blocksilverID, "supercraft:blocksilver").setInternalName("blocksilver").setExternalName("Block of Silver").register();
		electrumBlock = new BlockStorage(config.blockelectrumID, "supercraft:blockelectrum").setInternalName("blockelectrum").setExternalName("Block of Electrum").register();
		tantalumBlock = new BlockStorage(config.blocktantalumID, "supercraft:blocktantalum", StorageType.CRYSTAL).setInternalName("blocktantalum").setExternalName("Block of Tantalum").register();
		nisilBlock = new BlockStorage(config.blocknisilID, "supercraft:blocknisil").setInternalName("blocknisil").setExternalName("Block of Nisil").register();
		platinumBlock = new BlockStorage(config.blockplatinumID, "supercraft:blockplatinum").setInternalName("blockplatinum").setExternalName("Block of Platinum").register();
		lithiumBlock = new BlockStorage(config.blocklithiumID, "supercraft:blocklithium").setInternalName("blocklithium").setExternalName("Block of Lithium").register();
		
		elinvarBlock = new BlockStorage(config.blockelinvarID, "supercraft:blockelinvar", StorageType.DUST).setInternalName("blockelinvar").setExternalName("Block of Elinvar").register();
		cobaltBlock = new BlockCobalt(config.blockcobaltID, true).register();
		cobaltBlockOff = new BlockCobalt(config.blockcobaltoffID, false).register();
		
		draconiumBlock = new BlockStorage(config.blockdraconiumID, "supercraft:blockdraconium", StorageType.CRYSTAL).setInternalName("blockdraconium").setExternalName("Block of Draconium").register();
	}
	
	
	private static void createDecor() {
		refinedCraftingTable = new BlockRefinedCraftingTable(config.refinedcraftingtableID).register();
		cobaltTorch = new BlockCobaltTorch(config.torchcobaltID);
		BlockSupercraft.register(cobaltTorch, ((BlockCobaltTorch)cobaltTorch).getRegisterData());
	}
	
	private static void createElinvar() {
		conduit = new BlockConduit(config.conduitID).register();
		
		redstoneGenerator = new BlockRedstoneGenerator(config.redstoneconversiongeneratorID).register();
		radiantSolarGenerator = new BlockRadiantSolarGenerator(config.radiantsolargeneratorID).register();
		waveringLunarGenerator = new BlockWaveringLunarGenerator(config.waveringlunargeneratorID).register();
		celestialBalanceGenerator = new BlockCelestialBalanceGenerator(config.celestialbalancegeneratorID).register();
		
		ironboundStone = new BlockBoundStone(config.ironboundstoneID, "supercraft:ironboundstone").setInternalName("ironboundstone").setExternalName("Ironbound Stone").register();
		copperboundStone = new BlockBoundStone(config.copperboundstoneID, "supercraft:copperboundstone").setInternalName("copperboundstone").setExternalName("Copperbound Stone").register();
		silverboundStone = new BlockBoundStone(config.silverboundstoneID, "supercraft:silverboundstone").setInternalName("silverboundstone").setExternalName("Silverbound Stone").register();
		goldboundStone = new BlockBoundStone(config.goldboundstoneID, "supercraft:goldboundstone").setInternalName("goldboundstone").setExternalName("Goldbound Stone").register();
		
		essentialReducer = new BlockEssentialReducer(config.essentialreducerID).register();
		alloyInductor = new BlockAlloyInductor(config.alloyinductorID).register();
		electroplationEngine = new BlockElectroplationEngine(config.electroplationengineID).register();
	}
	
	private static void createMonoliths() {
		gravenStone = new BlockGravenStone(config.gravenStoneID).register();
		
		monolithDemission = new BlockMonolithDemission(config.monolithdemissionID, false).register();
		monolithDemissionActivated = new BlockMonolithDemission(config.monolithdemissiononID, true).register();
		monolithInception = new BlockMonolithInception(config.monolithinceptionID, false).register();
		monolithInceptionActivated = new BlockMonolithInception(config.monolithinceptiononID, true).register();
		monolithAscension = new BlockMonolithAscension(config.monolithascensionID, false).register();
		monolithAscensionActivated = new BlockMonolithAscension(config.monolithascensiononID, true).register();
		monolithTermination = new BlockMonolithTermination(config.monolithterminationID, false).register();
		monolithTerminationActivated = new BlockMonolithTermination(config.monolithterminationonID, true).register();
	}
	
	private static void createTrees() {
		supercraftLog1 = new BlockSupercraftLog1(config.woodsupercraft1ID).register();
		supercraftLog2 = new BlockSupercraftLog2(config.woodsupercraft2ID).register();
		supercraftLeaves1 = new BlockSupercraftLeaves1(config.leavessupercraft1ID).register();
		supercraftLeaves2 = new BlockSupercraftLeaves2(config.leavessupercraft2ID).register();
		radiantLeaves = new BlockLeavesRadiant(config.radiantleavesID).register();
		supercraftSapling1 = new BlockSupercraftSapling1(config.saplingsupercraft1ID).register();
		supercraftSapling2 = new BlockSupercraftSapling2(config.saplingsupercraft2ID).register();
		supercraftPlanks1 = new BlockSupercraftPlanks1(config.plankssupercraft1ID).register();
	}
	
	private static void createBuildingBlocks() {
		palestone = new BlockSupercraftStone(config.palestoneID, SupercraftStoneType.STONE, "supercraft:palestone").setInternalName("palestone").setExternalName("Palestone").register();
		palestoneBricks = new BlockSupercraftStone(config.palestonebricksID, SupercraftStoneType.LARGEBRICKS, "supercraft:palestonebricks", true).setInternalName("palestonebricks").setExternalName("Palestone Bricks").register();
		overgrownPalestoneBricks = new BlockSupercraftStone(config.overgrownpalestonebricksID, SupercraftStoneType.LARGEBRICKS, "supercraft:overgrownpalestonebricks").setInternalName("overgrownpalestonebricks").setExternalName("Overgrown Palestone Bricks").register();
		burnedPalestoneBricks = new BlockSupercraftStone(config.burnedpalestonebricksID, SupercraftStoneType.WEAKBRICKS, "supercraft:burnedpalestonebricks").setInternalName("burnedpalestonebricks").setExternalName("Burned Palestone Bricks").register();
		inscribedPalestone = new BlockSupercraftStone(config.inscribedpalestoneID, SupercraftStoneType.STRONGSTONE, "supercraft:inscribedpalestone").setInternalName("inscribedpalestone").setExternalName("Inscribed Palestone").register();
		blockOfGoldenwood = new BlockSupercraftStone(config.blockgoldenwoodID, SupercraftStoneType.ESSENTIAL, "supercraft:blockgoldenwood").setInternalName("blockgoldenwood").setExternalName("Block of the Goldenwood").register();
		
		nightrock = new BlockSupercraftStone(config.nightrockID, SupercraftStoneType.STONE, "supercraft:nightrock").setInternalName("nightrock").setExternalName("Nightrock").register();
		nightrockBricks = new BlockSupercraftStone(config.nightrockbricksID, SupercraftStoneType.LARGEBRICKS, "supercraft:nightrockbricks").setInternalName("nightrockbricks").setExternalName("Nightrock Bricks").register();
		burnedNightrockBricks = new BlockSupercraftStone(config.burnednightrockbricksID, SupercraftStoneType.WEAKBRICKS, "supercraft:burnednightrockbricks").setInternalName("burnednightrockbricks").setExternalName("Burned Nightrock Bricks").register();
		inscribedNightrock = new BlockSupercraftStone(config.inscribednightrockID, SupercraftStoneType.STRONGSTONE, "supercraft:inscribednightrock").setInternalName("inscribednightrock").setExternalName("Inscribed Nightrock").register();
		blockOfTenebral = new BlockSupercraftStone(config.blocktenebralID, SupercraftStoneType.ESSENTIAL, "supercraft:blocktenebral").setInternalName("blocktenebral").setExternalName("Block of the Tenebral").register();
		
		sandstoneBricks = new BlockSupercraftStone(config.sandstonebricksID, SupercraftStoneType.WEAKBRICKS, "supercraft:sandstonebricks").setInternalName("sandstonebricks").setExternalName("Sandstone Bricks").register();
		inscribedSandstone = new BlockSupercraftStone(config.inscribedsandstoneID, SupercraftStoneType.STRONGSTONE, "supercraft:inscribedsandstone").setInternalName("inscribedsandstone").setExternalName("Inscribed Sandstone").register();
		blockOfDesert = new BlockSupercraftStone(config.blockdesertID, SupercraftStoneType.ESSENTIAL, "supercraft:blockdesert").setInternalName("blockdesert").setExternalName("Block of the Desert").register();
		
		endstoneBricks = new BlockSupercraftStone(config.endstonebricksID, SupercraftStoneType.STRONGSTONE, "supercraft:endstonebricks").setInternalName("endstonebricks").setExternalName("End Stone Bricks").makeDragonUnbreakable().register();
		inscribedEndstone = new BlockSupercraftStone(config.inscribedendstoneID, SupercraftStoneType.STRONGSTONE, "supercraft:inscribedendstone").setInternalName("inscribedendstone").setExternalName("Inscribed End Stone").makeDragonUnbreakable().register();
		blockOfSky = new BlockSupercraftStone(config.blockskyID, SupercraftStoneType.ESSENTIAL, "supercraft:blocksky").setInternalName("blocksky").setExternalName("Block of the Sky").makeDragonUnbreakable().register();
	
		deadSpawner = new BlockDeadSpawner(config.deadspawnerID).register();
	}
	
	private static void createSlabs() {
		supercraftSlab1 = new BlockSupercraftSlab1(config.slabsupercraft1ID);
		BlockSupercraft.register(supercraftSlab1, ((BlockSupercraftSlab1)supercraftSlab1).getRegisterData());
		supercraftSlab2 = new BlockSupercraftSlab2(config.slabsupercraft2ID);
		BlockSupercraft.register(supercraftSlab2, ((BlockSupercraftSlab2)supercraftSlab2).getRegisterData());
	}
	
	private static void createStairs() {
		oliveStairs = new BlockSupercraftStairs(config.stairsoliveID, supercraftPlanks1, 0, "stairsolive", "Olive Wood Stairs");
		BlockSupercraft.register(oliveStairs, ((BlockSupercraftStairs)oliveStairs).getRegisterData());
		goldenwoodStairs = new BlockSupercraftStairs(config.stairsgoldenwoodID, supercraftPlanks1, 1, "stairsgoldenwood", "Goldenwood Wood Stairs");
		BlockSupercraft.register(goldenwoodStairs, ((BlockSupercraftStairs)goldenwoodStairs).getRegisterData());
		tenebriaStairs = new BlockSupercraftStairs(config.stairstenebriaID, supercraftPlanks1, 2, "stairstenebria", "Tenebria Wood Stairs");
		BlockSupercraft.register(tenebriaStairs, ((BlockSupercraftStairs)tenebriaStairs).getRegisterData());
		inisiaStairs = new BlockSupercraftStairs(config.stairsinisiaID, supercraftPlanks1, 3, "stairsinisia", "Inisia Wood Stairs");
		BlockSupercraft.register(inisiaStairs, ((BlockSupercraftStairs)inisiaStairs).getRegisterData());
		valensienStairs = new BlockSupercraftStairs(config.stairsvalensienID, supercraftPlanks1, 4, "stairsvalensien", "Valensien Wood Stairs");
		BlockSupercraft.register(valensienStairs, ((BlockSupercraftStairs)valensienStairs).getRegisterData());
		mortaliaStairs = new BlockSupercraftStairs(config.stairsmortaliaID, supercraftPlanks1, 5, "stairsmortalia", "Mortalia Wood Stairs");
		BlockSupercraft.register(mortaliaStairs, ((BlockSupercraftStairs)mortaliaStairs).getRegisterData());
		palestoneStairs = new BlockSupercraftStairs(config.stairspalestoneID, palestoneBricks, 0, "stairspalestone", "Palestone Brick Stairs");
		BlockSupercraft.register(palestoneStairs, ((BlockSupercraftStairs)palestoneStairs).getRegisterData());
		nightrockStairs = new BlockSupercraftStairs(config.stairsnightrockID, nightrockBricks, 0, "stairsnightrock", "Nightrock Brick Stairs");
		BlockSupercraft.register(nightrockStairs, ((BlockSupercraftStairs)nightrockStairs).getRegisterData());
		sandstonebrickStairs = new BlockSupercraftStairs(config.stairssandstonebricksID, sandstoneBricks, 0, "stairssandstonebricks", "Sandstone Brick Stairs");
		BlockSupercraft.register(sandstonebrickStairs, ((BlockSupercraftStairs)sandstonebrickStairs).getRegisterData());
		endstonebrickStairs = new BlockSupercraftStairs(config.stairsendstonebrickID, endstoneBricks, 0, "stairsendstonebricks", "End Stone Brick Stairs");
		BlockSupercraft.register(endstonebrickStairs, ((BlockSupercraftStairs)endstonebrickStairs).getRegisterData());
	}
	
	private static void createPlants() {
		bluebells = new BlockSupercraftFlower(config.bluebellsID, "supercraft:bluebells", EnumPlantType.Plains).setInternalName("bluebells").setExternalName("Bluebells").register();
		daisies = new BlockSupercraftFlower(config.daisiesID, "supercraft:daisies", EnumPlantType.Plains).setInternalName("daisies").setExternalName("Daisies").register();
		snapdragon = new BlockSupercraftFlower(config.snapdragonID, "supercraft:snapdragon", EnumPlantType.Plains).setInternalName("snapdragon").setExternalName("Snapdragon").register();
		inisiaMushroom = new BlockSupercraftMushroom(config.mushroominisiaID, "supercraft:mushroominisia", EnumPlantType.Cave).setInternalName("mushroominisia").setExternalName("Inisia Mushroom").register();
		valensienMushroom = new BlockSupercraftMushroom(config.mushroomvalensienID, "supercraft:mushroomvalensien", EnumPlantType.Cave).setInternalName("mushroomvalensien").setExternalName("Valensien Mushroom").register();
		mortaliaMushroom = new BlockSupercraftMushroom(config.mushroommortaliaID, "supercraft:mushroommortalia", EnumPlantType.Cave).setInternalName("mushroommortalia").setExternalName("Mortalia Mushroom").register();
	}
	
	private static void createLiquids() {
		ghostlyVaporFlowing = new BlockGhostlyVaporFlowing(config.ghostlyvaporflowingID);
		BlockSupercraft.register(ghostlyVaporFlowing, ((BlockGhostlyVaporFlowing)ghostlyVaporFlowing).getRegisterData());
		ghostlyVaporStill = new BlockGhostlyVaporStill(config.ghostlyvaporstillID);
		BlockSupercraft.register(ghostlyVaporStill, ((BlockGhostlyVaporStill)ghostlyVaporStill).getRegisterData());
	}
	
	private static void createVeneficia() {
		veneficianPodium = new BlockVeneficianPodium(config.veneficianpodiumID).register();
	}

}
