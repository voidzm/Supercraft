package com.voidzm.supercraft.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.voidzm.supercraft.Supercraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArcaneRod extends ItemSupercraft {

	private String[] internalNames = {"silver", "gold", "copper", "nisil", "lithium"};
	private ArrayList<String> names = new ArrayList<String>();
	
	protected Icon[] textures = new Icon[5];
	
	public ItemArcaneRod(int par1) {
		super(par1, null);
		this.populateNames();
		this.setMaxStackSize(64);
		this.setInternalName("arcanerod");
		this.setExternalName("Arcane Rod");
		this.makeMultiitem(this.names);
		this.setCreativeTab(Supercraft.veneficiaTab);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	private void populateNames() {
		this.names.add("Silver Arcane Rod");
		this.names.add("Golden Arcane Rod");
		this.names.add("Copper Arcane Rod");
		this.names.add("Nisilic Arcane Rod");
		this.names.add("Lithium Arcane Rod");
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:arcanerod_silver");
		textures[1] = par1IconRegister.registerIcon("supercraft:arcanerod_gold");
		textures[2] = par1IconRegister.registerIcon("supercraft:arcanerod_copper");
		textures[3] = par1IconRegister.registerIcon("supercraft:arcanerod_nisil");
		textures[4] = par1IconRegister.registerIcon("supercraft:arcanerod_lithium");
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return this.textures[damage];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + internalNames[itemstack.getItemDamage()];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for(int i = 0; i < internalNames.length; i++) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
	
}
