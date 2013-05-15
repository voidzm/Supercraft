//////////////////////////////////////
//*        SCMainMenu.java         *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StringTranslate;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.Supercraft;

import cpw.mods.fml.client.GuiModList;

public class SCMainMenu extends GuiScreen {

	private BufferedImage background;
	private ArrayList<GuiButtonTransparent> buttons;
	
	private int imageCycleTick = 0;
	
	private static final int imageTime = 200;
	private static final int transitionTime = 100;
	
	public SCMainMenu() {
		buttons = new ArrayList<GuiButtonTransparent>();
		try {
			background = ImageIO.read(this.getClass().getResourceAsStream("/mods/supercraft/textures/gui/bg.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringTranslate translator = StringTranslate.getInstance();
		createButton(translator.translateKey("menu.singleplayer"));
		createButton(translator.translateKey("menu.multiplayer"));
		createButton(translator.translateKey("menu.options"));
		createButton("Mods");
		createButton(translator.translateKey("menu.quit"));
		imageCycleTick = new Random().nextInt(4)*imageTime;
	}
	
	private void createButton(String text) {
		if(buttons.size() != 4) buttons.add(new GuiButtonTransparent(this, 22, 48 + (buttons.size() * 22), 106, 16, buttons.size(), text));
		else buttons.add(new GuiButtonTransparent(this, 22, 64 + (buttons.size() * 22), 106, 16, buttons.size(), text));
	} 
	
	public void buttonEvent(int id) {
		switch(id) {
		case 0:
			mc.displayGuiScreen(new GuiSelectWorld(this));
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 1:
			mc.displayGuiScreen(new GuiMultiplayer(this));
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 2:
			mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 3:
			mc.displayGuiScreen(new GuiModList(this));
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 4:
			mc.shutdown();
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		}
	}
	
	@Override
	protected void mouseClicked(int mx, int my, int par3) {
		for(GuiButtonTransparent button : buttons) {
			button.clickEvent(mx, my);
		}
	}
	
	@Override
	public void updateScreen() {
		imageCycleTick++;
	}
	
	public void drawScreen(int mouseX, int mouseY, float tick) {
		if(imageCycleTick >= imageTime*4) imageCycleTick = 0;
		float basef = imageCycleTick / imageTime;
		int base = (int)Math.floor(basef);
		int prog = (int)imageCycleTick % imageTime;
		if(base == 0) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg.png"));
		else if(base == 1) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg2.png"));
		else if(base == 2) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg3.png"));
		else if(base == 3) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg4.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int[] locs = this.calcPositions();
		this.drawTexture(locs[0], locs[1], locs[2], locs[3]);
		if(prog >= (imageTime-transitionTime)) {
			GL11.glPushMatrix();
			int alpha = (prog-(imageTime-transitionTime))*2;
			if(base == 0) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg2.png"));
			else if(base == 1) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg3.png"));
			else if(base == 2) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg4.png"));
			else if(base == 3) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg.png"));
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, ((float)alpha)/100);
			this.drawTexture(locs[0], locs[1], locs[2], locs[3]);
			GL11.glPopMatrix();
		}
		this.drawRect(0, 0, 150, height, 0x88000000);
		this.drawRect(150, 0, 151, height, 0xAA000000);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/minecraft.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawTexture(22, 22, 106, 16);
		for(GuiButtonTransparent iterated : buttons) {
			iterated.draw(mouseX, mouseY);
		}
		this.drawCenteredString(mc.fontRenderer, "Minecraft "+Supercraft.minecraftVersion, 75, height - 22, Color.GRAY.getRGB());
	}
	
	private int[] calcPositions() {
		int[] pos = new int[4];
		float bgRatio = (float)background.getWidth() / (float)background.getHeight();
		if((float)this.width / (float)this.height < bgRatio) {
			pos[2] = (int)(height * bgRatio);
			pos[3] = height;
		}
		else {
			pos[2] = width;
			pos[3] = (int)(width / bgRatio);
		}
		pos[0] = width / 2 - pos[2] / 2;
		pos[1] = height / 2 - pos[3] / 2;
		return pos;
	}
	
	private void drawTexture(int x, int y, int w, int h) {
		Tessellator tes = Tessellator.instance;
		tes.startDrawingQuads();
		tes.addVertexWithUV(x, y + h, 0, 0, 1);
		tes.addVertexWithUV(x + w, y + h, 0, 1, 1);
		tes.addVertexWithUV(x + w, y, 0, 1, 0);
		tes.addVertexWithUV(x, y, 0, 0, 0);
		tes.draw();
	}
	
}
