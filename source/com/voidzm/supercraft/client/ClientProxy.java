//**
//**  ClientProxy.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.client;

import net.minecraftforge.client.MinecraftForgeClient;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.render.InventoryConduitRender;
import com.voidzm.supercraft.render.InventoryVeneficiaCellRender;
import com.voidzm.supercraft.render.InventoryVeneficianRodRender;
import com.voidzm.supercraft.render.TileConduitRender;
import com.voidzm.supercraft.render.VeneficianPodiumRender;
import com.voidzm.supercraft.tileentity.TileEntityConduit;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

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
		MinecraftForgeClient.registerItemRenderer(ItemHandler.veneficianRod.itemID, new InventoryVeneficianRodRender());
		MinecraftForgeClient.registerItemRenderer(ItemHandler.veneficiaCell.itemID, new InventoryVeneficiaCellRender());
	}
	
	@Override
	public void initTickHandler() {
		super.initTickHandler();
	}
	
}
