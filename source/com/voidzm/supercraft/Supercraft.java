//**
//**  Supercraft.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

import com.voidzm.supercraft.gen.WorldGenOre;
import com.voidzm.supercraft.handler.BiomeHandler;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.CraftingHandler;
import com.voidzm.supercraft.handler.DimensionHandler;
import com.voidzm.supercraft.handler.EventsHandler;
import com.voidzm.supercraft.handler.FuelHandler;
import com.voidzm.supercraft.handler.GuiHandler;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.handler.PacketHandler;
import com.voidzm.supercraft.handler.TileEntityHandler;
import com.voidzm.supercraft.misc.CreativeTabElinvar;
import com.voidzm.supercraft.misc.CreativeTabVeneficia;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="Supercraft", name="Supercraft", version=Supercraft.supercraftVersion)
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"SCC|Elinvar", "SCS|Veneficia"}, packetHandler=PacketHandler.class)
public class Supercraft {

	public static final String supercraftVersion = "0.5.0";
	public static final String minecraftVersion = "1.6.2";
	
	@Instance(value="Supercraft")
	public static Supercraft instance;
	
	@SidedProxy(clientSide="com.voidzm.supercraft.client.ClientProxy", serverSide="com.voidzm.supercraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static SupercraftConfiguration configuration;
	
	public static EnumToolMaterial aluminumTool = EnumHelper.addToolMaterial("aluminum", 1, 2343, 4.0F, 1.0F, 1);
	public static EnumToolMaterial copperTool = EnumHelper.addToolMaterial("copper", 2, 593, 9.0F, 2.0F, 10);
	public static EnumToolMaterial lithiumTool = EnumHelper.addToolMaterial("lithium", 3, 2, 255.0F, 128.0F, -1);
	
	public static CreativeTabs elinvarTab = new CreativeTabElinvar("elinvarTab");
	public static CreativeTabs veneficiaTab = new CreativeTabVeneficia("veneficiaTab");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		configuration = new SupercraftConfiguration(event.getSuggestedConfigurationFile());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		BlockHandler.init(configuration);
		ItemHandler.init(configuration);
		CraftingHandler.init(configuration);
		BiomeHandler.init(configuration);
		DimensionHandler.init(configuration);
		GuiHandler.init();
		TileEntityHandler.init();
		EventsHandler.init();
		FuelHandler.init();
		WorldGenOre.init();
		proxy.registerRenderers();
		proxy.initTickHandler();
		StartupStats.loadingDone();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
	
}
