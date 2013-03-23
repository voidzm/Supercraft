//////////////////////////////////////
//*    BlockSupercraftSlab2.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockSupercraftSlab2 extends BlockSupercraftSlabBase {

	private final String[] types = new String[] {"palestone", "nightrock"};
	
	protected Icon[] textures = new Icon[8];
	
	public BlockSupercraftSlab2(int par1) {
		super(par1, Material.rock);
		this.setUnlocalizedName("supercraftSlab2");
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:palestone");
		textures[1] = par1IconRegister.registerIcon("supercraft:nightrock");
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return textures[meta];
	}
	
	public String getFullSlabName(int par1) {
		if(par1 < 0 || par1 >= types.length) {
			par1 = 0;
		}
		return super.getUnlocalizedName() + "." + types[par1];
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        if(par1 == BlockHandler.supercraftSlab2.blockID) {
        	par3List.add(new ItemStack(par1, 1, 0));
        	par3List.add(new ItemStack(par1, 1, 1));
        }
    }
	
}

