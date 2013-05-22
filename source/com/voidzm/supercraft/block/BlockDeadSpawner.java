package com.voidzm.supercraft.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDeadSpawner extends BlockSupercraft {

	public BlockDeadSpawner(int par1) {
		super(par1, Material.rock);
		this.setStepSound(soundMetalFootstep);
		this.setHardness(5.0F);
		this.disableStats();
		this.setInternalName("deadspawner");
		this.setExternalName("Monster Spawner");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("mobSpawner");
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return 0;
	}
	
	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return 0;
	}

}
