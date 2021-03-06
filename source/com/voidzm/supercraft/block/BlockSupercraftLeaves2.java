package com.voidzm.supercraft.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.item.ItemBlockSupercraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupercraftLeaves2 extends BlockSupercraftLeavesBase {

	private ArrayList<String> names = new ArrayList<String>();
	
	protected Icon[] textures = new Icon[4];
	
	public BlockSupercraftLeaves2(int par1) {
		super(par1);
		this.populateNames();
		this.setInternalName("supercraftleaves2");
		this.setExternalName("Supercraft Leaves 2");
		this.makeMultiblock(this.names, ItemBlockSupercraft.class);
	}
	
	private void populateNames() {
		this.names.add("Goldenwood Leaves");
		this.names.add("Tenebria Leaves");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:leavesgoldenwood_fancy");
		textures[1] = par1IconRegister.registerIcon("supercraft:leavestenebria_fancy");
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
		par3List.add(new ItemStack(par1, 1, 1));
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return BlockHandler.supercraftSapling2.blockID;
	}

}
