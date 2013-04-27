//////////////////////////////////////
//*  BlockSupercraftSlabBase.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockSupercraftSlabBase extends BlockHalfSlab {
	
	public BlockSupercraftSlabBase(int par1, Material par2) {
		super(par1, false, par2);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.useNeighborBrightness[par1] = true;
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

