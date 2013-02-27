package com.voidzm.supercraft.biome;

import java.util.Random;

import com.voidzm.supercraft.gen.WorldGenGoldenwood;
import com.voidzm.supercraft.gen.WorldGenGoldenwoodShrine;
import com.voidzm.supercraft.gen.WorldGenScatteredFlowers;
import com.voidzm.supercraft.gen.WorldGenTenebralPitfall;
import com.voidzm.supercraft.gen.WorldGenTenebria;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeGenTenebralWoods extends BiomeGenBase {

	protected int tenebriaAttemptsPerChunk;
	
	protected WorldGenTenebria tenebriaGenerator = new WorldGenTenebria();
	
	public BiomeGenTenebralWoods(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.reedsPerChunk = 1;
		this.tenebriaAttemptsPerChunk = 7;
		this.setBiomeName("Tenebral Woods");
		this.setMinMaxHeight(0.4F, 0.5F);
		this.setTemperatureRainfall(0.5F, 0.5F);
		this.setColor(0x525A5B);
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySkeleton.class, 1, 1, 1));
	}
	
	@Override
	public int getBiomeGrassColor() {
		return 0x525A5B;
	}
	
	@Override
	public int getBiomeFoliageColor() {
		return 0x525A5B;
	}
	
	@Override
	public int getSkyColorByTemp(float par1) {
		return 0x646770;
	}
	
	@Override
	public float getSpawningChance() {
		return 0.1F;
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
		if(par2Random.nextInt(128) == 0) {
			int xloc = par3 + par2Random.nextInt(16) + 8;
			int zloc = par4 + par2Random.nextInt(16) + 8;
			WorldGenTenebralPitfall gen = new WorldGenTenebralPitfall();
			gen.generate(par1World, par2Random, xloc, par1World.getHeightValue(xloc, zloc), zloc);
		}
		for(int i = 0; i < tenebriaAttemptsPerChunk; i++) {
			int xloc = par3 + par2Random.nextInt(16) + 8;
			int zloc = par4 + par2Random.nextInt(16) + 8;
			this.tenebriaGenerator.generate(par1World, par2Random, xloc, par1World.getHeightValue(xloc, zloc), zloc);
		}
		for(int i = 0; i < 6; i++) {
			WorldGenMinable nightrockGen = new WorldGenMinable(BlockHandler.nightrock.blockID, 24);
			int x = par3 + par2Random.nextInt(16);
			int y = par2Random.nextInt(128);
			int z = par4 + par2Random.nextInt(16);
			nightrockGen.generate(par1World, par2Random, x, y, z);
		}
	}

}
