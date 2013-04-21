//**
//**  BlockHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.block.Block;
import net.minecraftforge.common.EnumPlantType;

import com.voidzm.supercraft.block.*;
import com.voidzm.supercraft.block.BlockStorage.StorageType;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;

public class BlockHandler {
	
	protected SupercraftConfiguration config;
	
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

	public void init(SupercraftConfiguration configObject) {
		if(this.config != null) {
			throw new RuntimeException("Block handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for block handler initialization!");
		}
		this.config = configObject;
		this.createBlocks();
		StartupStats.outputBlockStats();
	}
	
	private void createBlocks() {
		this.createGlass();
		this.createOres();
		this.createStorageBlocks();
		this.createDecor();
		this.createElinvar();
		this.createMonoliths();
		this.createTrees();
		this.createBuildingBlocks();
		this.createSlabs();
		this.createStairs();
		this.createPlants();
		this.createLiquids();
	}
	
	private void createGlass() {
		reinforcedGlass = new BlockReinforcedGlass(this.config.reinforcedglassID).register();
		temperedGlass = new BlockTemperedGlass(this.config.temperedglassID).register();
		ornateGlass = new BlockOrnateGlass(this.config.ornateglassID).register();
		impactGlass = new BlockImpactGlass(this.config.impactglassID).register();
	}
	
	private void createOres() {
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
	}
	
	private void createStorageBlocks() {
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
	}
	
	
	private void createDecor() {
		refinedCraftingTable = new BlockRefinedCraftingTable(this.config.refinedcraftingtableID).register();
		cobaltTorch = new BlockCobaltTorch(this.config.torchcobaltID);
		BlockSupercraft.register(cobaltTorch, ((BlockCobaltTorch)cobaltTorch).getRegisterData());
	}
	
	private void createElinvar() {
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
	}
	
	private void createMonoliths() {
		gravenStone = new BlockGravenStone(this.config.gravenStoneID).register();
		
		monolithDemission = new BlockMonolithDemission(this.config.monolithdemissionID, false).register();
		monolithDemissionActivated = new BlockMonolithDemission(this.config.monolithdemissiononID, true).register();
		monolithInception = new BlockMonolithInception(this.config.monolithinceptionID, false).register();
		monolithInceptionActivated = new BlockMonolithInception(this.config.monolithinceptiononID, true).register();
		monolithAscension = new BlockMonolithAscension(this.config.monolithascensionID, false).register();
		monolithAscensionActivated = new BlockMonolithAscension(this.config.monolithascensiononID, true).register();
		monolithTermination = new BlockMonolithTermination(this.config.monolithterminationID, false).register();
		monolithTerminationActivated = new BlockMonolithTermination(this.config.monolithterminationonID, true).register();
	}
	
	private void createTrees() {
		supercraftLog1 = new BlockSupercraftLog1(this.config.woodsupercraft1ID).register();
		supercraftLog2 = new BlockSupercraftLog2(this.config.woodsupercraft2ID).register();
		supercraftLeaves1 = new BlockSupercraftLeaves1(this.config.leavessupercraft1ID).register();
		supercraftLeaves2 = new BlockSupercraftLeaves2(this.config.leavessupercraft2ID).register();
		radiantLeaves = new BlockLeavesRadiant(this.config.radiantleavesID).register();
		supercraftSapling1 = new BlockSupercraftSapling1(this.config.saplingsupercraft1ID).register();
		supercraftSapling2 = new BlockSupercraftSapling2(this.config.saplingsupercraft2ID).register();
		supercraftPlanks1 = new BlockSupercraftPlanks1(this.config.plankssupercraft1ID).register();
	}
	
	private void createBuildingBlocks() {
		palestone = new BlockPalestone(this.config.palestoneID).register();
		palestoneBricks = new BlockPalestoneBricks(this.config.palestonebricksID).register();
		overgrownPalestoneBricks = new BlockOvergrownPalestoneBricks(this.config.overgrownpalestonebricksID).register();
		burnedPalestoneBricks = new BlockBurnedPalestoneBricks(this.config.burnedpalestonebricksID).register();
		inscribedPalestone = new BlockInscribedPalestone(this.config.inscribedpalestoneID).register();
		blockOfGoldenwood = new BlockGoldenwood(this.config.blockgoldenwoodID).register();
		
		nightrock = new BlockNightrock(this.config.nightrockID).register();
		nightrockBricks = new BlockNightrockBricks(this.config.nightrockbricksID).register();
		burnedNightrockBricks = new BlockBurnedNightrockBricks(this.config.burnednightrockbricksID).register();
		inscribedNightrock = new BlockInscribedNightrock(this.config.inscribednightrockID).register();
		blockOfTenebral = new BlockTenebral(this.config.blocktenebralID).register();
	}
	
