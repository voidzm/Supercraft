//////////////////////////////////////
//*        BlockSilver.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockSilver extends Block {

	public BlockSilver(int id) {
		super(id, Material.iron);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setUnlocalizedName("silverBlock");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:blocksilver");
	}
	
}
