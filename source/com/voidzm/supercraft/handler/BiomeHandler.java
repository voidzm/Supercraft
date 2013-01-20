//////////////////////////////////////
//*        BiomeHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.biome.BiomeGenAlpha;
import com.voidzm.supercraft.biome.BiomeGenExtremeForest;
import com.voidzm.supercraft.biome.BiomeGenGrassySummits;
import com.voidzm.supercraft.biome.BiomeGenInsaneHills;
import com.voidzm.supercraft.biome.BiomeGenSavanna;
import com.voidzm.supercraft.biome.BiomeGenWinterForest;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeHandler {

	public static BiomeGenBase extremeForest;
	public static BiomeGenBase insaneHills;
	public static BiomeGenBase grassySummits;
	public static BiomeGenBase winterForest;
	public static BiomeGenBase alpha;
	public static BiomeGenBase savanna;
	
	public void populateAllAndInitialize() {
		this.createBiomes();
		this.registerBiomes();
		System.out.println("[Supercraft] 6 biomes added.");
	}
	
	private void createBiomes() {
		extremeForest = new BiomeGenExtremeForest(23);
		insaneHills = new BiomeGenInsaneHills(24);
		grassySummits = new BiomeGenGrassySummits(25);
		winterForest = new BiomeGenWinterForest(26);
		alpha = new BiomeGenAlpha(27);
		savanna = new BiomeGenSavanna(28);
	}
	
	private void registerBiomes() {
		GameRegistry.addBiome(extremeForest);
		GameRegistry.addBiome(insaneHills);
		GameRegistry.addBiome(grassySummits);
		GameRegistry.addBiome(winterForest);
		GameRegistry.addBiome(alpha);
		GameRegistry.addBiome(savanna);
	}
	
	public void removeVanillaBiomes() {
		GameRegistry.removeBiome(BiomeGenBase.forest);
		GameRegistry.removeBiome(BiomeGenBase.forestHills);
		GameRegistry.removeBiome(BiomeGenBase.extremeHills);
		GameRegistry.removeBiome(BiomeGenBase.extremeHillsEdge);
		GameRegistry.removeBiome(BiomeGenBase.desert);
		GameRegistry.removeBiome(BiomeGenBase.desertHills);
		GameRegistry.removeBiome(BiomeGenBase.icePlains);
		GameRegistry.removeBiome(BiomeGenBase.iceMountains);
		GameRegistry.removeBiome(BiomeGenBase.jungle);
		GameRegistry.removeBiome(BiomeGenBase.jungleHills);
		GameRegistry.removeBiome(BiomeGenBase.plains);
		GameRegistry.removeBiome(BiomeGenBase.swampland);
		GameRegistry.removeBiome(BiomeGenBase.taiga);
		GameRegistry.removeBiome(BiomeGenBase.taigaHills);
	}
	
}
