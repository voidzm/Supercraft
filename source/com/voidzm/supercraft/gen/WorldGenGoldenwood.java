package com.voidzm.supercraft.gen;

import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGoldenwood extends WorldGenerator {

	private int logID = BlockHandler.supercraftLog1.blockID;
	private int leavesID = BlockHandler.supercraftLeaves2.blockID;
	private int logData = 1;
	private int leavesData = 0;
	
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		return this.generate(var1, var2, var3, var4, var5, true);
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z, boolean biomeCheck) {
		int groundBlockID = world.getBlockId(x, y-1, z);
		Block groundBlock = Block.blocksList[groundBlockID];
		if((groundBlock == null || (groundBlockID != Block.grass.blockID && groundBlockID != Block.dirt.blockID)) || (!(world.getBiomeGenForCoords(x, z).biomeName.equalsIgnoreCase("Goldenwood Forest")) && biomeCheck)) {
			return false;
		}
		boolean canGenerate = true;
		int exposedTrunk = rand.nextInt(3) + 1; // Random between 1 and 3
		int totalHeight = exposedTrunk + 5;
		if(y > 255-totalHeight) return false;
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				for(int iy = 1; iy < totalHeight; iy++) {
					if(!this.shouldTreeOverwrite(world, x+ix, y+iy, z+iz)) canGenerate = false;
				}
			}
		}
		if(!canGenerate) return false;
		for(int i = 0; i < exposedTrunk; i++) {
			this.setBlockAndMetadata(world, x, y+i, z, logID, logData);
		}
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				if(ix == 0 && iz == 0) this.setBlockAndMetadata(world, x, y+exposedTrunk, z, logID, logData);
				else if(Math.abs(ix) == Math.abs(iz) && rand.nextInt(4) != 0) this.setBlockAndMetadata(world, x+ix, y+exposedTrunk, z+iz, leavesID, leavesData);
				else if(Math.abs(ix) != Math.abs(iz)) this.setBlockAndMetadata(world, x+ix, y+exposedTrunk, z+iz, leavesID, leavesData);
			}
		}
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				for(int iy = exposedTrunk+1; iy < exposedTrunk+3; iy++) {
					if(ix == 0 && iz == 0) this.setBlockAndMetadata(world, x, y+iy, z, logID, logData);
					else this.setBlockAndMetadata(world, x+ix, y+iy, z+iz, leavesID, leavesData);
				}
			}
		}
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				if(ix == 0 && iz == 0) this.setBlockAndMetadata(world, x, y+exposedTrunk+3, z, logID, logData);
				else if(Math.abs(ix) != Math.abs(iz)) this.setBlockAndMetadata(world, x+ix, y+exposedTrunk+3, z+iz, leavesID, leavesData);
			}
		}
		this.setBlockAndMetadata(world, x, y+exposedTrunk+4, z, leavesID, leavesData);
		return true;
	}
	
	protected boolean shouldTreeOverwrite(World world, int x, int y, int z) {
		int blockID = world.getBlockId(x, y, z);
		Block block = Block.blocksList[blockID];
		if(block == null) return true;
		if(block.isLeaves(world, x, y, z) || block.isAirBlock(world, x, y, z)) return true;
		else return false;
	}
	
}
