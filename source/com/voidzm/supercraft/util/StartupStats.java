package com.voidzm.supercraft.util;

import cpw.mods.fml.common.FMLLog;

public class StartupStats {

	private static int blocksCreated = 0;
	private static int itemsCreated = 0;
	private static int biomesCreated = 0;
	private static int tileEntitiesCreated = 0;
	
	public static void blockCreated() {
		blocksCreated++;
	}
	
	public static void outputBlockStats() {
		FMLLog.info("[Supercraft] %d blocks successfully loaded.", blocksCreated);
	}
	
	public static void itemCreated() {
		itemsCreated++;
	}
	
	public static void outputItemStats() {
		FMLLog.info("[Supercraft] %d items successfully loaded.", itemsCreated);
	}
	
	public static void outputCraftingStats(int number) {
		FMLLog.info("[Supercraft] %d recipes successfully loaded.", number);
	}
	
	public static void biomeCreated() {
		biomesCreated++;
	}
	
	public static void outputBiomeStats() {
		FMLLog.info("[Supercraft] %d biomes successfully loaded.", biomesCreated);
	}
	
	public static void outputDimensionStats() {
		FMLLog.info("[Supercraft] Dimensions successfully loaded.");
	}
	
	public static void tileEntityCreated() {
		tileEntitiesCreated++;
	}
	
	public static void outputTileEntityStats() {
		FMLLog.info("[Supercraft] %d tile entities successfully loaded.", tileEntitiesCreated);
	}
	
	public static void loadingDone() {
		FMLLog.info("[Supercraft] Loading complete.");
	}
	
}
