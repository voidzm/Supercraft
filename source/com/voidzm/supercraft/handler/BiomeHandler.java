//**
//**  BiomeHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;

import com.voidzm.supercraft.biome.*;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.registry.GameRegistry;

public class BiomeHandler {

	private static SupercraftConfiguration config;
	
	public static BiomeGenBase extremeForest;
	public static BiomeGenBase insaneHills;
	public static BiomeGenBase grassySummits;
	public static BiomeGenBase winterForest;
	public static BiomeGenBase alpha;
	public static BiomeGenBase savanna;
	public static BiomeGenBase sandyPeaks;
	public static BiomeGenBase icyRidges;
	public static BiomeGenBase goldenwoodForest;
	public static BiomeGenBase tenebralWoods;
	public static BiomeGenBase depths;
	public static BiomeGenBase cordiferHills;
	public static BiomeGenBase temperateForest;
	public static BiomeGenBase glacialWasteland;
	
	public static void init(SupercraftConfiguration configObject, boolean doVanillaBiomes) {
		if(config != null) {
			throw new RuntimeException("Biome handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for biome handler initialization!");
		}
		config = configObject;
		createBiomes();
		registerBiomes();
		if(!doVanillaBiomes) removeVanillaBiomes();
	}
	
	public static void init(SupercraftConfiguration configObject) {
		init(configObject, true);
	}
	
	private static void createBiomes() {
		extremeForest = new BiomeSupercraft(config.extremeforestID, BiomeProperties.EXTREMEFOREST, "Extreme Forest");
		insaneHills = new BiomeSupercraft(config.insanehillsID, BiomeProperties.INSANEHILLS, "Insane Hills");
		grassySummits = new BiomeSupercraft(config.grassysummitsID, BiomeProperties.GRASSYSUMMITS, "Grassy Summits");
		winterForest = new BiomeSupercraft(config.winterforestID, BiomeProperties.WINTERFOREST, "Winter Forest").setSnowy();
		alpha = new BiomeSupercraft(config.alphaID, BiomeProperties.ALPHA, "Alpha");
		savanna = new BiomeSupercraft(config.savannaID, BiomeProperties.SAVANNA, "Savanna");
		sandyPeaks = new BiomeSupercraft(config.sandypeaksID, BiomeProperties.SANDYPEAKS, "Sandy Peaks").setDesert();
		icyRidges = new BiomeSupercraft(config.icyridgesID, BiomeProperties.ICYRIDGES, "Icy Ridges").setSnowy();
		goldenwoodForest = new BiomeSupercraft(config.goldenwoodforestID, BiomeProperties.GOLDENWOODFOREST, "Goldenwood Forest").setSkyColor(0xD8EDED);
		tenebralWoods = new BiomeSupercraft(config.tenebralwoodsID, BiomeProperties.TENEBRALWOODS, "Tenebral Woods").setSkyColor(0x646770).setWaterMultiplier(0xDDAA44).setNoAnimals();
		depths = new BiomeGenDepths(config.depthsID);
		cordiferHills = new BiomeSupercraft(config.cordiferhillsID, BiomeProperties.CORDIFERHILLS, "Cordifer Hills");
		temperateForest = new BiomeSupercraft(config.temperateforestID, BiomeProperties.TEMPERATEFOREST, "Temperate Forest");
		glacialWasteland = new BiomeSupercraft(config.glacialwastelandID, BiomeProperties.GLACIALWASTELAND, "Glacial Wasteland").setTopBlock((byte)Block.blockSnow.blockID).setFillerBlock((byte)Block.blockSnow.blockID).setSnowy().setNoAnimals();
	}
	
	private static void registerBiomes() {
		/*addStandardBiome(extremeForest);
		addStandardBiome(insaneHills);
		addStandardBiome(grassySummits);
		addStandardBiome(winterForest);
		addStandardBiome(alpha);
		addVillageBiome(savanna);
		addStandardBiome(sandyPeaks);
		addStandardBiome(icyRidges);
		addStandardBiome(goldenwoodForest);
		addStandardBiome(tenebralWoods);
		addStandardBiome(cordiferHills);
		addStandardBiome(temperateForest);*/
		addStandardBiome(glacialWasteland);
	}
	
	private static void removeVanillaBiomes() {
		GameRegistry.removeBiome(BiomeGenBase.forest);
		GameRegistry.removeBiome(BiomeGenBase.extremeHills);
		GameRegistry.removeBiome(BiomeGenBase.desert);
		GameRegistry.removeBiome(BiomeGenBase.icePlains);
		GameRegistry.removeBiome(BiomeGenBase.jungle);
		GameRegistry.removeBiome(BiomeGenBase.plains);
		GameRegistry.removeBiome(BiomeGenBase.swampland);
		GameRegistry.removeBiome(BiomeGenBase.taiga);
		GameRegistry.removeBiome(BiomeGenBase.mushroomIsland);
	}
	
	private static void addStandardBiome(BiomeGenBase biome) {
		GameRegistry.addBiome(biome);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addStrongholdBiome(biome);
		StartupStats.biomeCreated();
	}
	
	private static void addVillageBiome(BiomeGenBase biome) {
		GameRegistry.addBiome(biome);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addVillageBiome(biome, true);
		BiomeManager.addStrongholdBiome(biome);
		StartupStats.biomeCreated();
	}
	
}
