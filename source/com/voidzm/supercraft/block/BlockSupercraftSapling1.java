package com.voidzm.supercraft.block;

import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.gen.WorldGenOlive;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockSupercraftSapling1 extends BlockSupercraftSaplingBase {

	protected Icon[] textures = new Icon[4];
	
	public BlockSupercraftSapling1(int par1) {
		super(par1);
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:saplingolive");
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return textures[meta];
	}
	
	@Override
	public void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
        if(!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) return;
        WorldGenerator treeGenerator = null;
        int metadata = par1World.getBlockMetadata(par2, par3, par4) & 3;
        if(metadata == 0) {
        	treeGenerator = new WorldGenOlive();
        	((WorldGenOlive)treeGenerator).generate(par1World, par5Random, par2, par3, par4, false);
        }
    }
	
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

}
