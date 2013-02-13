package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockGoldenwood extends Block {

	public BlockGoldenwood(int par1) {
		super(par1, 98, Material.rock);
		this.setHardness(4.0F);
		this.setResistance(15.0F);
		this.setLightValue(1.0F);
		this.setStepSound(soundStoneFootstep);
		this.setBlockName("blockOfGoldenwood");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}

}
