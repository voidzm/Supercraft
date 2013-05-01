package com.voidzm.supercraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockSupercraftStone extends BlockSupercraft {

	public enum SupercraftStoneType {
		STONE(1.5F, 10.0F), LARGEBRICKS(2.0F, 10.0F), SMALLBRICKS(2.0F, 10.0F), WEAKBRICKS(1.0F, 8.0F), STRONGSTONE(3.0F, 15.0F);
		public float hardness, resistance;
		private SupercraftStoneType(float hard, float rest) {
			this.hardness = hard;
			this.resistance = rest;
		}
	}
	
	private String icon;
	
	public BlockSupercraftStone(int par1, SupercraftStoneType type, String string) {
		super(par1, Material.rock);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(type.hardness);
		this.setResistance(type.resistance);
		this.icon = string;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(this.icon);
	}

}
