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
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockRefinedCraftingTable extends Block {

	protected Icon[][] textures = new Icon[16][3];
	
	public BlockRefinedCraftingTable(int par1) {
		super(par1, Material.rock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setUnlocalizedName("refinedCraftingTable");
	}
	
	public void func_94332_a(IconRegister par1IconRegister) {
		textures[0][0] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_stonebluetop");
		textures[0][1] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_stoneblueside");
		textures[0][2] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_stonebottom");
		
		textures[1][0] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_stonegreentop");
		textures[1][1] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_stonegreenside");
		textures[1][2] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_stonebottom");
		
		textures[2][0] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_stoneredtop");
		textures[2][1] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_stoneredside");
		textures[2][2] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_stonebottom");
		
		textures[3][0] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_nethertop");
		textures[3][1] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_netherside");
		textures[3][2] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_netherbottom");
		
		textures[4][0] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_endtop");
		textures[4][1] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_endside");
		textures[4][2] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_endbottom");
		
		textures[5][0] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_palestonetop");
		textures[5][1] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_palestoneside");
		textures[5][2] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_palestonebottom");
		
		textures[6][0] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_nightrocktop");
		textures[6][1] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_nightrockside");
		textures[6][2] = par1IconRegister.func_94245_a("supercraft:refinedcraftingtable_nightrockbottom");
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		if(side == 0) return this.textures[meta][2];
		else if(side == 1) return this.textures[meta][0];
		else return this.textures[meta][1];
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
		par3List.add(new ItemStack(par1, 1, 6));
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if(par1World.isRemote) return true;
		par5EntityPlayer.openGui(Supercraft.instance, 0, par1World, par2, par3, par4);
		return true;
	}

}
