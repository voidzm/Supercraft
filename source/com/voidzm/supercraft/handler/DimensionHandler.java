package com.voidzm.supercraft.handler;

import net.minecraftforge.common.DimensionManager;

import com.voidzm.supercraft.dimension.WorldProviderDeep;
import com.voidzm.supercraft.dimension.WorldProviderSurfaceAlternate;
import com.voidzm.supercraft.util.SupercraftConfiguration;

public class DimensionHandler {

	protected SupercraftConfiguration config;
	
	public void init(SupercraftConfiguration configObject) {
		if(this.config != null) {
			throw new RuntimeException("Dimension handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for dimension handler initialization!");
		}
		this.config = configObject;
		if(this.config.doalternatesurface) this.swapSurfaceProvider();
		this.createDimensions();
		System.out.println("[Supercraft] World providers added.");
	}
	
	private void swapSurfaceProvider() {
		DimensionManager.unregisterDimension(0);
		DimensionManager.registerProviderType(this.config.alternatesurfaceproviderID, WorldProviderSurfaceAlternate.class, true);
		DimensionManager.registerDimension(0, this.config.alternatesurfaceproviderID);
	}
	
	private void createDimensions() {
		DimensionManager.registerProviderType(this.config.thedeepproviderID, WorldProviderDeep.class, true);
		DimensionManager.registerDimension(this.config.thedeepID, this.config.thedeepproviderID);
	}
	
}
