package com.voidzm.supercraft.block;

import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupercraftColoredLeavesBase extends BlockSupercraftLeavesBase {

	public BlockSupercraftColoredLeavesBase(int par1) {
		super(par1);
	}
	
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		double var1 = 0.5D;
		double var3 = 1.0D;
		return ColorizerFoliage.getFoliageColor(var1, var3);
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1) {
		return ColorizerFoliage.getFoliageColorBasic();
	}
	
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		int var6 = 0;
		int var7 = 0;
		int var8 = 0;
		for(int var9 = -1; var9 <= 1; ++var9) {
			for(int var10 = -1; var10 <= 1; ++var10) {
				int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor();
				var6 += (var11 & 16711680) >> 16;
				var7 += (var11 & 65280) >> 8;
				var8 += var11 & 255;
			}
		}
        return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
	}

}
