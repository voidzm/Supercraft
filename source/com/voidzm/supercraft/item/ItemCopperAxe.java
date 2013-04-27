//////////////////////////////////////
//*       ItemCopperAxe.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemCopperAxe extends ItemAxe implements IRegisterable {

	private RegisterData rdata = new RegisterData();
	
	public ItemCopperAxe(int par1) {
		super(par1, Supercraft.copperTool);
		this.setUnlocalizedName("copperAxe");
		this.setCreativeTab(CreativeTabs.tabTools);
		MinecraftForge.setToolClass(this, "axe", 2);
		this.rdata.internalName = "axecopper";
		this.rdata.externalName = "Copper Axe";
	}

	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("supercraft:axecopper");
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}
	
}
