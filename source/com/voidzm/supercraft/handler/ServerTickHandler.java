//**
//**  ServerTickHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.EnumSet;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.biome.BiomeGenTenebralWoods;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.gui.SCMainMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {

	private int veneficiaUpdateTick = 0;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		WorldServer[] worlds = MinecraftServer.getServer().worldServers;
		ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();
		for(WorldServer world : worlds) {
			players.addAll(world.playerEntities);
		}
		boolean shouldUpdateVeneficia = false;
		if(veneficiaUpdateTick >= 20) {
			veneficiaUpdateTick = 0;
			shouldUpdateVeneficia = true;
		}
		for(EntityPlayer player : players) {
			NBTTagCompound playerData = player.getEntityData();
			if(!playerData.hasKey("Veneficia")) this.putDefaultVeneficiaProperties(playerData);
			NBTTagCompound veneficia = playerData.getCompoundTag("Veneficia");
			if(shouldUpdateVeneficia) {
				int currentEnergy = veneficia.getInteger("Energy");
				int maxEnergy = veneficia.getInteger("MaxEnergy");
				int cooldown = veneficia.getInteger("Cooldown");
				if(currentEnergy < maxEnergy) {
					System.out.println("Energy bumped to "+(currentEnergy+1)+".");
					veneficia.setInteger("Energy", currentEnergy+1);
				}
				if(cooldown > 0) veneficia.setInteger("Cooldown", cooldown-1);
			}
		}
		shouldUpdateVeneficia = false;
		veneficiaUpdateTick++;
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		return null;
	}
	
	private void putDefaultVeneficiaProperties(NBTTagCompound compound) {
		NBTTagCompound veneficia = new NBTTagCompound();
		veneficia.setInteger("Energy", 100);
		veneficia.setInteger("MaxEnergy", 100);
		veneficia.setInteger("Cooldown", 0);
		compound.setTag("Veneficia", veneficia);
	}

}
