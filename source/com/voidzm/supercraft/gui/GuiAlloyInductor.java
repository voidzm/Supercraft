package com.voidzm.supercraft.gui;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.container.ContainerAlloyInductor;
import com.voidzm.supercraft.container.ContainerEssentialReducer;
import com.voidzm.supercraft.entity.TileEntityAlloyInductor;
import com.voidzm.supercraft.entity.TileEntityEssentialReducer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiAlloyInductor extends GuiContainer {

	private TileEntityAlloyInductor tile;
	
	public GuiAlloyInductor(InventoryPlayer player, TileEntityAlloyInductor te) {
		super(new ContainerAlloyInductor(player, te));
		tile = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/supercraft/textures/gui/alloyinductor.png");
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		if(this.tile.isInducting()) {
			this.drawTexturedModalRect(x + 82, y + 22, 176, 0, this.tile.progressInductionScaled(39)+1, 17);
		}
		if(this.tile.essenceRemaining > 0) {
			this.drawTexturedModalRect(x + 64, y + 51, 176, 17, (33 - this.tile.progressEssenceScaled(33))+1, 14);
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString("Alloy Inductor", 8, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
}
