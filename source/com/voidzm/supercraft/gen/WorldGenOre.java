//**
//**  WorldGenOre.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.handler.BiomeHandler;
import com.voidzm.supercraft.handler.BlockHandler;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGenOre implements IWorldGenerator {

	private static WorldGenOre instance;
	
	public static WorldGenOre instance() {
		if(instance == null) {
			instance = new WorldGenOre();
		}
		return instance;
	}
	
	public static void init() {
		GameRegistry.registerWorldGenerator(instance());
	}
	
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
		if(world.provider.dimensionId == Supercraft.configuration.thedeepID) {
			generateDeep(world, random, chunkX, chunkZ);
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
		int ky = 96;
		for(int i = 0; i < 6; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(32);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.aluminumOre.blockID, 12).generate(world, rand, rx, ry, rz);
		}
		ky = 64;
		for(int i = 0; i < 8; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(32);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.copperOre.blockID, 4).generate(world, rand, rx, ry, rz);
		}
		ky = 0;
		for(int i = 0; i < 3; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(10);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.silverOre.blockID, 6).generate(world, rand, rx, ry, rz);
		}
		ky = 0;
		if(rand.nextInt(9) == 0) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(64);
			int rz = (cz * 16) + rand.nextInt(16);
			if(world.getBiomeGenForCoords(rx, rz).equals(BiomeHandler.goldenwoodForest)) new WorldGenMinable(BlockHandler.electrumOre.blockID, 16).generate(world, rand, rx, ry, rz);
		}
		ky = 0;
		for(int i = 0; i < 6; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(128);
			int rz = (cz * 16) + rand.nextInt(16);
			if(world.getBiomeGenForCoords(rx, rz).equals(BiomeHandler.goldenwoodForest)) new WorldGenMinable(BlockHandler.palestone.blockID, 24).generate(world, rand, rx, ry, rz);
		}
		ky = 0;
		for(int i = 0; i < 10; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(64);
			int rz = (cz * 16) + rand.nextInt(16);
			if(world.getBiomeGenForCoords(rx, rz).equals(BiomeHandler.tenebralWoods)) new WorldGenMinable(BlockHandler.nisilOre.blockID, 8).generate(world, rand, rx, ry, rz);
		}
		ky = 0;
		for(int i = 0; i < 6; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(128);
			int rz = (cz * 16) + rand.nextInt(16);
			if(world.getBiomeGenForCoords(rx, rz).equals(BiomeHandler.tenebralWoods)) new WorldGenMinable(BlockHandler.nightrock.blockID, 24).generate(world, rand, rx, ry, rz);
		}
		ky = 0;
		for(int i = 0; i < 8; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(32);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.platinumOre.blockID, 1).generate(world, rand, rx, ry, rz);
		}
		ky = 0;
		for(int i = 0; i < 1; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(24);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.tantalumOre.blockID, 4).generate(world, rand, rx, ry, rz);
		}
		ky = 0;
		if(rand.nextInt(4) == 0) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = ky + rand.nextInt(16);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.lithiumOre.blockID, 4).generate(world, rand, rx, ry, rz);
		}
	}
	
	private void generateEnd(World world, Random rand, int cx, int cz) {
		for(int i = 0; i < 16; i++) {
			int rx = (cx * 16) + rand.nextInt(16);
			int ry = rand.nextInt(56);
			int rz = (cz * 16) + rand.nextInt(16);
			new WorldGenMinable(BlockHandler.draconiumOre.blockID, 8, Block.whiteStone.blockID).generate(world, rand, rx, ry, rz);
		}
	}
	
	private void generateDeep(World world, Random rand, int cx, int cz) {
		for(int i = 0; i < 1; i++) {
			WorldGenMinable palestoneGen = new WorldGenMinable(BlockHandler.palestone.blockID, 12);
			int x = cx + rand.nextInt(16);
			int y = rand.nextInt(128);
			int z = cz + rand.nextInt(16);
			palestoneGen.generate(world, rand, x, y, z);
		}
		for(int i = 0; i < 1; i++) {
			WorldGenMinable nightrockGen = new WorldGenMinable(BlockHandler.nightrock.blockID, 12);
			int x = cx + rand.nextInt(16);
			int y = rand.nextInt(128);
			int z = cz + rand.nextInt(16);
			nightrockGen.generate(world, rand, x, y, z);
		}
		for(int i = 0; i < 4; i++) {
			WorldGenMinable gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
			int x = cx + rand.nextInt(16);
			int y = rand.nextInt(128);
			int z = cz + rand.nextInt(16);
			gravelGen.generate(world, rand, x, y, z);
		}
		for(int i = 0; i < 12; i++) {
			WorldGenMinable coalGen = new WorldGenMinable(Block.oreCoal.blockID, 12);
			int x = cx + rand.nextInt(16);
			int y = rand.nextInt(128);
			int z = cz + rand.nextInt(16);
			coalGen.generate(world, rand, x, y, z);
		}
		for(int i = 0; i < 4; i++) {
			WorldGenMinable jadeGen = new WorldGenMinable(BlockHandler.jadeOre.blockID, 3);
			int x = cx + rand.nextInt(16);
			int y = 104 + rand.nextInt(24);
			int z = cz + rand.nextInt(16);
			jadeGen.generate(world, rand, x, y, z);
		}
		for(int i = 0; i < 6; i++) {
			WorldGenMinable incendiumGen = new WorldGenMinable(BlockHandler.incendiumOre.blockID, 8);
			int x = cx + rand.nextInt(16);
			int y = rand.nextInt(128);
			int z = cz + rand.nextInt(16);
			incendiumGen.generate(world, rand, x, y, z);
		}
		for(int i = 0; i < 6; i++) {
			WorldGenMinable luxificenGen = new WorldGenMinable(BlockHandler.luxificenOre.blockID, 8);
			int x = cx + rand.nextInt(16);
			int y = rand.nextInt(128);
			int z = cz + rand.nextInt(16);
			luxificenGen.generate(world, rand, x, y, z);
		}
		for(int i = 0; i < 2; i++) {
			WorldGenMinable voltasniaGen = new WorldGenMinable(BlockHandler.voltasniaOre.blockID, 4);
			int x = cx + rand.nextInt(16);
			int y = rand.nextInt(24);
			int z = cz + rand.nextInt(16);
			voltasniaGen.generate(world, rand, x, y, z);
		}
	}
	
}
