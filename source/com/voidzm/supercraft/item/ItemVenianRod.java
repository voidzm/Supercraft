package com.voidzm.supercraft.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.misc.DamageSourceVeneficia;
import com.voidzm.supercraft.particle.EntityVenianFX;
import com.voidzm.supercraft.util.VenianProperties;
import com.voidzm.supercraft.util.VenianProperties.VenianAspect;
import com.voidzm.supercraft.util.VenianProperties.VenianMaterial;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemVenianRod extends ItemSupercraft {
	
	private String[] powerNames = {"Impotent", "Weak", "Sturdy", "Strong", "Omnipotent"};
	private String[] rangeNames = {"Blind", "Nearsighted", "Seeing", "Farsighted", "Allseeing"};
	private String[] drainNames = {"Draining", "Demanding", "Balanced", "Efficient", "Self-Sustaining"};
	private String[] aspectNames = {"Lightning", "Flaming", "Freezing"};

	public static DamageSource veneficiaDamage = new DamageSourceVeneficia();
	
	private Random rand = new Random();
	
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
		VenianProperties lightning = new VenianProperties(VenianAspect.LIGHTNING, VenianMaterial.LITHIUM, 10, 30, 3);
		list.add(lightning.applyProperties(lightningStack));
		ItemStack flamingStack = new ItemStack(itemID, 1, 0);
		VenianProperties flaming = new VenianProperties(VenianAspect.FLAMING, VenianMaterial.LITHIUM, 10, 30, 3);
		list.add(flaming.applyProperties(flamingStack));
		ItemStack freezingStack = new ItemStack(itemID, 1, 0);
		VenianProperties freezing = new VenianProperties(VenianAspect.FREEZING, VenianMaterial.LITHIUM, 10, 30, 3);
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
		if(!player.worldObj.isRemote) { // Server
			if(!this.canPlayerCast(player)) {
				player.stopUsingItem();
				count = this.getMaxItemUseDuration(stack);
			}
			if(rand.nextInt(50) > count) player.worldObj.playSoundAtEntity(player, "random.pop", 0.5F, 2.0F); 
			if(count <= 1) {
				player.stopUsingItem();
				this.doSpell(stack, player);
			}
		}
		else { // Client
			double pdisplacment = 0.0D;
			EntityPlayer thePlayer = FMLClientHandler.instance().getClient().thePlayer;
			if(!thePlayer.isEntityEqual(player)) pdisplacment = 1.6D;
			if(count >= 10 && count < 38) {
				for(int i = 0; i < (40 - count) * 1.5; i++) {
					double dist = (Math.random() * 15.0D);
					double rotation = (Math.random() * 2.0D * Math.PI);
					double dx = dist * Math.cos(rotation);
					double dz = dist * Math.sin(rotation);
					double absdy = Math.random() * Math.random() * 4;
					double dy = rand.nextBoolean() ? absdy : -absdy;
					double posX = player.posX+dx;
					double posY = player.posY+dy+pdisplacment;
					double posZ = player.posZ+dz;
					double motionX = -dx / 9.0D;
					double motionY = -dy / 9.0D;
					double motionZ = -dz / 9.0D;
					EntityVenianFX particle = new EntityVenianFX(player.worldObj, posX, posY, posZ, motionX, motionY, motionZ);
					Minecraft.getMinecraft().effectRenderer.addEffect(particle);
				}
			}
			if(count <= 1) {
				for(int i = 0; i < 300; i++) {
					double dist = (Math.random() * 8.0D);
					double rotation = (Math.random() * 2.0D * Math.PI);
					double dx = dist * Math.cos(rotation);
					double dz = dist * Math.sin(rotation);
					double absdy = Math.random() * Math.random() * 4;
					double dy = rand.nextBoolean() ? absdy : -absdy;
					double posX = player.posX;
					double posY = player.posY+pdisplacment;
					double posZ = player.posZ;
					double motionX = dx / 9.0D;
					double motionY = dy / 9.0D;
					double motionZ = dz / 9.0D;
					EntityVenianFX particle = new EntityVenianFX(player.worldObj, posX, posY, posZ, motionX, motionY, motionZ);
					Minecraft.getMinecraft().effectRenderer.addEffect(particle);
				}
				player.stopUsingItem();
			}
		}
	}
	
	private void doSpell(ItemStack stack, EntityPlayer player) {
		NBTTagCompound data = player.getEntityData();
		if(!data.hasKey("Veneficia")) return;
		NBTTagCompound veneficia = data.getCompoundTag("Veneficia");
		int currentEnergy = veneficia.getInteger("Energy");
		VenianProperties spellProperties = VenianProperties.readFromItemStack(stack);
		int targetEnergy = currentEnergy - spellProperties.drain;
		if(targetEnergy >= 0) veneficia.setInteger("Energy", targetEnergy);
		else {
			veneficia.setInteger("Energy", 0);
			int disparity = -targetEnergy;
			player.attackEntityFrom(veneficiaDamage, disparity);
		}
		if(!player.capabilities.isCreativeMode) {
			veneficia.setInteger("Cooldown", 5);
		}
		else veneficia.setInteger("Cooldown", 2);
		VenianAspect aspect = spellProperties.aspect;
		int range = spellProperties.range;
		int power = spellProperties.power;
		int px = MathHelper.floor_double(player.posX);
		int py = MathHelper.floor_double(player.posY);
		int pz = MathHelper.floor_double(player.posZ);
		World world = player.worldObj;
		if(aspect == VenianAspect.LIGHTNING) {
			world.playSoundAtEntity(player, "random.explode", 2.0F, 1.0F);
			for(int i = 0; i < power; i++) {
				int rx = rand.nextInt(range+1);
				if(rand.nextBoolean()) rx = -rx;
				int rz = rand.nextInt(range+1);
				if(rand.nextBoolean()) rz = -rz;
				int tx = px + rx;
				int tz = pz + rz;
				int ty = world.getHeightValue(tx, tz);
				world.addWeatherEffect(new EntityLightningBolt(player.worldObj, tx, ty, tz));	
			}
		}
		else if(aspect == VenianAspect.FLAMING) {
			world.playSoundAtEntity(player, "random.explode", 2.0F, 1.0F);
			for(int ix = -range; ix < range; ix++) {
				for(int iy = -range; iy < range; iy++) {
					for(int iz = -range; iz < range; iz++) {
						int id = world.getBlockId(px+ix, py+iy, pz+iz);
						if(id == 0 && Block.fire.canPlaceBlockAt(world, px+ix, py+iy, pz+iz)) {
							double distance = Math.sqrt(ix*ix + iz*iz);
							distance = Math.sqrt(distance*distance + iy*iy);
							if(distance > range) continue;
							double adj = distance / (double)range;
							adj = 1.0D - adj;
							double moddedPower = power * adj;
							int modifier = MathHelper.floor_double(100.0D - moddedPower);
							if(modifier < 0) modifier = 0;
							if(rand.nextInt(4) == 0 && rand.nextInt(100) > modifier) {
								world.setBlock(px+ix, py+iy, pz+iz, Block.fire.blockID);
							}
						}
					}
				}
			}
		}
		else if(aspect == VenianAspect.FREEZING) {
			world.playSoundAtEntity(player, "random.glass", 2.0F, 1.0F);
			for(int ix = -range; ix < range; ix++) {
				for(int iy = -range; iy < range; iy++) {
					for(int iz = -range; iz < range; iz++) {
						int id = world.getBlockId(px+ix, py+iy, pz+iz);
						if(id == Block.waterStill.blockID) {
							double distance = Math.sqrt(ix*ix + iz*iz);
							distance = Math.sqrt(distance*distance + iy*iy);
							if(distance > range) continue;
							double adj = distance / (double)range;
							adj = 1.0D - adj;
							double moddedPower = power * adj;
							int modifier = MathHelper.floor_double(100.0D - moddedPower);
							if(modifier < 0) modifier = 0;
							if(rand.nextInt(100) > modifier) world.setBlock(px+ix, py+iy, pz+iz, Block.ice.blockID);
						}
					}
				}
			}
		}
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
