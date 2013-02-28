//////////////////////////////////////
//*         BlockNisil.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockNisil extends Block {

	public BlockNisil(int id) {
		super(id, 118, Material.iron);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setBlockName("nisilBlock");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
}
