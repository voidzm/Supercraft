package com.voidzm.supercraft.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.util.VeneficianProperties;
import com.voidzm.supercraft.util.VeneficianProperties.VeneficiaMaterial;
import com.voidzm.supercraft.util.VeneficianProperties.VeneficiaType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemVeneficiaCell extends ItemSupercraft {
	
	public ItemVeneficiaCell(int par1) {
		super(par1, "supercraft:veneficiacell_lithium");
		this.setMaxStackSize(1);
		this.setInternalName("veneficiacell");
		this.setExternalName("Veneficia Cell");
		this.setCreativeTab(Supercraft.veneficiaTab);
		this.setMaxDamage(0);
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack) {
		VeneficianProperties prop = VeneficianProperties.readFromItemStack(par1ItemStack);
		int vitality = VeneficianProperties.getVitalityIndex(prop.vitality);
		int perception = VeneficianProperties.getPerceptionIndex(prop.perception);
		int energy = VeneficianProperties.getEnergyIndex(prop.energy);
		if(prop.aspect == VeneficiaType.EMPTY){
			return VeneficianProperties.materialNames[prop.material.index] + " Veneficia Cell";
		}
		return VeneficianProperties.materialNames[prop.material.index] + " Cell of " + VeneficianProperties.aspectNames[prop.aspect.index];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		ItemStack aluminum = new ItemStack(itemID, 1, 0);
		VeneficianProperties prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.ALUMINUM);
		list.add(prop.applyProperties(aluminum));
		
		ItemStack copper = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.COPPER);
		list.add(prop.applyProperties(copper));
		
		ItemStack silver = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.SILVER);
		list.add(prop.applyProperties(silver));
		
		ItemStack gold = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.GOLD);
		list.add(prop.applyProperties(gold));
		
		ItemStack electrum = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.ELECTRUM);
		list.add(prop.applyProperties(electrum));
		
		ItemStack nisil = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.NISIL);
		list.add(prop.applyProperties(nisil));
		
		ItemStack cobalt = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.COBALT);
		list.add(prop.applyProperties(cobalt));
		
		ItemStack platinum = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.PLATINUM);
		list.add(prop.applyProperties(platinum));
		
		ItemStack lithium = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.LITHIUM);
		list.add(prop.applyProperties(lithium));
		
		ItemStack extra = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.COBALT);
		prop.aspect = VeneficiaType.RAGE;
		list.add(prop.applyProperties(extra));
	}
	
}
