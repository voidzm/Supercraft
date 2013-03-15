package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockNightrockBricks extends Block {

	public BlockNightrockBricks(int par1) {
		super(par1, Material.rock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("nightrockBricks");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void func_94332_a(IconRegister par1IconRegister) {
		field_94336_cN = par1IconRegister.func_94245_a("supercraft:nightrockbricks");
	}

}
