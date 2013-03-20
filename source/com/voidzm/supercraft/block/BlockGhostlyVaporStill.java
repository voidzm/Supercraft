package com.voidzm.supercraft.block;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockGhostlyVaporStill extends BlockStationary {

	public static Icon stillVapor;
	public static Icon flowingVapor;
	
	public BlockGhostlyVaporStill(int par1) {
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
		return stillVapor;
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return par1 != 0 && par1 != 1 ? flowingVapor : stillVapor;
	}

}
