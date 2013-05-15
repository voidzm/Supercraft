package com.voidzm.supercraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

import com.voidzm.supercraft.Supercraft;

public class BlockBoundStone extends BlockSupercraft {

	private String iconString;
	
	public BlockBoundStone(int par1, String icon) {
		super(par1, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(Supercraft.elinvarTab);
		this.iconString = icon;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(this.iconString);
	}

}
