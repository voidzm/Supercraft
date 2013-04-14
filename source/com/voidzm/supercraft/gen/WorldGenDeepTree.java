package com.voidzm.supercraft.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.voidzm.supercraft.handler.BlockHandler;

public class WorldGenDeepTree extends WorldGenerator {

	private int logID = BlockHandler.supercraftLog2.blockID;
	private int leavesID = BlockHandler.radiantLeaves.blockID;

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		return this.generate(world, random, i, j, k, 0);
	}
	
	public boolean generate(World world, Random random, int x, int y, int z, int type) {
		boolean canGen = true;
		for(int ix = -1; ix < 2; ix++) {
			for(int iy = 1; iy < 3; iy++) {
				for(int iz = -1; iz < 2; iz++) {
					if(iy == 1 && !this.canOverwrite(world.getBlockId(x+ix, y+iy, z+iz))) canGen = false;
					if((iy == 2 && !(Math.abs(ix) == 1 && Math.abs(iz) == 1)) && !this.canOverwrite(world.getBlockId(x+ix, y+iy, z+iz))) canGen = false;
				}
			}
		}
		if(!canGen) return false;
		for(int ix = -1; ix < 2; ix++) {
			for(int iy = 0; iy < 3; iy++) {
				for(int iz = -1; iz < 2; iz++) {
					if(iy == 0 && (ix == 0 && iz == 0)) {
						this.setBlockAndMetadata(world, x+ix, y+iy, z+iz, logID, type);
					}
					if(iy == 1) {
						if(ix != 0 || iz != 0) this.setBlockAndMetadata(world, x+ix, y+iy, z+iz, leavesID, type);
						else this.setBlockAndMetadata(world, x+ix, y+iy, z+iz, logID, type);
					}
					if((iy == 2 && !(Math.abs(ix) == 1 && Math.abs(iz) == 1))) this.setBlockAndMetadata(world, x+ix, y+iy, z+iz, leavesID, type);
				}
			}
		}
		return true;
	}
	
	private boolean canOverwrite(int id) {
		if(id == 0 || id == this.leavesID) return true;
		return false;
	}
	
}
