package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockGoldenwood extends Block {

	public BlockGoldenwood(int par1) {
		super(par1, Material.rock);
		this.setHardness(4.0F);
		this.setResistance(15.0F);
		this.setLightValue(0.8F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("blockOfGoldenwood");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void func_94332_a(IconRegister par1IconRegister) {
		field_94336_cN = par1IconRegister.func_94245_a("supercraft:blockgoldenwood");
	}

}
