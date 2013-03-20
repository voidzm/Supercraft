//////////////////////////////////////
//*       ItemNisilShard.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.item;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.dimension.TeleporterDeep;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class ItemNisilShard extends Item {

	public ItemNisilShard(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("nisilShard");
	}
	
	public void func_94581_a(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.func_94245_a("supercraft:nisilshard");
	}
	
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(par2EntityPlayer instanceof EntityPlayerMP) {
			((EntityPlayerMP)par2EntityPlayer).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par2EntityPlayer, -2, new TeleporterDeep(DimensionManager.getWorld(-2)));
		}
		return true;
	}
	
}
