package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockSupercraftFlower extends BlockFlower {

	public String iconLocation;
	
	public BlockSupercraftFlower(int par1, String par2) {
		super(par1, Material.plants);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.iconLocation = par2;
	}
	
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Plains;
	}
	
	public void func_94332_a(IconRegister par1IconRegister) {
		field_94336_cN = par1IconRegister.func_94245_a(this.iconLocation);
	}

}
