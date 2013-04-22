//**
//**  FuelHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class FuelHandler implements IFuelHandler {

	private static FuelHandler instance;
	
	public static FuelHandler instance() {
		if(instance == null) {
			instance = new FuelHandler();
		}
		return instance;
	}
	
	public static void init() {
		GameRegistry.registerFuelHandler(instance());
	}
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.itemID == BlockHandler.coalBlock.blockID) {
			return 12800;
		}
		else return 0;
	}

}
