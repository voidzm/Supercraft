//**
//**  BlockImpactGlass.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockImpactGlass extends BlockSupercraftGlass {

	public BlockImpactGlass(int id) {
		super(id, "supercraft:impactglass");
		this.setHardness(3.0F);
		this.setResistance(18.0F);
		this.setInternalName("impactglass");
		this.setExternalName("Impact Glass");
	}

}