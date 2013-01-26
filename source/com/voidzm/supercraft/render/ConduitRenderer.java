package com.voidzm.supercraft.render;

import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.block.BlockConduit;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class ConduitRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(block.blockID == BlockHandler.conduit.blockID) {
			float minX = 0.4375F;
			float minY = 0.4375F;
			float minZ = 0.4375F;
			float maxX = 0.5625F;
			float maxY = 0.5625F;
			float maxZ = 0.5625F;
			if(world.getBlockId(x+1, y, z) == block.blockID) {
				maxX = 1.0F;
			}
			if(world.getBlockId(x-1, y, z) == block.blockID) {
				minX = 0.0F;
			}
			if(world.getBlockId(x, y+1, z) == block.blockID) {
				maxY = 1.0F;
			}
			if(world.getBlockId(x, y-1, z) == block.blockID) {
				minY = 0.0F;
			}
			if(world.getBlockId(x, y, z+1) == block.blockID) {
				maxZ = 1.0F;
			}
			if(world.getBlockId(x, y, z-1) == block.blockID) {
				minZ = 0.0F;
			}
			renderer.setRenderBounds(minX, 0.4375, 0.4375, maxX, 0.5625, 0.5625);
			renderer.setOverrideBlockTexture(BlockHandler.elinvarBlock.blockIndexInTexture);
			renderer.renderStandardBlock(block, x, y, z);
			if(minZ != 0.4375F) {
				renderer.setRenderBounds(0.4375, 0.4375, 0.0, 0.5625, 0.5625, 0.4375);
				renderer.renderStandardBlock(block, x, y, z);
			}
			if(maxZ != 0.5625F) {
				renderer.setRenderBounds(0.4375, 0.4375, 0.5625, 0.5625, 0.5625, 1.0);
				renderer.renderStandardBlock(block, x, y, z);
			}
			if(minY != 0.4375F) {
				renderer.setRenderBounds(0.4375, 0.0, 0.4375, 0.5625, 0.4375, 0.5625);
				renderer.renderStandardBlock(block, x, y, z);
			}
			if(maxY != 0.5625F) {
				renderer.setRenderBounds(0.4375, 0.5625, 0.4375, 0.5625, 1.0, 0.5625);
				renderer.renderStandardBlock(block, x, y, z);
			}
			renderer.clearOverrideBlockTexture();
			
			Tessellator tes = Tessellator.instance;
			tes.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			tes.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			int texI = BlockHandler.conduit.blockIndexInTexture;
			int pixX = (texI & 15) << 4;
	        int pixY = texI & 240;
	        double xStart = (double)((float)(pixX + 4.0F) / 256.0F);
	        double xEnd = (double)(((float)(pixX + 4.0F) + 7.99F) / 256.0F);
	        double yStart = (double)((float)(pixY + 4.0F) / 256.0F);
	        double yEnd = (double)(((float)(pixY + 4.0F) + 7.99F) / 256.0F);
	        double xHalfEnd = (double)(((float)(pixX + 4.0F) + 3.99F) / 256.0F);
	        double yHalfEnd = (double)(((float)(pixY + 4.0F) + 3.99F) / 256.0F);
			renderer.setOverrideBlockTexture(texI);
			if(world.getBlockId(x-1, y, z) != block.blockID) {
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
			if(world.getBlockId(x+1, y, z) != block.blockID) {
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
			if(world.getBlockId(x, y, z-1) != block.blockID) {
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
			if(world.getBlockId(x, y, z+1) != block.blockID) {
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
			if(world.getBlockId(x, y-1, z) != block.blockID) {
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
			if(world.getBlockId(x, y+1, z) != block.blockID) {
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
			renderer.clearOverrideBlockTexture();
			((BlockConduit)block).setBlockBoundsBasedOnState(renderer.blockAccess, x, y, z);
			return true;
		}
		else return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.conduitRenderID;
	}

}
