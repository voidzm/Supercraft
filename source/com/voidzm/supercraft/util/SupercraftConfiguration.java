package com.voidzm.supercraft.util;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class SupercraftConfiguration {

	public static final String CATEGORY_BIOME = "biome";
	public static final String CATEGORY_DIMENSION = "dimension";
	
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
	public int celestialbalancegeneratorID;
	
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
	
	public int ghostlyvaporflowingID;
	public int ghostlyvaporstillID;
	
	public int monolithdemissionID;
	public int monolithdemissiononID;
	
	public int gravenStoneID;
	
	public int monolithinceptionID;
	public int monolithinceptiononID;
	
	public int oreincendiumID;
	public int oreluxificenID;
	public int orejadeID;
	public int orevoltasniaID;
	
	public int mushroominisiaID;
	public int mushroomvalensienID;
	public int mushroommortaliaID;
	
	public int monolithascensionID;
	public int monolithascensiononID;
	
	public int stairsinisiaID;
	public int stairsvalensienID;
	public int stairsmortaliaID;
	
	public int radiantleavesID;
	
	public int oredraconiumID;
	public int blockdraconiumID;
	
	public int monolithterminationID;
	public int monolithterminationonID;
	
	public int electroplationengineID;
	
	public int goldboundstoneID;
	
	public int veneficianpodiumID;
	
	public int sandstonebricksID;
	public int inscribedsandstoneID;
	public int blockdesertID;
	
	public int endstonebricksID;
	public int inscribedendstoneID;
	public int blockskyID;
	
	public int stairssandstonebricksID;
	public int stairsendstonebrickID;
	
	public int deadspawnerID;
	
	///*** ITEMS ***///
	
	public int ironscrapID;
	public int diamondshardID;
	
	public int ingotaluminumID;
	public int tantalumcrystalID;
	public int ingotcopperID;
	public int copperchunkID;
	public int ingotsilverID;
	public int silverfragmentID;
	public int ingotelectrumID;
	public int electrumbitID;
	public int ingotnisilID;
	public int nisilshardID;
	public int ingotplatinumID;
	public int ingotlithiumID;
	public int ingotcobaltID;
	public int elinvardustID;
	public int cobaltdustID;
	
	public int bloodamberID;
	public int essenceID;
	
	public int pickaxealuminumID;
	public int swordaluminumID;
	public int shovelaluminumID;
	public int axealuminumID;
	public int hoealuminumID;
	
	public int pickaxecopperID;
	public int swordcopperID;
	public int shovelcopperID;
	public int axecopperID;
	public int hoecopperID;
	
	public int arcanebucketID;
	public int bucketghostlyvaporID;
	
	public int incendiumdustID;
	public int luxificendustID;
	public int jadecrystalID;
	public int voltasniarodID;
	
	public int draconiumID;
	
	public int arcanerodID;
	public int veneficianrodID;
	
	public int pickaxelithiumID;
	public int swordlithiumID;
	public int shovellithiumID;
	public int axelithiumID;
	public int hoelithiumID;
	
	public int veneficiacellID;
	
	///*** RECIPES ***///
	
	public boolean docoalblockrecipe;
	public boolean doironscraprecipe;
	public boolean dodiamondshardrecipe;
	
	///*** BIOMES ***///
	
	public int extremeforestID;
	public int insanehillsID;
	public int grassysummitsID;
	public int winterforestID;
	public int alphaID;
	public int savannaID;
	public int sandypeaksID;
	public int icyridgesID;
	public int goldenwoodforestID;
	public int tenebralwoodsID;
	public int depthsID;
	public int cordiferhillsID;
	public int temperateforestID;
	public int glacialwastelandID;
	public int junglemountainsID;
	public int coniferouswoodsID;
	
	///*** PROVIDERS ***///
	
	public boolean doalternatesurface;
	public int alternatesurfaceproviderID;
	public int thedeepproviderID;
	public int thedeepID;
	
	public SupercraftConfiguration(File file) {
		internalCfg = new Configuration(file);
		internalCfg.save();
		internalCfg.load();
		this.loadBlocksConfig();
		this.loadItemsConfig();
		this.loadGeneralConfig();
		this.loadBiomesConfig();
		this.loadDimensionConfig();
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
		celestialbalancegeneratorID = internalCfg.get(Configuration.CATEGORY_BLOCK, "celestialbalancegeneratorID", "1690").getInt();
		
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
	
		ghostlyvaporflowingID = internalCfg.get(Configuration.CATEGORY_BLOCK, "ghostlyvaporflowingID", "1666").getInt();
		ghostlyvaporstillID = internalCfg.get(Configuration.CATEGORY_BLOCK, "ghostlyvaporstillID", "1667").getInt();
		
		monolithdemissionID = internalCfg.get(Configuration.CATEGORY_BLOCK, "monolithdemissionID", "1668").getInt();
		monolithdemissiononID = internalCfg.get(Configuration.CATEGORY_BLOCK, "monolithdemissiononID", "1669").getInt();
		
		gravenStoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "gravenStoneID", "1670").getInt();
		
		monolithinceptionID = internalCfg.get(Configuration.CATEGORY_BLOCK, "monolithinceptionID", "1671").getInt();
		monolithinceptiononID = internalCfg.get(Configuration.CATEGORY_BLOCK, "monolithinceptiononID", "1672").getInt();

		oreincendiumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "oreincendiumID", "1673").getInt();
		oreluxificenID = internalCfg.get(Configuration.CATEGORY_BLOCK, "oreluxificenID", "1674").getInt();
		orejadeID = internalCfg.get(Configuration.CATEGORY_BLOCK, "orejadeID", "1675").getInt();
		orevoltasniaID = internalCfg.get(Configuration.CATEGORY_BLOCK, "orevoltasniaID", "1676").getInt();
		
		mushroominisiaID = internalCfg.get(Configuration.CATEGORY_BLOCK, "mushroominisiaID", "1677").getInt();
		mushroomvalensienID = internalCfg.get(Configuration.CATEGORY_BLOCK, "mushroomvalensienID", "1678").getInt();
		mushroommortaliaID = internalCfg.get(Configuration.CATEGORY_BLOCK, "mushroommortaliaID", "1679").getInt();
		
		monolithascensionID = internalCfg.get(Configuration.CATEGORY_BLOCK, "monolithascensionID", "1680").getInt();
		monolithascensiononID = internalCfg.get(Configuration.CATEGORY_BLOCK, "monolithascensiononID", "1681").getInt();
		
		stairsinisiaID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairsinisiaID", "1682").getInt();
		stairsvalensienID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairsvalensienID", "1683").getInt();
		stairsmortaliaID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairsmortaliaID", "1684").getInt();
		
		radiantleavesID = internalCfg.get(Configuration.CATEGORY_BLOCK, "radiantleavesID", "1685").getInt();
		
		oredraconiumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "oredraconiumID", "1686").getInt();
		blockdraconiumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockdraconiumID", "1687").getInt();
		
		monolithterminationID = internalCfg.get(Configuration.CATEGORY_BLOCK, "monolithterminationID", "1688").getInt();
		monolithterminationonID = internalCfg.get(Configuration.CATEGORY_BLOCK, "monolithterminationonID", "1689").getInt();
	
		electroplationengineID = internalCfg.get(Configuration.CATEGORY_BLOCK, "electroplationengineID", "1691").getInt();
		
		goldboundstoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "goldboundstoneID", "1692").getInt();
		
		veneficianpodiumID = internalCfg.get(Configuration.CATEGORY_BLOCK, "veneficianpodiumID", "1693").getInt();
		
		sandstonebricksID = internalCfg.get(Configuration.CATEGORY_BLOCK, "sandstonebricksID", "1694").getInt();
		inscribedsandstoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "inscribedsandstoneID", "1695").getInt();
		blockdesertID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockdesertID", "1696").getInt();
		
		endstonebricksID = internalCfg.get(Configuration.CATEGORY_BLOCK, "endstonebricksID", "1697").getInt();
		inscribedendstoneID = internalCfg.get(Configuration.CATEGORY_BLOCK, "inscribedendstoneID", "1698").getInt();
		blockskyID = internalCfg.get(Configuration.CATEGORY_BLOCK, "blockskyID", "1699").getInt();
		
		stairssandstonebricksID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairssandstonebricksID", "1700").getInt();
		stairsendstonebrickID = internalCfg.get(Configuration.CATEGORY_BLOCK, "stairsendstonebrickID", "1701").getInt();
		
		deadspawnerID = internalCfg.get(Configuration.CATEGORY_BLOCK, "deadspawnerID", "1702").getInt();
		
		// NEXT ID: 1703
		
	}
	
	private void loadItemsConfig() {
		ironscrapID = internalCfg.get(Configuration.CATEGORY_ITEM, "ironscrapID", "23000").getInt();
		diamondshardID = internalCfg.get(Configuration.CATEGORY_ITEM, "diamondshardID", "23001").getInt();
		
		ingotaluminumID = internalCfg.get(Configuration.CATEGORY_ITEM, "ingotaluminumID", "23002").getInt();
		tantalumcrystalID = internalCfg.get(Configuration.CATEGORY_ITEM, "tantalumcrystalID", "23003").getInt();
		ingotcopperID = internalCfg.get(Configuration.CATEGORY_ITEM, "ingotcopperID", "23004").getInt();
		copperchunkID = internalCfg.get(Configuration.CATEGORY_ITEM, "copperchunkID", "23005").getInt();
		ingotsilverID = internalCfg.get(Configuration.CATEGORY_ITEM, "ingotsilverID", "23006").getInt();
		silverfragmentID = internalCfg.get(Configuration.CATEGORY_ITEM, "silverfragmentID", "23007").getInt();
		ingotelectrumID = internalCfg.get(Configuration.CATEGORY_ITEM, "ingotelectrumID", "23008").getInt();
		electrumbitID = internalCfg.get(Configuration.CATEGORY_ITEM, "electrumbitID", "23009").getInt();
		ingotnisilID = internalCfg.get(Configuration.CATEGORY_ITEM, "ingotnisilID", "23010").getInt();
		nisilshardID = internalCfg.get(Configuration.CATEGORY_ITEM, "nisilshardID", "23011").getInt();
		ingotplatinumID = internalCfg.get(Configuration.CATEGORY_ITEM, "ingotplatinumID", "23012").getInt();
		ingotlithiumID = internalCfg.get(Configuration.CATEGORY_ITEM, "ingotlithiumID", "23013").getInt();
		ingotcobaltID = internalCfg.get(Configuration.CATEGORY_ITEM, "ingotcobaltID", "23014").getInt();
		elinvardustID = internalCfg.get(Configuration.CATEGORY_ITEM, "elinvardustID", "23015").getInt();
		cobaltdustID = internalCfg.get(Configuration.CATEGORY_ITEM, "cobaltdustID", "23016").getInt();
		
		bloodamberID = internalCfg.get(Configuration.CATEGORY_ITEM, "bloodamberID", "23017").getInt();
		essenceID = internalCfg.get(Configuration.CATEGORY_ITEM, "essenceID", "23018").getInt();
		
		pickaxealuminumID = internalCfg.get(Configuration.CATEGORY_ITEM, "pickaxealuminumID", "23019").getInt();
		swordaluminumID = internalCfg.get(Configuration.CATEGORY_ITEM, "swordaluminumID", "23020").getInt();
		shovelaluminumID = internalCfg.get(Configuration.CATEGORY_ITEM, "shovelaluminumID", "23021").getInt();
		axealuminumID = internalCfg.get(Configuration.CATEGORY_ITEM, "axealuminumID", "23022").getInt();
		hoealuminumID = internalCfg.get(Configuration.CATEGORY_ITEM, "hoealuminumID", "23023").getInt();
		
		pickaxecopperID = internalCfg.get(Configuration.CATEGORY_ITEM, "pickaxecopperID", "23024").getInt();
		swordcopperID = internalCfg.get(Configuration.CATEGORY_ITEM, "swordcopperID", "23025").getInt();
		shovelcopperID = internalCfg.get(Configuration.CATEGORY_ITEM, "shovelcopperID", "23026").getInt();
		axecopperID = internalCfg.get(Configuration.CATEGORY_ITEM, "axecopperID", "23027").getInt();
		hoecopperID = internalCfg.get(Configuration.CATEGORY_ITEM, "hoecopperID", "23028").getInt();
		
		arcanebucketID = internalCfg.get(Configuration.CATEGORY_ITEM, "arcanebucketID", "23029").getInt();
		bucketghostlyvaporID = internalCfg.get(Configuration.CATEGORY_ITEM, "bucketghostlyvaporID", "23030").getInt();
		
		incendiumdustID = internalCfg.get(Configuration.CATEGORY_ITEM, "incendiumdustID", "23031").getInt();
		luxificendustID = internalCfg.get(Configuration.CATEGORY_ITEM, "luxificendustID", "23032").getInt();
		jadecrystalID = internalCfg.get(Configuration.CATEGORY_ITEM, "jadecrystalID", "23033").getInt();
		voltasniarodID = internalCfg.get(Configuration.CATEGORY_ITEM, "voltasniarodID", "23034").getInt();
		
		draconiumID = internalCfg.get(Configuration.CATEGORY_ITEM, "draconiumID", "23035").getInt();
		
		arcanerodID = internalCfg.get(Configuration.CATEGORY_ITEM, "arcanerodID", "23036").getInt();
		veneficianrodID = internalCfg.get(Configuration.CATEGORY_ITEM, "veneficianrodID", "23037").getInt();
		
		pickaxelithiumID = internalCfg.get(Configuration.CATEGORY_ITEM, "pickaxelithiumID", "23038").getInt();
		swordlithiumID = internalCfg.get(Configuration.CATEGORY_ITEM, "swordlithiumID", "23039").getInt();
		shovellithiumID = internalCfg.get(Configuration.CATEGORY_ITEM, "shovellithiumID", "23040").getInt();
		axelithiumID = internalCfg.get(Configuration.CATEGORY_ITEM, "axelithiumID", "23041").getInt();
		hoelithiumID = internalCfg.get(Configuration.CATEGORY_ITEM, "hoelithiumID", "23042").getInt();
		
		veneficiacellID = internalCfg.get(Configuration.CATEGORY_ITEM, "veneficiacellID", "23043").getInt();
		
		// NEXT ID: 23044
		
	}
	
	private void loadGeneralConfig() {
		docoalblockrecipe = internalCfg.get(Configuration.CATEGORY_GENERAL, "docoalblockrecipe", "true").getBoolean(true);
		doironscraprecipe = internalCfg.get(Configuration.CATEGORY_GENERAL, "doironscraprecipe", "true").getBoolean(true);
		dodiamondshardrecipe = internalCfg.get(Configuration.CATEGORY_GENERAL, "dodiamondshardrecipe", "true").getBoolean(true);
	}
	
	private void loadBiomesConfig() {
		extremeforestID = internalCfg.get(this.CATEGORY_BIOME, "extremeforestID", "23").getInt();
		insanehillsID = internalCfg.get(this.CATEGORY_BIOME, "insanehillsID", "24").getInt();
		grassysummitsID = internalCfg.get(this.CATEGORY_BIOME, "grassysummitsID", "25").getInt();
		winterforestID = internalCfg.get(this.CATEGORY_BIOME, "winterforestID", "26").getInt();
		alphaID = internalCfg.get(this.CATEGORY_BIOME, "alphaID", "27").getInt();
		savannaID = internalCfg.get(this.CATEGORY_BIOME, "savannaID", "28").getInt();
		sandypeaksID = internalCfg.get(this.CATEGORY_BIOME, "sandypeaksID", "29").getInt();
		icyridgesID = internalCfg.get(this.CATEGORY_BIOME, "icyridgesID", "30").getInt();
		goldenwoodforestID = internalCfg.get(this.CATEGORY_BIOME, "goldenwoodforestID", "31").getInt();
		tenebralwoodsID = internalCfg.get(this.CATEGORY_BIOME, "tenebralwoodsID", "32").getInt();
		depthsID = internalCfg.get(this.CATEGORY_BIOME, "depthsID", "33").getInt();
		cordiferhillsID = internalCfg.get(this.CATEGORY_BIOME, "cordiferhillsID", "34").getInt();
		temperateforestID = internalCfg.get(this.CATEGORY_BIOME, "temperateforestID", "35").getInt();
		glacialwastelandID = internalCfg.get(this.CATEGORY_BIOME, "glacialwastelandID", "36").getInt();
		junglemountainsID = internalCfg.get(this.CATEGORY_BIOME, "junglemountainsID", "37").getInt();
		coniferouswoodsID = internalCfg.get(this.CATEGORY_BIOME, "coniferouswoodsID", "38").getInt();
	}
	
	private void loadDimensionConfig() {
		doalternatesurface = internalCfg.get(this.CATEGORY_DIMENSION, "doalternatesurface", "true").getBoolean(true);
		alternatesurfaceproviderID = internalCfg.get(this.CATEGORY_DIMENSION, "alternatesurfaceID", "16").getInt();
		thedeepproviderID = internalCfg.get(this.CATEGORY_DIMENSION, "thedeepID", "-2").getInt();
		thedeepID = internalCfg.get(this.CATEGORY_DIMENSION, "thedeepID", "-2").getInt();
	}
	
}
