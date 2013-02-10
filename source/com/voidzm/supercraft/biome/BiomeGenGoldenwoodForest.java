package com.voidzm.supercraft.biome;

import java.util.Random;

import com.voidzm.supercraft.gen.WorldGenGoldenwood;
import com.voidzm.supercraft.gen.WorldGenOlive;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeGenGoldenwoodForest extends BiomeGenBase {

	protected int goldenwoodAttemptsPerChunk;
	
	protected WorldGenGoldenwood goldenwoodGenerator = new WorldGenGoldenwood();
	
	public BiomeGenGoldenwoodForest(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = 5;
		this.theBiomeDecorator.reedsPerChunk = 8;
		this.goldenwoodAttemptsPerChunk = 5;
		this.setBiomeName("Goldenwood Forest");
		this.setMinMaxHeight(0.3F, 1.0F);
		this.setTemperatureRainfall(0.5F, 0.5F);
	}
	
	@Override
	public int getBiomeGrassColor() {
		return 0xEAEDD8;
	}
	
	@Override
	public int getBiomeFoliageColor() {
		return 0xCCE69A;
	}
	
	@Override
	public int getSkyColorByTemp(float par1) {
		return 0xD8EDED;
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
		for(int i = 0; i < goldenwoodAttemptsPerChunk; i++) {
			int xloc = par3 + par2Random.nextInt(16);
			int zloc = par4 + par2Random.nextInt(16);
			this.goldenwoodGenerator.generate(par1World, par2Random, xloc, par1World.getHeightValue(xloc, zloc), zloc);
		}
	}
	
}
