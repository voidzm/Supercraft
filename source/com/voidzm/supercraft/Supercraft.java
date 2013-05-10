//**
//**  Supercraft.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft;

import java.util.Arrays;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
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

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.versioning.InvalidVersionSpecificationException;
import cpw.mods.fml.common.versioning.VersionRange;
import cpw.mods.fml.relauncher.Side;

public class Supercraft extends DummyModContainer {

	@Instance("Supercraft")
	public static Supercraft instance;
	
	@SidedProxy(clientSide="com.voidzm.supercraft.client.ClientProxy", serverSide="com.voidzm.supercraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static SupercraftConfiguration configuration;
	
	public static EnumToolMaterial aluminumTool = EnumHelper.addToolMaterial("aluminum", 1, 2343, 4.0F, 1, 1);
	public static EnumToolMaterial copperTool = EnumHelper.addToolMaterial("copper", 2, 593, 9.0F, 2, 10);
	
	public static CreativeTabs elinvarTab = new CreativeTabElinvar("elinvarTab");
	public static CreativeTabs veneficiaTab = new CreativeTabVeneficia("veneficiaTab");
	
	public static PacketHandler packetHandler = new PacketHandler();
	
	@Subscribe
	public void preInit(FMLPreInitializationEvent event) {
		configuration = new SupercraftConfiguration(event.getSuggestedConfigurationFile());
	}
	
	@Subscribe
	public void load(FMLInitializationEvent event) {
		System.out.println("Loading Supercraft!");
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
		NetworkRegistry.instance().registerChannel(packetHandler, "SCC|Elinvar");
		NetworkRegistry.instance().registerChannel(packetHandler, "SCS|Veneficia");
		
		proxy.registerRenderers();
		proxy.initTickHandler();
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
		
		StartupStats.loadingDone();
	}
	
	@Subscribe
	public void postInit(FMLPostInitializationEvent event) {}

	@Override
	public String getModId() {
		return "Supercraft";
	}

	@Override
	public String getName() {
		return "Supercraft";
	}

	@Override
	public String getVersion() {
		return "0.4.0";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Override
	public boolean isNetworkMod() {
		return true;
	}

	@Override
	public String getDisplayVersion() {
		return "0.4.0";
	}

	@Override
	public VersionRange acceptableMinecraftVersionRange() {
		try {
			return VersionRange.createFromVersionSpec("1.5.2");
		} catch (InvalidVersionSpecificationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ModMetadata getMetadata() {
		ModMetadata meta = new ModMetadata();
		meta.modId = "Supercraft";
		meta.name = "Supercraft";
		meta.version = "0.4.0";
		meta.authorList = Arrays.asList("voidzm");
		meta.description = "A Minecraft mod that extends many areas of the game with new mid and end-game content that fits with vanilla!";
		meta.credits = "Designed and coded by voidzm.";
		return meta;
	}
	
}
