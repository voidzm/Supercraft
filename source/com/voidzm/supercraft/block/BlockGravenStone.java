package com.voidzm.supercraft.block;

import java.util.List;

import com.voidzm.supercraft.Supercraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockGravenStone extends Block {
	
	public Icon[] textures = new Icon[4];
	
	public BlockGravenStone(int par1) {
		super(par1, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(20.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName("gravenStone");
		this.setCreativeTab(CreativeTabs.tabBlock);
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
