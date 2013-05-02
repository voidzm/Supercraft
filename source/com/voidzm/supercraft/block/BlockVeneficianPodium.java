package com.voidzm.supercraft.block;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.client.ClientProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVeneficianPodium extends BlockSupercraft {

	public BlockVeneficianPodium(int par1) {
		super(par1, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
		this.setStepSound(soundStoneFootstep);
		this.setInternalName("veneficianpodium");
		this.setExternalName("Venefician Podium");
		this.setCreativeTab(Supercraft.veneficiaTab);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var1, float var2, float var3, float var4) {
		player.openGui(Supercraft.instance, 4, world, x, y, z);
		return true;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("obsidian");
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return true;
	}
	
	@Override
	public int getRenderType() {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) return -1;
		else return ClientProxy.veneficianPodiumRenderID;
	}
	
}
