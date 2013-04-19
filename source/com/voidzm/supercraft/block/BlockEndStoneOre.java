//**
//**  BlockEndStoneOre.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.block;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockEndStoneOre extends BlockSupercraft {

	private String iconString;
	private int itemDropID = -1;
	private int randomExtraDrop = 0;
	
	public BlockEndStoneOre(int par1, String icon, int dropItem) {
		super(par1, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.iconString = icon;
		this.itemDropID = dropItem+256;
	}
	
	public BlockEndStoneOre(int par1, String icon) {
		this(par1, icon, -1);
	}
	
	public BlockEndStoneOre setExtraDrop(int val) {
		this.randomExtraDrop = val;
		return this;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(iconString);
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		if(this.itemDropID != -1) return this.itemDropID;
		else return super.idDropped(par1, par2Random, par3);
	}
	
	@Override
	public int quantityDropped(Random par1Random) {
		return par1Random.nextInt(randomExtraDrop+1)+1;
	}
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z) {
		return false;
	}
	
	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random){
		if(par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1)) {
			int j = par2Random.nextInt(par1 + 2) - 1;
			if(j < 0){
				j = 0;
			}
			return this.quantityDropped(par2Random) * (j + 1);
        }
        else {
        	return this.quantityDropped(par2Random);
        }
	}

}
