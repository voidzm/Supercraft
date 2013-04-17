//////////////////////////////////////
//*     TileConduitRender.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.render;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.block.BlockConduit;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.protocol.IGenerator;
import com.voidzm.supercraft.protocol.IGenerator.GeneratorSide;
import com.voidzm.supercraft.tileentity.TileEntityConduit;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class TileConduitRender extends TileEntitySpecialRenderer {

	public static String normalPowerLineTexture = "supercraft:renderpowerok";
	public static String dangerPowerLineTexture = "supercraft:renderpowerdanger";
	
	private static String[] postfixes = new String[] {"wood", "stone", "iron", "copper", "aluminum", "silver", "gold", "electrum", "diamond", "cobalt", "platinum", "tantalum", "lithium"};
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		this.render(var1, var2, var4, var6, var8);
	}
	
	private void render(TileEntity tile, double x, double y, double z, float partialTick) { // X, Y, and Z measured relative to player
		if(tile instanceof TileEntityConduit) {
			int tileX = tile.xCoord;
			int tileY = tile.yCoord;
			int tileZ = tile.zCoord;
			World world = tile.worldObj;
			if(world == null) return;
			Block block = Block.blocksList[world.getBlockId(tileX, tileY, tileZ)];
			if(block == null) return;
			int metadata = world.getBlockMetadata(tileX, tileY, tileZ);
			
			RenderEngine engine = this.tileEntityRenderer.renderEngine;
			
			Tessellator t = Tessellator.instance;
			if(!t.isDrawing) {
				t.startDrawingQuads();
			}
			t.setBrightness(block.getMixedBrightnessForBlock(world, tileX, tileY, tileZ));
			t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
				
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);		

			float minX = 0.4375F;
			float minY = 0.4375F;
			float minZ = 0.4375F;
			float maxX = 0.5625F;
			float maxY = 0.5625F;
			float maxZ = 0.5625F;
			if(BlockConduit.isConduitConnectable(world.getBlockId(tileX+1, tileY, tileZ), GeneratorSide.EAST)) {
				maxX = 1.0F;
			}
			if(BlockConduit.isConduitConnectable(world.getBlockId(tileX-1, tileY, tileZ), GeneratorSide.WEST)) {
				minX = 0.0F;
			}
			if(BlockConduit.isConduitConnectable(world.getBlockId(tileX, tileY+1, tileZ), GeneratorSide.TOP)) {
				maxY = 1.0F;
			}
			if(BlockConduit.isConduitConnectable(world.getBlockId(tileX, tileY-1, tileZ), GeneratorSide.BOTTOM)) {
				minY = 0.0F;
			}
			if(BlockConduit.isConduitConnectable(world.getBlockId(tileX, tileY, tileZ+1), GeneratorSide.SOUTH)) {
				maxZ = 1.0F;
			}
			if(BlockConduit.isConduitConnectable(world.getBlockId(tileX, tileY, tileZ-1), GeneratorSide.NORTH)) {
				minZ = 0.0F;
			}
			if(((TileEntityConduit)tile).powerLevel() != 0) {
				if(((TileEntityConduit)tile).powerLevel() <= TileEntityConduit.limitForType(((TileEntityConduit)tile).conduitType())) {
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, engine.getTexture("/mods/supercraft/textures/blocks/renderpowerok.png"));
				}
				else {
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, engine.getTexture("/mods/supercraft/textures/blocks/renderpowerdanger.png"));
				}
				this.renderCuboidInGlobalCoords(t, x+minX, y+0.4375, z+0.4375, (maxX-minX), 0.125, 0.125);
				if(minZ != 0.4375F) {
					this.renderCuboidInGlobalCoords(t, x+0.4375, y+0.4375, z, 0.125, 0.125, 0.4375);
				}	
				if(maxZ != 0.5625F) {
					this.renderCuboidInGlobalCoords(t, x+0.4375, y+0.4375, z+0.5625, 0.125, 0.125, 0.4375);
				}
				if(minY != 0.4375F) {
					this.renderCuboidInGlobalCoords(t, x+0.4375, y, z+0.4375, 0.125, 0.4375, 0.125);
				}
				if(maxY != 0.5625F) {
					this.renderCuboidInGlobalCoords(t, x+0.4375, y+0.5625, z+0.4375, 0.125, 0.4375, 0.125);
				}
				t.draw();
			}
			
			if(!t.isDrawing) {
				t.startDrawingQuads();
			}
			t.setBrightness(block.getMixedBrightnessForBlock(world, tileX, tileY, tileZ));
			t.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, engine.getTexture("/mods/supercraft/textures/blocks/"+BlockConduit.texturePaths[metadata]));
			
			double xStart = 0.25;
			double xMid = 0.5;
			double xEnd = 0.75;
			double yStart = 0.25;
			double yMid = 0.5;
			double yEnd = 0.75;
			
			if(!this.isConduitConnectable(world.getBlockId(tileX-1, tileY, tileZ), GeneratorSide.WEST)) {
				t.addVertexWithUV(x+0.25, y+0.75, z+0.25, xStart, yStart);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.75, xEnd, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yStart);
			}
			else {
				t.addVertexWithUV(x+0.25, y+0.75, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x, y+0.75, z+0.25, xMid, yEnd);
				t.addVertexWithUV(x, y+0.75, z+0.75, xMid, yStart);
				
				t.addVertexWithUV(x, y+0.25, z+0.75, xMid, yStart);
				t.addVertexWithUV(x, y+0.25, z+0.25, xMid, yEnd);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.75, xEnd, yStart);
				
				t.addVertexWithUV(x+0.25, y+0.75, z+0.25, xStart, yStart);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x, y+0.25, z+0.25, xMid, yEnd);
				t.addVertexWithUV(x, y+0.75, z+0.25, xMid, yStart);
				
				t.addVertexWithUV(x, y+0.75, z+0.75, xMid, yStart);
				t.addVertexWithUV(x, y+0.25, z+0.75, xMid, yEnd);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.75, xEnd, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(tileX+1, tileY, tileZ), GeneratorSide.EAST)) {
				t.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.75, xStart, yEnd);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.25, xEnd, yStart);
			}
			else {
				t.addVertexWithUV(x+1.0, y+0.75, z+0.75, xMid, yStart);
				t.addVertexWithUV(x+1.0, y+0.75, z+0.25, xMid, yEnd);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.75, xEnd, yStart);
				
				t.addVertexWithUV(x+0.75, y+0.25, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x+1.0, y+0.25, z+0.25, xMid, yEnd);
				t.addVertexWithUV(x+1.0, y+0.25, z+0.75, xMid, yStart);
				
				t.addVertexWithUV(x+1.0, y+0.75, z+0.25, xMid, yStart);
				t.addVertexWithUV(x+1.0, y+0.25, z+0.25, xMid, yEnd);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.25, xEnd, yStart);
				
				t.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.75, xStart, yEnd);
				t.addVertexWithUV(x+1.0, y+0.25, z+0.75, xMid, yEnd);
				t.addVertexWithUV(x+1.0, y+0.75, z+0.75, xMid, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(tileX, tileY, tileZ-1), GeneratorSide.NORTH)) {
				t.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yStart);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yStart);
			}
			else {
				t.addVertexWithUV(x+0.75, y+0.75, z, xStart, yMid);
				t.addVertexWithUV(x+0.25, y+0.75, z, xEnd, yMid);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yStart);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yStart);
				
				t.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yStart);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yStart);
				t.addVertexWithUV(x+0.25, y+0.25, z, xStart, yMid);
				t.addVertexWithUV(x+0.75, y+0.25, z, xEnd, yMid);
				
				t.addVertexWithUV(x+0.25, y+0.75, z, xMid, yStart);
				t.addVertexWithUV(x+0.25, y+0.25, z, xMid, yEnd);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yStart);
				
				t.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yStart);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x+0.75, y+0.25, z, xMid, yEnd);
				t.addVertexWithUV(x+0.75, y+0.75, z, xMid, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(tileX, tileY, tileZ+1), GeneratorSide.SOUTH)) {
				t.addVertexWithUV(x+0.25, y+0.75, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yEnd);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yEnd);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.75, xEnd, yStart);
			}
			else {
				t.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+1.0, xEnd, yMid);
				t.addVertexWithUV(x+0.75, y+0.75, z+1.0, xStart, yMid);
				
				t.addVertexWithUV(x+0.75, y+0.25, z+1.0, xEnd, yMid);
				t.addVertexWithUV(x+0.25, y+0.25, z+1.0, xStart, yMid);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yEnd);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yEnd);
				
				t.addVertexWithUV(x+0.25, y+0.75, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yEnd);
				t.addVertexWithUV(x+0.25, y+0.25, z+1.0, xMid, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+1.0, xMid, yStart);
			
				t.addVertexWithUV(x+0.75, y+0.75, z+1.0, xMid, yStart);
				t.addVertexWithUV(x+0.75, y+0.25, z+1.0, xMid, yEnd);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yEnd);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.75, xEnd, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(tileX, tileY-1, tileZ), GeneratorSide.BOTTOM)) {
				t.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yStart);
			}
			else {
				t.addVertexWithUV(x+0.75, y+0.25, z+0.25, xStart, yStart);
				t.addVertexWithUV(x+0.75, y, z+0.25, xStart, yMid);
				t.addVertexWithUV(x+0.25, y, z+0.25, xEnd, yMid);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.25, xEnd, yStart);
				
				t.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.25, y, z+0.75, xStart, yMid);
				t.addVertexWithUV(x+0.75, y, z+0.75, xEnd, yMid);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yStart);
				
				t.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yStart);
				t.addVertexWithUV(x+0.25, y, z+0.25, xStart, yMid);
				t.addVertexWithUV(x+0.25, y, z+0.75, xEnd, yMid);
				t.addVertexWithUV(x+0.25, y+0.25, z+0.75, xEnd, yStart);
				
				t.addVertexWithUV(x+0.75, y+0.25, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.75, y, z+0.75, xStart, yMid);
				t.addVertexWithUV(x+0.75, y, z+0.25, xEnd, yMid);
				t.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(tileX, tileY+1, tileZ), GeneratorSide.TOP)) {
				t.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yStart);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yStart);
			}
			else {
				t.addVertexWithUV(x+0.75, y+1.0, z+0.25, xStart, yMid);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.25, y+1.0, z+0.25, xEnd, yMid);
				
				t.addVertexWithUV(x+0.25, y+1.0, z+0.75, xStart, yMid);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.75, xStart, yEnd);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.75, xEnd, yEnd);
				t.addVertexWithUV(x+0.75, y+1.0, z+0.75, xEnd, yMid);
				
				t.addVertexWithUV(x+0.25, y+1.0, z+0.25, xStart, yMid);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.25, xStart, yEnd);
				t.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yEnd);
				t.addVertexWithUV(x+0.25, y+1.0, z+0.75, xEnd, yMid);
				
				t.addVertexWithUV(x+0.75, y+1.0, z+0.75, xStart, yMid);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yEnd);
				t.addVertexWithUV(x+0.75, y+0.75, z+0.25, xEnd, yEnd);
				t.addVertexWithUV(x+0.75, y+1.0, z+0.25, xEnd, yMid);
			}
			
			t.draw();
			
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();
			
		}
	}
	
	private boolean isConduitConnectable(int blockID, GeneratorSide side) {
		if(BlockConduit.isConduit(blockID)) return true;
		else if(BlockConduit.isGenerator(blockID)) {
			IGenerator block = (IGenerator)Block.blocksList[blockID];
			if(block == null) return false;
			if(block.shouldConnectAtSide(side)) return true;
			else return false;
		}
		else if(BlockConduit.isMachine(blockID)) return true;
		else return false;
	}
	
	public void renderCuboidInGlobalCoords(Tessellator t, double x1, double y1, double z1, double width, double height, double depth) {
		double xStart = 0.0;
		double xEnd = 1.0;
		double yStart = 0.0;
		double yEnd = 1.0;
		
		double xsm = xStart;
		double xem = xEnd;
		double ysm = yStart;
		double yem = yEnd;
		
		double widthDiff = ((16 - (width * 16)) / 2) / 16.0F;
		double depthDiff = ((16 - (depth * 16)) / 2) / 16.0F;
		double heightDiff = ((16 - (height * 16)) / 2) / 16.0F;
		
		xsm += depthDiff;
		xem -= depthDiff;
		ysm += heightDiff;
		yem -= heightDiff;
			
		t.addVertexWithUV(x1, y1+height, z1, xsm, ysm);
		t.addVertexWithUV(x1, y1, z1, xsm, yem);
		t.addVertexWithUV(x1, y1, z1+depth, xem, yem);
		t.addVertexWithUV(x1, y1+height, z1+depth, xem, ysm);
		
		xsm = xStart;
		xem = xEnd;
		ysm = yStart;
		yem = yEnd;
		
		xsm += widthDiff;
		xem -= widthDiff;
		ysm += heightDiff;
		yem -= heightDiff;
		
		t.addVertexWithUV(x1+width, y1+height, z1, xsm, ysm);
		t.addVertexWithUV(x1+width, y1, z1, xsm, yem);
		t.addVertexWithUV(x1, y1, z1, xem, yem);
		t.addVertexWithUV(x1, y1+height, z1, xem, ysm);
		
		xsm = xStart;
		xem = xEnd;
		ysm = yStart;
		yem = yEnd;
		
		xsm += depthDiff;
		xem -= depthDiff;
		ysm += heightDiff;
		yem -= heightDiff;
		
		t.addVertexWithUV(x1+width, y1+height, z1+depth, xsm, ysm);
		t.addVertexWithUV(x1+width, y1, z1+depth, xsm, yem);
		t.addVertexWithUV(x1+width, y1, z1, xem, yem);
		t.addVertexWithUV(x1+width, y1+height, z1, xem, ysm);
		
		xsm = xStart;
		xem = xEnd;
		ysm = yStart;
		yem = yEnd;
		
		xsm += widthDiff;
		xem -= widthDiff;
		ysm += heightDiff;
		yem -= heightDiff;
		
		t.addVertexWithUV(x1, y1+height, z1+depth, xsm, ysm);
		t.addVertexWithUV(x1, y1, z1+depth, xsm, yem);
		t.addVertexWithUV(x1+width, y1, z1+depth, xem, yem);
		t.addVertexWithUV(x1+width, y1+height, z1+depth, xem, ysm);
		
		xsm = xStart;
		xem = xEnd;
		ysm = yStart;
		yem = yEnd;
		
		xsm += depthDiff;
		xem -= depthDiff;
		ysm += widthDiff;
		yem -= widthDiff;
		
		t.addVertexWithUV(x1+width, y1+height, z1, xsm, ysm);
		t.addVertexWithUV(x1, y1+height, z1, xsm, yem);
		t.addVertexWithUV(x1, y1+height, z1+depth, xem, yem);
		t.addVertexWithUV(x1+width, y1+height, z1+depth, xem, ysm);
		
		xsm = xStart;
		xem = xEnd;
		ysm = yStart;
		yem = yEnd;
		
		xsm += depthDiff;
		xem -= depthDiff;
		ysm += widthDiff;
		yem -= widthDiff;
		
		t.addVertexWithUV(x1+width, y1, z1+depth, xsm, ysm);
		t.addVertexWithUV(x1, y1, z1+depth, xsm, yem);
		t.addVertexWithUV(x1, y1, z1, xem, yem);
		t.addVertexWithUV(x1+width, y1, z1, xem, ysm);
	}
	
	public static void renderCuboidInGlobalCoordsNoCheck(Tessellator t, double x1, double y1, double z1, double width, double height, double depth, int texture) {
		int pixX = (texture & 15) << 4;
		int pixY = texture & 240;
		double xStart = (double)(float)(pixX / 256.0F);
		double xEnd = (double)(float)((pixX + 15.99F) / 256.0F);
		double yStart = (double)(float)(pixY / 256.0F);
		double yEnd = (double)(float)((pixY + 15.99F) / 256.0F);
		
		double xsm = xStart;
		double xem = xEnd;
		double ysm = yStart;
		double yem = yEnd;
		
		double widthDiff = ((16 - (width * 16)) / 2) / 256.0F;
		double depthDiff = ((16 - (depth * 16)) / 2) / 256.0F;
		double heightDiff = ((16 - (height * 16)) / 2) / 256.0F;

		t.addVertexWithUV(x1, y1+height, z1, xsm, ysm);
		t.addVertexWithUV(x1, y1, z1, xsm, yem);
		t.addVertexWithUV(x1, y1, z1+depth, xem, yem);
		t.addVertexWithUV(x1, y1+height, z1+depth, xem, ysm);
		
		t.addVertexWithUV(x1+width, y1+height, z1, xsm, ysm);
		t.addVertexWithUV(x1+width, y1, z1, xsm, yem);
		t.addVertexWithUV(x1, y1, z1, xem, yem);
		t.addVertexWithUV(x1, y1+height, z1, xem, ysm);
		
		t.addVertexWithUV(x1+width, y1+height, z1+depth, xsm, ysm);
		t.addVertexWithUV(x1+width, y1, z1+depth, xsm, yem);
		t.addVertexWithUV(x1+width, y1, z1, xem, yem);
		t.addVertexWithUV(x1+width, y1+height, z1, xem, ysm);
		
		t.addVertexWithUV(x1, y1+height, z1+depth, xsm, ysm);
		t.addVertexWithUV(x1, y1, z1+depth, xsm, yem);
		t.addVertexWithUV(x1+width, y1, z1+depth, xem, yem);
		t.addVertexWithUV(x1+width, y1+height, z1+depth, xem, ysm);
		
		t.addVertexWithUV(x1+width, y1+height, z1, xsm, ysm);
		t.addVertexWithUV(x1, y1+height, z1, xsm, yem);
		t.addVertexWithUV(x1, y1+height, z1+depth, xem, yem);
		t.addVertexWithUV(x1+width, y1+height, z1+depth, xem, ysm);

		t.addVertexWithUV(x1+width, y1, z1+depth, xsm, ysm);
		t.addVertexWithUV(x1, y1, z1+depth, xsm, yem);
		t.addVertexWithUV(x1, y1, z1, xem, yem);
		t.addVertexWithUV(x1+width, y1, z1, xem, ysm);
	}

}
