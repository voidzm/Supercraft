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
	
	public void func_94332_a(IconRegister par1IconRegister) {
		field_94336_cN = par1IconRegister.func_94245_a("supercraft:torchcobalt");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {}

}
