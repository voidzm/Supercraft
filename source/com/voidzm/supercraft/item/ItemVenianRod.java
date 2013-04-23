package com.voidzm.supercraft.item;

import java.util.ArrayList;
import java.util.List;

import com.voidzm.supercraft.Supercraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.Icon;

public class ItemVenianRod extends ItemSupercraft {
	
	public ItemVenianRod(int par1) {
		super(par1, "supercraft:arcanerod_silver");
		this.setMaxStackSize(1);
		this.setInternalName("venianrod");
		this.setExternalName("Venian Rod");
		this.setCreativeTab(Supercraft.veneficiaTab);
		this.setMaxDamage(0);
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return "Rod";
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		ItemStack emptyRodOfFlaming = new ItemStack(itemID, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagCompound display = new NBTTagCompound();
		NBTTagList lore = new NBTTagList();
		lore.appendTag(new NBTTagString("", "§7Empty Rod of §9Test"));
		display.setTag("Lore", lore);
		tag.setTag("display", display);
		emptyRodOfFlaming.stackTagCompound = tag;
		list.add(emptyRodOfFlaming);
	}
	
}
