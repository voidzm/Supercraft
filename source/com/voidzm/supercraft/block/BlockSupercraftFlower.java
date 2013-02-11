package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockSupercraftFlower extends BlockFlower {

	public BlockSupercraftFlower(int par1, int par2) {
		super(par1, par2, Material.plants);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Plains;
	}

}
