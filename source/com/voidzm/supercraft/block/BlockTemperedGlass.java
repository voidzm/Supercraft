//////////////////////////////////////
//*     BlockTemperedGlass.java    *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockTemperedGlass extends BlockSupercraftGlass {

	public BlockTemperedGlass(int id) {
		super(id, "supercraft:temperedglass");
		this.setHardness(0.1F);
		this.setResistance(30.0F);
		this.setUnlocalizedName("temperedGlass");
	}


}