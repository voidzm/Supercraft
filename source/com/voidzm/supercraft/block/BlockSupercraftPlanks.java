//////////////////////////////////////
//*    BlockSupercraftPlanks.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.List;

import com.voidzm.supercraft.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockSupercraftPlanks extends Block {
	
	public static final String[] woodType = new String[] {"olive", "goldenwood"};

	public BlockSupercraftPlanks(int par1) {
		super(par1, 7, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setBlockName("supercraftPlanks");
		this.setRequiresSelfNotify();
	}
	
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		switch(par2) {
		case 0:
			return 7;
		case 1:
			return 74;
		default:
			return 7;
		}
	}
	
	public int damageDropped(int par1) {
		return par1;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
}
