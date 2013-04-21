package com.voidzm.supercraft.util;

import cpw.mods.fml.common.FMLLog;

public class StartupStats {

	private static int blocksCreated = 0;
	private static int itemsCreated = 0;
	
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
	
}
