package com.voidzm.supercraft.util;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.ItemHandler;

public class EssentialReducerRecipes {

	public enum EssentialAspect {
		TERRAEN(0), AERONIC(1), INFERNAL(2), AQUAEOUS(3), VERDEAL(4), NETHEREAL(5), DRACONIC(6), FERRIC(7), RADANTIS(8), VIDALIS(9), MORTALIC(10), LUXORUM(11), TENEBRA(12);
		public int index;
		private EssentialAspect(int var1) {
			this.index = var1;
		}
	}
	
	public static ArrayList<EssentialReducerInputMap> inputMappings = new ArrayList<EssentialReducerInputMap>();
	public static ArrayList<EssentialReducerCatalystMap> catalystMappings = new ArrayList<EssentialReducerCatalystMap>();
	
	static {
		populateInputs();
		populateCatalysts();
	}
	
	private static void populateInputs() {
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.coal), EssentialAspect.TERRAEN, EssentialAspect.INFERNAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.diamond), EssentialAspect.RADANTIS, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.ingotIron), EssentialAspect.FERRIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.ingotGold), EssentialAspect.FERRIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.silk), EssentialAspect.VIDALIS, EssentialAspect.MORTALIC));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.feather), EssentialAspect.VERDEAL, EssentialAspect.VIDALIS));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.gunpowder), EssentialAspect.MORTALIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.seeds), EssentialAspect.VIDALIS, EssentialAspect.VERDEAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.wheat), EssentialAspect.VIDALIS, EssentialAspect.VERDEAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.flint), EssentialAspect.TERRAEN, EssentialAspect.INFERNAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.leather), EssentialAspect.VERDEAL, EssentialAspect.VIDALIS));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.brick), EssentialAspect.TERRAEN, EssentialAspect.VERDEAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.clay), EssentialAspect.TERRAEN, EssentialAspect.VERDEAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.reed), EssentialAspect.VIDALIS, EssentialAspect.VERDEAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.egg), EssentialAspect.VERDEAL, EssentialAspect.VIDALIS));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.lightStoneDust), EssentialAspect.LUXORUM, EssentialAspect.NETHEREAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.sugar), EssentialAspect.VERDEAL, EssentialAspect.VIDALIS));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.pumpkinSeeds), EssentialAspect.VIDALIS, EssentialAspect.VERDEAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.melonSeeds), EssentialAspect.VIDALIS, EssentialAspect.VERDEAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.blazeRod), EssentialAspect.NETHEREAL, EssentialAspect.INFERNAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.goldNugget), EssentialAspect.FERRIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.netherStalkSeeds), EssentialAspect.NETHEREAL, EssentialAspect.VIDALIS));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.emerald), EssentialAspect.RADANTIS, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.netherStar), EssentialAspect.NETHEREAL, EssentialAspect.RADANTIS));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.ironScrap), EssentialAspect.FERRIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.diamondShard), EssentialAspect.RADANTIS, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.aluminumIngot), EssentialAspect.FERRIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.tantalumCrystal), EssentialAspect.RADANTIS, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.copperIngot), EssentialAspect.FERRIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.copperChunk), EssentialAspect.FERRIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.silverIngot), EssentialAspect.AERONIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.electrumIngot), EssentialAspect.FERRIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.electrumBit), EssentialAspect.FERRIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.bloodAmber), EssentialAspect.MORTALIC, EssentialAspect.VERDEAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.nisilIngot), EssentialAspect.TENEBRA, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.nisilShard), EssentialAspect.TENEBRA, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.redstone), EssentialAspect.RADANTIS, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.snowball), EssentialAspect.VERDEAL, EssentialAspect.AERONIC));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.paper), EssentialAspect.VERDEAL, EssentialAspect.AERONIC));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.book), EssentialAspect.VERDEAL, EssentialAspect.AERONIC));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.slimeBall), EssentialAspect.VERDEAL, EssentialAspect.MORTALIC));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.bone), EssentialAspect.MORTALIC, EssentialAspect.TERRAEN));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.enderPearl), EssentialAspect.DRACONIC, EssentialAspect.AERONIC));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.eyeOfEnder), EssentialAspect.DRACONIC, EssentialAspect.AERONIC));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.fireballCharge), EssentialAspect.INFERNAL, EssentialAspect.NETHEREAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(ItemHandler.elinvarDust), EssentialAspect.NETHEREAL, EssentialAspect.RADANTIS));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Block.waterlily), EssentialAspect.AQUAEOUS, EssentialAspect.VERDEAL));
		inputMappings.add(new EssentialReducerInputMap(new ItemStack(Item.glassBottle), EssentialAspect.AQUAEOUS, EssentialAspect.AERONIC));
	}
	
	private static void populateCatalysts() {
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.dirt), EssentialAspect.TERRAEN));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.stone), EssentialAspect.TERRAEN));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.gravel), EssentialAspect.TERRAEN));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(BlockHandler.palestone), EssentialAspect.TERRAEN));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(BlockHandler.nightrock), EssentialAspect.TERRAEN));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.grass), EssentialAspect.VERDEAL));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.cobblestone), EssentialAspect.TERRAEN));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.planks), EssentialAspect.VERDEAL));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.sand), EssentialAspect.TERRAEN));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.oreGold), EssentialAspect.FERRIC));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.oreIron), EssentialAspect.FERRIC));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.oreCoal), EssentialAspect.TERRAEN));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.wood), EssentialAspect.VERDEAL));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.glass), EssentialAspect.AERONIC));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.oreLapis), EssentialAspect.RADANTIS));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.sandStone), EssentialAspect.TERRAEN));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.cloth), EssentialAspect.VERDEAL));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.blockGold), EssentialAspect.FERRIC));
		catalystMappings.add(new EssentialReducerCatalystMap(new ItemStack(Block.blockSteel), EssentialAspect.FERRIC));
	}
	
	public static boolean isStackReducable(ItemStack stack) {
		for(EssentialReducerInputMap map : inputMappings) {
			if(map.getID() == stack.itemID) return true;
		}
		return false;
	}
	
	public static boolean isStackCatalytic(ItemStack stack) {
		for(EssentialReducerCatalystMap map : catalystMappings) {
			if(map.getID() == stack.itemID) return true;
		}
		return false;
	}
	
	public static ItemStack outputForInputAndCatalyst(ItemStack input, ItemStack catalyst) {
		if(!isStackReducable(input) || !isStackCatalytic(catalyst)) return null;
		EssentialReducerInputMap targetInputMap = null;
		for(EssentialReducerInputMap map : inputMappings) {
			if(map.getID() == input.itemID) {
				targetInputMap = map;
				break;
			}
		}
		if(targetInputMap == null) return null;
		EssentialReducerCatalystMap targetCatalystMap = null;
		for(EssentialReducerCatalystMap map : catalystMappings) {
			if(map.getID() == catalyst.itemID) {
				targetCatalystMap = map;
				break;
			}
		}
		if(targetCatalystMap == null) return null;
		if(targetCatalystMap.getAspect() == targetInputMap.getSecondary()) return new ItemStack(ItemHandler.essence, 1, targetInputMap.getSecondary().index);
		else return new ItemStack(ItemHandler.essence, 1, targetInputMap.getPrimary().index);
	}
	
}
