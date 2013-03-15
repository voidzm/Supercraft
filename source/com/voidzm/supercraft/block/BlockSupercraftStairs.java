//////////////////////////////////////
//*   BlockSupercraftStairs.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockSupercraftStairs extends BlockStairs {

	public BlockSupercraftStairs(int par1, Block par2Block, int par3) {
		super(par1, par2Block, par3);
		this.useNeighborBrightness[par1] = true;
		this.setStepSound(Block.soundWoodFootstep);
		this.setUnlocalizedName("supercraftStairs");
	}
	
}
