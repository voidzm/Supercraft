package com.voidzm.supercraft.dimension;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.handler.BiomeHandler;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderHell;

public class WorldProviderDeep extends WorldProvider {

	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeHandler.depths, 0.5F, 0.5F);
		this.isHellWorld = false;
		this.hasNoSky = true;
		this.dimensionId = Supercraft.configuration.thedeepID;
	}
	
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderDeep(this.worldObj, this.worldObj.getSeed());
	}
	
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.0F;
	}
	
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}
	
	public float[] calcSunriseSunsetColors(float par1, float par2) {
		return null;
	}
	
	public Vec3 getFogColor(float par1, float par2) {
		return this.worldObj.getWorldVec3Pool().getVecFromPool(0.1D, 0.1D, 0.1D);
	}
	
	public boolean isSkyColored() {
		return false;
	}
	
	public boolean canRespawnHere() {
		return false;
	}
	
	public boolean isSurfaceWorld() {
		return false;
	}
	
	@Override
	public String getDimensionName() {
		return "The Deep";
	}
	
	@Override
	public double getMovementFactor() {
		return 1.0;
	}

}
