package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

public class BlockCobaltTorch extends BlockTorch {

	public BlockCobaltTorch(int par1) {
		super(par1);
		this.setHardness(0.0F);
		this.setLightValue(1.0F);
		this.setStepSound(soundWoodFootstep);
		this.setUnlocalizedName("cobaltTorch");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:torchcobalt");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {}

}
