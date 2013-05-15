package com.voidzm.supercraft.dimension;

import net.minecraft.world.WorldProviderSurface;

import com.voidzm.supercraft.handler.BiomeHandler;

public class WorldProviderSurfaceAlternate extends WorldProviderSurface {

	public boolean doesXZShowFog(int par1, int par2) {
		if(this.getBiomeGenForCoords(par1, par2).equals(BiomeHandler.tenebralWoods)) return true;
		else return false;
	}
	
}
