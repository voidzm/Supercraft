package com.voidzm.supercraft.block;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockSupercraftLog2 extends BlockSupercraftLogBase {

	public BlockSupercraftLog2(int par1) {
		super(par1);
		this.setUnlocalizedName("supercraftLog2");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0][0] = par1IconRegister.registerIcon("supercraft:woodinisia_side");
		textures[0][1] = par1IconRegister.registerIcon("supercraft:woodinisia_end");
		textures[1][0] = par1IconRegister.registerIcon("supercraft:woodvalensien_side");
		textures[1][1] = par1IconRegister.registerIcon("supercraft:woodvalensien_end");
		textures[2][0] = par1IconRegister.registerIcon("supercraft:woodmortalia_side");
		textures[2][1] = par1IconRegister.registerIcon("supercraft:woodmortalia_end");
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
	}

}
