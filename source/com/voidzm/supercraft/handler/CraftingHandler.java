//////////////////////////////////////
//*       CraftingHandler.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all crafting recipes.

package com.voidzm.supercraft.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingHandler {

	// Official vanilla Minecraft
	
	private static ItemStack glass;
	private static ItemStack ironIngot;
	private static ItemStack goldNugget;
	private static ItemStack diamond;
	private static ItemStack obsidian;
	
	// Supercraft
	
	public void populateAllAndInitialize() {
		this.initializeCraftingRefs();
		this.addRecipes();
		System.out.println("[Supercraft] 4 recipes added.");
	}
	
	private void initializeCraftingRefs() {
		glass = new ItemStack(Block.glass);
		ironIngot = new ItemStack(Item.ingotIron);
		goldNugget = new ItemStack(Item.goldNugget);
		diamond = new ItemStack(Item.diamond);
		obsidian = new ItemStack(Block.obsidian);
	}
	
	private void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(BlockHandler.reinforcedGlass), "a a", " b ", "a a", 'a', ironIngot, 'b', glass);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.temperedGlass), "a a", " b ", "a a", 'a', goldNugget, 'b', glass);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.ornateGlass), "a a", " b ", "a a", 'a', diamond, 'b', glass);
		GameRegistry.addRecipe(new ItemStack(BlockHandler.impactGlass), "a a", " b ", "a a", 'a', obsidian, 'b', glass);
	}
	
}
