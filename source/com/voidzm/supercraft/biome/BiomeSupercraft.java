package com.voidzm.supercraft.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeSupercraft extends BiomeGenBase {
	
	private BiomeProperties properties;
	
	private int grassColor;
	private int leavesColor;
	
	private BiomeStructureGen[] decorators;
	
	private int skyColor = -1;
	private int waterMultiplier = -1;
	
	public BiomeSupercraft(int par1, BiomeProperties prop, String name) {
		super(par1);
		this.properties = prop;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = prop.grass;
		this.theBiomeDecorator.reedsPerChunk = prop.sugarCane;
		this.theBiomeDecorator.flowersPerChunk = prop.flowers;
		this.setMinMaxHeight(prop.minHeight, prop.maxHeight);
		this.setTemperatureRainfall(prop.temperature, prop.rainfall);
		this.setBiomeName(name);
		this.grassColor = prop.grassColor;
		this.leavesColor = prop.leavesColor;
		this.decorators = prop.gens;
	}
	
	@Override
	public int getBiomeGrassColor() {
		return this.grassColor;
	}
	
	@Override
	public int getBiomeFoliageColor() {
		return this.leavesColor;
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
		for(BiomeStructureGen gen : this.decorators) {
			gen.generate(par1World, par2Random, par3, par4);
		}
	}
	
	public BiomeSupercraft setSnowy() {
		this.setEnableSnow();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
		return this;
	}
	
	public BiomeSupercraft setDesert() {
		this.setDisableRain();
		this.setNoAnimals();
		this.theBiomeDecorator.deadBushPerChunk = 2;
		this.theBiomeDecorator.cactiPerChunk = 6;
		this.topBlock = (byte)Block.sand.blockID;
		this.fillerBlock = (byte)Block.sand.blockID;
		return this;
	}
	
	public BiomeSupercraft setNoAnimals() {
		this.spawnableCreatureList.clear();
		return this;
	}

	public BiomeSupercraft setSkyColor(int sky) {
		this.skyColor = sky;
		return this;
	}
	
	@Override
	public int getSkyColorByTemp(float par1) {
		if(this.skyColor != -1) return this.skyColor;
		else return super.getSkyColorByTemp(par1);
	}
	
	public BiomeSupercraft setWaterMultiplier(int water) {
		this.waterMultiplier = water;
		return this;
	}
	
	@Override
	public int getWaterColorMultiplier() {
		if(this.waterMultiplier != -1) return this.waterMultiplier;
		else return super.getWaterColorMultiplier();
	}
	
	public BiomeSupercraft setTopBlock(byte newTop) {
		this.topBlock = newTop;
		return this;
	}
	
	public BiomeSupercraft setFillerBlock(byte newFiller) {
		this.fillerBlock = newFiller;
		return this;
	}

}
