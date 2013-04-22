//**
//**  BiomeHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

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
		extremeForest = new BiomeGenExtremeForest(config.extremeforestID);
		insaneHills = new BiomeGenInsaneHills(config.insanehillsID);
		grassySummits = new BiomeGenGrassySummits(config.grassysummitsID);
		winterForest = new BiomeGenWinterForest(config.winterforestID);
		alpha = new BiomeGenAlpha(config.alphaID);
		savanna = new BiomeGenSavanna(config.savannaID);
		sandyPeaks = new BiomeGenSandyPeaks(config.sandypeaksID);
		icyRidges = new BiomeGenIcyRidges(config.icyridgesID);
		goldenwoodForest = new BiomeGenGoldenwoodForest(config.goldenwoodforestID);
		tenebralWoods = new BiomeGenTenebralWoods(config.tenebralwoodsID);
		depths = new BiomeGenDepths(config.depthsID);
	}
	
	private static void registerBiomes() {
		addStandardBiome(extremeForest);
		addStandardBiome(insaneHills);
		addStandardBiome(grassySummits);
		addStandardBiome(winterForest);
		addStandardBiome(alpha);
		addVillageBiome(savanna);
		addStandardBiome(sandyPeaks);
		addStandardBiome(icyRidges);
		addStandardBiome(goldenwoodForest);
		addStandardBiome(tenebralWoods);
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
