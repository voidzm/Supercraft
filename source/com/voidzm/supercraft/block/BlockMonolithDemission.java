package com.voidzm.supercraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.dimension.TeleporterDeep;
import com.voidzm.supercraft.tileentity.TileEntityMonolithDemission;

public class BlockMonolithDemission extends BlockSupercraft {

	private final boolean isActivated;
	
	public BlockMonolithDemission(int par1, boolean par2) {
		super(par1, Material.rock);
		this.setHardness(4.0F);
		this.setResistance(20.0F);
		this.isActivated = par2;
		this.setStepSound(soundStoneFootstep);
		this.setInternalName("monolithdemission");
		this.setExternalName("Monolith of Demission");
		if(!par2) this.setCreativeTab(CreativeTabs.tabBlock);
		if(par2) {
			this.setInternalName("monolithdemissionactivated");
			this.setLightValue(0.5F);
		}
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		if(isActivated) this.blockIcon = par1IconRegister.registerIcon("supercraft:monolithdemission_on");
		else this.blockIcon = par1IconRegister.registerIcon("supercraft:monolithdemission");
	}
	
	@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(par1World.isRemote || !this.isActivated) return;
		int theDeepID = Supercraft.configuration.thedeepID;
		if(par1World.provider.dimensionId != 0) {
			this.detonate(par1World, par2, par3, par4);
			return;
		}
		MinecraftServer server = MinecraftServer.getServer();
		WorldServer targetWorld = server.worldServerForDimension(theDeepID);
		if(par5Entity instanceof EntityPlayerMP) {
			server.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par5Entity, theDeepID, new TeleporterDeep(targetWorld));
			return;
		}
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
		return new TileEntityMonolithDemission();
	}
	
}
