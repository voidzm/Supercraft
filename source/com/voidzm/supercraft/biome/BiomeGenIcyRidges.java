//////////////////////////////////////
//*     BiomeGenIcyRidges.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////


package com.voidzm.supercraft.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenIcyRidges extends BiomeGenBase {

	public BiomeGenIcyRidges(int par1) {
		super(par1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
		this.theBiomeDecorator.treesPerChunk = 6;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.setBiomeName("Icy Ridges");
		this.setMinMaxHeight(0.5F, 1.8F);
		this.setTemperatureRainfall(0.1F, 0.8F);
		this.setEnableSnow();
	}
	
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
	}
	
}
