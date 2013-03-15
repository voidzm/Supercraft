package com.voidzm.supercraft.block;

import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupercraftLeaves1 extends BlockSupercraftColoredLeavesBase {

	public static final String[] leavesType = new String[] {"olive"};
	
	protected Icon[] textures = new Icon[4];
	
	public BlockSupercraftLeaves1(int par1) {
		super(par1);
		this.setUnlocalizedName("supercraftLeaves1");
	}
	
	public void func_94332_a(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.func_94245_a("supercraft:leavesolive_fancy");
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		int leavesCode = par2 & 3;
		return textures[leavesCode];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}
	
	public int idDropped(int par1, Random par2Random, int par3) {
		return BlockHandler.supercraftSapling1.blockID;
	}

}
