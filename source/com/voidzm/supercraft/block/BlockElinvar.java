//////////////////////////////////////
//*        BlockElinvar.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.MinecraftFakeLauncher;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

public class BlockElinvar extends Block {

	public BlockElinvar(int id) {
		super(id, 13, Material.rock);
		this.setHardness(2.0F);
		this.setResistance(4.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setBlockName("elinvarBlock");
		this.setCreativeTab(CreativeTabs.tabBlock);
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 2);
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
}
