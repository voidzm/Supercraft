package com.voidzm.supercraft.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.voidzm.supercraft.gen.WorldGenGoldenwood;
import com.voidzm.supercraft.gen.WorldGenTenebria;
import com.voidzm.supercraft.item.ItemBlockSupercraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupercraftSapling2 extends BlockSupercraftSaplingBase {

	private ArrayList<String> names = new ArrayList<String>();
	
	protected Icon[] textures = new Icon[4];
	
	public BlockSupercraftSapling2(int par1) {
		super(par1);
		this.populateNames();
		this.setInternalName("supercraftsapling2");
		this.setExternalName("Supercraft Sapling 2");
		this.makeMultiblock(this.names, ItemBlockSupercraft.class);
	}
	
	private void populateNames() {
		this.names.add("Goldenwood Sapling");
		this.names.add("Tenebria Sapling");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:saplinggoldenwood");
		textures[1] = par1IconRegister.registerIcon("supercraft:saplingtenebria");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		return textures[meta & 3];
	}
	
	@Override
	public void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
        if(!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) return;
        WorldGenerator treeGenerator = null;
        int metadata = par1World.getBlockMetadata(par2, par3, par4) & 3;
        if(metadata == 0) {
        	treeGenerator = new WorldGenGoldenwood();
        	((WorldGenGoldenwood)treeGenerator).generate(par1World, par5Random, par2, par3, par4, false);
        }
        else if(metadata == 1) {
        	treeGenerator = new WorldGenTenebria();
        	((WorldGenTenebria)treeGenerator).generate(par1World, par5Random, par2, par3, par4, false);
        }
    }
	
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }

}
