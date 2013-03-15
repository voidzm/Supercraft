package com.voidzm.supercraft.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenScatteredFlowers extends WorldGenerator {

	private int flowerID;
	
	public WorldGenScatteredFlowers(int par1) {
		this.flowerID = par1;
	}
	
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		for(int i = 0; i < 8; i++) {
			int x = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int y = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int z = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
			if(par1World.isAirBlock(x, y, z) && Block.blocksList[this.flowerID].canBlockStay(par1World, x, y, z)) {
				this.setBlock(par1World, x, y, z, flowerID);
			}
		}
		return true;
	}
	
}
