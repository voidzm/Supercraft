//////////////////////////////////////
//*    BlockSupercraftPlanks.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.List;

import com.voidzm.supercraft.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockSupercraftPlanks extends Block {

	protected Icon[] textures = new Icon[16];
	
	public BlockSupercraftPlanks(int par1) {
		super(par1, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setUnlocalizedName("supercraftPlanks");
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:planksolive");
		textures[1] = par1IconRegister.registerIcon("supercraft:planksgoldenwood");
		textures[2] = par1IconRegister.registerIcon("supercraft:plankstenebria");
		textures[3] = par1IconRegister.registerIcon("supercraft:planksinisia");
		textures[4] = par1IconRegister.registerIcon("supercraft:planksvalensien");
		textures[5] = par1IconRegister.registerIcon("supercraft:planksmortalia");
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return textures[meta];
	}
	
	public int damageDropped(int par1) {
		return par1;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
		par3List.add(new ItemStack(par1, 1, 4));
		par3List.add(new ItemStack(par1, 1, 5));
	}
	
}
