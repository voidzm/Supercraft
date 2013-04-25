//**
//**  Supercraft.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft;

import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

import com.voidzm.supercraft.gen.WorldGenOre;
import com.voidzm.supercraft.handler.BiomeHandler;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.CraftingHandler;
import com.voidzm.supercraft.handler.DimensionHandler;
import com.voidzm.supercraft.handler.EventHandler;
import com.voidzm.supercraft.handler.FuelHandler;
import com.voidzm.supercraft.handler.GuiHandler;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.handler.PacketHandler;
import com.voidzm.supercraft.handler.ServerTickHandler;
import com.voidzm.supercraft.handler.TileEntityHandler;
import com.voidzm.supercraft.misc.CreativeTabElinvar;
import com.voidzm.supercraft.misc.CreativeTabVeneficia;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="Supercraft", name="Supercraft", version="0.3.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"SCElinvar", "SCMachineUpdates"}, packetHandler=PacketHandler.class)
public class Supercraft {

	@Instance("Supercraft")
	public static Supercraft instance;
	
	@SidedProxy(clientSide="com.voidzm.supercraft.client.ClientProxy", serverSide="com.voidzm.supercraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static SupercraftConfiguration configuration;
	
	public static EnumToolMaterial aluminumTool = EnumHelper.addToolMaterial("aluminum", 1, 2343, 4.0F, 1, 1);
	public static EnumToolMaterial copperTool = EnumHelper.addToolMaterial("copper", 2, 593, 9.0F, 2, 10);
	
	public static CreativeTabs elinvarTab = new CreativeTabElinvar("elinvarTab");
	public static CreativeTabs veneficiaTab = new CreativeTabVeneficia("veneficiaTab");
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		configuration = new SupercraftConfiguration(event.getSuggestedConfigurationFile());
	}
	
	@Init
	public void load(FMLInitializationEvent event) {		
		BlockHandler.init(configuration);
		ItemHandler.init(configuration);
		CraftingHandler.init(configuration);
		BiomeHandler.init(configuration);
		DimensionHandler.init(configuration);
		TileEntityHandler.init();
		EventHandler.init();
		FuelHandler.init();
		GuiHandler.init();
		WorldGenOre.init();
		
		proxy.registerRenderers();
		proxy.initTickHandler();
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
		
		StartupStats.loadingDone();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {}
	
}
