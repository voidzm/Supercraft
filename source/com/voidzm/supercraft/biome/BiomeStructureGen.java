//**
//**  BiomeStructureGen.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.biome;

import java.util.Random;

import com.voidzm.supercraft.gen.WorldGenGoldenwood;
import com.voidzm.supercraft.gen.WorldGenGoldenwoodShrine;
import com.voidzm.supercraft.gen.WorldGenOlive;
import com.voidzm.supercraft.gen.WorldGenScatteredFlowers;
import com.voidzm.supercraft.gen.WorldGenTenebralPitfall;
import com.voidzm.supercraft.gen.WorldGenTenebria;
import com.voidzm.supercraft.gen.WorldGenIcySpikes;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeStructureGen {

	public enum StructureGenType {
		SMALLTREE(new WorldGenTrees(false), true), BIGTREE(new WorldGenBigTree(false), true), BIRCHTREE(new WorldGenForest(false), true), SPRUCETREE1(new WorldGenTaiga1(), true),
		SPRUCETREE2(new WorldGenTaiga2(false), true), OLIVETREE(new WorldGenOlive(), true), GOLDENWOODTREE(new WorldGenGoldenwood(), true), BLUEBELLS(new WorldGenScatteredFlowers(BlockHandler.bluebells.blockID), false),
		DAISIES(new WorldGenScatteredFlowers(BlockHandler.daisies.blockID), false), GOLDENWOODSHRINE(new WorldGenGoldenwoodShrine(), true), TENEBRALPITFALL(new WorldGenTenebralPitfall(), true),
		TENEBRIATREE(new WorldGenTenebria(), true), SNAPDRAGON(new WorldGenScatteredFlowers(BlockHandler.snapdragon.blockID), false), ICYSPIKES(new WorldGenIcySpikes(), true);
		public WorldGenerator gen;
		public boolean isOnSurface;
		private StructureGenType(WorldGenerator generator, boolean surface) {
			this.gen = generator;
			this.isOnSurface = surface;
		}
	}
	
	private StructureGenType type;
	
	private boolean isCommon;

	private int genFrequency;
	
	public BiomeStructureGen(StructureGenType genType, boolean common, int frequency) {
		this.type = genType;
		this.isCommon = common;
		this.genFrequency = frequency;
	}
	
	public boolean generate(World world, Random rand, int chunkX, int chunkZ) {
		if(this.isCommon) {
			for(int i = 0; i < this.genFrequency; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int z = chunkZ + rand.nextInt(16) + 8;
				int y;
				if(this.type.isOnSurface) y = world.getHeightValue(x, z);
				else y = rand.nextInt(128);
				this.type.gen.generate(world, rand, x, y, z);
			}
		}
		else {
			if(rand.nextInt(this.genFrequency) == 0) {
				int x = chunkX + rand.nextInt(16) + 8;
				int z = chunkZ + rand.nextInt(16) + 8;
				int y;
				if(this.type.isOnSurface) y = world.getHeightValue(x, z);
				else y = rand.nextInt(128);
				this.type.gen.generate(world, rand, x, y, z);
			}
		}
		return true;
	}
	
}
