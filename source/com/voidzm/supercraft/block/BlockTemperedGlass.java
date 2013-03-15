//////////////////////////////////////
//*     BlockTemperedGlass.java    *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockTemperedGlass extends BlockBreakable {

	public BlockTemperedGlass(int id) {
		super(id, "supercraft:temperedglass", Material.glass, false);
		this.setHardness(0.1F);
		this.setResistance(30.0F);
		this.setStepSound(Block.soundGlassFootstep);
		this.setUnlocalizedName("temperedGlass");
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
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}

}