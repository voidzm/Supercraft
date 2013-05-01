package com.voidzm.supercraft.util;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class VenianProperties {

	private static Random rand = new Random();
	
	public enum VenianAspect {
		LIGHTNING(0, 5), FLAMING(1, 10), FREEZING(2, 15);
		public int index;
		public int difficulty;
		private VenianAspect(int par1, int par2) {
			index = par1;
			difficulty = par2;
		}
	}
	
	public enum VenianMaterial {
		SILVER(0), GOLD(1), COPPER(2), NISIL(3), LITHIUM(4);
		public int index;
		private VenianMaterial(int par1) {
			index = par1;
		}
	}
	
	public VenianAspect aspect;
	public VenianMaterial material;
	public int power;
	public int range;
	public int drain;
	
	public VenianProperties(VenianAspect par1, VenianMaterial par2) {
		this(par1, par2, rand.nextInt(5)+3, rand.nextInt(5)+3, getRandomDrainForAspect(par1));
	}
	
	public VenianProperties(VenianAspect par1, VenianMaterial par2, int spellPower, int spellRange, int spellDrain) {
		this.aspect = par1;
		this.material = par2;
		this.power = spellPower;
		this.range = spellRange;
		this.drain = spellDrain;
	}
	
	private static int getRandomDrainForAspect(VenianAspect aspect) {
		int delta = (int)Math.ceil(aspect.difficulty / 2);
		return aspect.difficulty + rand.nextInt(delta) - rand.nextInt(delta);
	}
	
	public ItemStack applyProperties(ItemStack stack) {
		NBTTagCompound tag;
		if(stack.hasTagCompound()) tag = stack.getTagCompound();
		else {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		NBTTagCompound display;
		if(tag.hasKey("display")) display = tag.getCompoundTag("display");
		else {
			display = new NBTTagCompound();
			tag.setTag("display", display);
		}
		NBTTagList lore = new NBTTagList();
		display.setTag("Lore", lore);
		lore.appendTag(new NBTTagString("", "§7Strength: "+strengthFormatSymbolFromInt(this.power)+this.power));
		lore.appendTag(new NBTTagString("", "§7Perception: "+rangeFormatSymbolFromInt(this.range)+this.range));
		lore.appendTag(new NBTTagString("", "§7Impact: "+drainFormatSymbolFromInt(this.drain)+this.drain));
		
		NBTTagCompound venian = new NBTTagCompound();
		venian.setInteger("aspect", this.aspect.index);
		venian.setInteger("material", this.material.index);
		venian.setInteger("power", this.power);
		venian.setInteger("range", this.range);
		venian.setInteger("drain", this.drain);
		tag.setTag("Venian", venian);
		return stack;
	}
	
	public static VenianProperties readFromItemStack(ItemStack stack) {
		if(!stack.hasTagCompound()) return null;
		NBTTagCompound tag = stack.getTagCompound();
		if(!tag.hasKey("Venian")) return null;
		NBTTagCompound venian = tag.getCompoundTag("Venian");
		VenianProperties properties = new VenianProperties(VenianAspect.LIGHTNING, VenianMaterial.SILVER, 0, 0, 0);
		properties.aspect = VenianAspect.values()[venian.getInteger("aspect")];
		properties.material = VenianMaterial.values()[venian.getInteger("material")];
		properties.power = venian.getInteger("power");
		properties.range = venian.getInteger("range");
		properties.drain = venian.getInteger("drain");
		return properties;
	}
	
	private static String strengthFormatSymbolFromInt(int par1) {
		if(par1 <= 5) return "§c";
		else if(par1 <= 10) return "§6";
		else if(par1 <= 25) return "§e";
		else if(par1 <= 50) return "§a";
		else return "§9";
	}
	
	public static int getPowerIndexFromInt(int par1) {
		if(par1 <= 5) return 0;
		else if(par1 <= 10) return 1;
		else if(par1 <= 25) return 2;
		else if(par1 <= 50) return 3;
		else return 4;
	}
	
	private static String rangeFormatSymbolFromInt(int par1) {
		if(par1 <= 5) return "§c";
		else if(par1 <= 10) return "§6";
		else if(par1 <= 25) return "§e";
		else if(par1 <= 50) return "§a";
		else return "§9";
	}
	
	public static int getRangeIndexFromInt(int par1) {
		if(par1 <= 5) return 0;
		else if(par1 <= 10) return 1;
		else if(par1 <= 25) return 2;
		else if(par1 <= 50) return 3;
		else return 4;
	}
	
	private static String drainFormatSymbolFromInt(int par1) {
		if(par1 >= 50) return "§c";
		else if(par1 >= 25) return "§6";
		else if(par1 >= 10) return "§e";
		else if(par1 >= 5) return "§a";
		else return "§9";
	}
	
	public static int getDrainIndexFromInt(int par1) {
		if(par1 >= 50) return 0;
		else if(par1 >= 25) return 1;
		else if(par1 >= 10) return 2;
		else if(par1 >= 5) return 3;
		else return 4;
	}
	
}
