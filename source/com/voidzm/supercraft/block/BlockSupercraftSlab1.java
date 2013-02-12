//////////////////////////////////////
//*    BlockSupercraftSlab1.java   *//
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

public class BlockSupercraftSlab1 extends BlockSupercraftSlabBase {

	private final String[] types = new String[] {"olive", "goldenwood"};
	
	public BlockSupercraftSlab1(int par1) {
		super(par1, Material.wood);
		this.setBlockName("supercraftSlab1");
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
	
	public String getFullSlabName(int par1) {
		if(par1 < 0 || par1 >= types.length) {
			par1 = 0;
		}
		return super.getBlockName() + "." + types[par1];
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        if(par1 == BlockHandler.supercraftSlab1.blockID) {
        	par3List.add(new ItemStack(par1, 1, 0));
        	par3List.add(new ItemStack(par1, 1, 1));
        }
    }
	
}

