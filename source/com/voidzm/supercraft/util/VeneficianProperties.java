package com.voidzm.supercraft.util;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;

public class VeneficianProperties {

	private static Random rand = new Random();
	
	public static String[] vitalityNames = {"Impotent", "Weakened", "Sturdy", "Vital", "Omnipotent"};
	public static String[] perceptionNames = {"Blind", "Nearsighted", "Seeing", "Farsighted", "Allseeing"};
	public static String[] energyNames = {"Deenergized", "Discharged", "Balanced", "Charged", "Energized"};
	public static String[] aspectNames = {"Decay", "Atrophy", "Arachnia", "Detonation", "Lightning", "Blazing", "Rifting", "Rage", "Despair", "Breaching", "Withering", "Disparity", "Eruption", "Infection", "Irrigation", "Freezing", "Empty", "Void"};
	public static String[] stabilityWords = {"Mutating", "Unstable", "Unpredictable", "Tempermental", "Predictable", "Stable", "Inert"};
	public static String[] materialNames = {"Aluminum", "Copper", "Silver", "Golden", "Electrum", "Nisilic", "Cobalt", "Platinum", "Lithium"};
	
	private static int[][] energyChangeFactors = {{0, 5}, {-2, 5}, {-2, 3}, {-10, 2}, {-5, 5}, {0, 10}, {0, 5}, {-5, 0}, {-2, 2}}; // Downward is positive. Largest changes should be about 10.
	private static int[][] perceptionChangeFactors = {{-2, 0}, {-2, 1}, {-1, 1}, {-1, 4}, {-2, 2}, {-4, 0}, {-2, 0}, {0, 2}, {-1, 1}}; // Upward is positive. Largest changes should be about 4.
	private static int[][] vitalityChangeFactors = {{-1, 0}, {-1, 1}, {-1, 1}, {-1, 3}, {-2, 2}, {-3, 0}, {-2, 0}, {0, 2}, {-1, 1}}; // Upward is positive. Largest changes should be about 3.
	
	private static VeneficiaType[][] aspectMutations = {{VeneficiaType.DECAY, VeneficiaType.RAGE}, {VeneficiaType.BREACHING, VeneficiaType.WITHERING},
		{VeneficiaType.BREACHING, VeneficiaType.INFECTION}, {VeneficiaType.DISPARITY, VeneficiaType.LIGHTNING}, {VeneficiaType.DETONATION, VeneficiaType.RIFTING},
		{VeneficiaType.ERUPTION, VeneficiaType.DESPAIR}, {VeneficiaType.LIGHTNING, VeneficiaType.DESPAIR}, {VeneficiaType.DECAY, VeneficiaType.BLAZING},
		{VeneficiaType.RIFTING, VeneficiaType.ERUPTION}, {VeneficiaType.BREACHING, VeneficiaType.ATROPHY}, {VeneficiaType.ATROPHY, VeneficiaType.DESPAIR},
		{VeneficiaType.DECAY, VeneficiaType.ERUPTION}, {VeneficiaType.DISPARITY, VeneficiaType.BLAZING}, {VeneficiaType.ARACHNIA, VeneficiaType.IRRIGATION},
		{VeneficiaType.INFECTION, VeneficiaType.FREEZING}, {VeneficiaType.IRRIGATION, VeneficiaType.DECAY}};
	
	private static boolean[] aspectMutationFactors = {false, false, false, true, true, false, false, true, true};
	
	public enum VeneficiaType {
		DECAY(0, "Zombie"), ATROPHY(1, "Skeleton"), ARACHNIA(2, "Spider"), DETONATION(3, "Creeper"), LIGHTNING(4, "Creeper"), BLAZING(5, "Blaze"), RIFTING(6, "Enderman"), RAGE(7, "PigZombie"), DESPAIR(8, "Ghast"), BREACHING(9, "Silverfish"), WITHERING(10, "Skeleton"), DISPARITY(11, "Slime"), ERUPTION(12, "LavaSlime"), INFECTION(13, "CaveSpider"), IRRIGATION(14, "Squid"), FREEZING(15, "SnowMan"), EMPTY(16, ""), VOID(17, "");
		public int index;
		public String entityName;
		private VeneficiaType(int par1, String eName) {
			index = par1;
			entityName = eName;
		}
	}
	
	public enum VeneficiaMaterial {
		ALUMINUM(0), COPPER(1), SILVER(2), GOLD(3), ELECTRUM(4), NISIL(5), COBALT(6), PLATINUM(7), LITHIUM(8);
		public int index;
		private VeneficiaMaterial(int par1) {
			index = par1;
		}
	}
	
	public VeneficiaType aspect;
	public VeneficiaMaterial material;
	public int vitality;
	public int perception;
	public int energy;
	public int stability;
	
