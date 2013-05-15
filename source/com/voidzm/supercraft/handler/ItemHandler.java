//**
//**  ItemHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.block.BlockSupercraftStone.SupercraftStoneType;
import com.voidzm.supercraft.item.ItemArcaneBucket;
import com.voidzm.supercraft.item.ItemArcaneRod;
import com.voidzm.supercraft.item.ItemEssence;
import com.voidzm.supercraft.item.ItemSupercraft;
import com.voidzm.supercraft.item.ItemSupercraftAxe;
import com.voidzm.supercraft.item.ItemSupercraftHoe;
import com.voidzm.supercraft.item.ItemSupercraftPickaxe;
import com.voidzm.supercraft.item.ItemSupercraftShovel;
import com.voidzm.supercraft.item.ItemSupercraftSword;
import com.voidzm.supercraft.item.ItemVenianRod;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;
import com.voidzm.supercraft.util.TransformationMatrix;

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
	
	public static Item lithiumPickaxe;
	public static Item lithiumSword;
	public static Item lithiumShovel;
	public static Item lithiumAxe;
	public static Item lithiumHoe;
	
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
	public static Item arcaneRod;
	public static Item venianRod;

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
		TransformationMatrix desertMatrix = new TransformationMatrix(BlockHandler.inscribedSandstone.blockID, 0, BlockHandler.sandstoneBricks.blockID, 0, Block.sand.blockID, 0, BlockHandler.blockOfDesert.blockID, 0, Block.sand.blockID, 0, Block.glass.blockID, 0);
		diamondShard = new ItemSupercraft(config.diamondshardID, "supercraft:diamondshard").setInternalName("diamondshard").setExternalName("Diamond Shard").makeTransforming(desertMatrix).register();
	}
	
	private static void createMetals() {
		aluminumIngot = new ItemSupercraft(config.ingotaluminumID, "supercraft:ingotaluminum").setInternalName("ingotaluminum").setExternalName("Aluminum Ingot").register();
		copperIngot = new ItemSupercraft(config.ingotcopperID, "supercraft:ingotcopper").setInternalName("ingotcopper").setExternalName("Copper Ingot").register();
		copperChunk = new ItemSupercraft(config.copperchunkID, "supercraft:copperchunk").setInternalName("copperchunk").setExternalName("Copper Chunk").register();
		silverIngot = new ItemSupercraft(config.ingotsilverID, "supercraft:ingotsilver").setInternalName("ingotsilver").setExternalName("Silver Ingot").register();
		silverFragment = new ItemSupercraft(config.silverfragmentID, "supercraft:silverfragment").setInternalName("silverfragment").setExternalName("Silver Fragment").register();
		electrumIngot = new ItemSupercraft(config.ingotelectrumID, "supercraft:ingotelectrum").setInternalName("ingotelectrum").setExternalName("Electrum Ingot").register();
		TransformationMatrix goldenwoodMatrix = new TransformationMatrix(BlockHandler.inscribedPalestone.blockID, 0, BlockHandler.overgrownPalestoneBricks.blockID, 0, BlockHandler.supercraftLog1.blockID, 1, BlockHandler.blockOfGoldenwood.blockID, 0, BlockHandler.burnedPalestoneBricks.blockID, 0, Block.cobblestone.blockID, 0);
		electrumBit = new ItemSupercraft(config.electrumbitID, "supercraft:electrumbit").setInternalName("electrumbit").setExternalName("Electrum Bit").makeTransforming(goldenwoodMatrix).register();
		nisilIngot = new ItemSupercraft(config.ingotnisilID, "supercraft:ingotnisil").setInternalName("ingotnisil").setExternalName("Nisil Ingot").register();
		nisilShard = new ItemSupercraft(config.nisilshardID, "supercraft:nisilshard").setInternalName("nisilshard").setExternalName("Nisil Shard").register();
		platinumIngot = new ItemSupercraft(config.ingotplatinumID, "supercraft:ingotplatinum").setInternalName("ingotplatinum").setExternalName("Platinum Ingot").register();
		lithiumIngot = new ItemSupercraft(config.ingotlithiumID, "supercraft:ingotlithium").setInternalName("ingotlithium").setExternalName("Lithium Ingot").register();
		metallicCobaltIngot = new ItemSupercraft(config.ingotcobaltID, "supercraft:ingotcobalt").setInternalName("ingotcobalt").setExternalName("Metallic Cobalt Ingot").register();
	}
	
	private static void createTools() {
		aluminumPickaxe = new ItemSupercraftPickaxe(config.pickaxealuminumID, Supercraft.aluminumTool, "pickaxealuminum", "Aluminum Pickaxe", "supercraft:pickaxealuminum");
		ItemSupercraft.register(aluminumPickaxe, ((ItemSupercraftPickaxe)aluminumPickaxe).getRegisterData());
		aluminumSaber = new ItemSupercraftSword(config.swordaluminumID, Supercraft.aluminumTool, "swordaluminum", "Aluminum Saber", "supercraft:swordaluminum");
		ItemSupercraft.register(aluminumSaber, ((ItemSupercraftSword)aluminumSaber).getRegisterData());
		aluminumShovel = new ItemSupercraftShovel(config.shovelaluminumID, Supercraft.aluminumTool, "shovelaluminum", "Aluminum Shovel", "supercraft:shovelaluminum");
		ItemSupercraft.register(aluminumShovel, ((ItemSupercraftShovel)aluminumShovel).getRegisterData());
		aluminumAxe = new ItemSupercraftAxe(config.axealuminumID, Supercraft.aluminumTool, "axealuminum", "Aluminum Axe", "supercraft:axealuminum");
		ItemSupercraft.register(aluminumAxe, ((ItemSupercraftAxe)aluminumAxe).getRegisterData());
		aluminumHoe = new ItemSupercraftHoe(config.hoealuminumID, Supercraft.aluminumTool, "hoealuminum", "Aluminum Hoe", "supercraft:hoealuminum");
		ItemSupercraft.register(aluminumHoe, ((ItemSupercraftHoe)aluminumHoe).getRegisterData());

		copperPickaxe = new ItemSupercraftPickaxe(config.pickaxecopperID, Supercraft.copperTool, "pickaxecopper", "Copper Pickaxe", "supercraft:pickaxecopper");
		ItemSupercraft.register(copperPickaxe, ((ItemSupercraftPickaxe)copperPickaxe).getRegisterData());
		copperSword = new ItemSupercraftSword(config.swordcopperID, Supercraft.copperTool, "swordcopper", "Copper Sword", "supercraft:swordcopper");
		ItemSupercraft.register(copperSword, ((ItemSupercraftSword)copperSword).getRegisterData());
		copperShovel = new ItemSupercraftShovel(config.shovelcopperID, Supercraft.copperTool, "shovelcopper", "Copper Shovel", "supercraft:shovelcopper");
		ItemSupercraft.register(copperShovel, ((ItemSupercraftShovel)copperShovel).getRegisterData());
		copperAxe = new ItemSupercraftAxe(config.axecopperID, Supercraft.copperTool, "axecopper", "Copper Axe", "supercraft:axecopper");
		ItemSupercraft.register(copperAxe, ((ItemSupercraftAxe)copperAxe).getRegisterData());
		copperHoe = new ItemSupercraftHoe(config.hoecopperID, Supercraft.copperTool, "hoecopper", "Copper Hoe", "supercraft:hoecopper");
		ItemSupercraft.register(copperHoe, ((ItemSupercraftHoe)copperHoe).getRegisterData());
		
		lithiumPickaxe = new ItemSupercraftPickaxe(config.pickaxelithiumID, Supercraft.lithiumTool, "pickaxelithium", "Lithium Pickaxe", "supercraft:pickaxelithium");
		ItemSupercraft.register(lithiumPickaxe, ((ItemSupercraftPickaxe)lithiumPickaxe).getRegisterData());
		lithiumSword = new ItemSupercraftSword(config.swordlithiumID, Supercraft.lithiumTool, "swordlithium", "Lithium Sword", "supercraft:swordlithium");
		ItemSupercraft.register(lithiumSword, ((ItemSupercraftSword)lithiumSword).getRegisterData());
		lithiumShovel = new ItemSupercraftShovel(config.shovellithiumID, Supercraft.lithiumTool, "shovellithium", "Lithium Shovel", "supercraft:shovellithium");
		ItemSupercraft.register(lithiumShovel, ((ItemSupercraftShovel)lithiumShovel).getRegisterData());
		lithiumAxe = new ItemSupercraftAxe(config.axelithiumID, Supercraft.lithiumTool, "axelithium", "Lithium Axe", "supercraft:axelithium");
		ItemSupercraft.register(lithiumAxe, ((ItemSupercraftAxe)lithiumAxe).getRegisterData());
		lithiumHoe = new ItemSupercraftHoe(config.hoelithiumID, Supercraft.lithiumTool, "hoelithium", "Lithium Hoe", "supercraft:hoelithium");
		ItemSupercraft.register(lithiumHoe, ((ItemSupercraftHoe)lithiumHoe).getRegisterData());
		
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
		TransformationMatrix skyMatrix = new TransformationMatrix(BlockHandler.inscribedEndstone.blockID, 0, BlockHandler.endstoneBricks.blockID, 0, Block.obsidian.blockID, 0, BlockHandler.blockOfSky.blockID, 0, Block.whiteStone.blockID, 0, Block.obsidian.blockID, 0);
		draconium = new ItemSupercraft(config.draconiumID, "supercraft:draconium").setInternalName("draconium").setExternalName("Draconium").makeTransforming(skyMatrix).register();
		TransformationMatrix tenebralMatrix = new TransformationMatrix(BlockHandler.inscribedNightrock.blockID, 0, BlockHandler.nightrockBricks.blockID, 0, BlockHandler.supercraftLog1.blockID, 2, BlockHandler.blockOfTenebral.blockID, 0, BlockHandler.burnedNightrockBricks.blockID, 0, Block.cobblestone.blockID, 0);
		bloodAmber = new ItemSupercraft(config.bloodamberID, "supercraft:bloodamber").setInternalName("bloodamber").setExternalName("Blood Amber").makeTransforming(tenebralMatrix).register();
		arcaneRod = new ItemArcaneRod(config.arcanerodID).register();
		venianRod = new ItemVenianRod(config.venianrodID).register();
	}
	
}
