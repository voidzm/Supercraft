package com.voidzm.supercraft.util;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenSand;

public class CustomBiomeDecorator {

	public World currentWorld;
	public Random currentRandom;
	public int chunkX;
	public int chunkZ;
	public BiomeGenBase biomeGen;
	public ArrayList<BiomeDecorationEntry> decorators = new ArrayList<BiomeDecorationEntry>();
	
	public CustomBiomeDecorator(BiomeGenBase biome) {
		this.biomeGen = biome;
		this.populateDecorators();
	}
	
	private void populateDecorators() {
		decorators.add(new BiomeDecorationEntry(new WorldGenMinable(Block.dirt.blockID, 32), 20, false, 0, 128));
		decorators.add(new BiomeDecorationEntry(new WorldGenMinable(Block.gravel.blockID, 32), 10, false, 0, 128));
		decorators.add(new BiomeDecorationEntry(new WorldGenMinable(Block.oreCoal.blockID, 16), 20, false, 0, 128));
		decorators.add(new BiomeDecorationEntry(new WorldGenMinable(Block.oreIron.blockID, 8), 20, false, 0, 64));
		decorators.add(new BiomeDecorationEntry(new WorldGenMinable(Block.oreGold.blockID, 8), 2, false, 0, 32));
		decorators.add(new BiomeDecorationEntry(new WorldGenMinable(Block.oreRedstone.blockID, 7), 8, false, 0, 16));
		decorators.add(new BiomeDecorationEntry(new WorldGenMinable(Block.oreLapis.blockID, 6), 1, false, 0, 32));
		decorators.add(new BiomeDecorationEntry(new WorldGenMinable(Block.oreDiamond.blockID, 7), 1, false, 0, 16));
		decorators.add(new BiomeDecorationEntry(new WorldGenSand(7, Block.sand.blockID), 3, true));
	}
	
	public void decorateChunk(World targetWorld, Random rand, int xCoord, int zCoord) {
		if(this.currentWorld != null) {
			throw new RuntimeException("Already decorating the chunk at ("+xCoord+", "+zCoord+")!");
		}
		else {
			this.currentWorld = targetWorld;
			this.currentRandom = rand;
			this.chunkX = xCoord;
			this.chunkZ = zCoord;
			this.doDecoration();
			this.currentWorld = null;
			this.currentRandom = null;
		}
	}
	
	private void doDecoration() {
		
	}
	
}
