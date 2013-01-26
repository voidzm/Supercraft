//////////////////////////////////////
//*        ClientProxy.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.render.ConduitRenderer;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int conduitRenderID;
	
	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCKS_PNG);
		conduitRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(conduitRenderID, new ConduitRenderer());
	}
	
}
