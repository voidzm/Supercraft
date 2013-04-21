package com.voidzm.supercraft.item;

import java.util.ArrayList;

import com.voidzm.supercraft.block.BlockSupercraft;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;
import com.voidzm.supercraft.util.StartupStats;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSupercraft extends Item {

	private String internalName;
	private String externalName;
	
	public boolean isMultiitem = false;
	
	private ArrayList<String> externalNames = null;
	
	private String iconString;
	private boolean shimmering = false;
	
	public ItemSupercraft(int par1, String icon, CreativeTabs tab) {
		super(par1);
		this.setMaxStackSize(64);
		this.iconString = icon;
		this.setCreativeTab(tab);
	}
	
	public ItemSupercraft(int par1, String icon) {
		this(par1, icon, CreativeTabs.tabMaterials);
	}
	
	public ItemSupercraft setInternalName(String newInternalName) {
		this.internalName = newInternalName;
		this.setUnlocalizedName(newInternalName);
		return this;
	}
	
	public ItemSupercraft setExternalName(String newExternalName) {
		this.externalName = newExternalName;
		return this;
	}
	
	public String internalName() {
		return this.internalName;
	}
	
	public String externalName() {
		return this.externalName;
	}
	
	public void makeMultiitem(ArrayList<String> names) {
		this.isMultiitem = true;
		this.externalNames = names;
	}
	
	public ArrayList<String> fetchExternalNames() {
		return this.externalNames;
	}
	
	public ItemSupercraft register() {
		if(!this.isMultiitem) {
			GameRegistry.registerItem(this, this.internalName);
			LanguageRegistry.addName(this, this.externalName);
			StartupStats.itemCreated();
		}
		else {
			GameRegistry.registerItem(this, this.internalName);
			int i = 0;
			for(String name : externalNames) {
				ItemStack stack = new ItemStack(this, 1, i);
				LanguageRegistry.addName(stack, name);
				StartupStats.itemCreated();
				i++;
			}
		}
		return this;
	}
	
	public static void register(Item item, RegisterData data) {
		if(!(item instanceof IRegisterable)) return;
		if(!data.isMulti) {
			GameRegistry.registerItem(item, data.internalName);
			LanguageRegistry.addName(item, data.externalName);
			StartupStats.itemCreated();
		}
		else {
			GameRegistry.registerItem(item, data.internalName);
			int i = 0;
			for(String name : data.externalNames) {
				ItemStack stack = new ItemStack(item, 1, i);
				LanguageRegistry.addName(stack, name);
				StartupStats.itemCreated();
				i++;
			}
		}
	}
	
	@Override
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon(this.iconString);
	}
	
	public ItemSupercraft setHasShimmerEffect(boolean value) {
		this.shimmering = value;
		return this;
	}
	
	@Override
	public boolean hasEffect(ItemStack par1) {
		return this.shimmering;
	}

}
