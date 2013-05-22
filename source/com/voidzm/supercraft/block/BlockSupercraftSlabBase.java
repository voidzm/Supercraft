//////////////////////////////////////
//*  BlockSupercraftSlabBase.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockSupercraftSlabBase extends BlockHalfSlab {
	
	public BlockSupercraftSlabBase(int par1, Material par2) {
		super(par1, false, par2);
		this.setCreativeTab(CreativeTabs.tabBlock);
		Block.useNeighborBrightness[par1] = true;
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		if(par2 == Material.wood) this.setStepSound(Block.soundWoodFootstep);
		else this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName("supercraftslabbase");
	}
	
	@Override
	protected ItemStack createStackedBlock(int par1) {
        return new ItemStack(this.blockID, 2, par1 & 7);
    }
	
	@Override
	public String getFullSlabName(int par1) {
		return null;
    }
	
}

