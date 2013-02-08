//////////////////////////////////////
//*     BiomeGenSandyPeaks.java    *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenSandyPeaks extends BiomeGenBase {

	public BiomeGenSandyPeaks(int par1) {
		super(par1);
		this.spawnableCreatureList.clear();
		this.topBlock = (byte)Block.sand.blockID;
		this.fillerBlock = (byte)Block.sand.blockID;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = 2;
		this.theBiomeDecorator.reedsPerChunk = 10;
		this.theBiomeDecorator.cactiPerChunk = 6;
		this.setBiomeName("Sandy Peaks");
		this.setMinMaxHeight(0.4F, 1.8F);
		this.setTemperatureRainfall(1.5F, 0.0F);
		this.setDisableRain();
	}
	
}
