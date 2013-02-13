package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInscribedPalestone extends Block {

	public BlockInscribedPalestone(int par1) {
		super(par1, 97, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
		this.setStepSound(soundStoneFootstep);
		this.setBlockName("inscribedPalestone");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}

}
