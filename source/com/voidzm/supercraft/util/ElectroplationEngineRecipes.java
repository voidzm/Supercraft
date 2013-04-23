package com.voidzm.supercraft.util;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.handler.ItemHandler;

public class ElectroplationEngineRecipes {

	public static ArrayList<ElectroplationEngineMap> mappings = new ArrayList<ElectroplationEngineMap>();
	
	static {
		populateCombos();
	}
	
	private static void populateCombos() {
		mappings.add(new ElectroplationEngineMap(new ItemStack(ItemHandler.voltasniaRod), new ItemStack(ItemHandler.silverIngot), new ItemStack(ItemHandler.arcaneRod, 1, 0)));
		mappings.add(new ElectroplationEngineMap(new ItemStack(ItemHandler.voltasniaRod), new ItemStack(Item.ingotGold), new ItemStack(ItemHandler.arcaneRod, 1, 1)));
		mappings.add(new ElectroplationEngineMap(new ItemStack(ItemHandler.voltasniaRod), new ItemStack(ItemHandler.copperIngot), new ItemStack(ItemHandler.arcaneRod, 1, 2)));
		mappings.add(new ElectroplationEngineMap(new ItemStack(ItemHandler.voltasniaRod), new ItemStack(ItemHandler.nisilIngot), new ItemStack(ItemHandler.arcaneRod, 1, 3)));
		mappings.add(new ElectroplationEngineMap(new ItemStack(ItemHandler.voltasniaRod), new ItemStack(ItemHandler.lithiumIngot), new ItemStack(ItemHandler.arcaneRod, 1, 4)));
	}
	
	public static boolean isValidCatalyst(ItemStack stack) {
		for(ElectroplationEngineMap map : mappings) {
			if(map.getCatalystID() == stack.itemID) return true;
		}
		return false;
	}
	
	public static boolean isValidMetal(ItemStack stack) {
		for(ElectroplationEngineMap map : mappings) {
			if(map.getMetalID() == stack.itemID) return true;
		}
		return false;
	}
	
	public static ItemStack outputForInputs(ItemStack catalyst, ItemStack metal) {
		ArrayList<ElectroplationEngineMap> secondaryDepthList = new ArrayList<ElectroplationEngineMap>();
		for(ElectroplationEngineMap map : mappings) { 
			if(map.getCatalystID() == catalyst.itemID) {
				secondaryDepthList.add(map);
			}
		}
		if(secondaryDepthList.size() == 0) return null;
		for(ElectroplationEngineMap map : secondaryDepthList) {
			if(map.getMetalID() == metal.itemID) {
				return map.getOutput();
			}
		}
		return null;
	}
	
}
