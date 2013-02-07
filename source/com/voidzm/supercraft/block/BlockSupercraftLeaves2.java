package com.voidzm.supercraft.block;

import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupercraftLeaves2 extends BlockSupercraftLeavesBase {

	public static final String[] leavesType = new String[] {"goldenwood"};
	
	public BlockSupercraftLeaves2(int par1) {
		super(par1, 80);
		this.setBlockName("supercraftLeaves2");
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}
	
	public int idDropped(int par1, Random par2Random, int par3) {
		return BlockHandler.supercraftSapling.blockID;
	}

}
