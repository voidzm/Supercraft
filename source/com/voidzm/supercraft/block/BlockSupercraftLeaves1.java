package com.voidzm.supercraft.block;

import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupercraftLeaves1 extends BlockSupercraftColoredLeavesBase {

	public static final String[] leavesType = new String[] {"olive"};
	
	public BlockSupercraftLeaves1(int par1) {
		super(par1, 16);
		this.setBlockName("supercraftLeaves1");
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}
	
	public int idDropped(int par1, Random par2Random, int par3) {
		return BlockHandler.supercraftSapling1.blockID;
	}

}