package com.voidzm.supercraft.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockSupercraftLogBase extends BlockSupercraft {

	protected Icon[][] textures = new Icon[4][2];
	
	public BlockSupercraftLogBase(int par1) {
		super(par1, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setInternalName("supercraftlogbase");
	}
	
	public int getRenderType() {
		return 31;
	}
	 
	public int quantityDropped(Random par1Random) {
		return 1;
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		byte var7 = 4;
		int var8 = var7 + 1;
		if(par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
			for(int var9 = -var7; var9 <= var7; ++var9) {
				for(int var10 = -var7; var10 <= var7; ++var10) {
					for(int var11 = -var7; var11 <= var7; ++var11) {
						int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);
						if(Block.blocksList[var12] != null) {
							Block.blocksList[var12].beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
						}
					}
				}
			}
		}
	}
	
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
		int var10 = par9 & 3;
		byte var11 = 0;
		switch(par5) {
		case 0:
		case 1:
			var11 = 0;
			break;
		case 2:
		case 3:
			var11 = 8;
			break;
		case 4:
		case 5:
			var11 = 4;
		}
		return var10 | var11;
	}
	
	public void registerIcons(IconRegister par1IconRegister) {} // Overridden in subclasses.

	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		int logCode = meta & 3;
		int rotationCode = meta & 12;
		Icon logSide, logEnd;
		logSide = textures[logCode][0];
		logEnd = textures[logCode][1];
		if(rotationCode == 0) {
			Icon val = null;
			switch(side) {
			case 0:
			case 1:
				val = logEnd;
				break;
			case 2:
			case 3:
				val = logSide;
				break;
			case 4:
			case 5:
				val = logSide;
				break;
			}
			return val;
		}
		else if(rotationCode == 4) {
			Icon val = null;
			switch(side) {
			case 0:
			case 1:
				val = logSide;
				break;
			case 2:
			case 3:
				val = logSide;
				break;
			case 4:
			case 5:
				val = logEnd;
				break;
			}
			return val;
		}
		else if(rotationCode == 8) {
			Icon val = null;
			switch(side) {
			case 0:
			case 1:
				val = logSide;
				break;
			case 2:
			case 3:
				val = logEnd;
				break;
			case 4:
			case 5:
				val = logSide;
				break;
			}
			return val;
		}
		else {
			Icon val = null;
			switch(side) {
			case 0:
			case 1:
				val = logSide;
				break;
			case 2:
			case 3:
				val = logSide;
				break;
			case 4:
			case 5:
				val = logSide;
				break;
			}
			return val;
		}
	}
	
	public int damageDropped(int par1) {
		return par1 & 3;
	}

	public static int limitToValidMetadata(int par0){
		return par0 & 3;
	}

	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {} // Overridden in subclasses.

	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 1, limitToValidMetadata(par1));
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isWood(World world, int x, int y, int z) {
		return true;
	}

}
