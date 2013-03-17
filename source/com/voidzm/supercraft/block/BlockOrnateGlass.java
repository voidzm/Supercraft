//////////////////////////////////////
//*      BlockOrnateGlass.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockOrnateGlass extends BlockSupercraftGlass {

	public BlockOrnateGlass(int id) {
		super(id, "supercraft:ornateglass");
		this.setHardness(10.0F);
		this.setResistance(1.0F);
		this.setUnlocalizedName("ornateGlass");
	}

}