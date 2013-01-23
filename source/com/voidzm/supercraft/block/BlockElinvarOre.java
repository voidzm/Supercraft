//////////////////////////////////////
//*      BlockElinvarOre.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.ItemHandler;

import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockElinvarOre extends BlockOre {

	public BlockElinvarOre(int par1) {
		super(par1, 12);
		this.setHardness(1.0F);
		this.setResistance(3.0F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockName("elinvarOre");
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return ItemHandler.elinvarDust.itemID;
	}
	
}
