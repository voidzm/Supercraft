//////////////////////////////////////
//*   BlockSupercraftSapling.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.gen.WorldGenOlive;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockSupercraftSapling extends BlockFlower {

	public static final String[] saplingTypes = new String[] {"olive"};
	private static int textureIndex = 8;

    public BlockSupercraftSapling(int par1) {
        super(par1, textureIndex);
        float var3 = 0.4F;
        this.setTickRandomly(true);
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.0F);
        this.setStepSound(Block.soundGrassFootstep);
        this.setBlockName("supercraftSapling");
        this.setRequiresSelfNotify();
    }
	
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if(!par1World.isRemote) {
            super.updateTick(par1World, par2, par3, par4, par5Random);
            if(par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0) {
                int var6 = par1World.getBlockMetadata(par2, par3, par4);
                if((var6 & 8) == 0) {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 8);
                }
                else {
                    this.growTree(par1World, par2, par3, par4, par5Random);
                }
            }
        }
    }
    
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        par2 &= 3;
        return par2 == 0 ? 8 : 8;
    }
    
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
        if(!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) return;
        WorldGenerator treeGenerator = null;
        int metadata = par1World.getBlockMetadata(par2, par3, par4) & 3;
        if(metadata == 0) {
        	treeGenerator = new WorldGenOlive();
        }
        treeGenerator.generate(par1World, par5Random, par2, par3, par4);
    }
    
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5) {
        return par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    public int damageDropped(int par1) {
        return par1 & 3;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }
    
    @Override
    public String getTextureFile() {
    	return CommonProxy.BLOCKS_PNG;
	}
    
}

