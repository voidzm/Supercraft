package com.voidzm.supercraft.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

public class ItemSupercraftPickaxe extends ItemPickaxe implements IRegisterable {

	private EnumToolMaterial material;
	private RegisterData rdata = new RegisterData();
	private String icon;
	
	public ItemSupercraftPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial, String unlocalized, String localized, String iconPath) {
		super(par1, par2EnumToolMaterial);
		material = par2EnumToolMaterial;
		this.setUnlocalizedName(unlocalized);
		this.setCreativeTab(CreativeTabs.tabTools);
		rdata.internalName = unlocalized;
		rdata.externalName = localized;
		MinecraftForge.setToolClass(this, "pickaxe", material.getHarvestLevel());
		icon = iconPath;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(this.icon);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving) {
		super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLiving);
		if(this.material.equals(Supercraft.aluminumTool)) {
			if((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
				int efficiencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, par1ItemStack);
				par1ItemStack.damageItem(efficiencyLevel, par7EntityLiving);
			}
		}
		else if((this.material.equals(Supercraft.lithiumTool) && !par2World.isRemote) && par3 == Block.mobSpawner.blockID) {
			float f = 0.7F;
            double d0 = (double)(par2World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d1 = (double)(par2World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d2 = (double)(par2World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            EntityItem entityitem = new EntityItem(par2World, (double)par4 + d0, (double)par5 + d1, (double)par6 + d2, new ItemStack(BlockHandler.deadSpawner));
            entityitem.delayBeforeCanPickup = 10;
            par2World.spawnEntityInWorld(entityitem);
		}
		return true;
	}
	
	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}

}
