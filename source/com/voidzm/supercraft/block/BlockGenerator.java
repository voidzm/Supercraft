//////////////////////////////////////
//*      BlockGenerator.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockGenerator extends Block {

	public BlockGenerator(int id, int tex) {
		super(id, tex, Material.iron);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setBlockName("generator");
		this.setCreativeTab(Supercraft.elinvarTab);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		this.updateState(par1World, par2, par3, par4);
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		this.updateState(par1World, par2, par3, par4);
	}
	
	public void updateState(World par1World, int par2, int par3, int par4) {
		// Subclasses override this to perform updates.
	}
	
}
