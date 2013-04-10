package com.voidzm.supercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockStorage extends Block {

	public enum StorageType { METAL, CRYSTAL, DUST };
	
	private StorageType storageType = StorageType.METAL;
	private String iconString;
	
	public BlockStorage(int id, String unlocalized, String iconName, StorageType type) {
		super(id, (type == StorageType.METAL ? Material.iron : Material.rock));
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound((type == StorageType.METAL ? Block.soundMetalFootstep : Block.soundStoneFootstep));
		this.setUnlocalizedName(unlocalized);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.storageType = type;
		this.iconString = iconName;
	}
	
	public BlockStorage(int id, String unlocalized, String iconName) {
		this(id, unlocalized, iconName, StorageType.METAL);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(iconString);
	}
	
}
