//////////////////////////////////////
//*         BlockCoal.java         *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCoal extends Block {

	public BlockCoal(int id) {
		super(id, 4, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setBlockName("coalBlock");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
}
