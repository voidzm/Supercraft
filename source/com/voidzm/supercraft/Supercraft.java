//////////////////////////////////////
//*        Supercraft.java         *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft;

import com.voidzm.supercraft.dimension.WorldProviderSurfaceAlternate;
import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.entity.TileEntityEssentialReducer;
import com.voidzm.supercraft.event.EventBonemeal;
import com.voidzm.supercraft.gen.WorldGenOre;
import com.voidzm.supercraft.gui.SCMainMenu;
import com.voidzm.supercraft.handler.BiomeHandler;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.CraftingHandler;
import com.voidzm.supercraft.handler.FuelHandler;
import com.voidzm.supercraft.handler.GuiHandler;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.handler.PacketHandler;
import com.voidzm.supercraft.handler.SCTickHandler;
import com.voidzm.supercraft.misc.CreativeTabElinvar;

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
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.ClientRegistry;
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
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="Supercraft", name="Supercraft", version="0.3.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"SCElinvar", "SCMachineUpdates"}, packetHandler=PacketHandler.class)
public class Supercraft {

	@Instance("Supercraft")
	public static Supercraft instance;
	
	@SidedProxy(clientSide="com.voidzm.supercraft.client.ClientProxy", serverSide="com.voidzm.supercraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static final BlockHandler blockHandler = new BlockHandler();
	public static final ItemHandler itemHandler = new ItemHandler();
	public static final CraftingHandler craftingHandler = new CraftingHandler();
	public static final BiomeHandler biomeHandler = new BiomeHandler();
	public static final FuelHandler fuelHandler = new FuelHandler();
	public static final GuiHandler guiHandler = new GuiHandler();
	
	public static EnumToolMaterial aluminumTool = EnumHelper.addToolMaterial("aluminum", 1, 2343, 4.0F, 1, 1);
	public static EnumToolMaterial copperTool = EnumHelper.addToolMaterial("copper", 2, 593, 9.0F, 2, 10);
	
	public static CreativeTabs elinvarTab = new CreativeTabElinvar("elinvarTab");
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Nothing here yet.
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		blockHandler.populateAllAndInitialize();
		proxy.registerRenderers();
		proxy.initializeGui();
		itemHandler.populateAllAndInitialize();
		craftingHandler.populateAllAndInitialize();
		biomeHandler.populateAllAndInitialize();

		// biomeHandler.removeVanillaBiomes(); // For biome testing only.
		
		GameRegistry.registerFuelHandler(fuelHandler);
		DimensionManager.unregisterDimension(0);
		DimensionManager.registerProviderType(16, WorldProviderSurfaceAlternate.class, true);
		DimensionManager.registerDimension(0, 16);
		MinecraftForge.EVENT_BUS.register(new EventBonemeal());
		GameRegistry.registerWorldGenerator(new WorldGenOre());
		GameRegistry.registerTileEntity(TileEntityConduit.class, "tileEntityConduit");
		GameRegistry.registerTileEntity(TileEntityEssentialReducer.class, "tileEntityEssentialReducer");
		NetworkRegistry.instance().registerGuiHandler(instance, guiHandler);
		LanguageRegistry.instance().addStringLocalization("itemGroup.elinvarTab", "Elinvar");
		System.out.println("[Supercraft] Loaded.");
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Nothing here yet.
	}
	
}
