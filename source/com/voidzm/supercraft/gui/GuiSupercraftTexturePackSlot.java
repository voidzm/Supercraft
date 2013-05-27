package com.voidzm.supercraft.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.texturepacks.GuiTexturePacks;
import net.minecraft.client.texturepacks.ITexturePack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class GuiSupercraftTexturePackSlot extends GuiSupercraftSlot {

	private GuiSupercraftTexturePacks parent;

	public GuiSupercraftTexturePackSlot(GuiSupercraftTexturePacks par1) {
		super(GuiSupercraftTexturePacks.fetchMinecraft(par1), par1.width, par1.height, 32, par1.height - 51, 36);
		this.parent = par1;
	}

	@Override
	protected int getSize() {
		return GuiSupercraftTexturePacks.fetchMinecraft(this.parent).texturePackList.availableTexturePacks().size();
	}

	@Override
	protected void elementClicked(int par1, boolean par2) {
		List list = GuiSupercraftTexturePacks.fetchMinecraft(this.parent).texturePackList.availableTexturePacks();
		try {
			GuiSupercraftTexturePacks.fetchMinecraft(this.parent).texturePackList.setTexturePack((ITexturePack)list.get(par1));
			GuiSupercraftTexturePacks.fetchMinecraft(this.parent).renderEngine.refreshTextures();
			GuiSupercraftTexturePacks.fetchMinecraft(this.parent).renderGlobal.loadRenderers();
		}
		catch(Exception exception) {
			GuiSupercraftTexturePacks.fetchMinecraft(this.parent).texturePackList.setTexturePack((ITexturePack)list.get(0));
			GuiSupercraftTexturePacks.fetchMinecraft(this.parent).renderEngine.refreshTextures();
			GuiSupercraftTexturePacks.fetchMinecraft(this.parent).renderGlobal.loadRenderers();
		}
	}

	@Override
	protected boolean isSelected(int i) {
		List list = GuiSupercraftTexturePacks.fetchMinecraft(this.parent).texturePackList.availableTexturePacks();
		return GuiSupercraftTexturePacks.fetchMinecraft(this.parent).texturePackList.getSelectedTexturePack() == list.get(i);
	}

	@Override
	protected int getContentHeight() {
		return this.getSize() * 36;
	}

	@Override
	protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
		ITexturePack itexturepack = (ITexturePack)GuiSupercraftTexturePacks.fetchMinecraft(this.parent).texturePackList.availableTexturePacks().get(par1);
		itexturepack.bindThumbnailTexture(GuiSupercraftTexturePacks.fetchMinecraft(this.parent).renderEngine);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		par5Tessellator.startDrawingQuads();
		par5Tessellator.setColorOpaque_I(16777215);
		par5Tessellator.addVertexWithUV((double)par2, (double)(par3 + par4), 0.0D, 0.0D, 1.0D);
		par5Tessellator.addVertexWithUV((double)(par2 + 32), (double)(par3 + par4), 0.0D, 1.0D, 1.0D);
		par5Tessellator.addVertexWithUV((double)(par2 + 32), (double)par3, 0.0D, 1.0D, 0.0D);
		par5Tessellator.addVertexWithUV((double)par2, (double)par3, 0.0D, 0.0D, 0.0D);
		par5Tessellator.draw();
		String s = itexturepack.getTexturePackFileName();
		if(!itexturepack.isCompatible()) {
			s = EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("texturePack.incompatible") + " - " + s;
		}
		if(s.length() > 32) {
			s = s.substring(0, 32).trim() + "...";
		}
		this.parent.drawString(GuiSupercraftTexturePacks.fetchFontRenderer(this.parent), s, par2 + 32 + 2, par3 + 1, 16777215);
		this.parent.drawString(GuiSupercraftTexturePacks.fetchFontRenderer(this.parent), itexturepack.getFirstDescriptionLine(), par2 + 32 + 2, par3 + 12, 8421504);
		this.parent.drawString(GuiSupercraftTexturePacks.fetchFontRenderer(this.parent), itexturepack.getSecondDescriptionLine(), par2 + 32 + 2, par3 + 12 + 10, 8421504);
	}

}
