//**
//**  GuiSupercraftButton.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiSupercraftButton extends GuiButton {

	private GuiScreen parent;
	private int x;
	private int y;
	
	public GuiSupercraftButton(GuiScreen screen, int par1, int par2, int par3, int par4, int par5, String par6Str) {
		super(par1, par2, par3, par4, par5, par6Str);
		this.parent = screen;
		this.x = par2;
		this.y = par3;
	}
	
	public boolean isInside(int mx, int my) {
		return mx >= x && my >= y && mx <= x + width && my <= y + height;
	}
	
	public void clickEvent(int mx, int my) {
		if(isInside(mx, my)) {
			if(this.parent instanceof GuiVeneficianPodium) {
				((GuiVeneficianPodium)this.parent).buttonEvent(this.id);
			}
		}
	}

}
