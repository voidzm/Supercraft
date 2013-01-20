//////////////////////////////////////
//*    BiomeGenWinterForest.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenWinterForest extends BiomeGenBase {

	public BiomeGenWinterForest(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 5;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.worldGeneratorTrees = new WorldGenTrees(false, 3, 0, 0, false);
		this.setBiomeName("Winter Forest");
		this.setMinMaxHeight(0.2F, 0.4F);
		this.setTemperatureRainfall(0.1F, 0.8F);
		this.setEnableSnow();
	}
	
	@Override
	public int getBiomeGrassColor() {
		return 0x98D9D4;
	}
	
	@Override
	public int getBiomeFoliageColor() {
		return 0x98D9D4;
	}
	
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees;
	}
	
}
