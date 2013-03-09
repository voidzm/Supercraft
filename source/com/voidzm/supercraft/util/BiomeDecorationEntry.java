package com.voidzm.supercraft.util;

import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecorationEntry {

	public WorldGenerator generator;
	public int cyclesPerChunk;
	public boolean hasAutomaticHeight;
	public boolean doOffsets;
	public int minY;
	public int maxY;
	
	public BiomeDecorationEntry(WorldGenerator gen, int cycles, boolean offsets) {
		this(gen, cycles, true, offsets, 0, 0);
	}
	
	public BiomeDecorationEntry(WorldGenerator gen, int cycles, boolean offsets, int minY, int maxY) {
		this(gen, cycles, false, offsets, minY, maxY);
	}
	
	public BiomeDecorationEntry(WorldGenerator gen, int cycles, boolean hasAutoheight, boolean offsets, int minY, int maxY) {
		this.generator = gen;
		this.cyclesPerChunk = cycles;
		this.hasAutomaticHeight = hasAutoheight;
		this.doOffsets = offsets;
		this.minY = minY;
		this.maxY = maxY;
	}
	
}
