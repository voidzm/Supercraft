//////////////////////////////////////
//*        ItemHandler.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all items.

package com.voidzm.supercraft.handler;

import java.util.ArrayList;

import com.voidzm.supercraft.item.*;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHandler {

	protected LanguageHandler languageHandler = new LanguageHandler();
	protected SupercraftConfiguration config;
	
	public static Item ironScrap;
	public static Item diamondShard;
	
	public static Item aluminumIngot;
	public static Item tantalumCrystal;
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
	public static Item elinvarDust;
	public static Item cobaltDust;
	
	public static Item bloodAmber;
	public static Item essence;

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
	
	public void init(SupercraftConfiguration configObject) {
		if(this.config != null) {
			throw new RuntimeException("Itme handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for item handler initialization!");
		}
		this.config = configObject;
		this.createItems();
		this.populateLanguage();
		this.registerItems();
		this.initializeLanguage();
		System.out.println("[Supercraft] " + languageHandler.count() + " items added.");
	}
	
	private void populateLanguage() {
		languageHandler.add(ironScrap, "Iron Scrap");
		languageHandler.add(diamondShard, "Diamond Shard");

		languageHandler.add(aluminumIngot, "Aluminum Ingot");
		languageHandler.add(tantalumCrystal, "Tantalum Crystal");
		languageHandler.add(copperIngot, "Copper Ingot");
		languageHandler.add(copperChunk, "Copper Chunk");
		languageHandler.add(silverIngot, "Silver Ingot");
		languageHandler.add(silverFragment, "Silver Fragment");
		languageHandler.add(electrumIngot, "Electrum Ingot");
		languageHandler.add(electrumBit, "Electrum Bit");
		languageHandler.add(nisilIngot, "Nisil Ingot");
		languageHandler.add(nisilShard, "Nisil Shard");
		languageHandler.add(platinumIngot, "Platinum Ingot");
		languageHandler.add(lithiumIngot, "Lithium Ingot");
		languageHandler.add(metallicCobaltIngot, "Metallic Cobalt Ingot");
		languageHandler.add(elinvarDust, "Elinvar Dust");
		languageHandler.add(cobaltDust, "Cobalt Dust");

		languageHandler.add(bloodAmber, "Blood Amber");
		ArrayList<String> essenceList = new ArrayList<String>();
		essenceList.add("Terrean Essence");
		essenceList.add("Aeronic Essence");
		essenceList.add("Infernal Essence");
		essenceList.add("Aquaeous Essence");
		essenceList.add("Verdeal Essence");
		essenceList.add("Nethereal Essence");
		essenceList.add("Draconic Essence");
		essenceList.add("Ferric Essence");
		essenceList.add("Radantis Essence");
		essenceList.add("Vidalis Essence");
		essenceList.add("Mortalic Essence");
		essenceList.add("Luxorum Essence");
		essenceList.add("Tenebra Essence");
		languageHandler.add(essence, essenceList);

		languageHandler.add(aluminumPickaxe, "Aluminum Pickaxe");
		languageHandler.add(aluminumSaber, "Aluminum Saber");
		languageHandler.add(aluminumShovel, "Aluminum Shovel");
		languageHandler.add(aluminumAxe, "Aluminum Axe");
		languageHandler.add(aluminumHoe, "Aluminum Hoe");
		
		languageHandler.add(copperPickaxe, "Copper Pickaxe");
		languageHandler.add(copperSword, "Copper Sword");
		languageHandler.add(copperShovel, "Copper Shovel");
		languageHandler.add(copperAxe, "Copper Axe");
		languageHandler.add(copperHoe, "Copper Hoe");
		
		languageHandler.add(arcaneBucket, "Arcane Bucket");
		languageHandler.add(bucketGhostlyVapor, "Ghostly Vapor Bucket");
	}
	
	private void createItems() {
		ironScrap = new ItemSupercraft(this.config.ironscrapID, "ironScrap", "supercraft:ironscrap");
		diamondShard = new ItemSupercraft(this.config.diamondshardID, "diamondShard", "supercraft:diamondshard");

		aluminumIngot = new ItemSupercraft(this.config.ingotaluminumID, "aluminumIngot", "supercraft:ingotaluminum");
		tantalumCrystal = new ItemSupercraft(this.config.tantalumcrystalID, "tantalumCrystal", "supercraft:tantalumcrystal").setHasShimmerEffect(true);
		copperIngot = new ItemSupercraft(this.config.ingotcopperID, "copperIngot", "supercraft:ingotcopper");
		copperChunk = new ItemSupercraft(this.config.copperchunkID, "copperChunk", "supercraft:copperchunk");
		silverIngot = new ItemSupercraft(this.config.ingotsilverID, "silverIngot", "supercraft:ingotsilver");
		silverFragment = new ItemSupercraft(this.config.silverfragmentID, "silverFragment", "supercraft:silverfragment");
		electrumIngot = new ItemSupercraft(this.config.ingotelectrumID, "electrumIngot", "supercraft:ingotelectrum");
		electrumBit = new ItemElectrumBit(this.config.electrumbitID);
		nisilIngot = new ItemSupercraft(this.config.ingotnisilID, "nisilIngot", "supercraft:ingotnisil");
		nisilShard = new ItemSupercraft(this.config.nisilshardID, "nisilShard", "supercraft:nisilshard");
		platinumIngot = new ItemSupercraft(this.config.ingotplatinumID, "platinumIngot", "supercraft:ingotplatinum");
		lithiumIngot = new ItemSupercraft(this.config.ingotlithiumID, "lithiumIngot", "supercraft:ingotlithium");
		metallicCobaltIngot = new ItemSupercraft(this.config.ingotcobaltID, "metallicCobaltIngot", "supercraft:ingotcobalt");
		elinvarDust = new ItemSupercraft(this.config.elinvardustID, "elinvarDust", "supercraft:elinvardust");
		cobaltDust = new ItemSupercraft(this.config.cobaltdustID, "cobaltDust", "supercraft:cobaltdust");
		
		bloodAmber = new ItemBloodAmber(this.config.bloodamberID);
		essence = new ItemEssence(this.config.essenceID);
		
		aluminumPickaxe = new ItemAluminumPickaxe(this.config.pickaxealuminumID);
		aluminumSaber = new ItemAluminumSaber(this.config.swordaluminumID);
		aluminumShovel = new ItemAluminumShovel(this.config.shovelaluminumID);
		aluminumAxe = new ItemAluminumAxe(this.config.axealuminumID);
		aluminumHoe = new ItemAluminumHoe(this.config.hoealuminumID);

		copperPickaxe = new ItemCopperPickaxe(this.config.pickaxecopperID);
		copperSword = new ItemCopperSword(this.config.swordcopperID);
		copperShovel = new ItemCopperShovel(this.config.shovelcopperID);
		copperAxe = new ItemCopperAxe(this.config.axecopperID);
		copperHoe = new ItemCopperHoe(this.config.hoecopperID);
		
		arcaneBucket = new ItemArcaneBucket(this.config.arcanebucketID, 0, "supercraft:arcanebucket");
		bucketGhostlyVapor = new ItemArcaneBucket(this.config.bucketghostlyvaporID, BlockHandler.ghostlyVaporFlowing.blockID, "supercraft:bucketghostlyvapor");
	}
	
	private void registerItems() {
		GameRegistry.registerItem(ironScrap, "ironScrap");
		GameRegistry.registerItem(diamondShard, "diamondShard");

		GameRegistry.registerItem(aluminumIngot, "aluminumIngot");
		GameRegistry.registerItem(tantalumCrystal, "tantalumCrystal");
		GameRegistry.registerItem(copperIngot, "copperIngot");
		GameRegistry.registerItem(copperChunk, "copperChunk");
		GameRegistry.registerItem(silverIngot, "silverIngot");
		GameRegistry.registerItem(silverFragment, "silverFragment");
		GameRegistry.registerItem(electrumIngot, "electrumIngot");
		GameRegistry.registerItem(electrumBit, "electrumBit");
		GameRegistry.registerItem(nisilIngot, "nisilIngot");
		GameRegistry.registerItem(nisilShard, "nisilShard");
		GameRegistry.registerItem(platinumIngot, "platinumIngot");
		GameRegistry.registerItem(lithiumIngot, "lithiumIngot");
		GameRegistry.registerItem(metallicCobaltIngot, "metallicCobaltIngot");
		GameRegistry.registerItem(elinvarDust, "elinvarDust");
		GameRegistry.registerItem(cobaltDust, "cobaltDust");
		
		GameRegistry.registerItem(bloodAmber, "bloodAmber");
		GameRegistry.registerItem(essence, "essence");

		GameRegistry.registerItem(aluminumPickaxe, "aluminumPickaxe");
		GameRegistry.registerItem(aluminumSaber, "aluminumSaber");
		GameRegistry.registerItem(aluminumShovel, "aluminumShovel");
		GameRegistry.registerItem(aluminumAxe, "aluminumAxe");
		GameRegistry.registerItem(aluminumHoe, "aluminumHoe");
		
		GameRegistry.registerItem(copperPickaxe, "copperPickaxe");
		GameRegistry.registerItem(copperSword, "copperSword");
		GameRegistry.registerItem(copperShovel, "copperShovel");
		GameRegistry.registerItem(copperAxe, "copperAxe");
		GameRegistry.registerItem(copperHoe, "copperHoe");

		GameRegistry.registerItem(arcaneBucket, "arcaneBucket");
		GameRegistry.registerItem(bucketGhostlyVapor, "bucketGhostlyVapor");
	}
	
	private void initializeLanguage() {
		LanguageRegistry.addName(ironScrap, (String)this.languageHandler.getString(ironScrap));
		LanguageRegistry.addName(diamondShard, (String)this.languageHandler.getString(diamondShard));
		
		LanguageRegistry.addName(aluminumIngot, (String)this.languageHandler.getString(aluminumIngot));
		LanguageRegistry.addName(tantalumCrystal, (String)this.languageHandler.getString(tantalumCrystal));
		LanguageRegistry.addName(copperIngot, (String)this.languageHandler.getString(copperIngot));
		LanguageRegistry.addName(copperChunk, (String)this.languageHandler.getString(copperChunk));
		LanguageRegistry.addName(silverIngot, (String)this.languageHandler.getString(silverIngot));
		LanguageRegistry.addName(silverFragment, (String)this.languageHandler.getString(silverFragment));
		LanguageRegistry.addName(electrumIngot, (String)this.languageHandler.getString(electrumIngot));
		LanguageRegistry.addName(electrumBit, (String)this.languageHandler.getString(electrumBit));
		LanguageRegistry.addName(nisilIngot, (String)this.languageHandler.getString(nisilIngot));
		LanguageRegistry.addName(nisilShard, (String)this.languageHandler.getString(nisilShard));
		LanguageRegistry.addName(platinumIngot, (String)this.languageHandler.getString(platinumIngot));
		LanguageRegistry.addName(lithiumIngot, (String)this.languageHandler.getString(lithiumIngot));
		LanguageRegistry.addName(metallicCobaltIngot, (String)this.languageHandler.getString(metallicCobaltIngot));
		LanguageRegistry.addName(elinvarDust, (String)this.languageHandler.getString(elinvarDust));
		LanguageRegistry.addName(cobaltDust, (String)this.languageHandler.getString(cobaltDust));

		LanguageRegistry.addName(bloodAmber, (String)this.languageHandler.getString(bloodAmber));
		int i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(essence))  {
			ItemStack stack = new ItemStack(essence, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}

		LanguageRegistry.addName(aluminumPickaxe, (String)this.languageHandler.getString(aluminumPickaxe));
		LanguageRegistry.addName(aluminumSaber, (String)this.languageHandler.getString(aluminumSaber));
		LanguageRegistry.addName(aluminumShovel, (String)this.languageHandler.getString(aluminumShovel));
		LanguageRegistry.addName(aluminumAxe, (String)this.languageHandler.getString(aluminumAxe));
		LanguageRegistry.addName(aluminumHoe, (String)this.languageHandler.getString(aluminumHoe));
		
		LanguageRegistry.addName(copperPickaxe, (String)this.languageHandler.getString(copperPickaxe));
		LanguageRegistry.addName(copperSword, (String)this.languageHandler.getString(copperSword));
		LanguageRegistry.addName(copperShovel, (String)this.languageHandler.getString(copperShovel));
		LanguageRegistry.addName(copperAxe, (String)this.languageHandler.getString(copperAxe));
		LanguageRegistry.addName(copperHoe, (String)this.languageHandler.getString(copperHoe));
		
		LanguageRegistry.addName(arcaneBucket, (String)this.languageHandler.getString(arcaneBucket));
		LanguageRegistry.addName(bucketGhostlyVapor, (String)this.languageHandler.getString(bucketGhostlyVapor));
	}
	
}
