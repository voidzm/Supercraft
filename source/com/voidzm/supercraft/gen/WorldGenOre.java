//////////////////////////////////////
//*        WorldGenOre.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.gen;

import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenOre implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
		case -1:
			generateNether();
			break;
		case 0:
			generateOverworld(world, random, chunkX, chunkZ);
			break;
		case 1:
			generateEnd();
			break;
		}
	}

	private void generateNether() {
		// No Nether ores...yet.
	}
	
	private void generateOverworld(World world, Random rand, int cx, int cz) {
		int ky = 96;
		for(int i = 0; i < 6; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(32);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.aluminumOre.blockID, 12).generate(world, rand, rx, ry, rz);
		}
		ky = 0;
		for(int i = 0; i < 1; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(24);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.tantalumOre.blockID, 4).generate(world, rand, rx, ry, rz);
		}
	}
	
	private void generateEnd() {
		// No End ores...yet.
	}
	
}
