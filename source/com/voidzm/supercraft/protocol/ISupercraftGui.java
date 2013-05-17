package com.voidzm.supercraft.protocol;

import net.minecraft.client.gui.FontRenderer;

public interface ISupercraftGui {

	public void buttonEvent(int id);

	public void drawCenteredString(FontRenderer render, String textMod, int i, int j, int rgb);

	public void drawQuad(int x, int y, int i, int j, int rgb);
	
}
