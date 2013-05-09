//////////////////////////////////////
//*   InventoryConduitRender.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.render;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.client.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class InventoryConduitRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if(block.getRenderType() == ClientProxy.conduitInvRenderID) {
			Tessellator t = Tessellator.instance;
			block.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
			renderer.setRenderBoundsFromBlock(block);
			block.setBlockBoundsForItemRender();
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
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
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(block.getRenderType() == ClientProxy.conduitInvRenderID) {
			// The rendering for conduits is taken care of by their TileEntitySpecialRenderer. No rendering is done here.
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.conduitInvRenderID;
	}

}
