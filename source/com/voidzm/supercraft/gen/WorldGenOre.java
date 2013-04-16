//////////////////////////////////////
//*        WorldGenOre.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.gen;

import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenOre implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
		case -1:
			generateNether(world, random, chunkX, chunkZ);
			break;
		case 0:
			generateOverworld(world, random, chunkX, chunkZ);
			break;
		case 1:
			generateEnd(world, random, chunkX, chunkZ);
			break;
		}
	}

	private void generateNether(World world, Random rand, int cx, int cz) {
		int ky = 104; // Elinvar
		for(int i = 0; i < 2; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(24);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.elinvarOre.blockID, 16, Block.netherrack.blockID).generate(world, rand, rx, ry, rz);
		}
		ky = 0; // Cobalt
		for(int i = 0; i < 8; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(24);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.cobaltOre.blockID, 4, Block.netherrack.blockID).generate(world, rand, rx, ry, rz);
		}
	}
	
	private void generateOverworld(World world, Random rand, int cx, int cz) {
		int ky = 96; // Aluminum
		for(int i = 0; i < 6; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(32);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.aluminumOre.blockID, 12).generate(world, rand, rx, ry, rz);
		}
		ky = 0; // Tantalum
		for(int i = 0; i < 1; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(24);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.tantalumOre.blockID, 4).generate(world, rand, rx, ry, rz);
		}
		ky = 64; // Copper
		for(int i = 0; i < 8; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(32);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.copperOre.blockID, 4).generate(world, rand, rx, ry, rz);
		}
		ky = 0; // Silver
		for(int i = 0; i < 3; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(10);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.silverOre.blockID, 6).generate(world, rand, rx, ry, rz);
		}
		ky = 0; // Platinum
		for(int i = 0; i < 8; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(32);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.platinumOre.blockID, 1).generate(world, rand, rx, ry, rz);
		}
		ky = 0; // Lithium
		if(rand.nextInt(4) == 0) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(16);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.lithiumOre.blockID, 4).generate(world, rand, rx, ry, rz);
		}
	}
	
	private void generateEnd(World world, Random rand, int cx, int cz) {
		int ky = 0; // Draconium
		for(int i = 0; i < 16; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = rand.nextInt(56);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.draconiumOre.blockID, 8, Block.whiteStone.blockID).generate(world, rand, rx, ry, rz);
		}
	}
	
}
