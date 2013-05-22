package com.voidzm.supercraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;

import com.voidzm.supercraft.gen.WorldGenDeepTree;
import com.voidzm.supercraft.handler.BlockHandler;

public class BlockSupercraftMushroom extends BlockSupercraftFlower {

	public String iconLocation;
	
	private EnumPlantType plantType;
	
	public BlockSupercraftMushroom(int par1, String par2, EnumPlantType type) {
		super(par1, par2, type);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.iconLocation = par2;
		this.plantType = type;
		float f = 0.2F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}
	
	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return this.plantType;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(this.iconLocation);
	}
	
	public static void doGrow(World par1World, int par2, int par3, int par4, Random par5Random) {
		WorldGenDeepTree gen = new WorldGenDeepTree();
		int bID = par1World.getBlockId(par2, par3, par4);
		int type = -1;
		if(bID == BlockHandler.inisiaMushroom.blockID) {
			type = 0;
		}
		else if(bID == BlockHandler.valensienMushroom.blockID) {
			type = 1;
		}
		else if(bID == BlockHandler.mortaliaMushroom.blockID) {
			type = 2;
		}
		if(type == -1) return;
		gen.generate(par1World, par5Random, par2, par3, par4, type);
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(par5Random.nextInt(8) == 0) {
			if(par5Random.nextInt(3) == 0) {
				BlockSupercraftMushroom.doGrow(par1World, par2, par3, par4, par5Random);
			}
			else {
				byte b0 = 4;
				int l = 16;
				int i1;
				int j1;
				int k1;
				for (i1 = par2 - b0; i1 <= par2 + b0; ++i1) {
					for (j1 = par4 - b0; j1 <= par4 + b0; ++j1) {
						for (k1 = par3 - 1; k1 <= par3 + 1; ++k1) {
							if (par1World.getBlockId(i1, k1, j1) == this.blockID) {
								--l;
								if (l <= 0)
									return;
							}
						}
					}
				}
				i1 = par2 + par5Random.nextInt(3) - 1;
				j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
				k1 = par4 + par5Random.nextInt(3) - 1;
				for (int l1 = 0; l1 < 4; ++l1) {
					if (par1World.isAirBlock(i1, j1, k1)
							&& this.canBlockStay(par1World, i1, j1, k1)) {
						par2 = i1;
						par3 = j1;
						par4 = k1;
					}
					i1 = par2 + par5Random.nextInt(3) - 1;
					j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
					k1 = par4 + par5Random.nextInt(3) - 1;
				}
				if (par1World.isAirBlock(i1, j1, k1)
						&& this.canBlockStay(par1World, i1, j1, k1)) {
					par1World.setBlock(i1, j1, k1, this.blockID);
				}
			}
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4){
		return super.canPlaceBlockAt(par1World, par2, par3, par4) && this.canBlockStay(par1World, par2, par3, par4);
	}
	
	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int par1){
		return Block.opaqueCubeLookup[par1];
	}
	
	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		if(par3 >= 0 && par3 < 256) {
			int l = par1World.getBlockId(par2, par3 - 1, par4);
			Block soil = Block.blocksList[l];
			return (l == Block.mycelium.blockID || par1World.getFullBlockLightValue(par2, par3, par4) < 13) && (soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this));
		}
		else return false;
	}

}
