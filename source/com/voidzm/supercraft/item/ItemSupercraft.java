package com.voidzm.supercraft.item;

import java.util.ArrayList;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.TransformationMatrix;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemSupercraft extends Item {

	private String internalName;
	private String externalName;
	
	public boolean isMultiitem = false;
	
	private ArrayList<String> externalNames = null;
	
	private String iconString;
	private boolean shimmering = false;
	
	private boolean hasTranformationMatrix = false;
	private TransformationMatrix matrix = null;
	
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
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(this.iconString);
	}
	
	public ItemSupercraft setHasShimmerEffect(boolean value) {
		this.shimmering = value;
		return this;
	}
	
	@Override
	public boolean hasEffect(ItemStack par1) {
		return this.shimmering;
	}
	
	public ItemSupercraft makeTransforming(TransformationMatrix mat) {
		this.hasTranformationMatrix = true;
		this.matrix = mat;
		return this;
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(!this.hasTranformationMatrix) return false;
		int block = par3World.getBlockId(par4, par5, par6);
		if(block == this.matrix.getTargetBlockID()) {
			if(par3World.getBlockId(par4+1, par5, par6) != this.matrix.getSecondaryBlockID() || par3World.getBlockMetadata(par4+1, par5, par6) != this.matrix.getSecondaryBlockMetadata()) {
				return false;
			}
			if(par3World.getBlockId(par4-1, par5, par6) != this.matrix.getSecondaryBlockID() || par3World.getBlockMetadata(par4-1, par5, par6) != this.matrix.getSecondaryBlockMetadata()) {
				return false;
			}
			if(par3World.getBlockId(par4, par5, par6+1) != this.matrix.getSecondaryBlockID() || par3World.getBlockMetadata(par4, par5, par6+1) != this.matrix.getSecondaryBlockMetadata()) {
				return false;
			}
			if(par3World.getBlockId(par4, par5, par6-1) != this.matrix.getSecondaryBlockID() || par3World.getBlockMetadata(par4, par5, par6-1) != this.matrix.getSecondaryBlockMetadata()) {
				return false;
			}
			if(par3World.getBlockId(par4+1, par5, par6+1) != this.matrix.getTertiaryBlockID() || par3World.getBlockMetadata(par4+1, par5, par6+1) != this.matrix.getTertiaryBlockMetadata()) {
				return false;
			}
			if(par3World.getBlockId(par4+1, par5, par6-1) != this.matrix.getTertiaryBlockID() || par3World.getBlockMetadata(par4+1, par5, par6-1) != this.matrix.getTertiaryBlockMetadata()) {
				return false;
			}
			if(par3World.getBlockId(par4-1, par5, par6+1) != this.matrix.getTertiaryBlockID() || par3World.getBlockMetadata(par4-1, par5, par6+1) != this.matrix.getTertiaryBlockMetadata()) {
				return false;
			}
			if(par3World.getBlockId(par4-1, par5, par6-1) != this.matrix.getTertiaryBlockID() || par3World.getBlockMetadata(par4-1, par5, par6-1) != this.matrix.getTertiaryBlockMetadata()) {
				return false;
			}
			if(!par2EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;
			par3World.setBlock(par4, par5, par6, this.matrix.getNewTargetBlockID(), this.matrix.getNewTargetBlockMetadata(), 2);
			par3World.setBlock(par4+1, par5, par6, this.matrix.getNewSecondaryBlockID(), this.matrix.getNewSecondaryBlockMetadata(), 2);
			par3World.setBlock(par4-1, par5, par6, this.matrix.getNewSecondaryBlockID(), this.matrix.getNewSecondaryBlockMetadata(), 2);
			par3World.setBlock(par4, par5, par6+1, this.matrix.getNewSecondaryBlockID(), this.matrix.getNewSecondaryBlockMetadata(), 2);
			par3World.setBlock(par4, par5, par6-1, this.matrix.getNewSecondaryBlockID(), this.matrix.getNewSecondaryBlockMetadata(), 2);
			par3World.setBlock(par4+1, par5, par6+1, this.matrix.getNewTertiaryBlockID(), this.matrix.getNewTertiaryBlockMetadata(), 2);
			par3World.setBlock(par4-1, par5, par6+1, this.matrix.getNewTertiaryBlockID(), this.matrix.getNewTertiaryBlockMetadata(), 2);
			par3World.setBlock(par4+1, par5, par6-1, this.matrix.getNewTertiaryBlockID(), this.matrix.getNewTertiaryBlockMetadata(), 2);
			par3World.setBlock(par4-1, par5, par6-1, this.matrix.getNewTertiaryBlockID(), this.matrix.getNewTertiaryBlockMetadata(), 2);
			par3World.spawnEntityInWorld(new EntityLightningBolt(par3World, par4, par5, par6));
			return true;
		}
		else return false;
	}

}
