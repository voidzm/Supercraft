package com.voidzm.supercraft.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.StringTranslate;
import net.minecraftforge.client.GuiControlsScrollPanel;

public class GuiSupercraftControls extends GuiSupercraftScreen {

	private GuiSupercraftScreen parent;
	private String screenTitle = "Controls";
	private GameSettings settings;
	private int buttonID = -1;

	public boolean isIngame = false;
	
	private GuiSupercraftControlsSlot controlsSlot;

	public GuiSupercraftControls(GuiSupercraftScreen par1GuiScreen, GameSettings par2GameSettings) {
		this.parent = par1GuiScreen;
		this.imageTick = par1GuiScreen.imageTick;
		if(par1GuiScreen instanceof GuiSupercraftOptions) {
			this.isIngame = ((GuiSupercraftOptions)par1GuiScreen).isIngame;
		}
		this.settings = par2GameSettings;
	}
	
	@Override
	public void initGui() {
		controlsSlot = new GuiSupercraftControlsSlot(this, settings);
		StringTranslate t = StringTranslate.getInstance();
		this.buttons.clear();
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 - 100, this.height - 32, 200, 16, 200, t.translateKey("gui.done")));
		controlsSlot.registerScrollButtons(buttons, 7, 8);
		this.screenTitle = t.translateKey("controls.title");
	}

	@Override
	public void buttonEvent(int id) {        
		switch(id) {
			case 200:
				this.parent.imageTick = this.imageTick;
				this.mc.displayGuiScreen(this.parent);
				break;
		}
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if(controlsSlot.keyTyped(par1, par2)) {
			super.keyTyped(par1, par2);
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		if(!isIngame) {
			super.drawScreenBackground(par1, par2, par3);
		}
		this.drawRect(0, 0, width, 40, 0xBB000000);
		this.drawRect(0, 40, width, 41, 0xDD000000);
		this.drawRect(0, 41, width, height-49, 0x88000000);
		this.drawRect(0, height-49, width, height-48, 0xDD000000);
		this.drawRect(0, height-48, width, height, 0xBB000000);
		super.drawScreenForeground(par1, par2, par3);
		controlsSlot.drawScreen(par1, par2, par3);
		drawCenteredString(fontRenderer, screenTitle, width / 2, 18, 0xffffff);
	}

}
