package com.voidzm.supercraft.gui;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.container.ContainerElectroplationEngine;
import com.voidzm.supercraft.tileentity.TileEntityAlloyInductor;
import com.voidzm.supercraft.tileentity.TileEntityElectroplationEngine;
import com.voidzm.supercraft.tileentity.TileEntityEssentialReducer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiElectroplationEngine extends GuiContainer {

	private TileEntityElectroplationEngine tile;
	
	public GuiElectroplationEngine(InventoryPlayer player, TileEntityElectroplationEngine te) {
		super(new ContainerElectroplationEngine(player, te));
		tile = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/supercraft/textures/gui/electroplationengine.png");
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		if(this.tile.isElectroplating()) {
			this.drawTexturedModalRect(x + 48, y + 31, 176, 0, this.tile.progressElectroplationScaled(26)+1, 17);
			this.drawTexturedModalRect(x + 102 + (26 - this.tile.progressElectroplationScaled(26)), y + 31, 176 + (26 - this.tile.progressElectroplationScaled(26)), 17, this.tile.progressElectroplationScaled(26)+1, 17);
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString("Electroplation Engine", 8, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
}
