//**
//**  ItemHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.item.*;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;

public class ItemHandler {

	private static SupercraftConfiguration config;
	
	//**  Vanilla Materials  **//
	
	public static Item ironScrap;
	public static Item diamondShard;
	
	//**  Metals  **//
	
	public static Item aluminumIngot;
	public static Item copperIngot;
	public static Item copperChunk;
	public static Item silverIngot;
	public static Item silverFragment;
	public static Item electrumIngot;
	public static Item electrumBit;
	public static Item nisilIngot;
	public static Item nisilShard;
	public static Item platinumIngot;
	public static Item lithiumIngot;
	public static Item metallicCobaltIngot;
	
	//**  Tools  **//
	
	public static Item aluminumPickaxe;
	public static Item aluminumSaber;
	public static Item aluminumShovel;
	public static Item aluminumAxe;
	public static Item aluminumHoe;
	
	public static Item copperPickaxe;
	public static Item copperSword;
	public static Item copperShovel;
	public static Item copperAxe;
	public static Item copperHoe;
	
	public static Item arcaneBucket;
	public static Item bucketGhostlyVapor;
	
	//**  Dusts  **//
	
	public static Item elinvarDust;
	public static Item cobaltDust;
	public static Item incendiumDust;
	public static Item luxificenDust;
	public static Item essence;
	
	//**  Crystals and Other  **//
	
	public static Item tantalumCrystal;
	public static Item jadeCrystal;
	public static Item voltasniaRod;
	public static Item draconium;
	public static Item bloodAmber;

	public static void init(SupercraftConfiguration configObject) {
		if(config != null) {
			throw new RuntimeException("Item handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for item handler initialization!");
		}
		config = configObject;
		createItems();
		StartupStats.outputItemStats();
	}
	
	private static void createItems() {
		createVanillaMaterials();
		createMetals();
		createTools();
		createDusts();
		createCrystalsAndOther();
	}
	
	private static void createVanillaMaterials() {
		ironScrap = new ItemSupercraft(config.ironscrapID, "supercraft:ironscrap").setInternalName("ironscrap").setExternalName("Iron Scrap").register();
		diamondShard = new ItemSupercraft(config.diamondshardID, "supercraft:diamondshard").setInternalName("diamondshard").setExternalName("Diamond Shard").register();
	}
	
	private static void createMetals() {
		aluminumIngot = new ItemSupercraft(config.ingotaluminumID, "supercraft:ingotaluminum").setInternalName("ingotaluminum").setExternalName("Aluminum Ingot").register();
		copperIngot = new ItemSupercraft(config.ingotcopperID, "supercraft:ingotcopper").setInternalName("ingotcopper").setExternalName("Copper Ingot").register();
		copperChunk = new ItemSupercraft(config.copperchunkID, "supercraft:copperchunk").setInternalName("copperchunk").setExternalName("Copper Chunk").register();
		silverIngot = new ItemSupercraft(config.ingotsilverID, "supercraft:ingotsilver").setInternalName("ingotsilver").setExternalName("Silver Ingot").register();
		silverFragment = new ItemSupercraft(config.silverfragmentID, "supercraft:silverfragment").setInternalName("silverfragment").setExternalName("Silver Fragment").register();
		electrumIngot = new ItemSupercraft(config.ingotelectrumID, "supercraft:ingotelectrum").setInternalName("ingotelectrum").setExternalName("Electrum Ingot").register();
		electrumBit = new ItemElectrumBit(config.electrumbitID).register();	
		nisilIngot = new ItemSupercraft(config.ingotnisilID, "supercraft:ingotnisil").setInternalName("ingotnisil").setExternalName("Nisil Ingot").register();
		nisilShard = new ItemSupercraft(config.nisilshardID, "supercraft:nisilshard").setInternalName("nisilshard").setExternalName("Nisil Shard").register();
		platinumIngot = new ItemSupercraft(config.ingotplatinumID, "supercraft:ingotplatinum").setInternalName("ingotplatinum").setExternalName("Platinum Ingot").register();
		lithiumIngot = new ItemSupercraft(config.ingotlithiumID, "supercraft:ingotlithium").setInternalName("ingotlithium").setExternalName("Lithium Ingot").register();
		metallicCobaltIngot = new ItemSupercraft(config.ingotcobaltID, "supercraft:ingotcobalt").setInternalName("ingotcobalt").setExternalName("Metallic Cobalt Ingot").register();
	}
	
