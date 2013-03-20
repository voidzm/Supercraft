//////////////////////////////////////
//*        BiomeHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.biome.*;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.server.FMLServerHandler;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeManager;

public class BiomeHandler {

	protected SupercraftConfiguration config;
	
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
	
	public void populateAllAndInitialize(SupercraftConfiguration configObject) {
		if(this.config != null) {
			throw new RuntimeException("Biome handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for biome handler initialization!");
		}
		this.config = configObject;
		this.createBiomes();
		this.registerBiomes();
		System.out.println("[Supercraft] 11 biomes added.");
	}
	
	private void createBiomes() {
		extremeForest = new BiomeGenExtremeForest(this.config.extremeforestID);
		insaneHills = new BiomeGenInsaneHills(this.config.insanehillsID);
		grassySummits = new BiomeGenGrassySummits(this.config.grassysummitsID);
		winterForest = new BiomeGenWinterForest(this.config.winterforestID);
		alpha = new BiomeGenAlpha(this.config.alphaID);
		savanna = new BiomeGenSavanna(this.config.savannaID);
		sandyPeaks = new BiomeGenSandyPeaks(this.config.sandypeaksID);
		icyRidges = new BiomeGenIcyRidges(this.config.icyridgesID);
		goldenwoodForest = new BiomeGenGoldenwoodForest(this.config.goldenwoodforestID);
		tenebralWoods = new BiomeGenTenebralWoods(this.config.tenebralwoodsID);
		depths = new BiomeGenDepths(this.config.depthsID);
	}
	
	private void registerBiomes() {
		this.addStandardBiome(extremeForest);
		this.addStandardBiome(insaneHills);
		this.addStandardBiome(grassySummits);
		this.addStandardBiome(winterForest);
		this.addStandardBiome(alpha);
		this.addVillageBiome(savanna);
		this.addStandardBiome(sandyPeaks);
		this.addStandardBiome(icyRidges);
		this.addStandardBiome(goldenwoodForest);
		this.addStandardBiome(tenebralWoods);
	}
	
	public void removeVanillaBiomes() {
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
	
	private void addStandardBiome(BiomeGenBase biome) {
		GameRegistry.addBiome(biome);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addStrongholdBiome(biome);
	}
	
	private void addVillageBiome(BiomeGenBase biome) {
		GameRegistry.addBiome(biome);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addVillageBiome(biome, true);
		BiomeManager.addStrongholdBiome(biome);
	}
	
}
