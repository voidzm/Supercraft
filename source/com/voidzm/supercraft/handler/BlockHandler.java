//////////////////////////////////////
//*        BlockHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// Handles the registration and management of all blocks.

package com.voidzm.supercraft.handler;

import java.util.ArrayList;

import com.voidzm.supercraft.block.*;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.item.ItemRefinedCraftingTable;
import com.voidzm.supercraft.item.ItemSupercraftLeaves1;
import com.voidzm.supercraft.item.ItemSupercraftLeaves2;
import com.voidzm.supercraft.item.ItemSupercraftLog;
import com.voidzm.supercraft.item.ItemSupercraftPlanks;
import com.voidzm.supercraft.item.ItemSupercraftSapling1;
import com.voidzm.supercraft.item.ItemSupercraftSapling2;
import com.voidzm.supercraft.item.ItemSupercraftSlab1;
import com.voidzm.supercraft.item.ItemSupercraftSlab2;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockHandler {

	protected LanguageHandler languageHandler = new LanguageHandler();
	
	public static Block reinforcedGlass;
	public static Block temperedGlass;
	public static Block ornateGlass;
	public static Block impactGlass;
	
	public static Block coalBlock;
	
	public static Block supercraftLog;
	public static Block supercraftLeaves1;
	public static Block supercraftLeaves2;
	public static Block supercraftPlanks;
	public static Block supercraftSapling1;
	public static Block supercraftSapling2;
	public static Block supercraftSlab1;
	public static Block supercraftSlab2;
	
	public static Block oliveStairs;
	public static Block goldenwoodStairs;
	public static Block tenebriaStairs;
	
	public static Block aluminumOre;
	public static Block aluminumBlock;
	
	public static Block tantalumOre;
	
	public static Block copperOre;
	public static Block copperBlock;
	
	public static Block elinvarOre;
	public static Block elinvarBlock;
	
	public static Block silverOre;
	public static Block silverBlock;
	
	public static Block electrumOre;
	public static Block electrumBlock;
	
	public static Block woodenConduit;
	public static Block stoneConduit;
	public static Block ironConduit;
	public static Block copperConduit;
	public static Block aluminumConduit;
	public static Block silverConduit;
	public static Block goldenConduit;
	public static Block electrumConduit;
	public static Block diamondConduit;
	
	public static Block refinedCraftingTable;
	
	public static Block redstoneGenerator;
	
	public static Block bluebells;
	public static Block daisies;
	
	public static Block palestone;
	public static Block palestoneBricks;
	public static Block palestoneStairs;
	public static Block inscribedPalestone;
	public static Block blockOfGoldenwood;
	public static Block overgrownPalestone;
	public static Block burnedPalestone;
	
	public static Block nightrock;
	public static Block nightrockBricks;
	public static Block nightrockStairs;
	public static Block inscribedNightrock;
	public static Block blockOfTenebral;
	public static Block burnedNightrock;
	
	public void populateAllAndInitialize() {
		this.createBlocks();
		this.populateLanguage();
		this.registerBlocks();
		this.initializeLanguage();
		this.overrideDefaultTextures();
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
		supercraftLogList.add("Goldenwood Wood");
		supercraftLogList.add("Tenebria Wood");
		supercraftLogList.add("Crystalline Tenebria Wood");
		languageHandler.add(supercraftLog, supercraftLogList);
		
		ArrayList<String> supercraftLeaves1List = new ArrayList<String>();
		supercraftLeaves1List.add("Olive Leaves");
		languageHandler.add(supercraftLeaves1, supercraftLeaves1List);
		
		ArrayList<String> supercraftLeaves2List = new ArrayList<String>();
		supercraftLeaves2List.add("Goldenwood Leaves");
		supercraftLeaves2List.add("Tenebria Leaves");
		languageHandler.add(supercraftLeaves2, supercraftLeaves2List);
		
		ArrayList<String> supercraftPlanksList = new ArrayList<String>();
		supercraftPlanksList.add("Olive Wood Planks");
		supercraftPlanksList.add("Goldenwood Wood Planks");
		supercraftPlanksList.add("Tenebria Wood Planks");
		languageHandler.add(supercraftPlanks, supercraftPlanksList);
		
		ArrayList<String> supercraftSapling1List = new ArrayList<String>();
		supercraftSapling1List.add("Olive Sapling");
		languageHandler.add(supercraftSapling1, supercraftSapling1List);
		
		ArrayList<String> supercraftSapling2List = new ArrayList<String>();
		supercraftSapling2List.add("Goldenwood Sapling");
		supercraftSapling2List.add("Tenebria Sapling");
		languageHandler.add(supercraftSapling2, supercraftSapling2List);
		
		ArrayList<String> supercraftSlab1List = new ArrayList<String>();
		supercraftSlab1List.add("Olive Wood Slab");
		supercraftSlab1List.add("Goldenwood Wood Slab");
		supercraftSlab1List.add("Tenebria Wood Slab");
		languageHandler.add(supercraftSlab1, supercraftSlab1List);
		
		ArrayList<String> supercraftSlab2List = new ArrayList<String>();
		supercraftSlab2List.add("Palestone Bricks Slab");
		supercraftSlab2List.add("Nightrock Bricks Slab");
		languageHandler.add(supercraftSlab2, supercraftSlab2List);
		
		languageHandler.add(oliveStairs, "Olive Wood Stairs");
		languageHandler.add(goldenwoodStairs, "Goldenwood Wood Stairs");
		languageHandler.add(tenebriaStairs, "Tenebria Wood Stairs");
		
		// Aluminum Material
		
		languageHandler.add(aluminumOre, "Aluminum Ore");
		languageHandler.add(aluminumBlock, "Block of Aluminum");
		
		// Tantalum Material
		
		languageHandler.add(tantalumOre, "Tantalum Ore");
		
		// Copper Material
		
		languageHandler.add(copperOre, "Copper Ore");
		languageHandler.add(copperBlock, "Block of Copper");
		
		// Silver Material
		
		languageHandler.add(silverOre, "Silver Ore");
		languageHandler.add(silverBlock, "Block of Silver");
		
		// Electrum Material
		
		languageHandler.add(electrumOre, "Electrum Ore");
		languageHandler.add(electrumBlock, "Block of Electrum");
		
		// Elinvar Material
		
		languageHandler.add(elinvarOre, "Elinvar Ore");
		languageHandler.add(elinvarBlock, "Block of Elinvar");
		
		// Elinvar Conduits
		
		languageHandler.add(woodenConduit, "Wooden Conduit");
		languageHandler.add(stoneConduit, "Stone Conduit");
		languageHandler.add(ironConduit, "Iron Conduit");
		languageHandler.add(copperConduit, "Copper Conduit");
		languageHandler.add(aluminumConduit, "Aluminum Conduit");
		languageHandler.add(silverConduit, "Silver Conduit");
		languageHandler.add(goldenConduit, "Golden Conduit");
		languageHandler.add(electrumConduit, "Electrum Conduit");
		languageHandler.add(diamondConduit, "Diamond Conduit");
		
		// Refined Crafting Tables
		
		ArrayList<String> refinedCraftingTableList = new ArrayList<String>();
		refinedCraftingTableList.add("Stone Crafting Table");
		refinedCraftingTableList.add("Stone Crafting Table");
		refinedCraftingTableList.add("Stone Crafting Table");
		refinedCraftingTableList.add("Nether Crafting Table");
		refinedCraftingTableList.add("End Crafting Table");
		languageHandler.add(refinedCraftingTable, refinedCraftingTableList);
		
		// Elinvar Generators
		
		languageHandler.add(redstoneGenerator, "Redstone Conversion Generator");
		
		// Supercraft Flowers
		
		languageHandler.add(bluebells, "Bluebells");
		languageHandler.add(daisies, "Daisies");
		
		// Palestone
		
		languageHandler.add(palestone, "Palestone");
		languageHandler.add(palestoneBricks, "Palestone Bricks");
		languageHandler.add(palestoneStairs, "Palestone Brick Stairs");
		languageHandler.add(inscribedPalestone, "Inscribed Palestone");
		languageHandler.add(blockOfGoldenwood, "Block of the Goldenwood");
		languageHandler.add(overgrownPalestone, "Overgrown Palestone Bricks");
		languageHandler.add(burnedPalestone, "Burned Palestone Bricks");
		
		// Nightrock
		
		languageHandler.add(nightrock, "Nightrock");
		languageHandler.add(nightrockBricks, "Nightrock Bricks");
		languageHandler.add(nightrockStairs, "Nightrock Brick Stairs");
		languageHandler.add(inscribedNightrock, "Inscribed Nightrock");
		languageHandler.add(blockOfTenebral, "Block of the Tenebral");
		languageHandler.add(burnedNightrock, "Burned Nightrock");
		
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
		supercraftLeaves1 = new BlockSupercraftLeaves1(1606);
		supercraftLeaves2 = new BlockSupercraftLeaves2(1633);
		supercraftPlanks = new BlockSupercraftPlanks(1607);
		supercraftSapling1 = new BlockSupercraftSapling1(1608);
		supercraftSapling2 = new BlockSupercraftSapling2(1635);
		supercraftSlab1 = new BlockSupercraftSlab1(1609);
		supercraftSlab2 = new BlockSupercraftSlab2(1640);
		
		oliveStairs = new BlockSupercraftStairs(1610, supercraftPlanks, 0).setBlockName("oliveStairs");
		goldenwoodStairs = new BlockSupercraftStairs(1634, supercraftPlanks, 1).setBlockName("goldenwoodStairs");
		tenebriaStairs = new BlockSupercraftStairs(1646, supercraftPlanks, 2).setBlockName("tenebriaStairs");
		
		// Aluminum Material
		
		aluminumOre = new BlockAluminumOre(1611);
		aluminumBlock = new BlockAluminum(1612);
		
		// Tantalum Material
		
		tantalumOre = new BlockTantalumOre(1613);
		
		// Copper Material
		
		copperOre = new BlockCopperOre(1619);
		copperBlock = new BlockCopper(1620);
		
		// Silver Material
		
		silverOre = new BlockSilverOre(1623);
		silverBlock = new BlockSilver(1624);
		
		// Electrum Material
		
		electrumOre = new BlockElectrumOre(1627);
		electrumBlock = new BlockElectrum(1628);
		
		// Elinvar Material
		
		elinvarOre = new BlockElinvarOre(1614);
		elinvarBlock = new BlockElinvar(1615);
		
		// Elinvar Conduits
		
		woodenConduit = new BlockWoodenConduit(1616);
		stoneConduit = new BlockStoneConduit(1617);
		ironConduit = new BlockIronConduit(1618);
		copperConduit = new BlockCopperConduit(1621);
		aluminumConduit = new BlockAluminumConduit(1622);
		silverConduit = new BlockSilverConduit(1625);
		goldenConduit = new BlockGoldenConduit(1626);
		electrumConduit = new BlockElectrumConduit(1629);
		diamondConduit = new BlockDiamondConduit(1630);
		
		// Refined Crafting Tables
		
		refinedCraftingTable = new BlockRefinedCraftingTable(1631);
		
		// Elinvar Generators
		
		redstoneGenerator = new BlockRedstoneGenerator(1632);
		
		// Supercraft Flowers
		
		bluebells = new BlockSupercraftFlower(1636, 76).setBlockName("bluebells");
		daisies = new BlockSupercraftFlower(1637, 77).setBlockName("daisies");
		
		// Palestone
		
		palestone = new BlockPalestone(1638);
		palestoneBricks = new BlockPalestoneBricks(1639);
		palestoneStairs = new BlockSupercraftStairs(1641, palestoneBricks, 0).setBlockName("palestoneStairs");
		inscribedPalestone = new BlockInscribedPalestone(1642);
		blockOfGoldenwood = new BlockGoldenwood(1643);
		overgrownPalestone = new BlockOvergrownPalestone(1644);
		burnedPalestone = new BlockBurnedPalestone(1645);
		
		// Nightrock
		
		nightrock = new BlockNightrock(1647);
		nightrockBricks = new BlockNightrockBricks(1648);
		nightrockStairs = new BlockSupercraftStairs(1649, nightrockBricks, 0).setBlockName("nightrockStairs");
		inscribedNightrock = new BlockInscribedNightrock(1650);
		blockOfTenebral = new BlockTenebral(1651);
		burnedNightrock = new BlockBurnedNightrock(1652);
		
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
		GameRegistry.registerBlock(supercraftLeaves1, ItemSupercraftLeaves1.class, "supercraftLeaves1");
		GameRegistry.registerBlock(supercraftLeaves2, ItemSupercraftLeaves2.class, "supercraftLeaves2");
		GameRegistry.registerBlock(supercraftPlanks, ItemSupercraftPlanks.class, "supercraftPlanks");
		GameRegistry.registerBlock(supercraftSapling1, ItemSupercraftSapling1.class, "supercraftSapling1");
		GameRegistry.registerBlock(supercraftSapling2, ItemSupercraftSapling2.class, "supercraftSapling2");
		GameRegistry.registerBlock(supercraftSlab1, ItemSupercraftSlab1.class, "supercraftSlab1");
		GameRegistry.registerBlock(supercraftSlab2, ItemSupercraftSlab2.class, "supercraftSlab2");
		
		GameRegistry.registerBlock(oliveStairs, "oliveStairs");
		GameRegistry.registerBlock(goldenwoodStairs, "goldenwoodStairs");
		GameRegistry.registerBlock(tenebriaStairs, "tenebriaStairs");
		
		// Aluminum Material
		
		GameRegistry.registerBlock(aluminumOre, "aluminumOre");
		GameRegistry.registerBlock(aluminumBlock, "aluminumBlock");
		
		// Tantalum Material
		
		GameRegistry.registerBlock(tantalumOre, "tantalumOre");
		
		// Copper Material
		
		GameRegistry.registerBlock(copperOre, "copperOre");
		GameRegistry.registerBlock(copperBlock, "copperBlock");
		
		// Silver Material
		
		GameRegistry.registerBlock(silverOre, "silverOre");
		GameRegistry.registerBlock(silverBlock, "silverBlock");
		
		// Electrum Material
		
		GameRegistry.registerBlock(electrumOre, "electrumOre");
		GameRegistry.registerBlock(electrumBlock, "electrumBlock");
		
		// Elinvar Material
		
		GameRegistry.registerBlock(elinvarOre, "elinvarOre");
		GameRegistry.registerBlock(elinvarBlock, "elinvarBlock");
		
		// Elinvar Conduits
		
		GameRegistry.registerBlock(woodenConduit, "woodenConduit");
		GameRegistry.registerBlock(stoneConduit, "stoneConduit");
		GameRegistry.registerBlock(ironConduit, "ironConduit");
		GameRegistry.registerBlock(copperConduit, "copperConduit");
		GameRegistry.registerBlock(aluminumConduit, "aluminumConduit");
		GameRegistry.registerBlock(silverConduit, "silverConduit");
		GameRegistry.registerBlock(goldenConduit, "goldenConduit");
		GameRegistry.registerBlock(electrumConduit, "electrumConduit");
		GameRegistry.registerBlock(diamondConduit, "diamondConduit");
		
		// Refined Crafting Tables
		
		GameRegistry.registerBlock(refinedCraftingTable, ItemRefinedCraftingTable.class, "refinedCraftingTable");
		
		// Elinvar Generators
		
		GameRegistry.registerBlock(redstoneGenerator, "redstoneGenerator");
		
		// Supercraft Flowers
		
		GameRegistry.registerBlock(bluebells, "bluebells");
		GameRegistry.registerBlock(daisies, "daisies");
		
		// Palestone
		
		GameRegistry.registerBlock(palestone, "palestone");
		GameRegistry.registerBlock(palestoneBricks, "palestoneBricks");
		GameRegistry.registerBlock(palestoneStairs, "palestoneStairs");
		GameRegistry.registerBlock(inscribedPalestone, "inscribedPalestone");
		GameRegistry.registerBlock(blockOfGoldenwood, "blockOfGoldenwood");
		GameRegistry.registerBlock(overgrownPalestone, "overgrownPalestone");
		GameRegistry.registerBlock(burnedPalestone, "burnedPalestone");
		
		// Nightrock

		GameRegistry.registerBlock(nightrock, "nightrock");
		GameRegistry.registerBlock(nightrockBricks, "nightrockBricks");
		GameRegistry.registerBlock(nightrockStairs, "nightrockStairs");
		GameRegistry.registerBlock(inscribedNightrock, "inscribedNightrock");
		GameRegistry.registerBlock(blockOfTenebral, "blockOfTenebral");
		GameRegistry.registerBlock(burnedNightrock, "burnedNightrock");
		
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
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftLeaves1))  {
			ItemStack stack = new ItemStack(supercraftLeaves1, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftLeaves2))  {
			ItemStack stack = new ItemStack(supercraftLeaves2, 1, i);
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
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftSapling1))  {
			ItemStack stack = new ItemStack(supercraftSapling1, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftSapling2))  {
			ItemStack stack = new ItemStack(supercraftSapling2, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftSlab1))  {
			ItemStack stack = new ItemStack(supercraftSlab1, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(supercraftSlab2))  {
			ItemStack stack = new ItemStack(supercraftSlab2, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		
		LanguageRegistry.addName(oliveStairs, (String)this.languageHandler.getString(oliveStairs));
		LanguageRegistry.addName(goldenwoodStairs, (String)this.languageHandler.getString(goldenwoodStairs));
		LanguageRegistry.addName(tenebriaStairs, (String)this.languageHandler.getString(tenebriaStairs));
		
		// Aluminum Material
		
		LanguageRegistry.addName(aluminumOre, (String)this.languageHandler.getString(aluminumOre));
		LanguageRegistry.addName(aluminumBlock, (String)this.languageHandler.getString(aluminumBlock));
		
		// Tantalum Material
		
		LanguageRegistry.addName(tantalumOre, (String)this.languageHandler.getString(tantalumOre));
		
		// Copper Material
		
		LanguageRegistry.addName(copperOre, (String)this.languageHandler.getString(copperOre));
		LanguageRegistry.addName(copperBlock, (String)this.languageHandler.getString(copperBlock));
		
		// Silver Material
		
		LanguageRegistry.addName(silverOre, (String)this.languageHandler.getString(silverOre));
		LanguageRegistry.addName(silverBlock, (String)this.languageHandler.getString(silverBlock));
		
		// Electrum Material
		
		LanguageRegistry.addName(electrumOre, (String)this.languageHandler.getString(electrumOre));
		LanguageRegistry.addName(electrumBlock, (String)this.languageHandler.getString(electrumBlock));
		
		// Elinvar Material
		
		LanguageRegistry.addName(elinvarOre, (String)this.languageHandler.getString(elinvarOre));
		LanguageRegistry.addName(elinvarBlock, (String)this.languageHandler.getString(elinvarBlock));
		
		// Elinvar Conduits
		
		LanguageRegistry.addName(woodenConduit, (String)this.languageHandler.getString(woodenConduit));
		LanguageRegistry.addName(stoneConduit, (String)this.languageHandler.getString(stoneConduit));
		LanguageRegistry.addName(ironConduit, (String)this.languageHandler.getString(ironConduit));
		LanguageRegistry.addName(copperConduit, (String)this.languageHandler.getString(copperConduit));
		LanguageRegistry.addName(aluminumConduit, (String)this.languageHandler.getString(aluminumConduit));
		LanguageRegistry.addName(silverConduit, (String)this.languageHandler.getString(silverConduit));
		LanguageRegistry.addName(goldenConduit, (String)this.languageHandler.getString(goldenConduit));
		LanguageRegistry.addName(electrumConduit, (String)this.languageHandler.getString(electrumConduit));
		LanguageRegistry.addName(diamondConduit, (String)this.languageHandler.getString(diamondConduit));
		
		// Refined Crafting Tables
		
		i = 0;
		for(String string : (ArrayList<String>)languageHandler.getString(refinedCraftingTable))  {
			ItemStack stack = new ItemStack(refinedCraftingTable, 1, i);
			LanguageRegistry.addName(stack, string);
			i++;
		}
		
		// Elinvar Generators
		
		LanguageRegistry.addName(redstoneGenerator, (String)this.languageHandler.getString(redstoneGenerator));
		
		// Supercraft Flowers
		
		LanguageRegistry.addName(bluebells, (String)this.languageHandler.getString(bluebells));
		LanguageRegistry.addName(daisies, (String)this.languageHandler.getString(daisies));
		
		// Palestone
		
		LanguageRegistry.addName(palestone, (String)this.languageHandler.getString(palestone));
		LanguageRegistry.addName(palestoneBricks, (String)this.languageHandler.getString(palestoneBricks));
		LanguageRegistry.addName(palestoneStairs, (String)this.languageHandler.getString(palestoneStairs));
		LanguageRegistry.addName(inscribedPalestone, (String)this.languageHandler.getString(inscribedPalestone));
		LanguageRegistry.addName(blockOfGoldenwood, (String)this.languageHandler.getString(blockOfGoldenwood));
		LanguageRegistry.addName(overgrownPalestone, (String)this.languageHandler.getString(overgrownPalestone));
		LanguageRegistry.addName(burnedPalestone, (String)this.languageHandler.getString(burnedPalestone));
		
		// Nightrock
		
		LanguageRegistry.addName(nightrock, (String)this.languageHandler.getString(nightrock));
		LanguageRegistry.addName(nightrockBricks, (String)this.languageHandler.getString(nightrockBricks));
		LanguageRegistry.addName(nightrockStairs, (String)this.languageHandler.getString(nightrockStairs));
		LanguageRegistry.addName(inscribedNightrock, (String)this.languageHandler.getString(inscribedNightrock));
		LanguageRegistry.addName(blockOfTenebral, (String)this.languageHandler.getString(blockOfTenebral));
		LanguageRegistry.addName(burnedNightrock, (String)this.languageHandler.getString(burnedNightrock));
		
	}
	
	private void overrideDefaultTextures() {
		Block.blockGold.setTextureFile(ClientProxy.BLOCKS_PNG);
		Block.blockGold.blockIndexInTexture = 52;
		Block.blockDiamond.setTextureFile(ClientProxy.BLOCKS_PNG);
		Block.blockDiamond.blockIndexInTexture = 53;
	}
	
}
