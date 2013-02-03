//////////////////////////////////////
//*      ItemCopperSword.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;

public class ItemCopperSword extends ItemSword {

	public ItemCopperSword(int par1) {
		super(par1, Supercraft.copperTool);
		this.setIconIndex(22);
		this.setItemName("copperSword");
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
}