	public VeneficianProperties(VeneficiaType par1, VeneficiaMaterial par2, int par3Vitality, int par4Perception, int par5Energy, int par6Stability) {
		this.aspect = par1;
		this.material = par2;
		this.vitality = par3Vitality;
		this.perception = par4Perception;
		this.energy = par5Energy;
		this.stability = par6Stability;
		this.checkLimits();
	}
	
	public ItemStack applyProperties(ItemStack stack) {
		this.checkLimits();
		if(this.aspect == VeneficiaType.EMPTY || this.aspect == VeneficiaType.VOID) {
			return this.doSpecialProperties(stack);
		}
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
		lore.appendTag(new NBTTagString("", getVitalityString(this.vitality)));
		lore.appendTag(new NBTTagString("", getPerceptionString(this.perception)));
		lore.appendTag(new NBTTagString("", getEnergyString(this.energy)));
		lore.appendTag(new NBTTagString("", getStabilityString(this.stability)));
		NBTTagCompound veneficia = new NBTTagCompound();
		veneficia.setInteger("Aspect", this.aspect.index);
		veneficia.setInteger("Material", this.material.index);
		veneficia.setInteger("Vitality", this.vitality);
		veneficia.setInteger("Perception", this.perception);
		veneficia.setInteger("Energy", this.energy);
		veneficia.setInteger("Stability", this.stability);
		tag.setTag("Veneficia", veneficia);
		return stack;
	}
	
	private ItemStack doSpecialProperties(ItemStack stack) {
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
		if(this.aspect == VeneficiaType.EMPTY) lore.appendTag(new NBTTagString("", EnumChatFormatting.GRAY + "Empty"));
		if(this.aspect == VeneficiaType.VOID) lore.appendTag(new NBTTagString("", EnumChatFormatting.GRAY + "Vacuum"));
		NBTTagCompound veneficia = new NBTTagCompound();
		veneficia.setInteger("Aspect", this.aspect.index);
		veneficia.setInteger("Material", this.material.index);
		veneficia.setInteger("Vitality", this.vitality);
		veneficia.setInteger("Perception", this.perception);
		veneficia.setInteger("Energy", this.energy);
		veneficia.setInteger("Stability", this.stability);
		tag.setTag("Veneficia", veneficia);
		return stack;
	}
	
	public static VeneficianProperties readFromItemStack(ItemStack stack) {
		if(!stack.hasTagCompound()) return null;
		NBTTagCompound tag = stack.getTagCompound();
		if(!tag.hasKey("Veneficia")) return null;
		NBTTagCompound venian = tag.getCompoundTag("Veneficia");
		VeneficianProperties prop = newEmptyProperties(VeneficiaMaterial.LITHIUM);
		prop.aspect = VeneficiaType.values()[venian.getInteger("Aspect")];
		prop.material = VeneficiaMaterial.values()[venian.getInteger("Material")];
		prop.vitality = venian.getInteger("Vitality");
		prop.perception = venian.getInteger("Perception");
		prop.energy = venian.getInteger("Energy");
		prop.stability = venian.getInteger("Stability");
		prop.checkLimits();
		return prop;
	}
	
	private static String getVitalityString(int par1) {
		String base = EnumChatFormatting.GRAY + "Vitality: ";
		EnumChatFormatting color;
		if(par1 <= 8) color = EnumChatFormatting.RED;
		else if(par1 <= 16) color = EnumChatFormatting.GOLD;
		else if(par1 <= 32) color = EnumChatFormatting.YELLOW;
		else if(par1 <= 64) color = EnumChatFormatting.GREEN;
		else color = EnumChatFormatting.BLUE;
		return base + color + par1 + EnumChatFormatting.RESET;
	}
	
	public static int getVitalityIndex(int par1) {
		if(par1 <= 8) return 0;
		else if(par1 <= 16) return 1;
		else if(par1 <= 32) return 2;
		else if(par1 <= 64) return 3;
		else return 4;
	}
	
	private static String getPerceptionString(int par1) {
		String base = EnumChatFormatting.GRAY + "Perception: ";
		EnumChatFormatting color;
		if(par1 <= 8) color = EnumChatFormatting.RED;
		else if(par1 <= 16) color = EnumChatFormatting.GOLD;
		else if(par1 <= 24) color = EnumChatFormatting.YELLOW;
		else if(par1 <= 48) color = EnumChatFormatting.GREEN;
		else color = EnumChatFormatting.BLUE;
		return base + color + par1 + EnumChatFormatting.RESET;
	}
	
	public static int getPerceptionIndex(int par1) {
		if(par1 <= 8) return 0;
		else if(par1 <= 16) return 1;
		else if(par1 <= 24) return 2;
		else if(par1 <= 48) return 3;
		else return 4;
	}
	
	private static String getEnergyString(int par1) {
		String base = EnumChatFormatting.GRAY + "Energy: ";
		EnumChatFormatting color;
		if(par1 >= 128) color = EnumChatFormatting.RED;
		else if(par1 >= 64) color = EnumChatFormatting.GOLD;
		else if(par1 >= 24) color = EnumChatFormatting.YELLOW;
		else if(par1 >= 8) color = EnumChatFormatting.GREEN;
		else color = EnumChatFormatting.BLUE;
		return base + color + par1 + EnumChatFormatting.RESET;
	}
	
