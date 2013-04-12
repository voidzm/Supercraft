package com.voidzm.supercraft.block;

import com.voidzm.supercraft.CommonProxy;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockSupercraftMushroom extends BlockMushroom {

	public String iconLocation;
	
	private EnumPlantType plantType;
	
	public BlockSupercraftMushroom(int par1, String par2, EnumPlantType type) {
		super(par1, par2);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.iconLocation = par2;
		this.plantType = type;
	}
	
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return this.plantType;
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(this.iconLocation);
	}

}
