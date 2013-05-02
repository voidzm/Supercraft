package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockSupercraftStone extends BlockSupercraft {

	public enum SupercraftStoneType {
		STONE(1.5F, 10.0F), LARGEBRICKS(2.0F, 10.0F), SMALLBRICKS(2.0F, 10.0F), WEAKBRICKS(1.0F, 8.0F), STRONGSTONE(3.0F, 15.0F), ESSENTIAL(4.0F, 15.0F);
		public float hardness, resistance;
		private SupercraftStoneType(float hard, float rest) {
			this.hardness = hard;
			this.resistance = rest;
		}
	}
	
	private String icon;
	
	public BlockSupercraftStone(int par1, SupercraftStoneType type, String string, boolean ticks) {
		super(par1, Material.rock);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(type.hardness);
		this.setResistance(type.resistance);
		this.icon = string;
		this.setTickRandomly(ticks);
		if(type == SupercraftStoneType.ESSENTIAL) this.setLightValue(0.625F);
	}
	
	public BlockSupercraftStone(int par1, SupercraftStoneType type, String string) {
		this(par1, type, string, false);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(this.icon);
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
