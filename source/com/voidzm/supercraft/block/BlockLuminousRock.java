package com.voidzm.supercraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

import com.voidzm.supercraft.handler.ItemHandler;

public class BlockLuminousRock extends Block {

	public BlockLuminousRock(int par1) {
		super(par1, Material.rock);
		this.setHardness(1.0F);
		this.setResistance(3.0F);
		this.setLightValue(0.675F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("luminousRock");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:luminousrock");
	}
	
}
