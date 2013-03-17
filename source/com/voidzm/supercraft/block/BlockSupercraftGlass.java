//////////////////////////////////////
//*    BlockSupercraftGlass.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockSupercraftGlass extends BlockBreakable {

	public BlockSupercraftGlass(int id, String icon) {
		super(id, icon, Material.glass, false);
		this.setStepSound(Block.soundGlassFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public int quantityDropped(Random random) {
		return 0;
	}
	
	public int getRenderBlockPass() {
		return 0;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean canSilkHarvest() {
		return true;
	}
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		int blockID = par1IBlockAccess.getBlockId(par2, par3, par4);
		return isGlassType(blockID) ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}
	
	public static boolean isGlassType(int id) {
		if(id == Block.glass.blockID) return true;
		else if(id == BlockHandler.reinforcedGlass.blockID) return true;
		else if(id == BlockHandler.temperedGlass.blockID) return true;
		else if(id == BlockHandler.ornateGlass.blockID) return true;
		else if(id == BlockHandler.impactGlass.blockID) return true;
		else return false;
	}

}
