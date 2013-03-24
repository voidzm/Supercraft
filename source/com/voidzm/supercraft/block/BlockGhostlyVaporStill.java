package com.voidzm.supercraft.block;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class BlockGhostlyVaporStill extends BlockStationary {

	public static Icon stillVapor;
	public static Icon flowingVapor;
	
	public BlockGhostlyVaporStill(int par1) {
		super(par1, Material.water);
		this.setHardness(100.0F);
		this.setLightValue(0.375F);
		this.setUnlocalizedName("ghostlyVapor");
		this.disableStats();
		LiquidDictionary.getOrCreateLiquid("Ghostly Vapor", new LiquidStack(this, LiquidContainerRegistry.BUCKET_VOLUME));
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		stillVapor = par1IconRegister.registerIcon("supercraft:ghostlyvapor");
		flowingVapor = par1IconRegister.registerIcon("supercraft:ghostlyvapor_flow");
	}
	
	public static Icon func_94424_b(String par0Str) {
		return stillVapor;
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return par1 != 0 && par1 != 1 ? flowingVapor : stillVapor;
	}

}