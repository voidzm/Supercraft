//////////////////////////////////////
//*     BlockDiamondConduit.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.entity.TileEntityConduit.CONDUIT_TYPE;

public class BlockDiamondConduit extends BlockConduit {

	public BlockDiamondConduit(int par1) {
		super(par1, 40, Material.rock);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setBlockName("diamondConduit");
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
	@Override
	public TileEntity createNewTileEntity(World par1World) {
		try {
			return new TileEntityConduit(CONDUIT_TYPE.DIAMOND);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
