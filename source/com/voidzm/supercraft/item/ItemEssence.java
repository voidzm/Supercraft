package com.voidzm.supercraft.item;

import java.util.List;

import com.voidzm.supercraft.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemEssence extends Item {

	private final static String[] names = {"terraen", "aeronic", "infernal", "aquaeous", "verdeal", "nethereal", "draconic", "ferric", "radantis", "vidalis", "mortalic", "luxorum", "tenebra"};
	
	protected Icon[] textures = new Icon[16];
	
	public ItemEssence(int par1) {
		super(par1);
		this.setMaxStackSize(64);
		this.setUnlocalizedName("essence");
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	public void updateIcons(IconRegister par1IconRegister) {
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
	public boolean hasEffect(ItemStack par1) {
		return false;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

}
