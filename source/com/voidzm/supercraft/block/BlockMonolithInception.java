package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockMonolithInception extends Block {

	private final boolean isActivated;
	
	public BlockMonolithInception(int par1, boolean par2) {
		super(par1, Material.rock);
		this.setHardness(4.0F);
		this.setResistance(20.0F);
		this.isActivated = par2;
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("monolithInception");
		if(!par2) this.setCreativeTab(CreativeTabs.tabBlock);
		if(par2) {
			this.setUnlocalizedName("monolithInceptionActivated");
			this.setLightValue(0.75F);
		}
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		if(isActivated) this.blockIcon = par1IconRegister.registerIcon("supercraft:monolithinception_on");
		else this.blockIcon = par1IconRegister.registerIcon("supercraft:monolithinception");
	}
	
	public int idDropped(int par1, Random par2Random, int par3) {
		return BlockHandler.monolithInception.blockID;
	}
	
}