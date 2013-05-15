//////////////////////////////////////
//*     ItemAluminumSaber.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

public class ItemAluminumSaber extends ItemSword implements IRegisterable {

	private RegisterData rdata = new RegisterData();
	
	public ItemAluminumSaber(int par1) {
		super(par1, Supercraft.aluminumTool);
		this.setUnlocalizedName("swordaluminum");
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.rdata.internalName = "swordaluminum";
		this.rdata.externalName = "Aluminum Saber";
	}

	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("supercraft:swordaluminum");
	}
	
	public int getDamageVsEntity(Entity par1Entity) {
		return 2;
	}

	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving) {
		super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLiving);
		if((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
			int sharpnessLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, par1ItemStack);
			par1ItemStack.damageItem((sharpnessLevel*2), par7EntityLiving);
		}
		return true;
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}
	
}
