package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockPalestoneBricks extends BlockSupercraft {

	public BlockPalestoneBricks(int par1) {
		super(par1, Material.rock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundStoneFootstep);
		this.setTickRandomly(true);
		this.setInternalName("palestonebricks");
		this.setExternalName("Palestone Bricks");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:palestonebricks");
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(par2 < 255 && (par1World.getBlockId(par2, par3+1, par4) == Block.waterStill.blockID || par1World.getBlockId(par2, par3+1, par4) == Block.waterMoving.blockID)) {
			if(par5Random.nextInt(10) == 0) {
				par1World.setBlock(par2, par3, par4, BlockHandler.overgrownPalestoneBricks.blockID);
				return;
			}
		}
		else if(par5Random.nextInt(40) == 0) {
			if(par1World.getBlockId(par2+1, par3, par4) == Block.waterStill.blockID || par1World.getBlockId(par2+1, par3, par4) == Block.waterMoving.blockID) {
				par1World.setBlock(par2, par3, par4, BlockHandler.overgrownPalestoneBricks.blockID);
				return;
			}
			if(par1World.getBlockId(par2-1, par3, par4) == Block.waterStill.blockID || par1World.getBlockId(par2-1, par3, par4) == Block.waterMoving.blockID) {
				par1World.setBlock(par2, par3, par4, BlockHandler.overgrownPalestoneBricks.blockID);
				return;
			}
			if(par1World.getBlockId(par2, par3, par4+1) == Block.waterStill.blockID || par1World.getBlockId(par2, par3, par4+1) == Block.waterMoving.blockID) {
				par1World.setBlock(par2, par3, par4, BlockHandler.overgrownPalestoneBricks.blockID);
				return;
			}
			if(par1World.getBlockId(par2, par3, par4-1) == Block.waterStill.blockID || par1World.getBlockId(par2, par3, par4-1) == Block.waterMoving.blockID) {
				par1World.setBlock(par2, par3, par4, BlockHandler.overgrownPalestoneBricks.blockID);
				return;
			}
		}
	}

}
