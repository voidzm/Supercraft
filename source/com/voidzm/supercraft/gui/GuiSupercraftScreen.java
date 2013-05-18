package com.voidzm.supercraft.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.Supercraft;
import cpw.mods.fml.client.GuiModList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;

public class GuiSupercraftScreen extends GuiScreen {

	private BufferedImage background;
	protected ArrayList<GuiButtonTransparent> buttons = new ArrayList<GuiButtonTransparent>();
	
	public int imageTick = 0;
	
	private static final int imageTime = 200;
	private static final int transitionTime = 100;
	
	public GuiSupercraftScreen() {
		try {
			background = ImageIO.read(this.getClass().getResourceAsStream("/mods/supercraft/textures/gui/bg.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		imageTick = new Random().nextInt(4)*imageTime;
	}
	
	public void buttonEvent(int id) {}
	
	@Override
	protected void mouseClicked(int mx, int my, int par3) {
		for(GuiButtonTransparent button : buttons) {
			button.clickEvent(mx, my);
		}
	}
	
	@Override
	public void updateScreen() {
		imageTick++;
	}
	
	public void drawScreenBackground(int mouseX, int mouseY, float tick) {
		if(imageTick >= imageTime*4) imageTick = 0;
		float basef = imageTick / imageTime;
		int base = (int)Math.floor(basef);
		int prog = (int)imageTick % imageTime;
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
	}
	
	public void drawScreenForeground(int mouseX, int mouseY, float tick) {
		for(GuiButtonTransparent iterated : buttons) {
			iterated.draw(mouseX, mouseY);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float tick) {
		this.drawScreenBackground(mouseX, mouseY, tick);
		this.drawScreenForeground(mouseX, mouseY, tick);
	}
	
	protected int[] calcPositions() {
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
	
	protected void drawTexture(int x, int y, int w, int h) {
		Tessellator tes = Tessellator.instance;
		tes.startDrawingQuads();
		tes.addVertexWithUV(x, y + h, 0, 0, 1);
		tes.addVertexWithUV(x + w, y + h, 0, 1, 1);
		tes.addVertexWithUV(x + w, y, 0, 1, 0);
		tes.addVertexWithUV(x, y, 0, 0, 0);
		tes.draw();
	}
	
	public Minecraft getMinecraft() {
		return this.mc;
	}
	
	public FontRenderer getFontRenderer() {
		return this.fontRenderer;
	}
	
	public float getZLevel() {
		return this.zLevel;
	}
	
}
