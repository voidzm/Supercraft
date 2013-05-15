//**
//**  ServerTickHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import java.util.ArrayList;
import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
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
