//////////////////////////////////////
//*   BlockSupercraftStairs.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockSupercraftStairs extends BlockStairs implements IRegisterable {

	private RegisterData rdata = new RegisterData();
	
	public BlockSupercraftStairs(int par1, Block par2Block, int par3, String internal, String external) {
		super(par1, par2Block, par3);
		this.useNeighborBrightness[par1] = true;
		this.setStepSound(Block.soundWoodFootstep);
		this.setUnlocalizedName(internal);
		this.rdata.internalName = internal;
		this.rdata.externalName = external;
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}
	
}
