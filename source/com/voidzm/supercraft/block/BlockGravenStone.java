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

public class BlockGravenStone extends BlockSupercraft {
	
	public Icon[] textures = new Icon[4];
	
	private ArrayList<String> names = new ArrayList<String>();
	
	public BlockGravenStone(int par1) {
		super(par1, Material.rock);
		this.populateNames();
		this.setHardness(3.0F);
		this.setResistance(20.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setInternalName("gravenStone");
		this.setExternalName("Graven Stone");
		this.makeMultiblock(this.names, ItemBlockSupercraft.class);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	private void populateNames() {
		this.names.add("Graven Stone of Darkness");
		this.names.add("Graven Stone of Shadow");
		this.names.add("Graven Stone of Gleaming");
		this.names.add("Graven Stone of Brilliance");
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:gravenstone_darkness");
		textures[1] = par1IconRegister.registerIcon("supercraft:gravenstone_shadow");
		textures[2] = par1IconRegister.registerIcon("supercraft:gravenstone_gleaming");
		textures[3] = par1IconRegister.registerIcon("supercraft:gravenstone_brilliance");
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return this.textures[par2];
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
	}

}
