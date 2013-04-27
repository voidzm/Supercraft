package com.voidzm.supercraft.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.item.ItemBlockSupercraft;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupercraftLeaves1 extends BlockSupercraftColoredLeavesBase {

	private ArrayList<String> names = new ArrayList<String>();
	
	protected Icon[] textures = new Icon[4];
	
	public BlockSupercraftLeaves1(int par1) {
		super(par1);
		this.populateNames();
		this.setInternalName("supercraftLeaves1");
		this.setExternalName("Supercraft Leaves 1");
		this.makeMultiblock(this.names, ItemBlockSupercraft.class);
	}
	
	private void populateNames() {
		this.names.add("Olive Leaves");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:leavesolive_fancy");
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		int leavesCode = par2 & 3;
		return textures[leavesCode];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return BlockHandler.supercraftSapling1.blockID;
	}

}
