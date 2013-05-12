//**
//**  ClientProxy.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.client;

import net.minecraftforge.client.MinecraftForgeClient;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.render.InventoryConduitRender;
import com.voidzm.supercraft.render.InventoryVenianRodRender;
import com.voidzm.supercraft.render.TileConduitRender;
import com.voidzm.supercraft.render.VeneficianPodiumRender;
import com.voidzm.supercraft.tileentity.TileEntityConduit;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
	
	public static int conduitInvRenderID;
	public static int veneficianPodiumRenderID;
	
	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityConduit.class, new TileConduitRender());
		conduitInvRenderID = RenderingRegistry.getNextAvailableRenderId();
		veneficianPodiumRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new InventoryConduitRender());
		RenderingRegistry.registerBlockHandler(new VeneficianPodiumRender());
		MinecraftForgeClient.registerItemRenderer(ItemHandler.venianRod.itemID, new InventoryVenianRodRender());
	}
	
	@Override
	public void initTickHandler() {
		super.initTickHandler();
	}
	
}
