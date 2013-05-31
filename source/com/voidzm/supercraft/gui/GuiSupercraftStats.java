package com.voidzm.supercraft.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringTranslate;

public class GuiSupercraftStats extends GuiSupercraftScreen {

	private static RenderItem renderItem = new RenderItem();
	
	private GuiSupercraftScreen parent;
	
	private String screenTitle = "Statistics";

	private GuiSupercraftStatsGeneralSlot generalSlot;
	private GuiSupercraftStatsItemSlot itemSlot;
	private GuiSupercraftStatsBlockSlot blockSlot;

	private StatFileWriter statFileWriter;

	private GuiSupercraftSlot selectedSlot = null;

	public GuiSupercraftStats(GuiSupercraftScreen par1GuiScreen, StatFileWriter par2StatFileWriter) {
		this.parent = par1GuiScreen;
		this.statFileWriter = par2StatFileWriter;
	}

	public void initGui() {
		this.screenTitle = StatCollector.translateToLocal("gui.stats");
		this.generalSlot = new GuiSupercraftStatsGeneralSlot(this);
		this.generalSlot.registerScrollButtons(this.buttonList, 1, 1);
		this.itemSlot = new GuiSupercraftStatsItemSlot(this);
		this.itemSlot.registerScrollButtons(this.buttonList, 1, 1);
		this.blockSlot = new GuiSupercraftStatsBlockSlot(this);
		this.blockSlot.registerScrollButtons(this.buttonList, 1, 1);
		this.selectedSlot = this.generalSlot;
		this.addHeaderButtons();
	}

	public void addHeaderButtons() {
		StringTranslate t = StringTranslate.getInstance();
		this.buttons.clear();
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 - 71, this.height - 28, 150, 16, 0, t.translateKey("gui.done")));
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 - 154, this.height - 52, 100, 16, 1, t.translateKey("stat.generalButton")));
		GuiButtonTransparent guibutton;
		this.buttons.add(guibutton = new GuiButtonTransparent(this, this.width / 2 - 46, this.height - 52, 100, 16, 2, t.translateKey("stat.blocksButton")));
		GuiButtonTransparent guibutton1;
		this.buttons.add(guibutton1 = new GuiButtonTransparent(this, this.width / 2 + 62, this.height - 52, 100, 16, 3, t.translateKey("stat.itemsButton")));
		if(this.blockSlot.getSize() == 0) {
			guibutton.enabled = false;
		}
		if(this.itemSlot.getSize() == 0) {
			guibutton1.enabled = false;
		}
	}

	@Override
	public void buttonEvent(int id) {
		switch(id) {
			case 0:
				this.mc.displayGuiScreen(this.parent);
				break;
			case 1:
				this.selectedSlot = this.generalSlot;
				break;
			case 2:
				this.selectedSlot = this.itemSlot;
				break;
			case 3:
				this.selectedSlot = this.blockSlot;
				break;
			default:
				this.selectedSlot.actionPerformed(id);
				break;
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawRect(0, 0, width, 40, 0xBB000000);
		this.drawRect(0, 40, width, 41, 0xDD000000);
		this.drawRect(0, 41, width, height-73, 0x88000000);
		this.drawRect(0, height-73, width, height-72, 0xDD000000);
		this.drawRect(0, height-72, width, height, 0xBB000000);
		super.drawScreenForeground(par1, par2, par3);
		this.selectedSlot.drawScreen(par1, par2, par3);
		this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 18, 16777215);
	}

	private void drawItemSprite(int par1, int par2, int par3){
		this.drawButtonBackground(par1 + 1, par2 + 1);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.enableGUIStandardItemLighting();
		renderItem.renderItemIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(par3, 1, 0), par1 + 2, par2 + 2);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	private void drawButtonBackground(int par1, int par2) {
		this.drawSprite(par1, par2, 0, 0);
	}

	private void drawSprite(int par1, int par2, int par3, int par4) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/gui/slot.png");
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 18), (double)this.zLevel, (double)((float)(par3 + 0) * 0.0078125F), (double)((float)(par4 + 18) * 0.0078125F));
		tessellator.addVertexWithUV((double)(par1 + 18), (double)(par2 + 18), (double)this.zLevel, (double)((float)(par3 + 18) * 0.0078125F), (double)((float)(par4 + 18) * 0.0078125F));
		tessellator.addVertexWithUV((double)(par1 + 18), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 18) * 0.0078125F), (double)((float)(par4 + 0) * 0.0078125F));
		tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 0) * 0.0078125F), (double)((float)(par4 + 0) * 0.0078125F));
		tessellator.draw();
	}

	public static StatFileWriter getStatsFileWriter(GuiSupercraftStats par0GuiStats) {
		return par0GuiStats.statFileWriter;
	}

	public static void drawSprite(GuiSupercraftStats par0GuiStats, int par1, int par2, int par3, int par4) {
		par0GuiStats.drawSprite(par1, par2, par3, par4);
	}

	public static void drawGradientRect(GuiSupercraftStats par0GuiStats, int par1, int par2, int par3, int par4, int par5, int par6) {
		par0GuiStats.drawGradientRect(par1, par2, par3, par4, par5, par6);
	}

	public static void drawItemSprite(GuiSupercraftStats par0GuiStats, int par1, int par2, int par3) {
		par0GuiStats.drawItemSprite(par1, par2, par3);
	}

}
