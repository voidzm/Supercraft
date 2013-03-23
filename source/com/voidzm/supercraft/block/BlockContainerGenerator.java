//////////////////////////////////////
//*  BlockContainerGenerator.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockContainerGenerator extends BlockContainer {

	public BlockContainerGenerator(int id) {
		super(id, Material.iron);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setUnlocalizedName("generator");
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

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return null;
	}
	
}
