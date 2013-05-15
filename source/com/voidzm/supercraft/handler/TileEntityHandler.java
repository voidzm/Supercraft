//**
//**  TileEntityHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.tileentity.TileEntity;

import com.voidzm.supercraft.tileentity.TileEntityAlloyInductor;
import com.voidzm.supercraft.tileentity.TileEntityConduit;
import com.voidzm.supercraft.tileentity.TileEntityElectroplationEngine;
import com.voidzm.supercraft.tileentity.TileEntityEssentialReducer;
import com.voidzm.supercraft.tileentity.TileEntityMonolithAscension;
import com.voidzm.supercraft.tileentity.TileEntityMonolithDemission;
import com.voidzm.supercraft.tileentity.TileEntityMonolithTermination;
import com.voidzm.supercraft.tileentity.TileEntitySolarGenerator;
import com.voidzm.supercraft.util.StartupStats;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void init() {
		registerTileEntities();
		StartupStats.outputTileEntityStats();
	}
	
	private static void registerTileEntities() {
		registerTileEntity(TileEntityConduit.class, "Supercraft.Conduit");
		registerTileEntity(TileEntityEssentialReducer.class, "Supercraft.EssentialReducer");
		registerTileEntity(TileEntitySolarGenerator.class, "Supercraft.SolarGenerator");
		registerTileEntity(TileEntityAlloyInductor.class, "Supercraft.AlloyInductor");
		registerTileEntity(TileEntityMonolithDemission.class, "Supercraft.MonolithDemission");
		registerTileEntity(TileEntityMonolithAscension.class, "Supercraft.MonolithAscension");
		registerTileEntity(TileEntityMonolithTermination.class, "Supercraft.MonolithTermination");
		registerTileEntity(TileEntityElectroplationEngine.class, "Supercraft.ElectroplationEngine");
	}
	
	private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
		GameRegistry.registerTileEntity(tileEntityClass, id);
		StartupStats.tileEntityCreated();
	}
	
}
