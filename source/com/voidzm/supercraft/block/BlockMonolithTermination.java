package com.voidzm.supercraft.block;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.dimension.TeleporterDeep;
import com.voidzm.supercraft.tileentity.TileEntityMonolithDemission;
import com.voidzm.supercraft.tileentity.TileEntityMonolithTermination;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class BlockMonolithTermination extends Block {

	private final boolean isActivated;
	
	public BlockMonolithTermination(int par1, boolean par2) {
		super(par1, Material.rock);
		this.setHardness(4.0F);
		this.setResistance(20.0F);
		this.isActivated = par2;
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("monolithTermination");
		if(!par2) this.setCreativeTab(CreativeTabs.tabBlock);
		if(par2) {
			this.setUnlocalizedName("monolithTerminationActivated");
			this.setLightValue(0.5F);
		}
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		if(isActivated) this.blockIcon = par1IconRegister.registerIcon("supercraft:monolithtermination_on");
		else this.blockIcon = par1IconRegister.registerIcon("supercraft:monolithtermination");
	}
	
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(par1World.isRemote || !this.isActivated) return;
		int theEndID = 1;
		if(par1World.provider.dimensionId != 0) {
			this.detonate(par1World, par2, par3, par4);
			return;
		}
		//MinecraftServer server = MinecraftServer.getServer();
		//WorldServer targetWorld = server.worldServerForDimension(theEndID);
		par5Entity.travelToDimension(theEndID);
		/*if(par5Entity instanceof EntityPlayerMP) {
			server.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par5Entity, theEndID);
			return;
		}*/
	}
	
	public void detonate(World par1World, int par2, int par3, int par4) {
		par1World.setBlockToAir(par2, par3, par4);
		par1World.createExplosion(new EntityItem(par1World), (double)par2, (double)par3, (double)par4, 2.0F, true);
	}
	
	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityMonolithTermination();
	}
	
}
