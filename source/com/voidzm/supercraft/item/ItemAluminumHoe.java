//////////////////////////////////////
//*      ItemAluminumHoe.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.entity.TileEntityConduit;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemAluminumHoe extends ItemHoe {

	public ItemAluminumHoe(int par1) {
		super(par1, Supercraft.aluminumTool);
		this.setIconIndex(20);
		this.setItemName("aluminumHoe");
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		TileEntity te = par3World.getBlockTileEntity(par4, par5, par6);
		if(te instanceof TileEntityConduit) {
			System.out.println("Status of TE for this block: " + ((TileEntityConduit)te).powerLevel());
		}
		return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
	}
	
	
}
