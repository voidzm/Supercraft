//////////////////////////////////////
//*      ItemAluminumHoe.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemAluminumHoe extends ItemHoe {

	public ItemAluminumHoe(int par1) {
		super(par1, Supercraft.aluminumTool);
		this.setIconIndex(20);
		this.setItemName("aluminumHoe");
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
}
