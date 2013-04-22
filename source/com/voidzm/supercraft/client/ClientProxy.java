//**
//**  ClientProxy.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.client;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.SCTickHandler;
import com.voidzm.supercraft.render.InventoryConduitRender;
import com.voidzm.supercraft.render.TileConduitRender;
import com.voidzm.supercraft.tileentity.TileEntityConduit;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
	
	public static int conduitInvRenderID;
	
	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityConduit.class, new TileConduitRender());
		conduitInvRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new InventoryConduitRender());
	}
	
	@Override
	public void initTickHandler() {
		TickRegistry.registerTickHandler(new SCTickHandler(), Side.CLIENT);
	}
	
}