	private static void createTools() {
		aluminumPickaxe = new ItemAluminumPickaxe(config.pickaxealuminumID);
		ItemSupercraft.register(aluminumPickaxe, ((ItemAluminumPickaxe)aluminumPickaxe).getRegisterData());
		aluminumSaber = new ItemAluminumSaber(config.swordaluminumID);
		ItemSupercraft.register(aluminumSaber, ((ItemAluminumSaber)aluminumSaber).getRegisterData());
		aluminumShovel = new ItemAluminumShovel(config.shovelaluminumID);
		ItemSupercraft.register(aluminumShovel, ((ItemAluminumShovel)aluminumShovel).getRegisterData());
		aluminumAxe = new ItemAluminumAxe(config.axealuminumID);
		ItemSupercraft.register(aluminumAxe, ((ItemAluminumAxe)aluminumAxe).getRegisterData());
		aluminumHoe = new ItemAluminumHoe(config.hoealuminumID);
		ItemSupercraft.register(aluminumHoe, ((ItemAluminumHoe)aluminumHoe).getRegisterData());

		copperPickaxe = new ItemCopperPickaxe(config.pickaxecopperID);
		ItemSupercraft.register(copperPickaxe, ((ItemCopperPickaxe)copperPickaxe).getRegisterData());
		copperSword = new ItemCopperSword(config.swordcopperID);
		ItemSupercraft.register(copperSword, ((ItemCopperSword)copperSword).getRegisterData());
		copperShovel = new ItemCopperShovel(config.shovelcopperID);
		ItemSupercraft.register(copperShovel, ((ItemCopperShovel)copperShovel).getRegisterData());
		copperAxe = new ItemCopperAxe(config.axecopperID);
		ItemSupercraft.register(copperAxe, ((ItemCopperAxe)copperAxe).getRegisterData());
		copperHoe = new ItemCopperHoe(config.hoecopperID);
		ItemSupercraft.register(copperHoe, ((ItemCopperHoe)copperHoe).getRegisterData());
		
		arcaneBucket = new ItemArcaneBucket(config.arcanebucketID, 0, "supercraft:arcanebucket").register();
		bucketGhostlyVapor = new ItemArcaneBucket(config.bucketghostlyvaporID, BlockHandler.ghostlyVaporFlowing.blockID, "supercraft:bucketghostlyvapor").register();
	}
	
	private static void createDusts() {
		elinvarDust = new ItemSupercraft(config.elinvardustID, "supercraft:elinvardust").setInternalName("elinvardust").setExternalName("Elinvar Dust").register();
		cobaltDust = new ItemSupercraft(config.cobaltdustID, "supercraft:cobaltdust").setInternalName("cobaltdust").setExternalName("Cobalt Dust").register();
		incendiumDust = new ItemSupercraft(config.incendiumdustID, "supercraft:incendiumdust").setInternalName("incendiumdust").setExternalName("Incendium Dust").register();
		luxificenDust = new ItemSupercraft(config.luxificendustID, "supercraft:luxificendust").setInternalName("luxificendust").setExternalName("Luxificen Dust").register();
		essence = new ItemEssence(config.essenceID).register();
	}
	
	private static void createCrystalsAndOther() {
		tantalumCrystal = new ItemSupercraft(config.tantalumcrystalID, "supercraft:tantalumcrystal").setHasShimmerEffect(true).setInternalName("tantalumcrystal").setExternalName("Tantalum Crystal").register();
		jadeCrystal = new ItemSupercraft(config.jadecrystalID, "supercraft:jadecrystal").setInternalName("jadecrystal").setExternalName("Jade Crystal").register();
		voltasniaRod = new ItemSupercraft(config.voltasniarodID, "supercraft:voltasniarod").setInternalName("voltasniarod").setExternalName("Voltasnia Rod").register();
		draconium = new ItemSupercraft(config.draconiumID, "supercraft:draconium").setInternalName("draconium").setExternalName("Draconium").register();
		bloodAmber = new ItemBloodAmber(config.bloodamberID).register();
	}
	
}
