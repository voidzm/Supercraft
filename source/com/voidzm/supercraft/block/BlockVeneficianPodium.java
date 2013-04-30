package com.voidzm.supercraft.block;

import com.voidzm.supercraft.Supercraft;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
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
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var1, float var2, float var3, float var4) {
		player.openGui(Supercraft.instance, 4, world, x, y, z);
		return true;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:blockcoal");
	}

}
