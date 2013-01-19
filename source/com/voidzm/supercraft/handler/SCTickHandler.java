//////////////////////////////////////
//*       SCTickHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.handler;

import java.util.EnumSet;

import com.voidzm.supercraft.Supercraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SCTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		Minecraft mcRef = Minecraft.getMinecraft();
		if(mcRef.currentScreen instanceof GuiMainMenu) {
			mcRef.displayGuiScreen(Supercraft.mainMenu);
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
