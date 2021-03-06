package com.voidzm.supercraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.voidzm.supercraft.tileentity.TileEntityMonolithTermination;

public class BlockMonolithTermination extends BlockSupercraft {

	private final boolean isActivated;
	
	public BlockMonolithTermination(int par1, boolean par2) {
		super(par1, Material.rock);
		this.setHardness(4.0F);
		this.setResistance(20.0F);
		this.isActivated = par2;
		this.setStepSound(soundStoneFootstep);
		this.setInternalName("monolithtermination");
		this.setExternalName("Monolith of Termination");
		if(!par2) this.setCreativeTab(CreativeTabs.tabBlock);
		if(par2) {
			this.setInternalName("monolithterminationactivated");
			this.setLightValue(0.5F);
		}
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		if(isActivated) this.blockIcon = par1IconRegister.registerIcon("supercraft:monolithtermination_on");
		else this.blockIcon = par1IconRegister.registerIcon("supercraft:monolithtermination");
	}
	
	@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(par1World.isRemote || !this.isActivated) return;
		int theEndID = 1;
		if(par1World.provider.dimensionId != 0) {
			this.detonate(par1World, par2, par3, par4);
			return;
		}
		par5Entity.travelToDimension(theEndID);
	}
	
	public void detonate(World par1World, int par2, int par3, int par4) {
		par1World.setBlockToAir(par2, par3, par4);
		par1World.createExplosion(new EntityItem(par1World), (double)par2, (double)par3, (double)par4, 2.0F, true);
	}
	
	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityMonolithTermination();
	}
	
}
