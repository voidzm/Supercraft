//////////////////////////////////////
//*   ItemSupercraftSapling2.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.handler.BlockHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSupercraftSapling2 extends ItemBlock {

	private final static String[] names = {"goldenwoodSapling", "tenebriaSapling"};
	
	public ItemSupercraftSapling2(int par1) {
		super(par1);
		setHasSubtypes(true);
		setItemName("supercraftSapling2");
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	} 
	
	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return getItemName() + "." + names[itemstack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int par1) {
		return BlockHandler.supercraftSapling2.getBlockTextureFromSideAndMetadata(0, par1);
	}
	
}
