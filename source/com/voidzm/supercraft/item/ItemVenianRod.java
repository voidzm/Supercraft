package com.voidzm.supercraft.item;

import java.util.ArrayList;
import java.util.List;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.util.VenianProperties;
import com.voidzm.supercraft.util.VenianProperties.VenianAspect;
import com.voidzm.supercraft.util.VenianProperties.VenianMaterial;

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
	
	private String[] powerNames = {"Impotent", "Weak", "Sturdy", "Strong", "Omnipotent"};
	private String[] rangeNames = {"Blind", "Nearsighted", "Seeing", "Farsighted", "Allseeing"};
	private String[] drainNames = {"Draining", "Demanding", "Balanced", "Efficient", "Self-Sustaining"};
	private String[] aspectNames = {"Lightning", "Flaming", "Freezing"};
	
	public ItemVenianRod(int par1) {
		super(par1, "supercraft:arcanerod_silver");
		this.setMaxStackSize(1);
		this.setInternalName("venianrod");
		this.setExternalName("Venian Rod");
		this.setCreativeTab(Supercraft.veneficiaTab);
		this.setMaxDamage(0);
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack) {
		VenianProperties prop = VenianProperties.readFromItemStack(par1ItemStack);
		int powerIndex = VenianProperties.getPowerIndexFromInt(prop.power);
		int rangeIndex = VenianProperties.getRangeIndexFromInt(prop.range);
		int drainIndex = VenianProperties.getDrainIndexFromInt(prop.drain);
		String descriptor;
		if(drainIndex > rangeIndex && drainIndex > powerIndex) descriptor = drainNames[drainIndex];
		else if(rangeIndex > powerIndex) descriptor = rangeNames[rangeIndex];
		else descriptor = powerNames[powerIndex];
		String aspect = aspectNames[prop.aspect.index];
		return descriptor + " Rod of " + aspect;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		ItemStack lightningStack = new ItemStack(itemID, 1, 0);
		VenianProperties lightning = new VenianProperties(VenianAspect.LIGHTNING, VenianMaterial.LITHIUM, 10, 30, 1);
		list.add(lightning.applyProperties(lightningStack));
		ItemStack flamingStack = new ItemStack(itemID, 1, 0);
		VenianProperties flaming = new VenianProperties(VenianAspect.FLAMING, VenianMaterial.LITHIUM, 10, 30, 1);
		list.add(flaming.applyProperties(flamingStack));
		ItemStack freezingStack = new ItemStack(itemID, 1, 0);
		VenianProperties freezing = new VenianProperties(VenianAspect.FREEZING, VenianMaterial.LITHIUM, 10, 30, 1);
		list.add(freezing.applyProperties(freezingStack));
	}
	
}
