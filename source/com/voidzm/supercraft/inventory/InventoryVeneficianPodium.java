package com.voidzm.supercraft.inventory;

import com.voidzm.supercraft.container.ContainerVeneficianPodium;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.render.InventoryConduitRender;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class InventoryVeneficianPodium extends InventoryBasic {

	private ContainerVeneficianPodium container;
	
	public InventoryVeneficianPodium(ContainerVeneficianPodium cont) {
		super("VeneficianPodium", true, 1);
		this.container = cont;
	}
	
	public void onInventoryChanged() {
		super.onInventoryChanged();
		this.container.onCraftMatrixChanged(this);
	}
	
	public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
		if(par2ItemStack.itemID == ItemHandler.venianRod.itemID) return true;
		return false;
	}

}
