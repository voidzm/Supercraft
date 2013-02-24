package com.voidzm.supercraft.dimension;

import com.voidzm.supercraft.biome.BiomeGenTenebralWoods;

import net.minecraft.world.WorldProviderSurface;

public class WorldProviderSurfaceAlternate extends WorldProviderSurface {

	public boolean doesXZShowFog(int par1, int par2) {
		if(this.getBiomeGenForCoords(par1, par2) instanceof BiomeGenTenebralWoods) return true;
		else return false;
	}
	
}