	public static int getEnergyIndex(int par1) {
		if(par1 >= 128) return 0;
		else if(par1 >= 64) return 1;
		else if(par1 >= 28) return 2;
		else if(par1 >= 8) return 3;
		else return 4;
	}
	
	private static String getStabilityString(int par1) {
		String base = EnumChatFormatting.WHITE + "" + EnumChatFormatting.BOLD;
		return base + stabilityWords[getStabilityIndex(par1)] + EnumChatFormatting.RESET;
	}
	
	private static int getStabilityIndex(int par1) {
		switch(par1) {
			case 0:
				return 0;
			case 1:
			case 2:
				return 1;
			case 3:
			case 4:
				return 2;
			case 5:
				return 3;
			case 6:
			case 7:
				return 4;
			case 8:
			case 9:
				return 5;
			case 10:
				return 6;
			default:
				return 0;
		}
	}
	
	private void checkLimits() {
		if(this.vitality < 1) this.vitality = 1;
		if(this.vitality > 128) this.vitality = 128;
		if(this.perception < 4) this.perception = 4;
		if(this.perception > 64) this.perception = 64;
		if(this.energy < 1) this.energy = 1;
		if(this.energy > 256) this.energy = 256;
		if(this.stability < 0) this.stability = 0;
		if(this.stability > 10) this.stability = 10;
	}

	public static VeneficianProperties newEmptyProperties(VeneficiaMaterial par1Mat) {
		return new VeneficianProperties(VeneficiaType.EMPTY, par1Mat, 0, 0, 0, 0);
	}
	
	public static VeneficianProperties newVoidProperties(VeneficiaMaterial par1Mat) {
		return new VeneficianProperties(VeneficiaType.VOID, par1Mat, 0, 0, 0, 0);
	}
	
	public boolean runStabilityUpdate() { // Try to mutate these properties. As Stability goes from 0 to 10, chance of mutation goes from 100% to 0%.
		if(this.aspect == VeneficiaType.EMPTY || this.aspect == VeneficiaType.VOID) return false;
		Random rand = new Random();
		if(!(rand.nextInt(10) < this.stability)) { // Do an update based on Stability.
			// Select a mutation. Energy and Perception each mutate 30% of the time, Vitality mutates 25% of the time, and Aspect only mutates 15% of the time.
			int factor = rand.nextInt(100);
			if(factor < 30) {
				this.doEnergyMutation(rand);
			}
			else if(factor < 60) {
				this.doPerceptionMutation(rand);
			}
			else if(factor < 85) {
				this.doVitalityMutation(rand);
			}
			else {
				this.doAspectMutation(rand);
			}
			// Since a mutation occured, the probability of mutation decreases by a random amount.
			this.stability += (rand.nextInt(2) + 1);
			this.checkLimits();
			return true;
		}
		else return false;
	}
	
	private void doEnergyMutation(Random rand) {
		int[] aspectFactors = perceptionChangeFactors[this.material.index];
		int lowestPossibleDelta = aspectFactors[0];
		int highestPossibleDelta = aspectFactors[1];
		int difference = highestPossibleDelta - lowestPossibleDelta;
		int unadjusted = rand.nextInt(difference + 1);
		int adjusted = unadjusted + lowestPossibleDelta;
		this.perception += adjusted;
		this.checkLimits();
	}
	
	private void doPerceptionMutation(Random rand) {
		int[] aspectFactors = energyChangeFactors[this.material.index];
		int lowestPossibleDelta = aspectFactors[0];
		int highestPossibleDelta = aspectFactors[1];
		int difference = highestPossibleDelta - lowestPossibleDelta;
		int unadjusted = rand.nextInt(difference + 1);
		int adjusted = unadjusted + lowestPossibleDelta;
		this.energy += adjusted;
		this.checkLimits();
	}
	
	private void doVitalityMutation(Random rand) {
		int[] aspectFactors = vitalityChangeFactors[this.material.index];
		int lowestPossibleDelta = aspectFactors[0];
		int highestPossibleDelta = aspectFactors[1];
		int difference = highestPossibleDelta - lowestPossibleDelta;
		int unadjusted = rand.nextInt(difference + 1);
		int adjusted = unadjusted + lowestPossibleDelta;
		this.vitality += adjusted;
		this.checkLimits();
	}

	private void doAspectMutation(Random rand) {
		VeneficiaType[] possibleNewAspects = aspectMutations[this.aspect.index];
		boolean doPositiveMutation = aspectMutationFactors[this.material.index];
		if(doPositiveMutation) {
			this.aspect = possibleNewAspects[1];
		}
		else {
			this.aspect = possibleNewAspects[0];
		}
		this.checkLimits();
	}
	
}
