package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.entity.TileEntityAlloyInductor;
import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.entity.TileEntityEssentialReducer;
import com.voidzm.supercraft.entity.TileEntityMonolithAscension;
import com.voidzm.supercraft.entity.TileEntityMonolithDemission;
import com.voidzm.supercraft.entity.TileEntityMonolithTermination;
import com.voidzm.supercraft.entity.TileEntityRadiantSolarGenerator;
import com.voidzm.supercraft.entity.TileEntityWaveringLunarGenerator;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public void init() {
		this.registerTileEntities();
		System.out.println("[Supercraft] 6 tile entities added.");
	}
	
	private void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityConduit.class, "Supercraft.Conduit");
		GameRegistry.registerTileEntity(TileEntityEssentialReducer.class, "Supercraft.EssentialReducer");
		GameRegistry.registerTileEntity(TileEntityRadiantSolarGenerator.class, "Supercraft.RadiantSolarGenerator");
		GameRegistry.registerTileEntity(TileEntityWaveringLunarGenerator.class, "Supercraft.WaveringLunarGenerator");
		GameRegistry.registerTileEntity(TileEntityAlloyInductor.class, "Supercraft.AlloyInductor");
		GameRegistry.registerTileEntity(TileEntityMonolithDemission.class, "Supercraft.MonolithDemission");
		GameRegistry.registerTileEntity(TileEntityMonolithAscension.class, "Supercraft.MonolithAscension");
		GameRegistry.registerTileEntity(TileEntityMonolithTermination.class, "Supercraft.MonolithTermination");
	}
	
}
