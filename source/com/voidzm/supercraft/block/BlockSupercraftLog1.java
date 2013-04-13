package com.voidzm.supercraft.block;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockSupercraftLog1 extends BlockSupercraftLogBase {

	public BlockSupercraftLog1(int par1) {
		super(par1);
		this.setUnlocalizedName("supercraftLog1");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0][0] = par1IconRegister.registerIcon("supercraft:woodolive_side");
		textures[0][1] = par1IconRegister.registerIcon("supercraft:woodolive_end");
		textures[1][0] = par1IconRegister.registerIcon("supercraft:woodgoldenwood_side");
		textures[1][1] = par1IconRegister.registerIcon("supercraft:woodgoldenwood_end");
		textures[2][0] = par1IconRegister.registerIcon("supercraft:woodtenebria_side");
		textures[2][1] = par1IconRegister.registerIcon("supercraft:woodtenebria_end");
		textures[3][0] = par1IconRegister.registerIcon("supercraft:woodtenebria_side");
		textures[3][1] = par1IconRegister.registerIcon("supercraft:woodtenebria_endcrystalline");
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
	}

}
