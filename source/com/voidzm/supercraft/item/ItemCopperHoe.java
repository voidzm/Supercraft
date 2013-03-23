//////////////////////////////////////
//*       ItemCopperHoe.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.entity.TileEntityConduit;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemCopperHoe extends ItemHoe {

	public ItemCopperHoe(int par1) {
		super(par1, Supercraft.copperTool);
		this.setUnlocalizedName("copperHoe");
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon("supercraft:hoecopper");
	}
	
}
