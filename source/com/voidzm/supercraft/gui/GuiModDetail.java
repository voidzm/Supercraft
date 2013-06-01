package com.voidzm.supercraft.gui;

import java.awt.Dimension;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StringTranslate;
import cpw.mods.fml.client.TextureFXManager;
import cpw.mods.fml.common.ModContainer;

public class GuiModDetail extends GuiSupercraftScreen {

	private GuiSupercraftModList parent;
	private ModContainer mod;
	
	public GuiModDetail(GuiSupercraftModList screen, ModContainer detailedMod) {
		this.parent = screen;
		this.imageTick = screen.imageTick;
		this.mod = detailedMod;
	}
	
	@Override
	public void initGui() {
		StringTranslate t = StringTranslate.getInstance();
		this.buttons.clear();
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 - 100, this.height - 33, 200, 16, 0, t.translateKey("gui.done")));
	}
	
	@Override
	public void buttonEvent(int id) {
		switch(id) {
			case 0:
				this.parent.imageTick = this.imageTick;
				this.mc.displayGuiScreen(this.parent);
				GuiSupercraftModList.getButtonSelect(this.parent).enabled = true;
				break;
		}
	}
	
	@Override
	public void drawScreenForeground(int mx, int my, float tick) {
		this.drawRect(0, 0, width, height, 0xBB000000);
		super.drawScreenForeground(mx, my, tick);
		String logoFile = this.mod.getMetadata().logoFile;
		if(!logoFile.isEmpty()) {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mc.renderEngine.bindTexture(logoFile);
			Dimension dim = TextureFXManager.instance().getTextureDimensions(logoFile);
			double scaleX = dim.width / 200.0;
			double scaleY = dim.height / 65.0;
			double scale = 1.0;
			if(scaleX > 1 || scaleY > 1) {
				scale = 1.0 / Math.max(scaleX, scaleY);
			}
			dim.width *= scale;
			dim.height *= scale;
			int top = 15;
			Tessellator t = Tessellator.instance;
			t.startDrawingQuads();
			t.addVertexWithUV((this.width / 2) - 100.0, top + dim.height, zLevel, 0, 1);
			t.addVertexWithUV((this.width / 2) - 100.0 + dim.width, top + dim.height, zLevel, 1, 1);
			t.addVertexWithUV((this.width / 2) - 100.0 + dim.width, top, zLevel, 1, 0);
			t.addVertexWithUV((this.width / 2) - 100.0, top, zLevel, 0, 0);
			t.draw();
			GL11.glDisable(GL11.GL_BLEND);
		}
		int offset = 5;
		int shift = 0;
		String modName = this.mod.getName();
		String modDisplayVersion = this.mod.getDisplayVersion();
		String modVersion = this.mod.getVersion();
		String modCredits = this.mod.getMetadata().credits;
		String modAuthors = this.mod.getMetadata().getAuthorList();
		String modWebsite = this.mod.getMetadata().url;
		String modDescription = this.mod.getMetadata().description;
		if(modName != null) {
			this.drawCenteredString(this.fontRenderer, modName, this.width / 2, 82 + offset + shift, 0xFFFFFF);
			shift += 15;
		}
		if(modDisplayVersion != null && modVersion != null) {
			this.drawCenteredString(this.fontRenderer, String.format("Version: %s (%s)", modDisplayVersion, modVersion), this.width / 2, 82 + offset + shift, 0xAAAAAA);
			shift += 15;
		}
		if(modCredits != null) {
			this.drawCenteredString(this.fontRenderer, String.format("Credits: %s", modCredits), this.width / 2, 82 + offset + shift, 0xAAAAAA);
			shift += 15;
		}
		if(modAuthors != null) {
			this.drawCenteredString(this.fontRenderer, String.format("Authors: %s", modAuthors), this.width / 2, 82 + offset + shift, 0xAAAAAA);
			shift += 15;
		}
		if(modWebsite != null) {
			this.drawCenteredString(this.fontRenderer, String.format("Website: %s", modWebsite), this.width / 2, 82 + offset + shift, 0xAAAAAA);
			shift += 15;
		}
		if(modDescription != null) {
			this.fontRenderer.drawSplitString(modDescription, this.width / 2 - 200, 157 + offset, 400, 0xCCCCCC);
		}
	}
	
}
