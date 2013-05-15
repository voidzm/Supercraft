package com.voidzm.supercraft.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.handler.BlockHandler;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class VeneficianPodiumRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if(block.getRenderType() == ClientProxy.veneficianPodiumRenderID) {
			Tessellator t = Tessellator.instance;
			block.setBlockBoundsForItemRender();
			renderer.setRenderBounds(0.0625, 0.0, 0.0625, 0.9375, 0.125, 0.9375);
			this.renderBlock(t, Block.obsidian, 0, renderer);
			renderer.setRenderBounds(0.25, 0.125, 0.25, 0.75, 0.3125, 0.75);
			this.renderBlock(t, Block.obsidian, 0, renderer);
			renderer.setRenderBounds(0.375, 0.3125, 0.375, 0.625, 0.6875, 0.625);
			this.renderBlock(t, BlockHandler.silverBlock, 0, renderer);
			renderer.setRenderBounds(0.25, 0.6875, 0.25, 0.75, 0.875, 0.75);
			this.renderBlock(t, BlockHandler.silverBlock, 0, renderer);
			renderer.setRenderBounds(0.0625, 0.875, 0.0625, 0.9375, 1.0, 0.9375);
			this.renderBlock(t, Block.planks, 0, renderer);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}

	private void renderBlock(Tessellator t, Block block, int metadata, RenderBlocks renderer) {
		t.startDrawingQuads();
		t.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
		t.draw();
		t.startDrawingQuads();
		t.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
		t.draw();
		t.startDrawingQuads();
		t.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
		t.draw();
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(block.getRenderType() != ClientProxy.veneficianPodiumRenderID) return false;
		Tessellator t = Tessellator.instance;
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.0625, 0.0, 0.0625, 0.9375, 0.125, 0.9375);
		renderer.renderStandardBlock(Block.obsidian, x, y, z);
		renderer.setRenderBounds(0.25, 0.125, 0.25, 0.75, 0.3125, 0.75);
		renderer.renderStandardBlock(Block.obsidian, x, y, z);
		renderer.setRenderBounds(0.375, 0.3125, 0.375, 0.625, 0.6875, 0.625);
		renderer.renderStandardBlock(BlockHandler.silverBlock, x, y, z);
		renderer.setRenderBounds(0.25, 0.6875, 0.25, 0.75, 0.875, 0.75);
		renderer.renderStandardBlock(BlockHandler.silverBlock, x, y, z);
		renderer.setRenderBounds(0.0625, 0.875, 0.0625, 0.9375, 1.0, 0.9375);
		renderer.renderStandardBlock(Block.planks, x, y, z);
		renderer.renderAllFaces = false;
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.veneficianPodiumRenderID;
	}

}
