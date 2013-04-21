package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBurnedNightrockBricks extends BlockSupercraft {

	public BlockBurnedNightrockBricks(int par1) {
		super(par1, Material.rock);
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundStoneFootstep);
		this.setInternalName("burnednightrockbricks");
		this.setExternalName("Burned Nightrock Bricks");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 0;
	}
	
	@Override
	public boolean canSilkHarvest() {
		return true;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:burnednightrockbricks");
	}

}
