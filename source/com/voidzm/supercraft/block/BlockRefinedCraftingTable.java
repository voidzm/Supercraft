//////////////////////////////////////
//* BlockRefinedCraftingTable.java *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.List;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.gui.SCGuiCrafting;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockRefinedCraftingTable extends Block {

	public BlockRefinedCraftingTable(int par1) {
		super(par1, 80, Material.rock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockName("refinedCraftingTable");
		this.setRequiresSelfNotify();
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		if(par2 == 0) {
			if(par1 == 0) return 62;
			if(par1 == 1) return 56;
			else return 57;
		}
		else if(par2 == 1) {
			if(par1 == 0) return 62;
			if(par1 == 1) return 58;
			else return 59;
		}
		else if(par2 == 2) {
			if(par1 == 0) return 62;
			if(par1 == 1) return 60;
			else return 61;
		}
		else if(par2 == 3) {
			if(par1 == 0) return 65;
			if(par1 == 1) return 63;
			else return 64;
		}
		else if(par2 == 4) {
			if(par1 == 0) return 68;
			if(par1 == 1) return 66;
			else return 67;
		}
		else return 255;
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
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if(par1World.isRemote) return true;
		par5EntityPlayer.openGui(Supercraft.instance, 0, par1World, par2, par3, par4);
		return true;
	}

}
