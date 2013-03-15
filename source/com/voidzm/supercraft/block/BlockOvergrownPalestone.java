package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockOvergrownPalestone extends Block {

	public BlockOvergrownPalestone(int par1) {
		super(par1, Material.rock);
		this.setHardness(1.8F);
		this.setResistance(9.0F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("overgrownPalestone");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void func_94332_a(IconRegister par1IconRegister) {
		field_94336_cN = par1IconRegister.func_94245_a("supercraft:overgrownpalestonebricks");
	}

}
