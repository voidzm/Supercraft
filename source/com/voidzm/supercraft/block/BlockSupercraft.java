package com.voidzm.supercraft.block;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockSupercraft extends Block {

	private String internalName;
	private String externalName;
	
	public boolean isMultiblock = false;
	
	private ArrayList<String> externalNames = null;
	
	public BlockSupercraft(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	public BlockSupercraft setInternalName(String newInternalName) {
		this.internalName = newInternalName;
		this.setUnlocalizedName(newInternalName);
		return this;
	}
	
	public BlockSupercraft setExternalName(String newExternalName) {
		this.externalName = newExternalName;
		return this;
	}
	
	public String internalName() {
		return this.internalName;
	}
	
	public String externalName() {
		return this.externalName;
	}
	
	public void makeMultiblock(ArrayList<String> names) {
		this.isMultiblock = true;
		this.externalNames = names;
	}
	
	public BlockSupercraft register() {
		GameRegistry.registerBlock(this, this.internalName);
		if(!this.isMultiblock) LanguageRegistry.addName(this, this.externalName);
		else {
			int i = 0;
			for(String name : externalNames) {
				ItemStack stack = new ItemStack(this, 1, i);
				LanguageRegistry.addName(stack, name);
				i++;
			}
		}
		return this;
	}

}
