//**
//**  BlockReinforcedGlass.java
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

public class BlockReinforcedGlass extends BlockSupercraftGlass {

	public BlockReinforcedGlass(int id) {
		super(id, "supercraft:reinforcedglass");
		this.setHardness(1.0F);
		this.setResistance(10.0F);
		this.setInternalName("reinforcedglass");
		this.setExternalName("Reinforced Glass");
	}

}
