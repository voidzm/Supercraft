//////////////////////////////////////
//*      ItemElectrumBit.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemElectrumBit extends Item {

	public ItemElectrumBit(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setUnlocalizedName("electrumBit");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon("supercraft:electrumbit");
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		int bID = par3World.getBlockId(par4, par5, par6);
		if(bID == BlockHandler.inscribedPalestone.blockID) {
			if(par3World.getBlockId(par4+1, par5, par6) != BlockHandler.overgrownPalestone.blockID) {
				return false;
			}
			if(par3World.getBlockId(par4-1, par5, par6) != BlockHandler.overgrownPalestone.blockID) {
				return false;
			}
			if(par3World.getBlockId(par4, par5, par6+1) != BlockHandler.overgrownPalestone.blockID) {
				return false;
			}
			if(par3World.getBlockId(par4, par5, par6-1) != BlockHandler.overgrownPalestone.blockID) {
				return false;
			}
			if(par3World.getBlockId(par4+1, par5, par6+1) != BlockHandler.supercraftLog1.blockID || par3World.getBlockMetadata(par4+1, par5, par6+1) != 1) {
				return false;
			}
			if(par3World.getBlockId(par4-1, par5, par6+1) != BlockHandler.supercraftLog1.blockID || par3World.getBlockMetadata(par4-1, par5, par6+1) != 1) {
				return false;
			}
			if(par3World.getBlockId(par4+1, par5, par6-1) != BlockHandler.supercraftLog1.blockID || par3World.getBlockMetadata(par4+1, par5, par6-1) != 1) {
				return false;
			}
			if(par3World.getBlockId(par4-1, par5, par6-1) != BlockHandler.supercraftLog1.blockID || par3World.getBlockMetadata(par4-1, par5, par6-1) != 1) {
				return false;
			}
			if(!par2EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;
			par3World.setBlock(par4, par5, par6, BlockHandler.blockOfGoldenwood.blockID);
			par3World.setBlock(par4+1, par5, par6, BlockHandler.burnedPalestone.blockID);
			par3World.setBlock(par4-1, par5, par6, BlockHandler.burnedPalestone.blockID);
			par3World.setBlock(par4, par5, par6+1, BlockHandler.burnedPalestone.blockID);
			par3World.setBlock(par4, par5, par6-1, BlockHandler.burnedPalestone.blockID);
			par3World.setBlock(par4+1, par5, par6+1, Block.cobblestone.blockID);
			par3World.setBlock(par4-1, par5, par6+1, Block.cobblestone.blockID);
			par3World.setBlock(par4+1, par5, par6-1, Block.cobblestone.blockID);
			par3World.setBlock(par4-1, par5, par6-1, Block.cobblestone.blockID);
			par3World.spawnEntityInWorld(new EntityLightningBolt(par3World, par4, par5, par6));
			return true;
		}
		else return false;
	}
	
}
