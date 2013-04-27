//**
//**  BlockSupercraftGlass.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockSupercraftGlass extends BlockSupercraft {

	private String iconString;
	
	public BlockSupercraftGlass(int id, String icon) {
		super(id, Material.glass);
		this.setStepSound(Block.soundGlassFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.iconString = icon;
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 0;
	}
	
	@Override
	public int getRenderBlockPass() {
		return 0;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean canSilkHarvest() {
		return true;
	}
	
	@Override
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
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(this.iconString);
	}

}
