//////////////////////////////////////
//*        BlockHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all blocks.

package com.voidzm.supercraft.handler;

import java.util.ArrayList;

import com.voidzm.supercraft.block.BlockCoal;
import com.voidzm.supercraft.block.BlockImpactGlass;
import com.voidzm.supercraft.block.BlockOrnateGlass;
import com.voidzm.supercraft.block.BlockReinforcedGlass;
import com.voidzm.supercraft.block.BlockSupercraftLeaves;
import com.voidzm.supercraft.block.BlockSupercraftLog;
import com.voidzm.supercraft.block.BlockSupercraftPlanks;
import com.voidzm.supercraft.block.BlockSupercraftSapling;
import com.voidzm.supercraft.block.BlockSupercraftStairs;
import com.voidzm.supercraft.block.BlockSupercraftWoodSlab;
import com.voidzm.supercraft.block.BlockTemperedGlass;
import com.voidzm.supercraft.item.ItemSupercraftLeaves;
import com.voidzm.supercraft.item.ItemSupercraftLog;
import com.voidzm.supercraft.item.ItemSupercraftPlanks;
import com.voidzm.supercraft.item.ItemSupercraftSapling;
import com.voidzm.supercraft.item.ItemSupercraftWoodSlab;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BlockHandler {

	protected LanguageHandler languageHandler = new LanguageHandler();
	
	public static Block reinforcedGlass;
	public static Block temperedGlass;
	public static Block ornateGlass;
	public static Block impactGlass;
	
	public static Block coalBlock;
	
	public static Block supercraftLog;
	public static Block supercraftLeaves;
	public static Block supercraftPlanks;
	public static Block supercraftSapling;
	public static Block supercraftWoodSlab;
	
	public static Block oliveStairs;
	
	public void populateAllAndInitialize() {
		this.createBlocks();
		this.populateLanguage();
		this.registerBlocks();
		this.initializeLanguage();
		System.out.println("[Supercraft] " + languageHandler.count() + " blocks added.");
	}
	
	private void populateLanguage() {
		
		// Advanced Glass
		
		languageHandler.add(reinforcedGlass, "Reinforced Glass");
		languageHandler.add(temperedGlass, "Tempered Glass");
		languageHandler.add(ornateGlass, "Ornate Glass");
		languageHandler.add(impactGlass, "Impact Glass");
		
		// Material Extensions
		
		languageHandler.add(coalBlock, "Block of Coal");
		
		// Supercraft Trees
		
		ArrayList<String> supercraftLogList = new ArrayList<String>();
		supercraftLogList.add("Olive Wood");
		languageHandler.add(supercraftLog, supercraftLogList);
		ArrayList<String> supercraftLeavesList = new ArrayList<String>();
		supercraftLeavesList.add("Olive Leaves");
		languageHandler.add(supercraftLeaves, supercraftLeavesList);
		ArrayList<String> supercraftPlanksList = new ArrayList<String>();
		supercraftPlanksList.add("Olive Wood Planks");
		languageHandler.add(supercraftPlanks, supercraftPlanksList);
		ArrayList<String> supercraftSaplingList = new ArrayList<String>();
		supercraftSaplingList.add("Olive Sapling");
		languageHandler.add(supercraftSapling, supercraftSaplingList);
		ArrayList<String> supercraftWoodSlabList = new ArrayList<String>();
		supercraftWoodSlabList.add("Olive Wood Slab");
		languageHandler.add(supercraftWoodSlab, supercraftWoodSlabList);
		
		languageHandler.add(oliveStairs, "Olive Wood Stairs");
		
	}
	
	private void createBlocks() {
		
		// Advanced Glass
		
		reinforcedGlass = new BlockReinforcedGlass(1600);
		temperedGlass = new BlockTemperedGlass(1601);
		ornateGlass = new BlockOrnateGlass(1602);
		impactGlass = new BlockImpactGlass(1603);
		
		// Material Extensions
		
		coalBlock = new BlockCoal(1604);
		
		// Supercraft Trees
		
		supercraftLog = new BlockSupercraftLog(1605);
		supercraftLeaves = new BlockSupercraftLeaves(1606);
		supercraftPlanks = new BlockSupercraftPlanks(1607);
		supercraftSapling = new BlockSupercraftSapling(1608);
		supercraftWoodSlab = new BlockSupercraftWoodSlab(1609);
		
		oliveStairs = new BlockSupercraftStairs(1610, supercraftPlanks, 0).setBlockName("oliveStairs");
	
	}
	
	private void registerBlocks() {
		
		// Advanced Glass
		
		GameRegistry.registerBlock(reinforcedGlass, "reinforcedGlass");
		GameRegistry.registerBlock(temperedGlass, "temperedGlass");
		GameRegistry.registerBlock(ornateGlass, "ornateGlass");
		GameRegistry.registerBlock(impactGlass, "impactGlass");
		
		// Material Extensions
		
		GameRegistry.registerBlock(coalBlock, "coalBlock");
	
		// Supercraft Trees
		
		GameRegistry.registerBlock(supercraftLog, ItemSupercraftLog.class, "supercraftLog");
		GameRegistry.registerBlock(supercraftLeaves, ItemSupercraftLeaves.class, "supercraftLeaves");
		GameRegistry.registerBlock(supercraftPlanks, ItemSupercraftPlanks.class, "supercraftPlanks");
		GameRegistry.registerBlock(supercraftSapling, ItemSupercraftSapling.class, "supercraftSapling");
		GameRegistry.registerBlock(supercraftWoodSlab, ItemSupercraftWoodSlab.class, "supercraftWoodSlab");
		
		GameRegistry.registerBlock(oliveStairs, "oliveStairs");
		
		// Ore Dictionary references
		
		OreDictionary.registerOre("plankWood", supercraftPlanks);
		
	}
	
	private void initializeLanguage() {
		
		// Advanced Glass
		
		LanguageRegistry.addName(reinforcedGlass, (String)this.languageHandler.getString(reinforcedGlass));
		LanguageRegistry.addName(temperedGlass, (String)this.languageHandler.getString(temperedGlass));
		LanguageRegistry.addName(ornateGlass, (String)this.languageHandler.getString(ornateGlass));
		LanguageRegistry.addName(impactGlass, (String)this.languageHandler.getString(impactGlass));
		
		// Material Extensions
		
		LanguageRegistry.addName(coalBlock, (String)this.languageHandler.getString(coalBlock));
		
		// Supercraft Trees
	
		int i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftLog))  {
			ItemStack stack = new ItemStack(supercraftLog, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftLeaves))  {
			ItemStack stack = new ItemStack(supercraftLeaves, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftPlanks))  {
			ItemStack stack = new ItemStack(supercraftPlanks, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftSapling))  {
			ItemStack stack = new ItemStack(supercraftSapling, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftWoodSlab))  {
			ItemStack stack = new ItemStack(supercraftWoodSlab, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		
		LanguageRegistry.addName(oliveStairs, (String)this.languageHandler.getString(oliveStairs));
		
	}
	
}
