package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.tileentity.TileEntityAlloyInductor;
import com.voidzm.supercraft.tileentity.TileEntityConduit;
import com.voidzm.supercraft.tileentity.TileEntityElectroplationEngine;
import com.voidzm.supercraft.tileentity.TileEntityEssentialReducer;
import com.voidzm.supercraft.tileentity.TileEntityMonolithAscension;
import com.voidzm.supercraft.tileentity.TileEntityMonolithDemission;
import com.voidzm.supercraft.tileentity.TileEntityMonolithTermination;
import com.voidzm.supercraft.tileentity.TileEntitySolarGenerator;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public void init() {
		this.registerTileEntities();
		System.out.println("[Supercraft] 8 tile entities added.");
	}
	
	private void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityConduit.class, "Supercraft.Conduit");
		GameRegistry.registerTileEntity(TileEntityEssentialReducer.class, "Supercraft.EssentialReducer");
		GameRegistry.registerTileEntity(TileEntitySolarGenerator.class, "Supercraft.SolarGenerator");
		GameRegistry.registerTileEntity(TileEntityAlloyInductor.class, "Supercraft.AlloyInductor");
		GameRegistry.registerTileEntity(TileEntityMonolithDemission.class, "Supercraft.MonolithDemission");
		GameRegistry.registerTileEntity(TileEntityMonolithAscension.class, "Supercraft.MonolithAscension");
		GameRegistry.registerTileEntity(TileEntityMonolithTermination.class, "Supercraft.MonolithTermination");
		GameRegistry.registerTileEntity(TileEntityElectroplationEngine.class, "Supercraft.ElectroplationEngine");
	}
	
}
