package com.voidzm.supercraft.util;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;

public class SupercraftConfiguration {

	private Configuration internalCfg;
	
	///*** BLOCKS ***///
	
	public int reinforcedglassID;
	public int temperedglassID;
	public int ornateglassID;
	public int impactglassID;
	
	public int blockcoalID;
	
	public int woodsupercraft1ID;
	public int woodsupercraft2ID;
	public int leavessupercraft1ID;
	public int leavessupercraft2ID;
	public int saplingsupercraft1ID;
	public int saplingsupercraft2ID;
	public int plankssupercraft1ID;
	public int plankssupercraft2ID;
	public int slabsupercraft1ID;
	public int slabsupercraft2ID;
	public int stairsoliveID;
	public int stairsgoldenwoodID;
	public int stairstenebriaID;
	
	public int orealuminumID;
	public int blockaluminumID;
	public int oretantalumID;
	public int blocktantalumID;
	public int orecopperID;
	public int blockcopperID;
	public int oreelinvarID;
	public int blockelinvarID;
	public int oresilverID;
	public int blocksilverID;
	public int oreelectrumID;
	public int blockelectrumID;
	public int orenisilID;
	public int blocknisilID;
	public int oreplatinumID;
	public int blockplatinumID;
	public int orelithiumID;
	public int blocklithiumID;
	
	public int conduitID;
	
	public int refinedcraftingtableID;
	
	public int redstoneconversiongeneratorID;
	public int radiantsolargeneratorID;
	public int waveringlunargeneratorID;
	
	public int essentialreducerID;
	public int alloyinductorID;
	
	public int bluebellsID;
	public int daisiesID;
	public int snapdragonID;
	
	public int palestoneID;
	public int palestonebricksID;
	public int stairspalestoneID;
	public int inscribedpalestoneID;
	public int blockgoldenwoodID;
	public int overgrownpalestonebricksID;
	public int burnedpalestonebricksID;

	public int nightrockID;
	public int nightrockbricksID;
	public int stairsnightrockID;
	public int inscribednightrockID;
	public int blocktenebralID;
	public int burnednightrockbricksID;
	
	public int ironboundstoneID;
	public int copperboundstoneID;
	public int silverboundstoneID;
	
	public int orecobaltID;
	public int blockcobaltID;
	public int blockcobaltoffID;
	public int torchcobaltID;
	
	public SupercraftConfiguration(File file) {
		internalCfg = new Configuration(file);
		internalCfg.save();
		internalCfg.load();
		this.loadBlocksConfig();
		internalCfg.save();
		System.out.println("[Supercraft] Configuration loaded from disk.");
	}
	
