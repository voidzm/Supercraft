package com.voidzm.supercraft.biome;

import java.util.Random;

import com.voidzm.supercraft.gen.WorldGenGoldenwood;
import com.voidzm.supercraft.gen.WorldGenGoldenwoodShrine;
import com.voidzm.supercraft.gen.WorldGenOlive;
import com.voidzm.supercraft.gen.WorldGenScatteredFlowers;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeGenGoldenwoodForest extends BiomeGenBase {

	protected int goldenwoodAttemptsPerChunk;
	
	protected WorldGenGoldenwood goldenwoodGenerator = new WorldGenGoldenwood();
	
	public BiomeGenGoldenwoodForest(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.reedsPerChunk = 8;
		this.goldenwoodAttemptsPerChunk = 5;
		this.setBiomeName("Goldenwood Forest");
		this.setMinMaxHeight(0.4F, 0.55F);
		this.setTemperatureRainfall(0.5F, 0.5F);
		this.setColor(0xEAEDD8);
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
		if(par2Random.nextInt(640) == 0) {
			int xloc = par3 + par2Random.nextInt(16);
			int zloc = par4 + par2Random.nextInt(16);
			WorldGenGoldenwoodShrine gen = new WorldGenGoldenwoodShrine();
			gen.generate(par1World, par2Random, xloc, par1World.getHeightValue(xloc, zloc), zloc);
		}
		for(int i = 0; i < goldenwoodAttemptsPerChunk; i++) {
			int xloc = par3 + par2Random.nextInt(16);
			int zloc = par4 + par2Random.nextInt(16);
			this.goldenwoodGenerator.generate(par1World, par2Random, xloc, par1World.getHeightValue(xloc, zloc), zloc);
		}
		for(int i = 0; i < 1; i++) {
			WorldGenScatteredFlowers gen = new WorldGenScatteredFlowers(BlockHandler.bluebells.blockID);
			gen.generate(par1World, par2Random, par3, (par1World.getHeightValue(par3, par4) - 2) + par2Random.nextInt(4), par4);
		}
		for(int i = 0; i < 1; i++) {
			WorldGenScatteredFlowers gen = new WorldGenScatteredFlowers(BlockHandler.daisies.blockID);
			gen.generate(par1World, par2Random, par3, (par1World.getHeightValue(par3, par4) - 2) + par2Random.nextInt(4), par4);
		}
		for(int i = 0; i < 6; i++) {
			WorldGenMinable palestoneGen = new WorldGenMinable(BlockHandler.palestone.blockID, 24);
			int x = par3 + par2Random.nextInt(16);
			int y = par2Random.nextInt(128);
			int z = par4 + par2Random.nextInt(16);
			palestoneGen.generate(par1World, par2Random, x, y, z);
		}
		int ky = 0; // Electrum
		if(par2Random.nextInt(9) != 0) return; // Because this exits this.decorate 89% of the time, KEEP THIS GENERATOR LAST.
		for(int i = 0; i < 1; i++) {
			int rx = par3 + par2Random.nextInt(16);
			int ry = ky + par2Random.nextInt(64);
			int rz = par4 + par2Random.nextInt(16);
			new WorldGenMinable(BlockHandler.electrumOre.blockID, 16).generate(par1World, par2Random, rx, ry, rz);
		}
	}
	
}
