//**
//**  DimensionHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.DimensionManager;

import com.voidzm.supercraft.dimension.WorldProviderDeep;
import com.voidzm.supercraft.dimension.WorldProviderSurfaceAlternate;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;

public class DimensionHandler {

	private static SupercraftConfiguration config;
	
	public static void init(SupercraftConfiguration configObject) {
		if(config != null) {
			throw new RuntimeException("Dimension handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for dimension handler initialization!");
		}
		config = configObject;
		int initialWorldListSize = DimensionManager.getWorlds().length;
		if(config.doalternatesurface) swapSurfaceProvider();
		createDimensions();
		int worldsAdded = DimensionManager.getWorlds().length - initialWorldListSize;
		StartupStats.outputDimensionStats();
	}
	
	private static void swapSurfaceProvider() {
		DimensionManager.unregisterDimension(0);
		DimensionManager.registerProviderType(config.alternatesurfaceproviderID, WorldProviderSurfaceAlternate.class, true);
		DimensionManager.registerDimension(0, config.alternatesurfaceproviderID);
	}
	
	private static void createDimensions() {
		DimensionManager.registerProviderType(config.thedeepproviderID, WorldProviderDeep.class, true);
		DimensionManager.registerDimension(config.thedeepID, config.thedeepproviderID);
	}
	
}
