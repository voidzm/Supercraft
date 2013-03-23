//////////////////////////////////////
//*         BlockCoal.java         *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.MinecraftFakeLauncher;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

public class BlockCoal extends Block {

	public BlockCoal(int id) {
		super(id, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName("coalBlock");
		this.setCreativeTab(CreativeTabs.tabBlock);
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 1);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:blockcoal");
	}
	
}
