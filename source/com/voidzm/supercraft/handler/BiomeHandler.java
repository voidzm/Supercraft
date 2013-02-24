//////////////////////////////////////
//*        BiomeHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.biome.BiomeGenAlpha;
import com.voidzm.supercraft.biome.BiomeGenExtremeForest;
import com.voidzm.supercraft.biome.BiomeGenGoldenwoodForest;
import com.voidzm.supercraft.biome.BiomeGenGrassySummits;
import com.voidzm.supercraft.biome.BiomeGenIcyRidges;
import com.voidzm.supercraft.biome.BiomeGenInsaneHills;
import com.voidzm.supercraft.biome.BiomeGenSandyPeaks;
import com.voidzm.supercraft.biome.BiomeGenSavanna;
import com.voidzm.supercraft.biome.BiomeGenTenebralWoods;
import com.voidzm.supercraft.biome.BiomeGenWinterForest;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.server.FMLServerHandler;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeManager;

public class BiomeHandler {

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
	
	public void populateAllAndInitialize() {
		this.createBiomes();
		this.registerBiomes();
		System.out.println("[Supercraft] 10 biomes added.");
	}
	
	private void createBiomes() {
		extremeForest = new BiomeGenExtremeForest(23);
		insaneHills = new BiomeGenInsaneHills(24);
		grassySummits = new BiomeGenGrassySummits(25);
		winterForest = new BiomeGenWinterForest(26);
		alpha = new BiomeGenAlpha(27);
		savanna = new BiomeGenSavanna(28);
		sandyPeaks = new BiomeGenSandyPeaks(29);
		icyRidges = new BiomeGenIcyRidges(30);
		goldenwoodForest = new BiomeGenGoldenwoodForest(31);
		tenebralWoods = new BiomeGenTenebralWoods(32);
	}
	
	private void registerBiomes() {
		/*this.addStandardBiome(extremeForest);
		this.addStandardBiome(insaneHills);
		this.addStandardBiome(grassySummits);
		this.addStandardBiome(winterForest);
		this.addStandardBiome(alpha);
		this.addVillageBiome(savanna);
		this.addStandardBiome(sandyPeaks);
		this.addStandardBiome(icyRidges);
		this.addStandardBiome(goldenwoodForest);*/
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
