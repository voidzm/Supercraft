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

public class BlockSupercraftSaplingBase extends BlockSupercraftFlower {

    public BlockSupercraftSaplingBase(int par1) {
        super(par1);
        float var3 = 0.4F;
        this.setTickRandomly(true);
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.0F);
        this.setStepSound(Block.soundGrassFootstep);
        this.setInternalName("supercraftsaplingbase");
        this.setExternalName("Supercraft Sapling Base");
    }
	
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if(!par1World.isRemote) {
            super.updateTick(par1World, par2, par3, par4, par5Random);
            if(par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0) {
                int var6 = par1World.getBlockMetadata(par2, par3, par4);
                if((var6 & 8) == 0) {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 8, 3);
                }
                else {
                    this.growTree(par1World, par2, par3, par4, par5Random);
                }
            }
        }
    }
    
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
    	// Subclasses override this
    }
    
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5) {
        return par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    @Override
    public int damageDropped(int par1) {
        return par1 & 3;
    }
	
}
