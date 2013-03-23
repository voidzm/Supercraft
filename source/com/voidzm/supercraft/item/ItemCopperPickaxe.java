//////////////////////////////////////
//*     ItemCopperPickaxe.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemCopperPickaxe extends ItemPickaxe {

	public ItemCopperPickaxe(int par1) {
		super(par1, Supercraft.copperTool);
		this.setUnlocalizedName("copperPickaxe");
		this.setCreativeTab(CreativeTabs.tabTools);
		MinecraftForge.setToolClass(this, "pickaxe", 2);
	}

	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon("supercraft:pickaxecopper");
	}
	
}

