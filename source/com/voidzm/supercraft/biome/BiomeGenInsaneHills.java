//////////////////////////////////////
//*    BiomeGenInsaneHills.java    *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenInsaneHills extends BiomeGenBase {

	public BiomeGenInsaneHills(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.grassPerChunk = 1;
		this.setBiomeName("Insane Hills");
		this.setMinMaxHeight(0.1F, 2.0F);
		this.setTemperatureRainfall(0.2F, 0.2F);
	}
	
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return (WorldGenerator)(par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : this.worldGeneratorTrees);
	}

}