	private void createSlabs() {
		supercraftSlab1 = new BlockSupercraftSlab1(this.config.slabsupercraft1ID);
		BlockSupercraft.register(supercraftSlab1, ((BlockSupercraftSlab1)supercraftSlab1).getRegisterData());
		supercraftSlab2 = new BlockSupercraftSlab2(this.config.slabsupercraft2ID);
		BlockSupercraft.register(supercraftSlab2, ((BlockSupercraftSlab2)supercraftSlab2).getRegisterData());
	}
	
	private void createStairs() {
		oliveStairs = new BlockSupercraftStairs(this.config.stairsoliveID, supercraftPlanks1, 0, "stairsolive", "Olive Wood Stairs");
		BlockSupercraft.register(oliveStairs, ((BlockSupercraftStairs)oliveStairs).getRegisterData());
		goldenwoodStairs = new BlockSupercraftStairs(this.config.stairsgoldenwoodID, supercraftPlanks1, 1, "stairsgoldenwood", "Goldenwood Wood Stairs");
		BlockSupercraft.register(goldenwoodStairs, ((BlockSupercraftStairs)goldenwoodStairs).getRegisterData());
		tenebriaStairs = new BlockSupercraftStairs(this.config.stairstenebriaID, supercraftPlanks1, 2, "stairstenebria", "Tenebria Wood Stairs");
		BlockSupercraft.register(tenebriaStairs, ((BlockSupercraftStairs)tenebriaStairs).getRegisterData());
		inisiaStairs = new BlockSupercraftStairs(this.config.stairsinisiaID, supercraftPlanks1, 3, "stairsinisia", "Inisia Wood Stairs");
		BlockSupercraft.register(inisiaStairs, ((BlockSupercraftStairs)inisiaStairs).getRegisterData());
		valensienStairs = new BlockSupercraftStairs(this.config.stairsvalensienID, supercraftPlanks1, 4, "stairsvalensien", "Valensien Wood Stairs");
		BlockSupercraft.register(valensienStairs, ((BlockSupercraftStairs)valensienStairs).getRegisterData());
		mortaliaStairs = new BlockSupercraftStairs(this.config.stairsmortaliaID, supercraftPlanks1, 5, "stairsmortalia", "Mortalia Wood Stairs");
		BlockSupercraft.register(mortaliaStairs, ((BlockSupercraftStairs)mortaliaStairs).getRegisterData());
		palestoneStairs = new BlockSupercraftStairs(this.config.stairspalestoneID, palestoneBricks, 0, "stairspalestone", "Palestone Brick Stairs");
		BlockSupercraft.register(palestoneStairs, ((BlockSupercraftStairs)palestoneStairs).getRegisterData());
		nightrockStairs = new BlockSupercraftStairs(this.config.stairsnightrockID, nightrockBricks, 0, "stairsnightrock", "Nightrock Brick Stairs");
		BlockSupercraft.register(nightrockStairs, ((BlockSupercraftStairs)nightrockStairs).getRegisterData());
	}
	
	private void createPlants() {
		bluebells = new BlockSupercraftFlower(this.config.bluebellsID, "supercraft:bluebells", EnumPlantType.Plains).setInternalName("bluebells").setExternalName("Bluebells").register();
		daisies = new BlockSupercraftFlower(this.config.daisiesID, "supercraft:daisies", EnumPlantType.Plains).setInternalName("daisies").setExternalName("Daisies").register();
		snapdragon = new BlockSupercraftFlower(this.config.snapdragonID, "supercraft:snapdragon", EnumPlantType.Plains).setInternalName("snapdragon").setExternalName("Snapdragon").register();
		inisiaMushroom = new BlockSupercraftMushroom(this.config.mushroominisiaID, "supercraft:mushroominisia", EnumPlantType.Cave).setInternalName("mushroominisia").setExternalName("Inisia Mushroom").register();
		valensienMushroom = new BlockSupercraftMushroom(this.config.mushroomvalensienID, "supercraft:mushroomvalensien", EnumPlantType.Cave).setInternalName("mushroomvalensien").setExternalName("Valensien Mushroom").register();
		mortaliaMushroom = new BlockSupercraftMushroom(this.config.mushroommortaliaID, "supercraft:mushroommortalia", EnumPlantType.Cave).setInternalName("mushroommortalia").setExternalName("Mortalia Mushroom").register();
	}
	
	private void createLiquids() {
		ghostlyVaporFlowing = new BlockGhostlyVaporFlowing(this.config.ghostlyvaporflowingID);
		BlockSupercraft.register(ghostlyVaporFlowing, ((BlockGhostlyVaporFlowing)ghostlyVaporFlowing).getRegisterData());
		ghostlyVaporStill = new BlockGhostlyVaporStill(this.config.ghostlyvaporstillID);
		BlockSupercraft.register(ghostlyVaporStill, ((BlockGhostlyVaporStill)ghostlyVaporStill).getRegisterData());
	}

}
