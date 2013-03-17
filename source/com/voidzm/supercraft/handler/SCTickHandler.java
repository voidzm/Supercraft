//////////////////////////////////////
//*       SCTickHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.handler;

import java.nio.FloatBuffer;
import java.util.EnumSet;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.biome.BiomeGenTenebralWoods;
import com.voidzm.supercraft.client.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.GLAllocation;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SCTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		Minecraft mcRef = Minecraft.getMinecraft();
		if(mcRef.currentScreen instanceof GuiMainMenu) {
			mcRef.displayGuiScreen(ClientProxy.mainMenu);
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// Nothing here.
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "SCTickHandler";
	}

}
