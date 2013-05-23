//**
//**  CraftingHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.voidzm.supercraft.util.EssentialReducerRecipes.EssentialAspect;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingHandler {

	private static SupercraftConfiguration config;
	
	public static void init(SupercraftConfiguration configObject) {
		if(config != null) {
			throw new RuntimeException("Crafting handler already loaded with configuration, cannot initialize again!"); 
		}
		if(configObject == null) {
			throw new RuntimeException("Configuration required for crafting handler initialization!");
		}
		config = configObject;
		int initialRecipeListSize = CraftingManager.getInstance().getRecipeList().size();
		createRecipes();
		createSmelting();
		initOreDictionary();
		int recipesAdded = CraftingManager.getInstance().getRecipeList().size() - initialRecipeListSize;
		StartupStats.outputCraftingStats(recipesAdded);
	}
	
	private static void createRecipes() {
		createGlassRecipes();
		createSlabRecipes();
		createStairRecipes();
		createSubmaterialToMaterialRecipes();
		createMaterialToBlockRecipes();
		createBlockToMaterialRecipes();
		createMaterialToSubmaterialRecipes();
		createToolRecipes();
		createConduitRecipes();
		createElinvarRecipes();
		createBlockRecipes();
		createDecorRecipes();
		createMonolithRecipes();
		createWoodAndPlantRecipes();
	}
	
	private static void createGlassRecipes() {
		GameRegistry.addRecipe(new ItemStack(BlockHandler.reinforcedGlass), new Object[] {"i i", " G ", "i i", 'i', ItemHandler.ironScrap, 'G', Block.glass});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.temperedGlass), new Object[] {"g g", " G ", "g g", 'g', Item.goldNugget, 'G', Block.glass});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.ornateGlass), new Object[] {"d d", " G ", "d d", 'd', ItemHandler.diamondShard, 'G', Block.glass});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.impactGlass), new Object[] {"O O", " G ", "O O", 'O', Block.obsidian, 'G', Block.glass});
	}
	
	private static void createSlabRecipes() {
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 0), new Object[] {"OOO", 'O', new ItemStack(BlockHandler.supercraftPlanks1, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 1), new Object[] {"GGG", 'G', new ItemStack(BlockHandler.supercraftPlanks1, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 2), new Object[] {"TTT", 'T', new ItemStack(BlockHandler.supercraftPlanks1, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 3), new Object[] {"III", 'I', new ItemStack(BlockHandler.supercraftPlanks1, 1, 3)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 4), new Object[] {"VVV", 'V', new ItemStack(BlockHandler.supercraftPlanks1, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab1, 6, 5), new Object[] {"MMM", 'M', new ItemStack(BlockHandler.supercraftPlanks1, 1, 5)});
	
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab2, 6, 0), new Object[] {"PPP", 'P', BlockHandler.palestoneBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab2, 6, 1), new Object[] {"NNN", 'N', BlockHandler.nightrockBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab2, 6, 2), new Object[] {"SSS", 'S', BlockHandler.sandstoneBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.supercraftSlab2, 6, 3), new Object[] {"EEE", 'E', BlockHandler.endstoneBricks});
	}
	
	private static void createStairRecipes() {
		GameRegistry.addRecipe(new ItemStack(BlockHandler.oliveStairs, 4), new Object[] {"O  ", "OO ", "OOO", 'O', new ItemStack(BlockHandler.supercraftPlanks1, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.oliveStairs, 4), new Object[] {"  O", " OO", "OOO", 'O', new ItemStack(BlockHandler.supercraftPlanks1, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.goldenwoodStairs, 4), new Object[] {"G  ", "GG ", "GGG", 'G', new ItemStack(BlockHandler.supercraftPlanks1, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.goldenwoodStairs, 4), new Object[] {"  G", " GG", "GGG", 'G', new ItemStack(BlockHandler.supercraftPlanks1, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.tenebriaStairs, 4), new Object[] {"T  ", "TT ", "TTT", 'T', new ItemStack(BlockHandler.supercraftPlanks1, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.tenebriaStairs, 4), new Object[] {"  T", " TT", "TTT", 'T', new ItemStack(BlockHandler.supercraftPlanks1, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inisiaStairs, 4), new Object[] {"I  ", "II ", "III", 'I', new ItemStack(BlockHandler.supercraftPlanks1, 1, 3)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inisiaStairs, 4), new Object[] {"  I", " II", "III", 'I', new ItemStack(BlockHandler.supercraftPlanks1, 1, 3)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.valensienStairs, 4), new Object[] {"V  ", "VV ", "VVV", 'V', new ItemStack(BlockHandler.supercraftPlanks1, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.valensienStairs, 4), new Object[] {"  V", " VV", "VVV", 'V', new ItemStack(BlockHandler.supercraftPlanks1, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.mortaliaStairs, 4), new Object[] {"M  ", "MM ", "MMM", 'M', new ItemStack(BlockHandler.supercraftPlanks1, 1, 5)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.mortaliaStairs, 4), new Object[] {"  M", " MM", "MMM", 'M', new ItemStack(BlockHandler.supercraftPlanks1, 1, 5)});
	
		GameRegistry.addRecipe(new ItemStack(BlockHandler.palestoneStairs, 4), new Object[] {"P  ", "PP ", "PPP", 'P', BlockHandler.palestoneBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.palestoneStairs, 4), new Object[] {"  P", " PP", "PPP", 'P', BlockHandler.palestoneBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.nightrockStairs, 4), new Object[] {"N  ", "NN ", "NNN", 'N', BlockHandler.nightrockBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.nightrockStairs, 4), new Object[] {"  N", " NN", "NNN", 'N', BlockHandler.nightrockBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.sandstonebrickStairs, 4), new Object[] {"S  ", "SS ", "SSS", 'S', BlockHandler.sandstoneBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.sandstonebrickStairs, 4), new Object[] {"  S", " SS", "SSS", 'S', BlockHandler.sandstoneBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.endstonebrickStairs, 4), new Object[] {"E  ", "EE ", "EEE", 'E', BlockHandler.endstoneBricks});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.endstonebrickStairs, 4), new Object[] {"  E", " EE", "EEE", 'E', BlockHandler.endstoneBricks});
	}
	
	private static void createSubmaterialToMaterialRecipes() {
		GameRegistry.addRecipe(new ItemStack(Item.ingotIron), new Object[] {"iii", "iii", "iii", 'i', ItemHandler.ironScrap});
		GameRegistry.addRecipe(new ItemStack(Item.diamond), new Object[] {"ddd", "ddd", "ddd", 'd', ItemHandler.diamondShard});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperIngot), new Object[] {"ccc", "ccc", "ccc", 'c', ItemHandler.copperChunk});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.silverIngot), new Object[] {"sss", "sss", "sss", 's', ItemHandler.silverFragment});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.electrumIngot), new Object[] {"eee", "eee", "eee", 'e', ItemHandler.electrumBit});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.nisilIngot), new Object[] {"nnn", "nnn", "nnn", 'n', ItemHandler.nisilShard});
	}
	
	private static void createMaterialToBlockRecipes() {
		if(config.docoalblockrecipe) GameRegistry.addRecipe(new ItemStack(BlockHandler.coalBlock), new Object[] {"ccc", "ccc", "ccc", 'c', Item.coal});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.aluminumBlock), new Object[] {"aaa", "aaa", "aaa", 'a', ItemHandler.aluminumIngot});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.copperBlock), new Object[] {"ccc", "ccc", "ccc", 'c', ItemHandler.copperIngot});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.silverBlock), new Object[] {"sss", "sss", "sss", 's', ItemHandler.silverIngot});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.electrumBlock), new Object[] {"eee", "eee", "eee", 'e', ItemHandler.electrumIngot});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.elinvarBlock), new Object[] {"eee", "eee", "eee", 'e', ItemHandler.elinvarDust});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.nisilBlock), new Object[] {"nnn", "nnn", "nnn", 'n', ItemHandler.nisilIngot});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.cobaltBlock), new Object[] {"ccc", "ccc", "ccc", 'c', ItemHandler.cobaltDust});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.tantalumBlock), new Object[] {"ttt", "ttt", "ttt", 't', ItemHandler.tantalumCrystal});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.platinumBlock), new Object[] {"ppp", "ppp", "ppp", 'p', ItemHandler.platinumIngot});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.lithiumBlock), new Object[] {"lll", "lll", "lll", 'l', ItemHandler.lithiumIngot});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.draconiumBlock), new Object[] {"ddd", "ddd", "ddd", 'd', ItemHandler.draconium});
	}
	
	private static void createBlockToMaterialRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(Item.coal, 9), BlockHandler.coalBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.aluminumIngot, 9), BlockHandler.aluminumBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.copperIngot, 9), BlockHandler.copperBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.silverIngot, 9), BlockHandler.silverBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.electrumIngot, 9), BlockHandler.electrumBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.elinvarDust, 9), BlockHandler.elinvarBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.nisilIngot, 9), BlockHandler.nisilBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.cobaltDust, 9), BlockHandler.cobaltBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.tantalumCrystal, 9), BlockHandler.tantalumBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.platinumIngot, 9), BlockHandler.platinumBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.lithiumIngot, 9), BlockHandler.lithiumBlock);	
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.draconium, 9), BlockHandler.draconiumBlock);
	}
	
	private static void createMaterialToSubmaterialRecipes() {
		if(config.doironscraprecipe) GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.ironScrap, 9), Item.ingotIron);
		if(config.dodiamondshardrecipe) GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.diamondShard, 9), Item.diamond);	
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.copperChunk, 9), ItemHandler.copperIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.silverFragment, 9), ItemHandler.silverIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.electrumBit, 9), ItemHandler.electrumIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.nisilShard, 9), ItemHandler.nisilIngot);
	}
	
	private static void createToolRecipes() {
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumPickaxe), new Object[] {"aaa", " s ", " s ", 'a', ItemHandler.aluminumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumSaber), new Object[] {"a", "a", "s", 'a', ItemHandler.aluminumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumShovel), new Object[] {"a", "s", "s", 'a', ItemHandler.aluminumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumAxe), new Object[] {"aa", "as", " s", 'a', ItemHandler.aluminumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumAxe), new Object[] {"aa", "sa", "s ", 'a', ItemHandler.aluminumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumHoe), new Object[] {"aa", " s", " s", 'a', ItemHandler.aluminumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.aluminumHoe), new Object[] {"aa", "s ", "s ", 'a', ItemHandler.aluminumIngot, 's', Item.stick});

		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperPickaxe), new Object[] {"ccc", " s ", " s ", 'c', ItemHandler.copperIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperSword), new Object[] {"c", "c", "s", 'c', ItemHandler.copperIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperShovel), new Object[] {"c", "s", "s", 'c', ItemHandler.copperIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperAxe), new Object[] {"cc", "cs", " s", 'c', ItemHandler.copperIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperAxe), new Object[] {"cc", "sc", "s ", 'c', ItemHandler.copperIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperHoe), new Object[] {"cc", " s", " s", 'c', ItemHandler.copperIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.copperHoe), new Object[] {"cc", "s ", "s ", 'c', ItemHandler.copperIngot, 's', Item.stick});
		
		GameRegistry.addRecipe(new ItemStack(ItemHandler.lithiumPickaxe), new Object[] {"lll", " s ", " s ", 'l', ItemHandler.lithiumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.lithiumSword), new Object[] {"l", "l", "s", 'l', ItemHandler.lithiumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.lithiumShovel), new Object[] {"l", "s", "s", 'l', ItemHandler.lithiumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.lithiumAxe), new Object[] {"ll", "ls", " s", 'l', ItemHandler.lithiumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.lithiumAxe), new Object[] {"ll", "sl", "s ", 'l', ItemHandler.lithiumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.lithiumHoe), new Object[] {"ll", " s", " s", 'l', ItemHandler.lithiumIngot, 's', Item.stick});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.lithiumHoe), new Object[] {"ll", "s ", "s ", 'l', ItemHandler.lithiumIngot, 's', Item.stick});
		
		GameRegistry.addRecipe(new ItemStack(ItemHandler.arcaneBucket), new Object[] {"s s", " s ", 's', ItemHandler.silverIngot});
	}
	
	private static void createConduitRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 0), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), "plankWood", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 1), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), Block.stone, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 2), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), Item.ingotIron, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 3), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), "ingotCopper", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 4), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), "ingotAluminum", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 5), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), "ingotSilver", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 6), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), Item.ingotGold, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 7), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), "ingotElectrum", Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 8), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), Item.diamond, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 9), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), ItemHandler.metallicCobaltIngot, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 10), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), ItemHandler.platinumIngot, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 11), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), ItemHandler.tantalumCrystal, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.conduit, 8, 12), new Object[] {"aba", "ccc", "aba", Character.valueOf('a'), ItemHandler.lithiumIngot, Character.valueOf('b'), Block.glass, Character.valueOf('c'), ItemHandler.elinvarDust}));
	}
	
	private static void createElinvarRecipes() {
		GameRegistry.addRecipe(new ItemStack(BlockHandler.ironboundStone), new Object[] {"i i", " S ", "i i", 'i', ItemHandler.ironScrap, 'S', Block.stone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.copperboundStone), new Object[] {"c c", " S ", "c c", 'c', ItemHandler.copperChunk, 'S', Block.stone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.silverboundStone), new Object[] {"s s", " S ", "s s", 's', ItemHandler.silverFragment, 'S', Block.stone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.goldboundStone), new Object[] {"g g", " S ", "g g", 'g', Item.goldNugget, 'S', Block.stone});
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.redstoneGenerator), new Object[] {"IrI", "OIO", "aaa", 'I', BlockHandler.ironboundStone, 'r', Item.redstone, 'O', Block.obsidian, 'a', ItemHandler.aluminumIngot});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.radiantSolarGenerator), new Object[] {"ara", "OcO", "CCC", 'a', new ItemStack(ItemHandler.essence, 1, EssentialAspect.AERONIC.index), 'r', new ItemStack(ItemHandler.essence, 1, EssentialAspect.RADANTIS.index), 'O', Block.obsidian, 'c', ItemHandler.copperIngot, 'C', BlockHandler.copperboundStone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.waveringLunarGenerator), new Object[] {"afa", "OsO", "SSS", 'a', new ItemStack(ItemHandler.essence, 1, EssentialAspect.AERONIC.index), 'f', new ItemStack(ItemHandler.essence, 1, EssentialAspect.FERRIC.index), 'O', Block.obsidian, 's', ItemHandler.silverIngot, 'S', BlockHandler.silverboundStone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.celestialBalanceGenerator), new Object[] {"aqa", "OiO", "III", 'a', new ItemStack(ItemHandler.essence, 1, EssentialAspect.AERONIC.index), 'q', new ItemStack(ItemHandler.essence, 1, EssentialAspect.AQUAEOUS.index), 'O', Block.obsidian, 'i', Item.ingotIron, 'I', BlockHandler.ironboundStone});
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.essentialReducer), new Object[] {"IfI", "ede", "III", 'I', BlockHandler.ironboundStone, 'f', Item.flint, 'e', ItemHandler.elinvarDust, 'd', Item.diamond});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.alloyInductor), new Object[] {"IiI", "ere", "III", 'I', BlockHandler.ironboundStone, 'i', Item.ingotIron, 'e', ItemHandler.elinvarDust, 'r', Item.redstone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.electroplationEngine), new Object[] {"GvG", "ere", "GGG", 'G', BlockHandler.goldboundStone, 'v', ItemHandler.voltasniaRod, 'e', ItemHandler.elinvarDust, 'r', Item.redstone});
	}
	
	private static void createBlockRecipes() {
		GameRegistry.addRecipe(new ItemStack(BlockHandler.palestoneBricks, 4), new Object[] {"PP", "PP", 'P', BlockHandler.palestone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inscribedPalestone), new Object[] {"P", "P", 'P', new ItemStack(BlockHandler.supercraftSlab2, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.nightrockBricks, 4), new Object[] {"NN", "NN", 'N', BlockHandler.nightrock});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inscribedNightrock), new Object[] {"N", "N", 'N', new ItemStack(BlockHandler.supercraftSlab2, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.sandstoneBricks, 9), new Object[] {"SSS", "SSS", "SSS", 'S', new ItemStack(Block.sandStone, 1, 2)});
		
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inscribedSandstone), new Object[] {"S", "S", 'S', new ItemStack(BlockHandler.supercraftSlab2, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.endstoneBricks, 4), new Object[] {"EE", "EE", 'E', Block.whiteStone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.inscribedEndstone), new Object[] {"E", "E", 'E', new ItemStack(BlockHandler.supercraftSlab2, 1, 3)});

		GameRegistry.addRecipe(new ItemStack(BlockHandler.gravenStone, 1, 0), new Object[] {" N ", "NNN", " N ", 'N', BlockHandler.nightrock});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.gravenStone, 1, 1), new Object[] {" N ", "NSN", " N ", 'N', BlockHandler.nightrock, 'S', Block.stone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.gravenStone, 1, 2), new Object[] {" P ", "PSP", " P ", 'P', BlockHandler.palestone, 'S', Block.stone});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.gravenStone, 1, 3), new Object[] {" P ", "PPP", " P ", 'P', BlockHandler.palestone});
	}
	
	private static void createDecorRecipes() {
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 0), new Object[] {" B ", "SCS", " S ", 'B', new ItemStack(Block.cloth, 1, 11), 'S', Block.stone, 'C', Block.workbench});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 1), new Object[] {" G ", "SCS", " S ", 'G', new ItemStack(Block.cloth, 1, 13), 'S', Block.stone, 'C', Block.workbench});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 2), new Object[] {" R ", "SCS", " S ", 'R', new ItemStack(Block.cloth, 1, 14), 'S', Block.stone, 'C', Block.workbench});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 3), new Object[] {" R ", "NCN", " N ", 'R', new ItemStack(Block.cloth, 1, 14), 'N', Block.netherBrick, 'C', Block.workbench});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 4), new Object[] {" P ", "ECE", " E ", 'P', new ItemStack(Block.cloth, 1, 10), 'E', Block.whiteStone, 'C', Block.workbench});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 5), new Object[] {" L ", "PCP", " P ", 'L', new ItemStack(Block.cloth, 1, 7), 'P', BlockHandler.palestone, 'C', Block.workbench});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.refinedCraftingTable, 1, 6), new Object[] {" D ", "NCN", " N ", 'D', new ItemStack(Block.cloth, 1, 8), 'N', BlockHandler.nightrock, 'C', Block.workbench});
	
		GameRegistry.addRecipe(new ItemStack(BlockHandler.cobaltTorch, 4), new Object[] {"c", "s", 'c', ItemHandler.cobaltDust, 's', Item.stick});
	}
	
	private static void createMonolithRecipes() {
		GameRegistry.addRecipe(new ItemStack(BlockHandler.monolithDemission), new Object[] {"GGG", "sCs", "GGG", 'G', new ItemStack(BlockHandler.gravenStone, 1, 0), 's', ItemHandler.silverIngot, 'C', BlockHandler.cobaltBlock});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.monolithAscension), new Object[] {"GGG", "vjv", "GGG", 'G', new ItemStack(BlockHandler.gravenStone, 1, 2), 'v', ItemHandler.voltasniaRod, 'j', ItemHandler.jadeCrystal});
		GameRegistry.addRecipe(new ItemStack(BlockHandler.monolithTermination), new Object[] {"GGG", "sDs", "GGG", 'G', new ItemStack(BlockHandler.gravenStone, 1, 3), 's', ItemHandler.silverIngot, 'D', BlockHandler.draconiumBlock});
	}
	
	private static void createWoodAndPlantRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks1, 4, 0), new ItemStack(BlockHandler.supercraftLog1, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks1, 4, 1), new ItemStack(BlockHandler.supercraftLog1, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks1, 4, 2), new ItemStack(BlockHandler.supercraftLog1, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks1, 4, 3), new ItemStack(BlockHandler.supercraftLog2, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks1, 4, 4), new ItemStack(BlockHandler.supercraftLog2, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.supercraftPlanks1, 4, 5), new ItemStack(BlockHandler.supercraftLog2, 1, 2));

		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), BlockHandler.bluebells);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 7), BlockHandler.daisies);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 14), BlockHandler.snapdragon);
	}
	
	private static void createSmelting() {
		GameRegistry.addSmelting(BlockHandler.aluminumOre.blockID, new ItemStack(ItemHandler.aluminumIngot, 1), 0.4F);
		GameRegistry.addSmelting(BlockHandler.copperOre.blockID, new ItemStack(ItemHandler.copperIngot, 1), 0.4F);
		GameRegistry.addSmelting(BlockHandler.silverOre.blockID, new ItemStack(ItemHandler.silverIngot, 1), 0.5F);
		GameRegistry.addSmelting(BlockHandler.electrumOre.blockID, new ItemStack(ItemHandler.electrumIngot, 1), 0.6F);
		GameRegistry.addSmelting(BlockHandler.nisilOre.blockID, new ItemStack(ItemHandler.nisilIngot, 1), 0.4F);
		GameRegistry.addSmelting(BlockHandler.platinumOre.blockID, new ItemStack(ItemHandler.platinumIngot, 1), 0.8F);
		GameRegistry.addSmelting(BlockHandler.lithiumOre.blockID, new ItemStack(ItemHandler.lithiumIngot, 1), 1.0F);
	}
	
	private static void initOreDictionary() {
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog1, 1, 0));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog1, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog1, 1, 2));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog2, 1, 0));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog2, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(BlockHandler.supercraftLog2, 1, 2));
		
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks1, 1, 0));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks1, 1, 1));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks1, 1, 2));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks1, 1, 3));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks1, 1, 4));
		OreDictionary.registerOre("plankWood", new ItemStack(BlockHandler.supercraftPlanks1, 1, 5));
		
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 0));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 1));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 2));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 3));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 4));
		OreDictionary.registerOre("slabWood", new ItemStack(BlockHandler.supercraftSlab1, 1, 5));
		
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.oliveStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.goldenwoodStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.tenebriaStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.inisiaStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.valensienStairs));
		OreDictionary.registerOre("stairWood", new ItemStack(BlockHandler.mortaliaStairs));
		
		OreDictionary.registerOre("treeSapling", new ItemStack(BlockHandler.supercraftSapling1, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(BlockHandler.supercraftSapling2, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(BlockHandler.supercraftSapling2, 1, 1));
		
		OreDictionary.registerOre("treeLeaves", new ItemStack(BlockHandler.supercraftLeaves1, 1, 0));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BlockHandler.supercraftLeaves2, 1, 0));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BlockHandler.supercraftLeaves2, 1, 1));
		
		OreDictionary.registerOre("ingotAluminum", new ItemStack(ItemHandler.aluminumIngot));
		OreDictionary.registerOre("ingotCopper", new ItemStack(ItemHandler.copperIngot));
		OreDictionary.registerOre("ingotSilver", new ItemStack(ItemHandler.silverIngot));
		OreDictionary.registerOre("ingotElectrum", new ItemStack(ItemHandler.electrumIngot));
	}
	
}
