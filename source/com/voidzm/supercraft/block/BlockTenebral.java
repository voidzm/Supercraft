package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTenebral extends BlockSupercraft {

	public BlockTenebral(int par1) {
		super(par1, Material.rock);
		this.setHardness(4.0F);
		this.setResistance(15.0F);
		this.setLightValue(0.6F);
		this.setStepSound(soundStoneFootstep);
		this.setInternalName("blocktenebral");
		this.setExternalName("Block of the Tenebral");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:blocktenebral");
	}

}
