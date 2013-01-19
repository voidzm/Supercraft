//////////////////////////////////////
//*        BlockHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all blocks.

package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.block.BlockImpactGlass;
import com.voidzm.supercraft.block.BlockOrnateGlass;
import com.voidzm.supercraft.block.BlockReinforcedGlass;
import com.voidzm.supercraft.block.BlockTemperedGlass;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;

public class BlockHandler {

	protected LanguageHandler languageHandler = new LanguageHandler();
	
	public static Block reinforcedGlass;
	public static Block temperedGlass;
	public static Block ornateGlass;
	public static Block impactGlass;
	
	public void populateAllAndInitialize() {
		this.createBlocks();
		this.populateLanguage();
		this.registerBlocks();
		this.initializeLanguage();
		System.out.println("[Supercraft] " + languageHandler.count() + " blocks added.");
	}
	
	private void populateLanguage() {
		languageHandler.add(reinforcedGlass, "Reinforced Glass");
		languageHandler.add(temperedGlass, "Tempered Glass");
		languageHandler.add(ornateGlass, "Ornate Glass");
		languageHandler.add(impactGlass, "Impact Glass");
	}
	
	private void createBlocks() {
		reinforcedGlass = new BlockReinforcedGlass(1600);
		temperedGlass = new BlockTemperedGlass(1601);
		ornateGlass = new BlockOrnateGlass(1602);
		impactGlass = new BlockImpactGlass(1603);
	}
	
	private void registerBlocks() {
		GameRegistry.registerBlock(reinforcedGlass, "reinforcedGlass");
		GameRegistry.registerBlock(temperedGlass, "temperedGlass");
		GameRegistry.registerBlock(ornateGlass, "ornateGlass");
		GameRegistry.registerBlock(impactGlass, "impactGlass");
	}
	
	private void initializeLanguage() {
		LanguageRegistry.addName(reinforcedGlass, this.languageHandler.getString(reinforcedGlass));
		LanguageRegistry.addName(temperedGlass, this.languageHandler.getString(temperedGlass));
		LanguageRegistry.addName(ornateGlass, this.languageHandler.getString(ornateGlass));
		LanguageRegistry.addName(impactGlass, this.languageHandler.getString(impactGlass));
	}
	
}
