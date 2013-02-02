//////////////////////////////////////
//*       BlockSilverOre.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;

public class BlockSilverOre extends BlockOre {

	public BlockSilverOre(int par1) {
		super(par1, 48);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockName("silverOre");
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
}
