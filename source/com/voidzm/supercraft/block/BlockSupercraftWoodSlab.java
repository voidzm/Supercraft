//////////////////////////////////////
//*  BlockSupercraftWoodSlab.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockSupercraftWoodSlab extends BlockHalfSlab {

	public static final String[] woodTypes = new String[] {"olive", "goldenwood"};
	
	public BlockSupercraftWoodSlab(int par1) {
		super(par1, false, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.useNeighborBrightness[par1] = true;
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setBlockName("supercraftWoodSingleSlab");
		this.setRequiresSelfNotify();
	}
	
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        switch(par2 & 7) {
        case 0:
        	return 7;
        case 1:
        	return 74;
        default:
        	return 255;
        }
    }
	
	public int getBlockTextureFromSide(int par1) {
        return this.getBlockTextureFromSideAndMetadata(par1, 0);
    }
	
	public int idDropped(int par1, Random par2Random, int par3) {
        return BlockHandler.supercraftWoodSlab.blockID;
    }
	
	protected ItemStack createStackedBlock(int par1) {
        return new ItemStack(BlockHandler.supercraftWoodSlab.blockID, 2, par1 & 7);
    }
	
	public String getFullSlabName(int par1) {
        if(par1 < 0 || par1 >= woodTypes.length) {
            par1 = 0;
        }
        return super.getBlockName() + "." + woodTypes[par1];
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        if(par1 == BlockHandler.supercraftWoodSlab.blockID) {
        	par3List.add(new ItemStack(par1, 1, 0));
        	par3List.add(new ItemStack(par1, 1, 1));
        }
    }
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
}

