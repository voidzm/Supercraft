//////////////////////////////////////
//*      BlockImpactGlass.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockImpactGlass extends BlockBreakable {

	public BlockImpactGlass(int id) {
		super(id, 3, Material.glass, false);
		this.setHardness(3.0F);
		this.setResistance(18.0F);
		this.setStepSound(Block.soundGlassFootstep);
		this.setBlockName("impactGlass");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
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
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}

}