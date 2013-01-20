//////////////////////////////////////
//*   BiomeGenGrassySummits.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenGrassySummits extends BiomeGenBase {

	public BiomeGenGrassySummits(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.generateLakes = false;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.setMinMaxHeight(1.8F, 1.8F);
		this.setTemperatureRainfall(0.7F, 0.3F);
		this.setBiomeName("Grassy Summits");
	}
	
	@Override
	public int getBiomeGrassColor() {
		return 0x94BA5B;
	}
	
	@Override
	public int getBiomeFoliageColor() {
		return 0x94BA5B;
	}
	
}

