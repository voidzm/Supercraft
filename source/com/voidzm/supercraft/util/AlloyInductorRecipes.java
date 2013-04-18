package com.voidzm.supercraft.util;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.handler.ItemHandler;

public class AlloyInductorRecipes {

	public static ArrayList<AlloyInductorComboMap> comboMappings = new ArrayList<AlloyInductorComboMap>();
	
	static {
		populateCombos();
	}
	
	private static void populateCombos() {
		comboMappings.add(new AlloyInductorComboMap(new ItemStack(ItemHandler.silverIngot), new ItemStack(Item.ingotGold), new ItemStack(ItemHandler.electrumIngot)));
		comboMappings.add(new AlloyInductorComboMap(new ItemStack(ItemHandler.cobaltDust), new ItemStack(Item.ingotIron), new ItemStack(ItemHandler.metallicCobaltIngot)));
		comboMappings.add(new AlloyInductorComboMap(new ItemStack(ItemHandler.cobaltDust), new ItemStack(ItemHandler.aluminumIngot), new ItemStack(ItemHandler.metallicCobaltIngot)));
		comboMappings.add(new AlloyInductorComboMap(new ItemStack(ItemHandler.cobaltDust), new ItemStack(ItemHandler.silverIngot), new ItemStack(ItemHandler.metallicCobaltIngot)));
	}
	
	public static boolean isValidAlloyInput(ItemStack stack) {
		for(AlloyInductorComboMap map : comboMappings) {
			if(map.getID1() == stack.itemID || map.getID2() == stack.itemID) return true;
		}
		return false;
	}
	
	public static boolean isValidInputForOther(ItemStack stack, ItemStack other) {
		if(stack.isItemEqual(other)) return false;
		ArrayList<AlloyInductorComboMap> secondaryDepthList = new ArrayList<AlloyInductorComboMap>();
		for(AlloyInductorComboMap map : comboMappings) { 
			if(map.getID1() == stack.itemID || map.getID2() == stack.itemID) {
				secondaryDepthList.add(map);
			}
		}
		if(secondaryDepthList.size() == 0) return false;
		for(AlloyInductorComboMap map : secondaryDepthList) {
			if(map.getID1() == other.itemID || map.getID2() == other.itemID) {
				return true;
			}
		}
		return false;
	}
	
	public static ItemStack outputForMetals(ItemStack metal1, ItemStack metal2) {
		if(metal1.isItemEqual(metal2)) return null;
		ArrayList<AlloyInductorComboMap> secondaryDepthList = new ArrayList<AlloyInductorComboMap>();
		for(AlloyInductorComboMap map : comboMappings) { 
			if(map.getID1() == metal1.itemID || map.getID2() == metal1.itemID) {
				secondaryDepthList.add(map);
			}
		}
		if(secondaryDepthList.size() == 0) return null;
		for(AlloyInductorComboMap map : secondaryDepthList) {
			if(map.getID1() == metal2.itemID || map.getID2() == metal2.itemID) {
				return map.getOutput();
			}
		}
		return null;
	}
	
}
