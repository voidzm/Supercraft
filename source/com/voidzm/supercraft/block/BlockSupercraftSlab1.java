//////////////////////////////////////
//*    BlockSupercraftSlab1.java   *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.item.ItemBlockSupercraft;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockSupercraftSlab1 extends BlockSupercraftSlabBase implements IRegisterable {

	private ArrayList<String> names = new ArrayList<String>();
	
	private final String[] types = new String[] {"olive", "goldenwood", "tenebria", "inisia", "valensien", "mortalia"};
	
	protected Icon[] textures = new Icon[8];
	
	private RegisterData rdata = new RegisterData();
	
	public BlockSupercraftSlab1(int par1) {
		super(par1, Material.wood);
		this.populateNames();
		this.buildRegisterData();
	}
	
	private void populateNames() {
		this.names.add("Olive Wood Slab");
		this.names.add("Goldenwood Wood Slab");
		this.names.add("Tenebria Wood Slab");
		this.names.add("Inisia Wood Slab");
		this.names.add("Valensien Wood Slab");
		this.names.add("Mortalia Wood Slab");
	}
	
	private void buildRegisterData() {
		rdata.internalName = "supercraftslab1";
		rdata.isMulti = true;
		rdata.externalNames = this.names;
		rdata.itemBlockClass = ItemBlockSupercraft.class;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:planksolive");
		textures[1] = par1IconRegister.registerIcon("supercraft:planksgoldenwood");
		textures[2] = par1IconRegister.registerIcon("supercraft:plankstenebria");
		textures[3] = par1IconRegister.registerIcon("supercraft:planksinisia");
		textures[4] = par1IconRegister.registerIcon("supercraft:planksvalensien");
		textures[5] = par1IconRegister.registerIcon("supercraft:planksmortalia");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		return textures[meta & 7];
	}
	
	@Override
	public String getFullSlabName(int par1) {
		if(par1 < 0 || par1 >= types.length) {
			par1 = 0;
		}
		return super.getUnlocalizedName() + "." + types[par1];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        if(par1 == BlockHandler.supercraftSlab1.blockID) {
        	par3List.add(new ItemStack(par1, 1, 0));
        	par3List.add(new ItemStack(par1, 1, 1));
        	par3List.add(new ItemStack(par1, 1, 2));
        	par3List.add(new ItemStack(par1, 1, 3));
        	par3List.add(new ItemStack(par1, 1, 4));
        	par3List.add(new ItemStack(par1, 1, 5));
        }
    }

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}
	
}

