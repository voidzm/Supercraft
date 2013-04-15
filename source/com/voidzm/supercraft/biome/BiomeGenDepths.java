//////////////////////////////////////
//*       BiomeGenDepths.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.biome;

import java.util.Random;

import com.voidzm.supercraft.gen.WorldGenDeepVines;
import com.voidzm.supercraft.gen.WorldGenScatteredFlowers;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenDepths extends BiomeGenBase {

	public BiomeGenDepths(int par1) {
		super(par1);
		this.setBiomeName("Depths");
		this.spawnableWaterCreatureList.clear();
	}
	
	@Override
	public int getSkyColorByTemp(float par1) {
		return 0x000000;
	}
	
	@Override
	public int getBiomeGrassColor() {
		return 0xB8BFBA;
	}
	
	@Override
	public int getBiomeFoliageColor() {
		return 0xB8BFBA;
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		if(par2Random.nextInt(4) == 0) {
			int xPos = par3 + par2Random.nextInt(16) + 8;
			int zPos = par4 + par2Random.nextInt(16) + 8;
			for(int iy = 126; iy > 64; iy--) {
				int bID = par1World.getBlockId(xPos, iy, zPos);
				if(bID == 0) {
					if(iy >= 125) {
						par1World.setBlock(xPos+1, iy, zPos, Block.bedrock.blockID);
						par1World.setBlock(xPos-1, iy, zPos, Block.bedrock.blockID);
						par1World.setBlock(xPos, iy, zPos+1, Block.bedrock.blockID);
						par1World.setBlock(xPos, iy, zPos-1, Block.bedrock.blockID);
						par1World.setBlock(xPos, iy, zPos, BlockHandler.ghostlyVaporFlowing.blockID, 0, 2);
						par1World.scheduledUpdatesAreImmediate = true;
		                BlockHandler.ghostlyVaporFlowing.updateTick(par1World, xPos, iy, zPos, par2Random);
		                par1World.scheduledUpdatesAreImmediate = false;
		                break;
					}
					if(par1World.getBlockId(xPos, iy+1, zPos) == Block.stone.blockID) {
						if((par1World.getBlockId(xPos+1, iy+1, zPos) == Block.stone.blockID && par1World.getBlockId(xPos-1, iy+1, zPos) == Block.stone.blockID) && (par1World.getBlockId(xPos, iy+1, zPos+1) == Block.stone.blockID && par1World.getBlockId(xPos, iy+1, zPos-1) == Block.stone.blockID)) {
							par1World.setBlock(xPos, iy+1, zPos, BlockHandler.ghostlyVaporFlowing.blockID, 0, 2);
							par1World.scheduledUpdatesAreImmediate = true;
			                BlockHandler.ghostlyVaporFlowing.updateTick(par1World, xPos, iy+1, zPos, par2Random);
			                par1World.scheduledUpdatesAreImmediate = false;
			                break;
						}
					}
				}
			}
		}
		for(int i = 0; i < 1; i++) {
			WorldGenMinable palestoneGen = new WorldGenMinable(BlockHandler.palestone.blockID, 12);
			int x = par3 + par2Random.nextInt(16);
			int y = par2Random.nextInt(128);
			int z = par4 + par2Random.nextInt(16);
			palestoneGen.generate(par1World, par2Random, x, y, z);
		}
		for(int i = 0; i < 1; i++) {
			WorldGenMinable nightrockGen = new WorldGenMinable(BlockHandler.nightrock.blockID, 12);
			int x = par3 + par2Random.nextInt(16);
			int y = par2Random.nextInt(128);
			int z = par4 + par2Random.nextInt(16);
			nightrockGen.generate(par1World, par2Random, x, y, z);
		}
		for(int i = 0; i < 4; i++) {
			WorldGenMinable gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
			int x = par3 + par2Random.nextInt(16);
			int y = par2Random.nextInt(128);
			int z = par4 + par2Random.nextInt(16);
			gravelGen.generate(par1World, par2Random, x, y, z);
		}
		for(int i = 0; i < 12; i++) {
			WorldGenMinable coalGen = new WorldGenMinable(Block.oreCoal.blockID, 12);
			int x = par3 + par2Random.nextInt(16);
			int y = par2Random.nextInt(128);
			int z = par4 + par2Random.nextInt(16);
			coalGen.generate(par1World, par2Random, x, y, z);
		}
		for(int i = 0; i < 4; i++) {
			WorldGenMinable jadeGen = new WorldGenMinable(BlockHandler.jadeOre.blockID, 3);
			int x = par3 + par2Random.nextInt(16);
			int y = 104 + par2Random.nextInt(24);
			int z = par4 + par2Random.nextInt(16);
			jadeGen.generate(par1World, par2Random, x, y, z);
		}
		for(int i = 0; i < 6; i++) {
			WorldGenMinable incendiumGen = new WorldGenMinable(BlockHandler.incendiumOre.blockID, 8);
			int x = par3 + par2Random.nextInt(16);
			int y = par2Random.nextInt(128);
			int z = par4 + par2Random.nextInt(16);
			incendiumGen.generate(par1World, par2Random, x, y, z);
		}
		for(int i = 0; i < 6; i++) {
			WorldGenMinable luxificenGen = new WorldGenMinable(BlockHandler.luxificenOre.blockID, 8);
			int x = par3 + par2Random.nextInt(16);
			int y = par2Random.nextInt(128);
			int z = par4 + par2Random.nextInt(16);
			luxificenGen.generate(par1World, par2Random, x, y, z);
		}
		for(int i = 0; i < 2; i++) {
			WorldGenMinable voltasniaGen = new WorldGenMinable(BlockHandler.voltasniaOre.blockID, 4);
			int x = par3 + par2Random.nextInt(16);
			int y = par2Random.nextInt(24);
			int z = par4 + par2Random.nextInt(16);
			voltasniaGen.generate(par1World, par2Random, x, y, z);
		}
		for(int i = 0; i < 8; i++) {
			WorldGenScatteredFlowers gen = new WorldGenScatteredFlowers(BlockHandler.inisiaMushroom.blockID);
			int xloc = par3 + par2Random.nextInt(16) + 8;
			int yloc = par2Random.nextInt(120);
			int zloc = par4 + par2Random.nextInt(16) + 8;
			gen.generate(par1World, par2Random, xloc, yloc, zloc);
		}
		for(int i = 0; i < 8; i++) {
			WorldGenScatteredFlowers gen = new WorldGenScatteredFlowers(BlockHandler.valensienMushroom.blockID);
			int xloc = par3 + par2Random.nextInt(16) + 8;
			int yloc = par2Random.nextInt(120);
			int zloc = par4 + par2Random.nextInt(16) + 8;
			gen.generate(par1World, par2Random, xloc, yloc, zloc);
		}
		for(int i = 0; i < 8; i++) {
			WorldGenScatteredFlowers gen = new WorldGenScatteredFlowers(BlockHandler.mortaliaMushroom.blockID);
			int xloc = par3 + par2Random.nextInt(16) + 8;
			int yloc = par2Random.nextInt(120);
			int zloc = par4 + par2Random.nextInt(16) + 8;
			gen.generate(par1World, par2Random, xloc, yloc, zloc);
		}
		for(int i = 0; i < 16; i++) {
			WorldGenDeepVines gen = new WorldGenDeepVines();
			int xloc = par3 + par2Random.nextInt(16) + 8;
			int yloc = par2Random.nextInt(120);
			int zloc = par4 + par2Random.nextInt(16) + 8;
			gen.generate(par1World, par2Random, xloc, yloc, zloc);
		}
	}
	
}
