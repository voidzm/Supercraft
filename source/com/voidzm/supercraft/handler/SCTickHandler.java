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
import com.voidzm.supercraft.gui.SCMainMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GLAllocation;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SCTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.CLIENT))) {
			GuiScreen currentGui = Minecraft.getMinecraft().currentScreen;
			if(currentGui instanceof GuiMainMenu) Minecraft.getMinecraft().displayGuiScreen(new SCMainMenu());
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.CLIENT))) {
			GuiScreen currentGui = Minecraft.getMinecraft().currentScreen;
			if(currentGui instanceof GuiMainMenu) Minecraft.getMinecraft().displayGuiScreen(new SCMainMenu());
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return null;
	}

}
