//////////////////////////////////////
//*   BiomeGenExtremeForest.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenExtremeForest extends BiomeGenBase {

	public BiomeGenExtremeForest(int par1) {
		super(par1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		this.theBiomeDecorator.treesPerChunk = 6;
		this.theBiomeDecorator.grassPerChunk = 1;
		this.setBiomeName("Extreme Forest");
		this.setMinMaxHeight(0.3F, 1.7F);
		this.setTemperatureRainfall(0.6F, 0.7F);
	}

	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return (WorldGenerator)(par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : this.worldGeneratorTrees);
	}
	
}
