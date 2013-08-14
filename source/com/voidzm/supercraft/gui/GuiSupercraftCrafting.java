//////////////////////////////////////
//*       GuiSupercraftCrafting.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.container.ContainerRefinedWorkbench;

public class GuiSupercraftCrafting extends GuiContainer {

	private static final ResourceLocation CRAFTINGTABLE_BACKGROUND = new ResourceLocation("textures/gui/container/crafting_table.png");
	
	public GuiSupercraftCrafting(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
		super(new ContainerRefinedWorkbench(par1InventoryPlayer, par2World, par3, par4, par5));
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.crafting"), 28, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	this.mc.renderEngine.func_110577_a(CRAFTINGTABLE_BACKGROUND);
    	int var5 = (this.width - this.xSize) / 2;
    	int var6 = (this.height - this.ySize) / 2;
    	this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }
	
}
