//////////////////////////////////////
//*        Supercraft.java         *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft;

import com.voidzm.supercraft.event.EventBonemeal;
import com.voidzm.supercraft.gen.WorldGenOre;
import com.voidzm.supercraft.gui.SCMainMenu;
import com.voidzm.supercraft.handler.BiomeHandler;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.CraftingHandler;
import com.voidzm.supercraft.handler.FuelHandler;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.handler.SCTickHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="Supercraft", name="Supercraft", version="0.1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Supercraft {

	@Instance
	public static Supercraft instance;
	
	@SidedProxy(clientSide="com.voidzm.supercraft.client.ClientProxy", serverSide="com.voidzm.supercraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static GuiScreen mainMenu;
	
	public static final BlockHandler blockHandler = new BlockHandler();
	public static final ItemHandler itemHandler = new ItemHandler();
	public static final CraftingHandler craftingHandler = new CraftingHandler();
	public static final BiomeHandler biomeHandler = new BiomeHandler();
	public static final FuelHandler fuelHandler = new FuelHandler();
	public static final SCTickHandler tickHandler = new SCTickHandler();
	
	public static EnumToolMaterial aluminumTool = EnumHelper.addToolMaterial("aluminum", 1, 2343, 4.0F, 1, 1);
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Nothing here yet.
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		mainMenu = new SCMainMenu();
		blockHandler.populateAllAndInitialize();
		itemHandler.populateAllAndInitialize();
		craftingHandler.populateAllAndInitialize();
		biomeHandler.populateAllAndInitialize();
		
		//biomeHandler.removeVanillaBiomes(); // For biome testing only.
		
		GameRegistry.registerFuelHandler(fuelHandler);
		TickRegistry.registerTickHandler(tickHandler, Side.CLIENT);
		MinecraftForge.EVENT_BUS.register(new EventBonemeal());
		GameRegistry.registerWorldGenerator(new WorldGenOre());
		System.out.println("[Supercraft] Loaded.");
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Nothing here yet.
	}
	
}
