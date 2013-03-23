package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInscribedNightrock extends Block {

	public BlockInscribedNightrock(int par1) {
		super(par1, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("inscribedNightrock");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:inscribednightrock");
	}

}
