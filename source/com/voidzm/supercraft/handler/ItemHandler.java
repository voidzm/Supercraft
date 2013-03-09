//////////////////////////////////////
//*        ItemHandler.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all items.

package com.voidzm.supercraft.handler;

import java.util.ArrayList;

import com.voidzm.supercraft.item.*;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
	
	public static Item copperIngot;
	public static Item copperChunk;
	public static Item copperPickaxe;
	public static Item copperSword;
	public static Item copperShovel;
	public static Item copperAxe;
	public static Item copperHoe;
	
	public static Item silverIngot;
	
	public static Item electrumIngot;
	public static Item electrumBit;
	
	public static Item elinvarDust;
	
	public static Item bloodAmber;
	
	public static Item nisilIngot;
	public static Item nisilShard;
	
	public static Item essence;
	
	public static Item cobaltDust;
	public static Item metallicCobaltIngot;
	
	public static Item platinumIngot;
	public static Item lithiumIngot;
	
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
		
		// Copper Material
		
		languageHandler.add(copperIngot, "Copper Ingot");
		languageHandler.add(copperChunk, "Copper Chunk");
		languageHandler.add(copperPickaxe, "Copper Pickaxe");
		languageHandler.add(copperSword, "Copper Sword");
		languageHandler.add(copperShovel, "Copper Shovel");
		languageHandler.add(copperAxe, "Copper Axe");
		languageHandler.add(copperHoe, "Copper Hoe");
		
		// Silver Material
		
		languageHandler.add(silverIngot, "Silver Ingot");
		
		// Electrum Material
		
		languageHandler.add(electrumIngot, "Electrum Ingot");
		languageHandler.add(electrumBit, "Electrum Bit");
		
		// Elinvar Material
		
		languageHandler.add(elinvarDust, "Elinvar Dust");
		
		// Tenebral Woods
		
		languageHandler.add(bloodAmber, "Blood Amber");
		
		// Nisil
		
		languageHandler.add(nisilIngot, "Nisil Ingot");
		languageHandler.add(nisilShard, "Nisil Shard");
		
		// Essence

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
		
		// Cobalt
		
		languageHandler.add(cobaltDust, "Cobalt Dust");
		languageHandler.add(metallicCobaltIngot, "Metallic Cobalt Ingot");
		
		// Platinum
		
		languageHandler.add(platinumIngot, "Platinum Ingot");
		
		// Lithium
		
		languageHandler.add(lithiumIngot, "Lithium Ingot");
		
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
		
		// Copper Material
		
		copperIngot = new ItemCopperIngot(23010);
		copperChunk = new ItemCopperChunk(23011);
		copperPickaxe = new ItemCopperPickaxe(23012);
		copperSword = new ItemCopperSword(23013);
		copperShovel = new ItemCopperShovel(23014);
		copperAxe = new ItemCopperAxe(23015);
		copperHoe = new ItemCopperHoe(23016);
		
		// Silver Material
		
		silverIngot = new ItemSilverIngot(23017);
		
		// Electrum Material
		
		electrumIngot = new ItemElectrumIngot(23018);
		electrumBit = new ItemElectrumBit(23019);
		
		// Elinvar Material
		
		elinvarDust = new ItemElinvarDust(23009);
		
		// Tenebral Woods
		
		bloodAmber = new ItemBloodAmber(23020);
		
		// Nisil
		
		nisilIngot = new ItemNisilIngot(23021);
		nisilShard = new ItemNisilShard(23022);
		
		// Essence
		
		essence = new ItemEssence(23023);
		
		// Cobalt
		
		cobaltDust = new ItemCobaltDust(23024);
		metallicCobaltIngot = new ItemMetallicCobaltIngot(23025);
		
		// Platinum
		
		platinumIngot = new ItemPlatinumIngot(23026);
		
		// Lithium
		
		lithiumIngot = new ItemLithiumIngot(23027);
		
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
		
		// Copper Material
		
		GameRegistry.registerItem(copperIngot, "copperIngot");
		GameRegistry.registerItem(copperChunk, "copperChunk");
		GameRegistry.registerItem(copperPickaxe, "copperPickaxe");
		GameRegistry.registerItem(copperSword, "copperSword");
		GameRegistry.registerItem(copperShovel, "copperShovel");
		GameRegistry.registerItem(copperAxe, "copperAxe");
		GameRegistry.registerItem(copperHoe, "copperHoe");
		
		// Silver Material
		
		GameRegistry.registerItem(silverIngot, "silverIngot");
		
		// Electrum Material
		
		GameRegistry.registerItem(electrumIngot, "electrumIngot");
		GameRegistry.registerItem(electrumBit, "electrumBit");
		
		// Elinvar Material
		
		GameRegistry.registerItem(elinvarDust, "elinvarDust");
		
		// Tenebral Woods
		
		GameRegistry.registerItem(bloodAmber, "bloodAmber");
		
		// Nisil
		
		GameRegistry.registerItem(nisilIngot, "nisilIngot");
		GameRegistry.registerItem(nisilShard, "nisilShard");
		
		// Essence
		
		GameRegistry.registerItem(essence, "essence");
		
		// Cobalt
		
		GameRegistry.registerItem(cobaltDust, "cobaltDust");
		GameRegistry.registerItem(metallicCobaltIngot, "metallicCobaltIngot");
		
		// Platinum
		
		GameRegistry.registerItem(platinumIngot, "platinumIngot");
		
		// Lithium
		
		GameRegistry.registerItem(lithiumIngot, "lithiumIngot");
				
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
		
		// Copper Material
		
		LanguageRegistry.addName(copperIngot, (String)this.languageHandler.getString(copperIngot));
		LanguageRegistry.addName(copperChunk, (String)this.languageHandler.getString(copperChunk));
		LanguageRegistry.addName(copperPickaxe, (String)this.languageHandler.getString(copperPickaxe));
		LanguageRegistry.addName(copperSword, (String)this.languageHandler.getString(copperSword));
		LanguageRegistry.addName(copperShovel, (String)this.languageHandler.getString(copperShovel));
		LanguageRegistry.addName(copperAxe, (String)this.languageHandler.getString(copperAxe));
		LanguageRegistry.addName(copperHoe, (String)this.languageHandler.getString(copperHoe));
		
		// Silver Material
		
		LanguageRegistry.addName(silverIngot, (String)this.languageHandler.getString(silverIngot));
		
		// Electrum Material
		
		LanguageRegistry.addName(electrumIngot, (String)this.languageHandler.getString(electrumIngot));
		LanguageRegistry.addName(electrumBit, (String)this.languageHandler.getString(electrumBit));
		
		// Elinvar Material
		
		LanguageRegistry.addName(elinvarDust, (String)this.languageHandler.getString(elinvarDust));
		
		// Tenebral Woods
		
		LanguageRegistry.addName(bloodAmber, (String)this.languageHandler.getString(bloodAmber));
		
		// Nisil 
	
		LanguageRegistry.addName(nisilIngot, (String)this.languageHandler.getString(nisilIngot));
		LanguageRegistry.addName(nisilShard, (String)this.languageHandler.getString(nisilShard));
		
		// Essence
		
		int i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(essence))  {
			ItemStack stack = new ItemStack(essence, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		
		// Cobalt
		
		LanguageRegistry.addName(cobaltDust, (String)this.languageHandler.getString(cobaltDust));
		LanguageRegistry.addName(metallicCobaltIngot, (String)this.languageHandler.getString(metallicCobaltIngot));
		
		// Platinum
		
		LanguageRegistry.addName(platinumIngot, (String)this.languageHandler.getString(platinumIngot));
		
		// Lithium
		
		LanguageRegistry.addName(lithiumIngot, (String)this.languageHandler.getString(lithiumIngot));
		
	}
	
}
