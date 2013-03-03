package com.voidzm.supercraft.gui;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.container.ContainerEssentialReducer;
import com.voidzm.supercraft.entity.TileEntityEssentialReducer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiEssentialReducer extends GuiContainer {

	private TileEntityEssentialReducer tile;
	
	public GuiEssentialReducer(InventoryPlayer player, TileEntityEssentialReducer te) {
		super(new ContainerEssentialReducer(player, te));
		tile = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		int tex = mc.renderEngine.getTexture("/com/voidzm/supercraft/img/essentialreducer.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(tex);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		if(this.tile.isReducing()) {
			this.drawTexturedModalRect(x + 69, y + 25, 176, 0, this.tile.progressScaled(32)+1, 37);
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString("Essential Reducer", 8, 6, 5592405);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 5592405);
	}
	
}
