//////////////////////////////////////
//*        ItemHandler.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all items.

package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.item.ItemAluminumAxe;
import com.voidzm.supercraft.item.ItemAluminumHoe;
import com.voidzm.supercraft.item.ItemAluminumIngot;
import com.voidzm.supercraft.item.ItemAluminumPickaxe;
import com.voidzm.supercraft.item.ItemAluminumSaber;
import com.voidzm.supercraft.item.ItemAluminumShovel;
import com.voidzm.supercraft.item.ItemDiamondShard;
import com.voidzm.supercraft.item.ItemElinvarDust;
import com.voidzm.supercraft.item.ItemIronScrap;
import com.voidzm.supercraft.item.ItemTantalumCrystal;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ItemHandler {

	protected LanguageHandler languageHandler = new LanguageHandler();
	
	public static Item ironScrap;
	public static Item diamondShard;
	
	public static Item aluminumIngot;
	public static Item aluminumPickaxe;
	public static Item aluminumSaber;
	public static Item aluminumShovel;
	public static Item aluminumAxe;
	public static Item aluminumHoe;
	
	public static Item tantalumCrystal;
	public static Item elinvarDust;
	
	public void populateAllAndInitialize() {
		this.createItems();
		this.populateLanguage();
		this.registerItems();
		this.initializeLanguage();
		System.out.println("[Supercraft] " + languageHandler.count() + " items added.");
	}
	
	private void populateLanguage() {
		
		// Material Extensions
		
		languageHandler.add(ironScrap, "Iron Scrap");
		languageHandler.add(diamondShard, "Diamond Shard");
		
		// Aluminum Material
		
		languageHandler.add(aluminumIngot, "Aluminum Ingot");
		languageHandler.add(aluminumPickaxe, "Aluminum Pickaxe");
		languageHandler.add(aluminumSaber, "Aluminum Saber");
		languageHandler.add(aluminumShovel, "Aluminum Shovel");
		languageHandler.add(aluminumAxe, "Aluminum Axe");
		languageHandler.add(aluminumHoe, "Aluminum Hoe");
		
		// Tantalum Material
		
		languageHandler.add(tantalumCrystal, "Tantalum Crystal");
		
		// Elinvar Material
		
		languageHandler.add(elinvarDust, "Elinvar Dust");
		
	}
	
	private void createItems() {
		
		// Material Extensions
		
		ironScrap = new ItemIronScrap(23000);
		diamondShard = new ItemDiamondShard(23001);
		
		// Aluminum Material
		
		aluminumIngot = new ItemAluminumIngot(23002);
		aluminumPickaxe = new ItemAluminumPickaxe(23003);
		aluminumSaber = new ItemAluminumSaber(23004);
		aluminumShovel = new ItemAluminumShovel(23005);
		aluminumAxe = new ItemAluminumAxe(23006);
		aluminumHoe = new ItemAluminumHoe(23007);
		
		// Tantalum Material
		
		tantalumCrystal = new ItemTantalumCrystal(23008);
		
		// Elinvar Material
		
		elinvarDust = new ItemElinvarDust(23009);
		
	}
	
	private void registerItems() {
		
		// Material Extensions
		
		GameRegistry.registerItem(ironScrap, "ironScrap");
		GameRegistry.registerItem(diamondShard, "diamondShard");
		
		// Aluminum Material
		
		GameRegistry.registerItem(aluminumIngot, "aluminumIngot");
		GameRegistry.registerItem(aluminumPickaxe, "aluminumPickaxe");
		GameRegistry.registerItem(aluminumSaber, "aluminumSaber");
		GameRegistry.registerItem(aluminumShovel, "aluminumShovel");
		GameRegistry.registerItem(aluminumAxe, "aluminumAxe");
		GameRegistry.registerItem(aluminumHoe, "aluminumHoe");
		
		// Tantalum Material
		
		GameRegistry.registerItem(tantalumCrystal, "tantalumCrystal");
		
		// Elinvar Material
		
		GameRegistry.registerItem(elinvarDust, "elinvarDust");
		
		// Ore Dictionary references
		
		OreDictionary.registerOre("aluminumIngot", aluminumIngot);
		
	}
	
	private void initializeLanguage() {
		
		// Material Extensions
		
		LanguageRegistry.addName(ironScrap, (String)this.languageHandler.getString(ironScrap));
		LanguageRegistry.addName(diamondShard, (String)this.languageHandler.getString(diamondShard));
		
		// Aluminum Material
		
		LanguageRegistry.addName(aluminumIngot, (String)this.languageHandler.getString(aluminumIngot));
		LanguageRegistry.addName(aluminumPickaxe, (String)this.languageHandler.getString(aluminumPickaxe));
		LanguageRegistry.addName(aluminumSaber, (String)this.languageHandler.getString(aluminumSaber));
		LanguageRegistry.addName(aluminumShovel, (String)this.languageHandler.getString(aluminumShovel));
		LanguageRegistry.addName(aluminumAxe, (String)this.languageHandler.getString(aluminumAxe));
		LanguageRegistry.addName(aluminumHoe, (String)this.languageHandler.getString(aluminumHoe));
		
		// Tantalum Material
		
		LanguageRegistry.addName(tantalumCrystal, (String)this.languageHandler.getString(tantalumCrystal));
		
		// Elinvar Material
		
		LanguageRegistry.addName(elinvarDust, (String)this.languageHandler.getString(elinvarDust));
		
	}
	
}
