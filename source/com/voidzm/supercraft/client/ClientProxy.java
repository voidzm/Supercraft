//////////////////////////////////////
//*        ClientProxy.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.MinecraftForgeClient;
import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.gui.SCMainMenu;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.SCTickHandler;
import com.voidzm.supercraft.render.InventoryConduitRender;
import com.voidzm.supercraft.render.TileConduitRender;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
	
	public static int conduitInvRenderID;
	
	public static GuiScreen mainMenu;
	public static final SCTickHandler tickHandler = new SCTickHandler();
	
	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCKS_PNG);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityConduit.class, new TileConduitRender());
		conduitInvRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new InventoryConduitRender());
	}
	
	@Override
	public void initializeGui() {
		mainMenu = new SCMainMenu();
		TickRegistry.registerTickHandler(tickHandler, Side.CLIENT);
	}
	
}
