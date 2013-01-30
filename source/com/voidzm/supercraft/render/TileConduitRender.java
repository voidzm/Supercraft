//////////////////////////////////////
//*     TileConduitRender.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.render;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.block.BlockConduit;
import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class TileConduitRender extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		this.render(var1, var2, var4, var6, var8);
	}
	
	private void render(TileEntity var1, double var2, double var4, double var6, float var8) {
		if(var1 instanceof TileEntityConduit) {
			float minX = 0.4375F;
			float minY = 0.4375F;
			float minZ = 0.4375F;
			float maxX = 0.5625F;
			float maxY = 0.5625F;
			float maxZ = 0.5625F;
			double x = var2;
			double y = var4;
			double z = var6;
			int ix = var1.xCoord;
			int iy = var1.yCoord;
			int iz = var1.zCoord;
			World world = var1.worldObj;
			Block targetBlock = Block.blocksList[world.getBlockId(ix, iy, iz)];
			if(this.isConduitConnectable(world.getBlockId(ix+1, iy, iz))) {
				maxX = 1.0F;
			}
			if(this.isConduitConnectable(world.getBlockId(ix-1, iy, iz))) {
				minX = 0.0F;
			}
			if(this.isConduitConnectable(world.getBlockId(ix, iy+1, iz))) {
				maxY = 1.0F;
			}
			if(this.isConduitConnectable(world.getBlockId(ix, iy-1, iz))) {
				minY = 0.0F;
			}
			if(this.isConduitConnectable(world.getBlockId(ix, iy, iz+1))) {
				maxZ = 1.0F;
			}
			if(this.isConduitConnectable(world.getBlockId(ix, iy, iz-1))) {
				minZ = 0.0F;
			}
			Tessellator tes = Tessellator.instance;
			tes.startDrawingQuads();
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			tes.setBrightness(world.getBlockLightValue(ix, iy, iz));
			tes.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			ForgeHooksClient.bindTexture(ClientProxy.BLOCKS_PNG, 0);
			
			//System.out.println("Comparing " + ((TileEntityConduit)var1).powerLevel() + " to 0.");
			
			if(((TileEntityConduit)var1).powerLevel() != 0) {
				int texIndex = BlockHandler.elinvarBlock.blockIndexInTexture;
				this.renderCuboidInGlobalCoords(tes, x+minX, y+0.4375, z+0.4375, (maxX-minX), 0.125, 0.125, texIndex);
				if(minZ != 0.4375F) {
					this.renderCuboidInGlobalCoords(tes, x+0.4375, y+0.4375, z, 0.125, 0.125, 0.4375, texIndex);
				}	
				if(maxZ != 0.5625F) {
					this.renderCuboidInGlobalCoords(tes, x+0.4375, y+0.4375, z+0.5625, 0.125, 0.125, 0.4375, texIndex);
				}
				if(minY != 0.4375F) {
					this.renderCuboidInGlobalCoords(tes, x+0.4375, y, z+0.4375, 0.125, 0.4375, 0.125, texIndex);
				}
				if(maxY != 0.5625F) {
					this.renderCuboidInGlobalCoords(tes, x+0.4375, y+0.5625, z+0.4375, 0.125, 0.4375, 0.125, texIndex);
				}
			}
			
			int texI = targetBlock.blockIndexInTexture;
			int pixX = (texI & 15) << 4;
	        int pixY = texI & 240;
	        double xStart = (double)((float)(pixX + 4.0F) / 256.0F);
	        double xEnd = (double)(((float)(pixX + 4.0F) + 7.99F) / 256.0F);
	        double yStart = (double)((float)(pixY + 4.0F) / 256.0F);
	        double yEnd = (double)(((float)(pixY + 4.0F) + 7.99F) / 256.0F);
	        double xHalfEnd = (double)(((float)(pixX + 4.0F) + 3.99F) / 256.0F);
	        double yHalfEnd = (double)(((float)(pixY + 4.0F) + 3.99F) / 256.0F);
			if(!this.isConduitConnectable(world.getBlockId(ix-1, iy, iz))) {
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.25, xStart, yStart);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.75, xEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yStart);
			}
			else {
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x, y+0.75, z+0.25, xHalfEnd, yEnd);
				tes.addVertexWithUV(x, y+0.75, z+0.75, xHalfEnd, yStart);
				
				tes.addVertexWithUV(x, y+0.25, z+0.75, xHalfEnd, yStart);
				tes.addVertexWithUV(x, y+0.25, z+0.25, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.75, xEnd, yStart);
				
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.25, xStart, yStart);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x, y+0.25, z+0.25, xHalfEnd, yEnd);
				tes.addVertexWithUV(x, y+0.75, z+0.25, xHalfEnd, yStart);
				
				tes.addVertexWithUV(x, y+0.75, z+0.75, xHalfEnd, yStart);
				tes.addVertexWithUV(x, y+0.25, z+0.75, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.75, xEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(ix+1, iy, iz))) {
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.75, xStart, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.25, xEnd, yStart);
			}
			else {
				tes.addVertexWithUV(x+1.0, y+0.75, z+0.75, xHalfEnd, yStart);
				tes.addVertexWithUV(x+1.0, y+0.75, z+0.25, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.75, xEnd, yStart);
				
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x+1.0, y+0.25, z+0.25, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+1.0, y+0.25, z+0.75, xHalfEnd, yStart);
				
				tes.addVertexWithUV(x+1.0, y+0.75, z+0.25, xHalfEnd, yStart);
				tes.addVertexWithUV(x+1.0, y+0.25, z+0.25, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.25, xEnd, yStart);
				
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.75, xStart, yEnd);
				tes.addVertexWithUV(x+1.0, y+0.25, z+0.75, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+1.0, y+0.75, z+0.75, xHalfEnd, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(ix, iy, iz-1))) {
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yStart);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yStart);
			}
			else {
				tes.addVertexWithUV(x+0.75, y+0.75, z, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z, xEnd, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yStart);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yStart);
				
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yStart);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yStart);
				tes.addVertexWithUV(x+0.25, y+0.25, z, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z, xEnd, yHalfEnd);
				
				tes.addVertexWithUV(x+0.25, y+0.75, z, xHalfEnd, yStart);
				tes.addVertexWithUV(x+0.25, y+0.25, z, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yStart);
				
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yStart);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z, xHalfEnd, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(ix, iy, iz+1))) {
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.75, xEnd, yStart);
			}
			else {
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+1.0, xEnd, yHalfEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+1.0, xStart, yHalfEnd);
				
				tes.addVertexWithUV(x+0.75, y+0.25, z+1.0, xEnd, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+1.0, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yEnd);
				
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+1.0, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+1.0, xHalfEnd, yStart);
			
				tes.addVertexWithUV(x+0.75, y+0.75, z+1.0, xHalfEnd, yStart);
				tes.addVertexWithUV(x+0.75, y+0.25, z+1.0, xHalfEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.75, xEnd, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(ix, iy-1, iz))) {
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yStart);
			}
			else {
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.25, xStart, yStart);
				tes.addVertexWithUV(x+0.75, y, z+0.25, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y, z+0.25, xEnd, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.25, xEnd, yStart);
				
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.25, y, z+0.75, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.75, y, z+0.75, xEnd, yHalfEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.75, xEnd, yStart);
				
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.25, xStart, yStart);
				tes.addVertexWithUV(x+0.25, y, z+0.25, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y, z+0.75, xEnd, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y+0.25, z+0.75, xEnd, yStart);
				
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.75, y, z+0.75, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.75, y, z+0.25, xEnd, yHalfEnd);
				tes.addVertexWithUV(x+0.75, y+0.25, z+0.25, xEnd, yStart);
			}
			if(!this.isConduitConnectable(world.getBlockId(ix, iy+1, iz))) {
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yStart);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yStart);
			}
			else {
				tes.addVertexWithUV(x+0.75, y+1.0, z+0.25, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+1.0, z+0.25, xEnd, yHalfEnd);
				
				tes.addVertexWithUV(x+0.25, y+1.0, z+0.75, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.75, xStart, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.75, xEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+1.0, z+0.75, xEnd, yHalfEnd);
				
				tes.addVertexWithUV(x+0.25, y+1.0, z+0.25, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.25, xStart, yEnd);
				tes.addVertexWithUV(x+0.25, y+0.75, z+0.75, xEnd, yEnd);
				tes.addVertexWithUV(x+0.25, y+1.0, z+0.75, xEnd, yHalfEnd);
				
				tes.addVertexWithUV(x+0.75, y+1.0, z+0.75, xStart, yHalfEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.75, xStart, yEnd);
				tes.addVertexWithUV(x+0.75, y+0.75, z+0.25, xEnd, yEnd);
				tes.addVertexWithUV(x+0.75, y+1.0, z+0.25, xEnd, yHalfEnd);
			}
			tes.draw();
			((BlockConduit)targetBlock).setBlockBoundsBasedOnState(world, ix, iy, iz);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();
		}
	}
	
	private boolean isConduitConnectable(int blockID) {
		if(BlockConduit.isConduit(blockID)) return true;
		else if(BlockConduit.isGenerator(blockID)) return true;
		else return false;
	}
	
	public void renderCuboidInGlobalCoords(Tessellator t, double x1, double y1, double z1, double width, double height, double depth, int texture) {
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

}
