package com.voidzm.supercraft.block;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockGhostlyVaporFlowing extends BlockFlowing {

	public static Icon stillVapor;
	public static Icon flowingVapor;
	
	public BlockGhostlyVaporFlowing(int par1) {
		super(par1, Material.water);
		this.setHardness(100.0F);
		this.setLightValue(0.375F);
		this.setUnlocalizedName("ghostlyVapor");
		this.disableStats();
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void func_94332_a(IconRegister par1IconRegister) {
		stillVapor = par1IconRegister.func_94245_a("supercraft:ghostlyvapor");
		flowingVapor = par1IconRegister.func_94245_a("supercraft:ghostlyvapor_flow");
	}
	
	public static Icon func_94424_b(String par0Str) {
		return flowingVapor;
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return par1 != 0 && par1 != 1 ? flowingVapor : stillVapor;
	}

}
