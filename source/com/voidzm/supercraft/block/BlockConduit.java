package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.entity.TileEntityConduit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;

public class BlockConduit extends Block {
	
	public BlockConduit(int par1) {
		super(par1, 32, Material.rock);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setBlockName("conduit");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setLightOpacity(0);
		this.useNeighborBrightness[par1] = true;
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
	
	
	/*public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.updateBoundingBox(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}*/
	
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		return false;
	}
	 
	/*public AxisAlignedBB getSelectedBoundingBoxFromPool(IBlockAccess par1World, int x, int y, int z) {
		float lowX = 0.25F;
		float lowY = 0.25F;
		float lowZ = 0.25F;
		float highX = 0.75F;
		float highY = 0.75F;
		float highZ = 0.75F;
		if(par1World.getBlockId(x+1, y, z) == this.blockID) {
			highX = 1.0F;
		}
		if(par1World.getBlockId(x-1, y, z) == this.blockID) {
			lowX = 0.0F;
		}
		if(par1World.getBlockId(x, y+1, z) == this.blockID) {
			highY = 1.0F;
		}
		if(par1World.getBlockId(x, y-1, z) == this.blockID) {
			lowY = 0.0F;
		}
		if(par1World.getBlockId(x, y, z+1) == this.blockID) {
			highZ = 1.0F;
		}
		if(par1World.getBlockId(x, y, z-1) == this.blockID) {
			lowZ = 0.0F;
		}
		return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)((float)x + lowX), (double)((float)y + lowY), (double)((float)z + lowZ), (double)((float)x + highX), (double)((float)y + highY), (double)((float)z + highZ));
	}*/
	
	/*public AxisAlignedBB getCollisionBoundingBoxFromPool(IBlockAccess par1World, int x, int y, int z) {
		float lowX = 0.25F;
		float lowY = 0.25F;
		float lowZ = 0.25F;
		float highX = 0.75F;
		float highY = 0.75F;
		float highZ = 0.75F;
		if(par1World.getBlockId(x+1, y, z) == this.blockID) {
			highX = 1.0F;
		}
		if(par1World.getBlockId(x-1, y, z) == this.blockID) {
			lowX = 0.0F;
		}
		if(par1World.getBlockId(x, y+1, z) == this.blockID) {
			highY = 1.0F;
		}
		if(par1World.getBlockId(x, y-1, z) == this.blockID) {
			lowY = 0.0F;
		}
		if(par1World.getBlockId(x, y, z+1) == this.blockID) {
			highZ = 1.0F;
		}
		if(par1World.getBlockId(x, y, z-1) == this.blockID) {
			lowZ = 0.0F;
		}
		System.out.println("AABBCollision: " + lowX + ", " + highX + ", " + lowY + ", " + highY + ", " + lowZ + ", " + highZ + ".");
		return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)((float)x + lowX), (double)((float)y + lowY), (double)((float)z + lowZ), (double)((float)x + highX), (double)((float)y + highY), (double)((float)z + highZ));
	}*/
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		float lowX = 0.25F;
		float lowY = 0.25F;
		float lowZ = 0.25F;
		float highX = 0.75F;
		float highY = 0.75F;
		float highZ = 0.75F;
		if(world.getBlockId(x+1, y, z) == this.blockID) {
			highX = 1.0F;
		}
		if(world.getBlockId(x-1, y, z) == this.blockID) {
			lowX = 0.0F;
		}
		if(world.getBlockId(x, y+1, z) == this.blockID) {
			highY = 1.0F;
		}
		if(world.getBlockId(x, y-1, z) == this.blockID) {
			lowY = 0.0F;
		}
		if(world.getBlockId(x, y, z+1) == this.blockID) {
			highZ = 1.0F;
		}
		if(world.getBlockId(x, y, z-1) == this.blockID) {
			lowZ = 0.0F;
		}
		//System.out.println("Bounds: " + lowX + ", " + highX + ", " + lowY + ", " + highY + ", " + lowZ + ", " + highZ + ".");
		this.setBlockBounds(lowX, lowY, lowZ, highX, highY, highZ);
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {

	}

	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return true;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	public TileEntity createNewTileEntity(World par1World) {
		try {
			return new TileEntityConduit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getRenderType() {
		return ClientProxy.conduitRenderID;
	}

}
