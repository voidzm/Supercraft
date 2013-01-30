//////////////////////////////////////
//*        ClientProxy.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.render.TileConduitRender;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCKS_PNG);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityConduit.class, new TileConduitRender());
	}
	
}
