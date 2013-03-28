package com.voidzm.supercraft.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenRavine;

public class MapGenDeepFissures extends MapGenRavine {

	protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte) {
		if(this.rand.nextInt(75) == 0) {
			double d0 = (double)(par2 * 16 + this.rand.nextInt(16));
			double d1 = (double)(this.rand.nextInt(100) + 16);
			double d2 = (double)(par3 * 16 + this.rand.nextInt(16));
			byte b0 = 1;
			for(int i1 = 0; i1 < b0; ++i1) {
				float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float f2 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 4.0F;
				this.generateRavine(this.rand.nextLong(), par4, par5, par6ArrayOfByte, d0, d1, d2, f2, f, f1, 0, 0, 24.0D);
			}
		}
	}
	
}
