package com.voidzm.supercraft.gui;

import java.util.Date;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.storage.SaveFormatComparator;

public class GuiSupercraftWorldSlot {

	private GuiSupercraftSelectWorld parent;

	private final Minecraft mc;

	private int width;
	private int height;
	protected int top;
	protected int bottom;
	private int right;
	private int left;
	protected final int slotHeight;

	private int scrollUpButtonID;
	private int scrollDownButtonID;

	protected int mouseX;
	protected int mouseY;
	private float initialClickY = -2.0F;

	private float scrollMultiplier;
	private float amountScrolled;

	private int selectedElement = -1;
	private long lastClicked = 0L;

	private boolean showSelectionBox = true;
	private boolean field_77243_s;
	private int field_77242_t;

	public GuiSupercraftWorldSlot(GuiSupercraftSelectWorld guiParent) {
		this.mc = Minecraft.getMinecraft();
		this.width = guiParent.width;
		this.height = guiParent.height;
		this.top = 48;
		this.bottom = guiParent.height - 80;
		this.slotHeight = 36;
		this.left = 0;
		this.right = guiParent.width;
		this.parent = guiParent;
	}

	protected int getSize() {
		return GuiSupercraftSelectWorld.getSize(this.parent).size();
	}

	protected void elementClicked(int par1, boolean par2) {
		GuiSupercraftSelectWorld.onElementSelected(this.parent, par1);
		boolean flag1 = GuiSupercraftSelectWorld.getSelectedWorld(this.parent) >= 0 && GuiSupercraftSelectWorld.getSelectedWorld(this.parent) < this.getSize();
		GuiSupercraftSelectWorld.getSelectButton(this.parent).enabled = flag1;
		GuiSupercraftSelectWorld.getRenameButton(this.parent).enabled = flag1;
		GuiSupercraftSelectWorld.getDeleteButton(this.parent).enabled = flag1;
		GuiSupercraftSelectWorld.func_82312_f(this.parent).enabled = flag1;
		if(par2 && flag1) {
			this.parent.selectWorld(par1);
		}
	}

	protected boolean isSelected(int i) {
		return i == GuiSupercraftSelectWorld.getSelectedWorld(this.parent);
	}

	protected int getContentHeight() {
		return GuiSupercraftSelectWorld.getSize(this.parent).size() * 36;
	}

	protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
		SaveFormatComparator saveformatcomparator = (SaveFormatComparator)GuiSupercraftSelectWorld.getSize(this.parent).get(par1);
		String s = saveformatcomparator.getDisplayName();
		if(s == null || MathHelper.stringNullOrLengthZero(s)) {
			s = GuiSupercraftSelectWorld.func_82313_g(this.parent) + " " + (par1 + 1);
		}
		String s1 = saveformatcomparator.getFileName();
		s1 = s1 + " (" + GuiSupercraftSelectWorld.func_82315_h(this.parent).format(new Date(saveformatcomparator.getLastTimePlayed()));
		s1 = s1 + ")";
		String s2 = "";
		if(saveformatcomparator.requiresConversion()) {
			s2 = GuiSupercraftSelectWorld.func_82311_i(this.parent) + " " + s2;
		}
		else {
			s2 = GuiSupercraftSelectWorld.func_82314_j(this.parent)[saveformatcomparator.getEnumGameType().getID()];
			if(saveformatcomparator.isHardcoreModeEnabled()) {
				s2 = EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("gameMode.hardcore") + EnumChatFormatting.RESET;
			}
			if(saveformatcomparator.getCheatsEnabled()) {
				s2 = s2 + ", " + StatCollector.translateToLocal("selectWorld.cheats");
			}
		}
		this.parent.drawString(this.parent.fontRenderer(), s, par2 + 2, par3 + 1, 16777215);
		this.parent.drawString(this.parent.fontRenderer(), s1, par2 + 2, par3 + 12, 8421504);
		this.parent.drawString(this.parent.fontRenderer(), s2, par2 + 2, par3 + 12 + 10, 8421504);
	}

	public void setShowSelectionBox(boolean par1) {
		this.showSelectionBox = par1;
	}

	protected void func_77223_a(boolean par1, int par2) {
		this.field_77243_s = par1;
		this.field_77242_t = par2;
		if(!par1) {
			this.field_77242_t = 0;
		}
	}

	protected void func_77222_a(int par1, int par2, Tessellator par3Tessellator) {}

	protected void func_77224_a(int par1, int par2) {}

	protected void func_77215_b(int par1, int par2) {}

	public int func_77210_c(int par1, int par2) {
		int k = this.width / 2 - 110;
		int l = this.width / 2 + 110;
		int i1 = par2 - this.top - this.field_77242_t + (int)this.amountScrolled - 4;
		int j1 = i1 / this.slotHeight;
		return par1 >= k && par1 <= l && j1 >= 0 && i1 >= 0 && j1 < this.getSize() ? j1 : -1;
	}
	
	public void registerScrollButtons(List par1List, int par2, int par3) {
		this.scrollUpButtonID = par2;
		this.scrollDownButtonID = par3;
	}
	
	private void bindAmountScrolled() {
		int i = this.func_77209_d();
		if(i < 0) {
			i /= 2;
		}
		if(this.amountScrolled < 0.0F) {
			this.amountScrolled = 0.0F;
		}
		if(this.amountScrolled > (float)i) {
			this.amountScrolled = (float)i;
		}
	}

	public int func_77209_d() {
		return this.getContentHeight() - (this.bottom - this.top - 4);
	}

	public void func_77208_b(int par1) {
		this.amountScrolled += (float)par1;
		this.bindAmountScrolled();
		this.initialClickY = -2.0F;
	}

	public void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.enabled) {
			if(par1GuiButton.id == this.scrollUpButtonID) {
				this.amountScrolled -= (float)(this.slotHeight * 2 / 3);
				this.initialClickY = -2.0F;
				this.bindAmountScrolled();
			}
			else if(par1GuiButton.id == this.scrollDownButtonID) {
				this.amountScrolled += (float)(this.slotHeight * 2 / 3);
				this.initialClickY = -2.0F;
				this.bindAmountScrolled();
			}
		}
	}

	public void drawScreen(int par1, int par2, float par3) {
		this.mouseX = par1;
		this.mouseY = par2;
		int k = this.getSize();
		int l = this.getScrollBarX();
		int i1 = l + 6;
		int j1;
		int k1;
		int l1;
		int i2;
		int j2;
		if(Mouse.isButtonDown(0)) {
			if(this.initialClickY == -1.0F) {
				boolean flag = true;
				if(par2 >= this.top && par2 <= this.bottom) {
					int k2 = this.width / 2 - 110;
					j1 = this.width / 2 + 110;
					k1 = par2 - this.top - this.field_77242_t + (int)this.amountScrolled - 4;
					l1 = k1 / this.slotHeight;
					if(par1 >= k2 && par1 <= j1 && l1 >= 0 && k1 >= 0 && l1 < k) {
						boolean flag1 = l1 == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L;
						this.elementClicked(l1, flag1);
						this.selectedElement = l1;
						this.lastClicked = Minecraft.getSystemTime();
					}
					else if(par1 >= k2 && par1 <= j1 && k1 < 0) {
						this.func_77224_a(par1 - k2, par2 - this.top + (int)this.amountScrolled - 4);
						flag = false;
					}
					if(par1 >= l && par1 <= i1) {
						this.scrollMultiplier = -1.0F;
						j2 = this.func_77209_d();
						if(j2 < 1) {
							j2 = 1;
						}
						i2 = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / (float)this.getContentHeight());
						if(i2 < 32) {
							i2 = 32;
						}
						if(i2 > this.bottom - this.top - 8) {
							i2 = this.bottom - this.top - 8;
						}
						this.scrollMultiplier /= (float)(this.bottom - this.top - i2) / (float)j2;
					}
					else {
						this.scrollMultiplier = 1.0F;
					}
					if(flag) {
						this.initialClickY = (float)par2;
					}
					else {
						this.initialClickY = -2.0F;
					}
				}
				else {
					this.initialClickY = -2.0F;
				}
			}
			else if(this.initialClickY >= 0.0F) {
				this.amountScrolled -= ((float)par2 - this.initialClickY) * this.scrollMultiplier;
				this.initialClickY = (float)par2;
			}
		}
		else {
			while(!this.mc.gameSettings.touchscreen && Mouse.next()) {
				int l2 = Mouse.getEventDWheel();
				if(l2 != 0) {
					if(l2 > 0) {
						l2 = -1;
					}
					else if(l2 < 0) {
						l2 = 1;
					}
					this.amountScrolled += (float)(l2 * this.slotHeight / 2);
				}
			}
			this.initialClickY = -1.0F;
		}
		this.bindAmountScrolled();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		Tessellator tessellator = Tessellator.instance;
		drawContainerBackground(tessellator);
		j1 = this.width / 2 - 92 - 16;
		k1 = this.top + 4 - (int)this.amountScrolled;
		if(this.field_77243_s) {
			this.func_77222_a(j1, k1, tessellator);
		}
		int i3;
		for(l1 = 0; l1 < k; ++l1) {
			j2 = k1 + l1 * this.slotHeight + this.field_77242_t;
			i2 = this.slotHeight - 4;
			if(j2 <= this.bottom && j2 + i2 >= this.top) {
				if(this.showSelectionBox && this.isSelected(l1)) {
					i3 = this.width / 2 - 110;
					int j3 = this.width / 2 + 110;
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					GL11.glShadeModel(GL11.GL_SMOOTH);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					tessellator.startDrawingQuads();
					tessellator.setColorRGBA_I(6710886, 96);
					tessellator.addVertexWithUV((double)(i3 - 4), (double)(j2 + i2 + 6), 0.0D, 0.0D, 1.0D);
					tessellator.addVertexWithUV((double)(j3 + 4), (double)(j2 + i2 + 6), 0.0D, 1.0D, 1.0D);
					tessellator.addVertexWithUV((double)(j3 + 4), (double)(j2 - 6), 0.0D, 1.0D, 0.0D);
					tessellator.addVertexWithUV((double)(i3 - 4), (double)(j2 - 6), 0.0D, 0.0D, 0.0D);
					tessellator.setColorRGBA_I(0, 127);
					tessellator.addVertexWithUV((double)(i3 - 3), (double)(j2 + i2 + 5), 0.0D, 0.0D, 1.0D);
					tessellator.addVertexWithUV((double)(j3 + 3), (double)(j2 + i2 + 5), 0.0D, 1.0D, 1.0D);
					tessellator.addVertexWithUV((double)(j3 + 3), (double)(j2 - 5), 0.0D, 1.0D, 0.0D);
					tessellator.addVertexWithUV((double)(i3 - 3), (double)(j2 - 5), 0.0D, 0.0D, 0.0D);
					tessellator.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}
				this.drawSlot(l1, j1, j2, i2, tessellator);
			}
		}
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		byte b0 = 4;
		this.overlayBackground(0, this.top, 255, 255);
		this.overlayBackground(this.bottom, this.height, 255, 255);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		j2 = this.func_77209_d();
		if(j2 > 0) {
			i2 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
			if(i2 < 32) {
				i2 = 32;
			}
			if(i2 > this.bottom - this.top - 8) {
				i2 = this.bottom - this.top - 8;
			}
			i3 = (int)this.amountScrolled * (this.bottom - this.top - i2) / j2 + this.top;
			if(i3 < this.top) {
				i3 = this.top;
			}
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_I(0, 255);
			tessellator.addVertexWithUV((double)l, (double)this.bottom, 0.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV((double)i1, (double)this.bottom, 0.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV((double)i1, (double)this.top, 0.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV((double)l, (double)this.top, 0.0D, 0.0D, 0.0D);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_I(8421504, 255);
			tessellator.addVertexWithUV((double)l, (double)(i3 + i2), 0.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV((double)i1, (double)(i3 + i2), 0.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV((double)i1, (double)i3, 0.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV((double)l, (double)i3, 0.0D, 0.0D, 0.0D);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_I(12632256, 255);
			tessellator.addVertexWithUV((double)l, (double)(i3 + i2 - 1), 0.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV((double)(i1 - 1), (double)(i3 + i2 - 1), 0.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV((double)(i1 - 1), (double)i3, 0.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV((double)l, (double)i3, 0.0D, 0.0D, 0.0D);
			tessellator.draw();
		}
		this.func_77215_b(par1, par2);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}

	protected int getScrollBarX() {
		return this.width / 2 + 124;
	}

	protected void drawBackground() {}

	protected void overlayBackground(int par1, int par2, int par3, int par4) {}

	protected void drawContainerBackground(Tessellator tess) {}

}
