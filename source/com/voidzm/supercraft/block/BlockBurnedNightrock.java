package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBurnedNightrock extends Block {

	public BlockBurnedNightrock(int par1) {
		super(par1, Material.rock);
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("burnedNightrock");
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
	
	public void func_94332_a(IconRegister par1IconRegister) {
		field_94336_cN = par1IconRegister.func_94245_a("supercraft:burnednightrockbricks");
	}

}
