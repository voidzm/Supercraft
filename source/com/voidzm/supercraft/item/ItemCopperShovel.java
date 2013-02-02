//////////////////////////////////////
//*      ItemCopperShovel.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;

public class ItemCopperShovel extends ItemSpade {

	public ItemCopperShovel(int par1) {
		super(par1, Supercraft.copperTool);
		this.setIconIndex(23);
		this.setItemName("copperShovel");
		this.setCreativeTab(CreativeTabs.tabTools);
		MinecraftForge.setToolClass(this, "shovel", 2);
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
}
