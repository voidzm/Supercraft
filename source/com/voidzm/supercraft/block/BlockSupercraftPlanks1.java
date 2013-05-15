//////////////////////////////////////
//*    BlockSupercraftPlanks.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.voidzm.supercraft.item.ItemBlockSupercraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupercraftPlanks1 extends BlockSupercraft {

	private ArrayList<String> names = new ArrayList<String>();
	
	protected Icon[] textures = new Icon[16];
	
	public BlockSupercraftPlanks1(int par1) {
		super(par1, Material.wood);
		this.populateNames();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setInternalName("supercraftplanks1");
		this.setExternalName("Supercraft Planks 1");
		this.makeMultiblock(this.names, ItemBlockSupercraft.class);
	}
	
	private void populateNames() {
		this.names.add("Olive Wood Planks");
		this.names.add("Goldenwood Wood Planks");
		this.names.add("Tenebria Wood Planks");
		this.names.add("Inisia Wood Planks");
		this.names.add("Valensien Wood Planks");
		this.names.add("Mortalia Wood Planks");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:planksolive");
		textures[1] = par1IconRegister.registerIcon("supercraft:planksgoldenwood");
		textures[2] = par1IconRegister.registerIcon("supercraft:plankstenebria");
		textures[3] = par1IconRegister.registerIcon("supercraft:planksinisia");
		textures[4] = par1IconRegister.registerIcon("supercraft:planksvalensien");
		textures[5] = par1IconRegister.registerIcon("supercraft:planksmortalia");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		return textures[meta];
	}
	
	@Override
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
