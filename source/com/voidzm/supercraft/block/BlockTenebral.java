package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTenebral extends Block {

	public BlockTenebral(int par1) {
		super(par1, 109, Material.rock);
		this.setHardness(4.0F);
		this.setResistance(15.0F);
		this.setLightValue(0.6F);
		this.setStepSound(soundStoneFootstep);
		this.setBlockName("blockOfTenebral");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}

}