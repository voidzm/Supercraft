//////////////////////////////////////
//*   BlockRedstoneGenerator.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.protocol.IGenerator;

public class BlockRedstoneGenerator extends BlockGenerator implements IGenerator {

	public BlockRedstoneGenerator(int id) {
		super(id, 69);
		this.setBlockName("redstoneGenerator");
	}
	
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		if(side == 0) return 70;
		else if(side == 1) return 71;
		else return 69;
	}

	@Override
	public int powerOutputAt(World world, int x, int y, int z) {
		return 1;
	}
	
	private boolean shouldOutputPowerAt(World world, int x, int y, int z) {
		if(world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockGettingPowered(x, y, z)) {
			return true;
		}
		else return false;
	}
	
	@Override
	public boolean doesOutputPowerAt(World world, int x, int y, int z) {
		if(this.shouldOutputPowerAt(world, x, y, z) && world.getBlockMetadata(x, y, z) == 1) return true;
		else return false;
	}
	
	@Override
	public void updateState(World par1World, int par2, int par3, int par4) {
		if(this.shouldOutputPowerAt(par1World, par2, par3, par4)) {
			if(par1World.getBlockMetadata(par2, par3, par4) == 0) {
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 1);
			}
		}
		else if(par1World.getBlockMetadata(par2, par3, par4) == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);
		}
	}

	@Override
	public boolean shouldConnectAtSide(GeneratorSide side) {
		if(side != GeneratorSide.BOTTOM && side != GeneratorSide.TOP) return true;
		else return false;
	}

}
