package com.voidzm.supercraft.item;

import java.util.List;

import com.voidzm.supercraft.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemEssence extends Item {

	private final static String[] names = {"terraen", "aeronic", "infernal", "aquaeous", "verdeal", "nethereal", "draconic", "ferric", "radantis", "vidalis", "mortalic", "luxorum", "tenebra"};
	
	public ItemEssence(int par1) {
		super(par1);
		this.setMaxStackSize(64);
		this.setIconIndex(13);
		this.setItemName("essence");
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
	@Override
	public int getIconFromDamage(int damage) {
		if(damage < 3) return this.iconIndex + damage;
		else return this.iconIndex + damage + 16;
	}
	
	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return getItemName() + "." + names[itemstack.getItemDamage()];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for(int i = 0; i < names.length; i++) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
	
	@Override
	public boolean hasEffect(ItemStack par1) {
		return false;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

}
