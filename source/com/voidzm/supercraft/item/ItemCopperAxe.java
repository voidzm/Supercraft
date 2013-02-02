//////////////////////////////////////
//*       ItemCopperAxe.java       *//
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
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemCopperAxe extends ItemAxe {

	public ItemCopperAxe(int par1) {
		super(par1, Supercraft.copperTool);
		this.setIconIndex(24);
		this.setItemName("copperAxe");
		this.setCreativeTab(CreativeTabs.tabTools);
		MinecraftForge.setToolClass(this, "axe", 2);
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
}
