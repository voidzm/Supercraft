package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockCobalt extends Block {

	private final boolean isGlowing;
	
	public BlockCobalt(int par1, boolean par2) {
		super(par1, Material.rock);
		this.isGlowing = par2;
		this.setHardness(0.8F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("cobaltBlock");
		if(par2) {
			this.setLightValue(1.0F);
			this.setCreativeTab(CreativeTabs.tabBlock);
		}
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		if(isGlowing) this.blockIcon = par1IconRegister.registerIcon("supercraft:blockcobalt");
		else this.blockIcon = par1IconRegister.registerIcon("supercraft:blockcobalt_off");
	}
	
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		if(!par1World.isRemote) {
			if(this.isGlowing && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
				par1World.setBlock(par2, par3, par4, BlockHandler.cobaltBlockOff.blockID);
			}
			else if(!this.isGlowing && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
				par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
			}
		}
	}
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		if(!par1World.isRemote) {
			if(this.isGlowing && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
				par1World.setBlock(par2, par3, par4, BlockHandler.cobaltBlockOff.blockID);
			}
			else if(!this.isGlowing && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
				par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
			}
		}
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(!par1World.isRemote && !this.isGlowing && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
			par1World.setBlock(par2, par3, par4, BlockHandler.cobaltBlock.blockID);
		}
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return BlockHandler.cobaltBlock.blockID;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return BlockHandler.cobaltBlock.blockID;
	}
	
	
}
