//////////////////////////////////////
//*       WorldGenOlive.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.gen;

import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenOlive extends WorldGenerator {

	private int oliveLogID = BlockHandler.supercraftLog.blockID;
	private int oliveLeavesID = BlockHandler.supercraftLeaves1.blockID;
	
	public boolean generate(World var1, Random var2, int xloc, int yloc, int zloc) {
		return this.generate(var1, var2, xloc, yloc, zloc, true);
	}
	
	public boolean generate(World var1, Random var2, int xloc, int yloc, int zloc, boolean biomeCheck) {
		int wx = xloc, wy = yloc, wz = zloc;
		int t1 = var1.getBlockId(wx, wy-1, wz);
		Block b1 = Block.blocksList[t1];
		if((b1 == null || (t1 != Block.grass.blockID && t1 != Block.dirt.blockID)) || (!(var1.getBiomeGenForCoords(xloc, zloc).biomeName.equalsIgnoreCase("Savanna")) && biomeCheck)) {
			return false;
		}
		int t2 = var2.nextInt(3) + 1;
		int h;
		wy++;
		for(h = 0; h < t2-1; h++) {
			if(var1.getBlockId(wx, wy, wz) != 0) return false;
			wy++;
		}
		int a, b, c;
		for(a = -2; a < 3; a++) {
			for(b = -2; b < 3; b++) {
				for(c = 0; c < 5; c++) {
					if(var1.getBlockId(wx+a, wy+c, wz+b) != 0) return false;
				}
			}
		}
		wx = xloc;
		wy = yloc;
		wz = zloc;
		int i;
		for(i = 0; i < t2; i++) {
			this.setBlockAndMetadata(var1, wx, wy, wz, oliveLogID, 0);
			wy++;
		}
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLogID, 0);
		wx++;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLeavesID, 0);
		wx -= 2;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLeavesID, 0);
		wx++;
		wz++;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLeavesID, 0);
		wz -= 2;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLeavesID, 0);
		wz++;
		wy++;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLogID, 0);
		int j, k, px, pz;
		for(j = -2; j < 3; j++) {
			for(k = -2; k < 3; k++) {
				 px = wx+j;
				 pz = wz+k;
				 if((j==0 && k==0) || (Math.abs(j) == 2 && Math.abs(j) == Math.abs(k))) {
					 continue;
				 }
				 else this.setBlockAndMetadata(var1, px, wy, pz, oliveLeavesID, 0);
			}
		}
		wy++;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLogID, 0);
		wx++;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLeavesID, 0);
		wx -= 2;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLeavesID, 0);
		wx++;
		wz++;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLeavesID, 0);
		wz -= 2;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLeavesID, 0);
		wz++;
		wy++;
		this.setBlockAndMetadata(var1, wx, wy, wz, oliveLeavesID, 0);
		return true;
	}
	
}
