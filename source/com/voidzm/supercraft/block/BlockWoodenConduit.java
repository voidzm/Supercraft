//////////////////////////////////////
//*    BlockWoodenConduit.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.entity.TileEntityConduit.CONDUIT_TYPE;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWoodenConduit extends BlockConduit {

	public BlockWoodenConduit(int par1) {
		super(par1, 32, Material.wood);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setBlockName("woodenConduit");
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
	@Override
	public TileEntity createNewTileEntity(World par1World) {
		try {
			return new TileEntityConduit(CONDUIT_TYPE.WOOD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
