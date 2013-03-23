package com.voidzm.supercraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemGravenStone extends ItemBlock {

	private final String[] names = {"darkness", "shadow", "gleaming", "brilliance"};
	
	public ItemGravenStone(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("gravenStone");
	}
	
	@Override
	public int getMetadata(int par1) {
		return par1;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}

}
