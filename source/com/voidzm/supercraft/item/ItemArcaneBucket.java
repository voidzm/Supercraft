package com.voidzm.supercraft.item;

import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.ItemHandler;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemArcaneBucket extends ItemSupercraft {

	private int liquidID;
	private String icon;
	
	public ItemArcaneBucket(int par1, int par2, String iconID) {
		super(par1, iconID);
		this.liquidID = par2;
		this.icon = iconID;
		this.setMaxStackSize(par2 == 0 ? 16 : 1);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setInternalName("arcanebucket");
		this.setExternalName("Arcane Bucket");
		if(par2 == BlockHandler.ghostlyVaporFlowing.blockID) {
			this.setInternalName("bucketghostlyvapor");
			this.setExternalName("Ghostly Vapor Bucket");
		}
		if(par2 == 0) this.setContainerItem(ItemHandler.arcaneBucket);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(this.icon);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(par2World.isRemote) return par1ItemStack;
		float f = 1.0F;
		double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double)f;
		double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double)f + 1.62D - (double)par3EntityPlayer.yOffset;
		double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double)f;
		boolean flag = this.liquidID == 0;
		MovingObjectPosition objPos = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);
		if(objPos == null) return par1ItemStack;
		else {
			if(objPos.typeOfHit == EnumMovingObjectType.TILE) {
				int bx = objPos.blockX;
				int by = objPos.blockY;
				int bz = objPos.blockZ;
				if(!par2World.canMineBlock(par3EntityPlayer, bx, by, bz)) return par1ItemStack;
				if(this.liquidID == 0) {
					if(!par3EntityPlayer.canPlayerEdit(bx, by, bz, objPos.sideHit, par1ItemStack)) return par1ItemStack;
					int targetedID = par2World.getBlockId(bx, by, bz);
					if((targetedID == BlockHandler.ghostlyVaporStill.blockID || targetedID == BlockHandler.ghostlyVaporFlowing.blockID) && par2World.getBlockMetadata(bx, by, bz) == 0) {
						par2World.setBlockToAir(bx, by, bz);
						if(par3EntityPlayer.capabilities.isCreativeMode) return par1ItemStack;
						if(--par1ItemStack.stackSize <= 0) return new ItemStack(ItemHandler.bucketGhostlyVapor);
						if(!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ItemHandler.bucketGhostlyVapor))) par3EntityPlayer.dropPlayerItem(new ItemStack(ItemHandler.bucketGhostlyVapor.itemID, 1, 0));
						return par1ItemStack;
					}
				}
				else {
					if(this.liquidID < 0) return new ItemStack(ItemHandler.arcaneBucket);
					switch(objPos.sideHit) {
					case 0:
						--by;
						break;
					case 1:
						++by;
						break;
					case 2:
						--bz;
						break;
					case 3:
						++bz;
						break;
					case 4:
						--bx;
						break;
					case 5:
						++bx;
						break;
					default:
						break;
					}
					if(!par3EntityPlayer.canPlayerEdit(bx, by, bz, objPos.sideHit, par1ItemStack)) return par1ItemStack;
					if(this.tryPlaceContainedLiquid(par2World, d0, d1, d2, bx, by, bz) && !par3EntityPlayer.capabilities.isCreativeMode) return new ItemStack(ItemHandler.arcaneBucket);
				}
			}
			return par1ItemStack;
		}
	}
	
	public boolean tryPlaceContainedLiquid(World par1World, double par2, double par4, double par6, int par8, int par9, int par10) {
		if(this.liquidID <= 0) return false;
		else if(!par1World.isAirBlock(par8, par9, par10) && par1World.getBlockMaterial(par8, par9, par10).isSolid()) return false;
		else par1World.setBlock(par8, par9, par10, this.liquidID, 0, 3);
		return true;
	}
	
}
