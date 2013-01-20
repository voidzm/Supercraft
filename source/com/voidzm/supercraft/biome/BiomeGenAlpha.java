//////////////////////////////////////
//*       BiomeGenAlpha.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenAlpha extends BiomeGenBase {

	public BiomeGenAlpha(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.grassPerChunk = 1;
		this.theBiomeDecorator.reedsPerChunk = 5;
		this.theBiomeDecorator.flowersPerChunk = 5;
		this.setBiomeName("Alpha");
		this.setMinMaxHeight(-0.2F, 0.2F);
		this.setTemperatureRainfall(0.6F, 0.9F);
	}
	
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return this.worldGeneratorTrees;
	}
	
	public int getBiomeGrassColor() {
		return 0x6FDE50;
	}

}
