package com.voidzm.supercraft.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.util.StringTranslate;

public class GuiSupercraftYesNo extends GuiSupercraftScreen {

	protected GuiSupercraftScreen parent;
	
	protected String message1;
	protected String message2;
	protected String buttonText1;
	protected String buttonText2;

	protected int worldNumber;

	public GuiSupercraftYesNo(GuiSupercraftScreen par1GuiScreen, String par2Str, String par3Str, int par4) {
		this.parent = par1GuiScreen;
		this.message1 = par2Str;
		this.message2 = par3Str;
		this.worldNumber = par4;
		StringTranslate stringtranslate = StringTranslate.getInstance();
		this.buttonText1 = stringtranslate.translateKey("gui.yes");
		this.buttonText2 = stringtranslate.translateKey("gui.no");
		this.imageTick = par1GuiScreen.imageTick;
	}

	public GuiSupercraftYesNo(GuiSupercraftScreen par1GuiScreen, String par2Str, String par3Str, String par4Str, String par5Str, int par6) {
		this.parent = par1GuiScreen;
		this.message1 = par2Str;
		this.message2 = par3Str;
		this.buttonText1 = par4Str;
		this.buttonText2 = par5Str;
		this.worldNumber = par6;
		this.imageTick = par1GuiScreen.imageTick;
	}

	@Override
	public void initGui() {
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 - 155, this.height / 6 + 96, 140, 16, 0, this.buttonText1));
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 - 155 + 160, this.height / 6 + 96, 140, 16, 1, this.buttonText2));
	}

	@Override
	public void buttonEvent(int id) {
		this.parent.imageTick = this.imageTick;
		switch(id) {
		case 0:
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			this.parent.confirmClicked(true, this.worldNumber);
			break;
		case 1:
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			this.parent.confirmClicked(false, this.worldNumber);
			break;
		}
	}

	@Override
	public void drawScreenForeground(int par1, int par2, float par3) {
		this.drawRect(0, 0, width, height, 0x88000000);
		this.drawRect(0, 0, width, height, 0x77000000);
		super.drawScreenForeground(par1, par2, par3);
		this.drawCenteredString(this.fontRenderer, this.message1, this.width / 2, 70, 16777215);
		this.drawCenteredString(this.fontRenderer, this.message2, this.width / 2, 90, 16777215);
	}
	
}

