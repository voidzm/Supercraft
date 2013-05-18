package com.voidzm.supercraft.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiFlatPresets;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;

public class GuiSupercraftPresetsListSlot extends GuiSupercraftSlot {

	public int field_82459_a;
	private final GuiSupercraftFlatPresets parent;

	public GuiSupercraftPresetsListSlot(GuiSupercraftFlatPresets screen) {
		super(screen.getMinecraft(), screen.width, screen.height, 80, screen.height - 37, 24);
		this.parent = screen;
		this.field_82459_a = -1;
	}

	private void func_82457_a(int par1, int par2, int par3) {
		this.func_82456_d(par1 + 1, par2 + 1);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.enableGUIStandardItemLighting();
		GuiSupercraftFlatPresets.getPresetIconRenderer().renderItemIntoGUI(this.parent.getFontRenderer(), this.parent.getMinecraft().renderEngine, new ItemStack(par3, 1, 0), par1 + 2, par2 + 2);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	private void func_82456_d(int par1, int par2) {
		this.func_82455_b(par1, par2, 0, 0);
	}

	private void func_82455_b(int par1, int par2, int par3, int par4) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.parent.getMinecraft().renderEngine.bindTexture("/gui/slot.png");
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 18), (double)this.parent.getZLevel(), (double)((float)(par3 + 0) * 0.0078125F), (double)((float)(par4 + 18) * 0.0078125F));
		tessellator.addVertexWithUV((double)(par1 + 18), (double)(par2 + 18), (double)this.parent.getZLevel(), (double)((float)(par3 + 18) * 0.0078125F), (double)((float)(par4 + 18) * 0.0078125F));
		tessellator.addVertexWithUV((double)(par1 + 18), (double)(par2 + 0), (double)this.parent.getZLevel(), (double)((float)(par3 + 18) * 0.0078125F), (double)((float)(par4 + 0) * 0.0078125F));
		tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.parent.getZLevel(), (double)((float)(par3 + 0) * 0.0078125F), (double)((float)(par4 + 0) * 0.0078125F));
		tessellator.draw();
	}

	protected int getSize() {
		return GuiSupercraftFlatPresets.getPresets().size();
	}

	protected void elementClicked(int par1, boolean par2) {
		this.field_82459_a = par1;
		this.parent.func_82296_g();
		GuiSupercraftFlatPresets.func_82298_b(this.parent).setText(((GuiSupercraftFlatPresets.GuiFlatPresetsItem)GuiSupercraftFlatPresets.getPresets().get(GuiSupercraftFlatPresets.func_82292_a(this.parent).field_82459_a)).presetData);
	}

	protected boolean isSelected(int par1) {
		return par1 == this.field_82459_a;
	}

	protected void drawBackground() {}

	protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
		GuiSupercraftFlatPresets.GuiFlatPresetsItem guiflatpresetsitem = (GuiSupercraftFlatPresets.GuiFlatPresetsItem)GuiSupercraftFlatPresets.getPresets().get(par1);
		this.func_82457_a(par2, par3, guiflatpresetsitem.iconId);
		this.parent.getFontRenderer().drawString(guiflatpresetsitem.presetName, par2 + 18 + 5, par3 + 6, 16777215);
	}

}
