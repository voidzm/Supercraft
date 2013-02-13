package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBurnedPalestone extends Block {

	public BlockBurnedPalestone(int par1) {
		super(par1, 99, Material.rock);
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundStoneFootstep);
		this.setBlockName("burnedPalestone");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 0;
	}
	
	@Override
	public boolean canSilkHarvest() {
		return true;
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}

}