	private void loadBlocksConfig() {
		reinforcedglassID = internalCfg.get(Configuration.CATEGORY_BLOCK, "reinforcedglassID", "1600").getInt();
		temperedglassID = internalCfg.get(Configuration.CATEGORY_BLOCK, "temperedglassID", "1601").getInt();
		ornateglassID = internalCfg.get(Configuration.CATEGORY_BLOCK, "ornateglassID", "1602").getInt();
		impactglassID = internalCfg.get(Configuration.CATEGORY_BLOCK, "impactglassID", "1603").getInt();
		
		blockcoalID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockcoalID", "1604").getInt();
		
		woodsupercraft1ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "woodsupercraft1ID", "1605").getInt();
		woodsupercraft2ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "woodsupercraft2ID", "1606").getInt();
		leavessupercraft1ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "leavessupercraft1ID", "1607").getInt();
		leavessupercraft2ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "leavessupercraft2ID", "1608").getInt();
		saplingsupercraft1ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "saplingsupercraft1ID", "1664").getInt();
		saplingsupercraft2ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "saplingsupercraft2ID", "1665").getInt();
		plankssupercraft1ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "plankssupercraft1ID", "1609").getInt();
		plankssupercraft2ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "plankssupercraft2ID", "1610").getInt();
		slabsupercraft1ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "slabsupercraft1ID", "1611").getInt();
		slabsupercraft2ID = internalCfg.get(Configuration.CATEGORY_BLOCK, "slabsupercraft2ID", "1612").getInt();
		stairsoliveID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairsoliveID", "1613").getInt();
		stairsgoldenwoodID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairsgoldenwoodID", "1614").getInt();
		stairstenebriaID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairstenebriaID", "1615").getInt();
		
		orealuminumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "orealuminumID", "1616").getInt();
		blockaluminumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockaluminumID", "1617").getInt();
		oretantalumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "oretantalumID", "1618").getInt();
		blocktantalumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blocktantalumID", "1619").getInt();
		orecopperID = internalCfg.get(Configuration.CATEGORY_BLOCK, "orecopperID", "1620").getInt();
		blockcopperID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockcopperID", "1621").getInt();
		oreelinvarID = internalCfg.get(Configuration.CATEGORY_BLOCK, "oreelinvarID", "1622").getInt();
		blockelinvarID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockelinvarID", "1623").getInt();
		oresilverID = internalCfg.get(Configuration.CATEGORY_BLOCK, "oresilverID", "1624").getInt();
		blocksilverID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blocksilverID", "1625").getInt();
		oreelectrumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "oreelectrumID", "1626").getInt();
		blockelectrumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockelectrumID", "1627").getInt();
		orenisilID = internalCfg.get(Configuration.CATEGORY_BLOCK, "orenisilID", "1628").getInt();
		blocknisilID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blocknisilID", "1629").getInt();
		oreplatinumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "oreplatinumID", "1630").getInt();
		blockplatinumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockplatinumID", "1631").getInt();
		orelithiumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "orelithiumID", "1632").getInt();
		blocklithiumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blocklithiumID", "1633").getInt();
		
		conduitID = internalCfg.get(Configuration.CATEGORY_BLOCK, "conduitID", "1634").getInt();
		
		refinedcraftingtableID = internalCfg.get(Configuration.CATEGORY_BLOCK, "refinedcraftingtableID", "1635").getInt();
		
		redstoneconversiongeneratorID = internalCfg.get(Configuration.CATEGORY_BLOCK, "redstoneconversiongeneratorID", "1636").getInt();
		radiantsolargeneratorID = internalCfg.get(Configuration.CATEGORY_BLOCK, "radiantsolargeneratorID", "1637").getInt();
		waveringlunargeneratorID = internalCfg.get(Configuration.CATEGORY_BLOCK, "waveringlunargeneratorID", "1638").getInt();
		
		essentialreducerID = internalCfg.get(Configuration.CATEGORY_BLOCK, "essentialreducerID", "1639").getInt();
		alloyinductorID = internalCfg.get(Configuration.CATEGORY_BLOCK, "alloyinductorID", "1640").getInt();
		
		bluebellsID = internalCfg.get(Configuration.CATEGORY_BLOCK, "bluebellsID", "1641").getInt();
		daisiesID = internalCfg.get(Configuration.CATEGORY_BLOCK, "daisiesID", "1642").getInt();
		snapdragonID = internalCfg.get(Configuration.CATEGORY_BLOCK, "snapdragonID", "1643").getInt();
		
		palestoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "palestoneID", "1644").getInt();
		palestonebricksID = internalCfg.get(Configuration.CATEGORY_BLOCK, "palestonebricksID", "1645").getInt();
		stairspalestoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairspalestoneID", "1646").getInt();
		inscribedpalestoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "inscribedpalestoneID", "1647").getInt();
		blockgoldenwoodID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockgoldenwoodID", "1648").getInt();
		overgrownpalestonebricksID = internalCfg.get(Configuration.CATEGORY_BLOCK, "overgrownpalestonebricksID", "1649").getInt();
		burnedpalestonebricksID = internalCfg.get(Configuration.CATEGORY_BLOCK, "burnedpalestonebricksID", "1650").getInt();
		
		nightrockID = internalCfg.get(Configuration.CATEGORY_BLOCK, "nightrockID", "1651").getInt();
		nightrockbricksID = internalCfg.get(Configuration.CATEGORY_BLOCK, "nightrockbricksID", "1652").getInt();
		stairsnightrockID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairsnightrockID", "1653").getInt();
		inscribednightrockID = internalCfg.get(Configuration.CATEGORY_BLOCK, "inscribednightrockID", "1654").getInt();
		blocktenebralID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blocktenebralID", "1655").getInt();
		burnednightrockbricksID = internalCfg.get(Configuration.CATEGORY_BLOCK, "burnednightrockbricksID", "1656").getInt();
	
		ironboundstoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "ironboundstoneID", "1657").getInt();
		copperboundstoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "copperboundstoneID", "1658").getInt();
		silverboundstoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "silverboundstoneID", "1659").getInt();
		
		orecobaltID = internalCfg.get(Configuration.CATEGORY_BLOCK, "orecobaltID", "1660").getInt();
		blockcobaltID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockcobaltID", "1661").getInt();
		blockcobaltoffID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockcobaltoffID", "1662").getInt();
		torchcobaltID = internalCfg.get(Configuration.CATEGORY_BLOCK, "torchcobaltID", "1663").getInt();
	
		// NEXT ID: 1666
		
	}
	
}
