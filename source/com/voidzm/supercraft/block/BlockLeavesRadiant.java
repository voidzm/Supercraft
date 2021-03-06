package com.voidzm.supercraft.block;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockLeavesRadiant extends BlockSupercraftColoredLeavesBase {
	
	public BlockLeavesRadiant(int par1) {
		super(par1);
		this.setInternalName("radiantleaves");
		this.setExternalName("Radiant Leaves");
		this.setLightValue(0.375F);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("supercraft:leavesradiant_fancy");
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		return this.blockIcon;
	}
	
	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

}
