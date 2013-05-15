//////////////////////////////////////
//*      BlockGenerator.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import com.voidzm.supercraft.Supercraft;

public class BlockGenerator extends BlockSupercraft {

	public BlockGenerator(int id) {
		super(id, Material.iron);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setInternalName("generator");
		this.setCreativeTab(Supercraft.elinvarTab);
	}
	
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		this.updateState(par1World, par2, par3, par4);
	}
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		this.updateState(par1World, par2, par3, par4);
	}
	
	public void updateState(World par1World, int par2, int par3, int par4) {
		// Subclasses override this to perform updates.
	}
	
}
