//////////////////////////////////////
//*       ItemBloodAmber.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBloodAmber extends Item {

	public ItemBloodAmber(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setIconIndex(10);
		this.setItemName("bloodAmber");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		int bID = par3World.getBlockId(par4, par5, par6);
		if(bID == BlockHandler.inscribedNightrock.blockID) {
			if(par3World.getBlockId(par4+1, par5, par6) != BlockHandler.nightrockBricks.blockID) {
				return false;
			}
			if(par3World.getBlockId(par4-1, par5, par6) != BlockHandler.nightrockBricks.blockID) {
				return false;
			}
			if(par3World.getBlockId(par4, par5, par6+1) != BlockHandler.nightrockBricks.blockID) {
				return false;
			}
			if(par3World.getBlockId(par4, par5, par6-1) != BlockHandler.nightrockBricks.blockID) {
				return false;
			}
			if(par3World.getBlockId(par4+1, par5, par6+1) != BlockHandler.supercraftLog.blockID || par3World.getBlockMetadata(par4+1, par5, par6+1) != 2) {
				return false;
			}
			if(par3World.getBlockId(par4-1, par5, par6+1) != BlockHandler.supercraftLog.blockID || par3World.getBlockMetadata(par4-1, par5, par6+1) != 2) {
				return false;
			}
			if(par3World.getBlockId(par4+1, par5, par6-1) != BlockHandler.supercraftLog.blockID || par3World.getBlockMetadata(par4+1, par5, par6-1) != 2) {
				return false;
			}
			if(par3World.getBlockId(par4-1, par5, par6-1) != BlockHandler.supercraftLog.blockID || par3World.getBlockMetadata(par4-1, par5, par6-1) != 2) {
				return false;
			}
			if(!par2EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;
			par3World.setBlockWithNotify(par4, par5, par6, BlockHandler.blockOfTenebral.blockID);
			par3World.setBlockWithNotify(par4+1, par5, par6, BlockHandler.burnedNightrock.blockID);
			par3World.setBlockWithNotify(par4-1, par5, par6, BlockHandler.burnedNightrock.blockID);
			par3World.setBlockWithNotify(par4, par5, par6+1, BlockHandler.burnedNightrock.blockID);
			par3World.setBlockWithNotify(par4, par5, par6-1, BlockHandler.burnedNightrock.blockID);
			par3World.setBlockWithNotify(par4+1, par5, par6+1, Block.cobblestone.blockID);
			par3World.setBlockWithNotify(par4-1, par5, par6+1, Block.cobblestone.blockID);
			par3World.setBlockWithNotify(par4+1, par5, par6-1, Block.cobblestone.blockID);
			par3World.setBlockWithNotify(par4-1, par5, par6-1, Block.cobblestone.blockID);
			par3World.spawnEntityInWorld(new EntityLightningBolt(par3World, par4, par5, par6));
			return true;
		}
		else return false;
	}
	
}
