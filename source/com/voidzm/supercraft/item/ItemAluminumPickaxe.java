//////////////////////////////////////
//*    ItemAluminumPickaxe.java    *//
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
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemAluminumPickaxe extends ItemPickaxe {

	public ItemAluminumPickaxe(int par1) {
		super(par1, Supercraft.aluminumTool);
		this.setIconIndex(16);
		this.setItemName("aluminumPickaxe");
		this.setCreativeTab(CreativeTabs.tabTools);
		MinecraftForge.setToolClass(this, "pickaxe", 1);
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving) {
		super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLiving);
		if((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
			int efficiencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, par1ItemStack);
			par1ItemStack.damageItem(efficiencyLevel, par7EntityLiving);
		}
		return true;
	}
	
}

