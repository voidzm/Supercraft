package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockIronboundStone extends Block {

	public BlockIronboundStone(int par1) {
		super(par1, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("ironboundStone");
		this.setCreativeTab(Supercraft.elinvarTab);
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:ironboundstone");
	}

}
