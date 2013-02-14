//////////////////////////////////////
//*      BiomeGenSavanna.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.biome;

import java.util.Random;

import com.voidzm.supercraft.gen.WorldGenOlive;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeGenSavanna extends BiomeGenBase {

	protected int oliveAttemptsPerChunk;
	protected int percentageOfOliveAttempt;
	
	protected WorldGenOlive oliveGenerator = new WorldGenOlive();
	
	public BiomeGenSavanna(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 9;
		this.theBiomeDecorator.flowersPerChunk = 1;
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.generateLakes = false;
		this.oliveAttemptsPerChunk = 1;
		this.percentageOfOliveAttempt = 20;
		this.setBiomeName("Savanna");
		this.setMinMaxHeight(0.1F, 0.12F);
		this.setTemperatureRainfall(0.7F, 0.2F);
	}
	
	@Override
	public int getBiomeGrassColor() {
		return 0xADA263;
	}
	
	@Override
	public int getBiomeFoliageColor() {
		return 0xADA263;
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
		for(int i = 0; i < oliveAttemptsPerChunk; i++) {
			if(par2Random.nextInt(100) > percentageOfOliveAttempt) {
				continue;
			}
			int xloc = par3 + par2Random.nextInt(16);
			int zloc = par4 + par2Random.nextInt(16);
			this.oliveGenerator.generate(par1World, par2Random, xloc, par1World.getHeightValue(xloc, zloc), zloc);
		}
	}
	
}
