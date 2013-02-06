//////////////////////////////////////
//*       SCGuiCrafting.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.gui;

import org.lwjgl.opengl.GL11;

import com.voidzm.container.ContainerRefinedWorkbench;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SCGuiCrafting extends GuiContainer {

	public SCGuiCrafting(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
		super(new ContainerRefinedWorkbench(par1InventoryPlayer, par2World, par3, par4, par5));
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.crafting"), 28, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	int var4 = this.mc.renderEngine.getTexture("/gui/crafting.png");
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	this.mc.renderEngine.bindTexture(var4);
    	int var5 = (this.width - this.xSize) / 2;
    	int var6 = (this.height - this.ySize) / 2;
    	this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }
	
}
