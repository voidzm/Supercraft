package com.voidzm.supercraft.util;

import cpw.mods.fml.common.FMLLog;

public class StartupStats {

	private static int blocksCreated = 0;
	
	public static void blockCreated() {
		blocksCreated++;
	}
	
	public static void outputBlockStats() {
		FMLLog.info("[Supercraft] %d blocks successfully loaded.", blocksCreated);
	}
	
}
