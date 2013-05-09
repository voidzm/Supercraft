package com.voidzm.supercraft.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.voidzm.supercraft.handler.BiomeHandler;

public class WorldGenIcySpikes extends WorldGenerator {

	public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		if(!(var1.provider.getBiomeGenForCoords(var3, var5).equals(BiomeHandler.glacialWasteland))) return false;
		if(var1.getBlockMaterial(var3, var4-1, var5) == Material.water) return false;
		for(int ix = -3; ix < 3; ix++) {
			for(int iz = -3; iz < 3; iz++) {
				if(var2.nextInt(3) == 0) {
					int factor = 6 - (Math.abs(ix) + Math.abs(iz));
					int height = factor + var2.nextInt(4);
					for(int iy = -2; iy < height; iy++) {
						this.setBlock(var1, var3+ix, var4+iy, var5+iz, Block.ice.blockID);
					}
				}
			}
		}
		return true;
	}
	
}
