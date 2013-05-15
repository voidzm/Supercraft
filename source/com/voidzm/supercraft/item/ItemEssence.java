package com.voidzm.supercraft.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEssence extends ItemSupercraft {

	private final static String[] names = {"terraen", "aeronic", "infernal", "aquaeous", "verdeal", "nethereal", "draconic", "ferric", "radantis", "vidalis", "mortalic", "luxorum", "tenebra"};
	
	private ArrayList<String> namesExternal = new ArrayList<String>();
	
	protected Icon[] textures = new Icon[16];
	
	public ItemEssence(int par1) {
		super(par1, null);
		this.populateNames();
		this.setMaxStackSize(64);
		this.setInternalName("essence");
		this.setExternalName("Essence");
		this.makeMultiitem(this.namesExternal);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	private void populateNames() {
		this.namesExternal.add("Terraen Essence");
		this.namesExternal.add("Aeronic Essence");
		this.namesExternal.add("Infernal Essence");
		this.namesExternal.add("Aquaeous Essence");
		this.namesExternal.add("Verdeal Essence");
		this.namesExternal.add("Nethereal Essence");
		this.namesExternal.add("Draconic Essence");
		this.namesExternal.add("Ferric Essence");
		this.namesExternal.add("Radantis Essence");
		this.namesExternal.add("Vidalis Essence");
		this.namesExternal.add("Mortalic Essence");
		this.namesExternal.add("Luxorum Essence");
		this.namesExternal.add("Tenebra Essence");
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:essenceterraen");
		textures[1] = par1IconRegister.registerIcon("supercraft:essenceaeronic");
		textures[2] = par1IconRegister.registerIcon("supercraft:essenceinfernal");
		textures[3] = par1IconRegister.registerIcon("supercraft:essenceaquaeous");
		textures[4] = par1IconRegister.registerIcon("supercraft:essenceverdeal");
		textures[5] = par1IconRegister.registerIcon("supercraft:essencenethereal");
		textures[6] = par1IconRegister.registerIcon("supercraft:essencedraconic");
		textures[7] = par1IconRegister.registerIcon("supercraft:essenceferric");
		textures[8] = par1IconRegister.registerIcon("supercraft:essenceradantis");
		textures[9] = par1IconRegister.registerIcon("supercraft:essencevidalis");
		textures[10] = par1IconRegister.registerIcon("supercraft:essencemortalic");
		textures[11] = par1IconRegister.registerIcon("supercraft:essenceluxorum");
		textures[12] = par1IconRegister.registerIcon("supercraft:essencetenebra");
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return this.textures[damage];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for(int i = 0; i < names.length; i++) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

}
