package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockOvergrownPalestone extends Block {

	public BlockOvergrownPalestone(int par1) {
		super(par1, 96, Material.rock);
		this.setHardness(1.8F);
		this.setResistance(9.0F);
		this.setStepSound(soundStoneFootstep);
		this.setBlockName("overgrownPalestone");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}

}
