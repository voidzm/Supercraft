//////////////////////////////////////
//*        SCGuiButton.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.gui;

import java.awt.Color;

import com.voidzm.supercraft.protocol.ISupercraftGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class GuiButtonTransparent {
	
	private ISupercraftGui mainMenu;
	private int x, y, width, height, alpha;
	public int id;
	private String text;
	private FontRenderer render;
	public boolean enabled = true;

	public GuiButtonTransparent(ISupercraftGui menu, int par1, int par2, int par3, int par4, int par5, String string) {
		this.mainMenu = menu;
		this.x = par1;
		this.y = par2;
		this.width = par3;
		this.height = par4;
		this.id = par5;
		this.text = string;
		this.alpha = 0;
		this.render = Minecraft.getMinecraft().fontRenderer;
	}
	
	public void draw(int mx, int my) {
		if(isInside(mx, my) && this.enabled) {
			alpha = 30;
		}
		else {
			alpha = 0;
		}
		Color boxColor = new Color(255, 255, 255, alpha);
		String textMod = text;
		if(isInside(mx, my) && this.enabled) {
			textMod = "> " + textMod + " <";
		}
		mainMenu.drawCenteredString(render, textMod, x + (width / 2), y + (height / 4), this.enabled ? Color.WHITE.getRGB() : 6710886);
		mainMenu.drawQuad(x, y, x + width, y + height, boxColor.getRGB());
	}
	
	public boolean isInside(int mx, int my) {
		return mx >= x && my >= y && mx <= x + width && my <= y + height;
	}
	
	public void clickEvent(int mx, int my) {
		if(isInside(mx, my) && this.enabled) {
			alpha = 60;
			this.mainMenu.buttonEvent(this.id);
		}
	}
	
}
