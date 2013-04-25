package com.voidzm.supercraft.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.voidzm.particle.EntityVenianFX;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.util.VenianProperties;
import com.voidzm.supercraft.util.VenianProperties.VenianAspect;
import com.voidzm.supercraft.util.VenianProperties.VenianMaterial;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

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
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(this.canPlayerCast(par2EntityPlayer)) par2EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return false;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(this.canPlayerCast(par3EntityPlayer)) par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 40;
	}
	
	public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count) {
		if(!player.worldObj.isRemote && !this.canPlayerCast(player)) {
			player.stopUsingItem();
			return;
		}
		if(!player.worldObj.isRemote && count <= 1) {
			player.stopUsingItem();
			this.doSpell(stack, player);
		}
		if(!player.worldObj.isRemote && count < 0) {
			player.stopUsingItem();
		}
		if(player.worldObj.isRemote && count >= 10 && count < 38) {
			Random rand = new Random();
			for(int i = 0; i < (40 - count) * 1.5; i++) {
				double dist = (Math.random() * 5.0D);
				double rotation = (Math.random() * 2.0D * Math.PI);
				double dx = dist * Math.cos(rotation);
				double dz = dist * Math.sin(rotation);
				double absdy = Math.random() * Math.random() * 4;
				double dy = rand.nextBoolean() ? absdy : -absdy;
				double posX = player.posX+dx;
				double posY = player.posY+dy;
				double posZ = player.posZ+dz;
				double motionX = -dx / 9.0D;
				double motionY = -dy / 9.0D;
				double motionZ = -dz / 9.0D;
				EntityVenianFX particle = new EntityVenianFX(player.worldObj, posX, posY, posZ, motionX, motionY, motionZ);
				Minecraft.getMinecraft().effectRenderer.addEffect(particle);
			}
		}
	}
	
	private void doSpell(ItemStack stack, EntityPlayer player) {
		System.out.println("Cast the spell.");
		NBTTagCompound data = player.getEntityData();
		if(!data.hasKey("Veneficia")) return;
		NBTTagCompound veneficia = data.getCompoundTag("Veneficia");
		int currentEnergy = veneficia.getInteger("Energy");
		veneficia.setInteger("Energy", currentEnergy-10);
		veneficia.setInteger("Cooldown", 5);
	}
	
	private boolean canPlayerCast(EntityPlayer player) {
		if(player.worldObj.isRemote) return true;
		NBTTagCompound data = player.getEntityData();
		if(!data.hasKey("Veneficia")) {
			return false;
		}
		NBTTagCompound veneficia = data.getCompoundTag("Veneficia");
		if(veneficia.getInteger("Cooldown") > 0) {
			return false;
		}
		return true;
	}
	
}
