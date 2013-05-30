package com.voidzm.supercraft.gui;

import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.stats.StatFileWriter;

public class GuiSupercraftAchievements extends GuiAchievements {

	public GuiSupercraftAchievements(StatFileWriter par1StatFileWriter) {
		super(par1StatFileWriter);
	}
	
	@Override
	public void drawDefaultBackground() {
		this.drawRect(0, 0, width, height, 0xBB000000);
	}

}
