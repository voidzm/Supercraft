//**
//**  Supercraft.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

import com.voidzm.supercraft.event.EventBonemeal;
import com.voidzm.supercraft.event.EventDepths;
import com.voidzm.supercraft.gen.WorldGenOre;
import com.voidzm.supercraft.handler.BiomeHandler;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.CraftingHandler;
import com.voidzm.supercraft.handler.DimensionHandler;
import com.voidzm.supercraft.handler.FuelHandler;
import com.voidzm.supercraft.handler.GuiHandler;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.handler.PacketHandler;
import com.voidzm.supercraft.handler.TileEntityHandler;
import com.voidzm.supercraft.misc.CreativeTabElinvar;
import com.voidzm.supercraft.util.SupercraftConfiguration;

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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="Supercraft", name="Supercraft", version="0.3.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"SCElinvar", "SCMachineUpdates"}, packetHandler=PacketHandler.class)
public class Supercraft {

	@Instance("Supercraft")
	public static Supercraft instance;
	
	@SidedProxy(clientSide="com.voidzm.supercraft.client.ClientProxy", serverSide="com.voidzm.supercraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static final FuelHandler fuelHandler = new FuelHandler();
	public static final GuiHandler guiHandler = new GuiHandler();
	
	public static SupercraftConfiguration configuration;
	
	public static EnumToolMaterial aluminumTool = EnumHelper.addToolMaterial("aluminum", 1, 2343, 4.0F, 1, 1);
	public static EnumToolMaterial copperTool = EnumHelper.addToolMaterial("copper", 2, 593, 9.0F, 2, 10);
	
	public static CreativeTabs elinvarTab = new CreativeTabElinvar("elinvarTab");
	
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
		
		proxy.registerRenderers();
		proxy.initializeGui();

		MinecraftForge.EVENT_BUS.register(new EventBonemeal());
		MinecraftForge.EVENT_BUS.register(new EventDepths());
		GameRegistry.registerWorldGenerator(new WorldGenOre());
		GameRegistry.registerFuelHandler(fuelHandler);
		NetworkRegistry.instance().registerGuiHandler(instance, guiHandler);
		LanguageRegistry.instance().addStringLocalization("itemGroup.elinvarTab", "Elinvar");
		System.out.println("[Supercraft] Loaded.");
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Nothing here yet.
	}
	
}
