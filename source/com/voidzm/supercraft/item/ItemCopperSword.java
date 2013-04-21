//////////////////////////////////////
//*      ItemCopperSword.java      *//
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
import net.minecraftforge.common.MinecraftForge;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

public class ItemCopperSword extends ItemSword implements IRegisterable {

	private RegisterData rdata = new RegisterData();
	
	public ItemCopperSword(int par1) {
		super(par1, Supercraft.copperTool);
		this.setUnlocalizedName("copperSword");
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.rdata.internalName = "swordcopper";
		this.rdata.externalName = "Copper Sword";
	}

	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon("supercraft:swordcopper");
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}
	
}
