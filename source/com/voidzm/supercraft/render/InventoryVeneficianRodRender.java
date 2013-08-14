package com.voidzm.supercraft.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.util.VeneficianProperties;

public class InventoryVeneficianRodRender implements IItemRenderer {
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if(type == ItemRenderType.INVENTORY) return true;
		else if(type == ItemRenderType.ENTITY) return true;
		else if(type == ItemRenderType.EQUIPPED) return true;
		else if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) return true;
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return (helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION) ? true : false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if(type == ItemRenderType.INVENTORY) this.renderInInventory(type, item, data);
		else if(type == ItemRenderType.EQUIPPED) this.renderInHand(type, item, data);
		else if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) this.renderInHand(type, item, data);
		else if(type == ItemRenderType.ENTITY) this.renderOnGround(type, item, data);
	}
	
	private void renderInInventory(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		VeneficianProperties prop = VeneficianProperties.readFromItemStack(item);
		this.bindMaterialForIndex(prop.material.index);
		t.addVertexWithUV(1.0, 3.0, 0.0, 0.125, 0.125);
		t.addVertexWithUV(1.0, 15.0, 0.0, 0.125, 0.875);
		t.addVertexWithUV(13.0, 15.0, 0.0, 0.875, 0.875);
		t.addVertexWithUV(13.0, 3.0, 0.0, 0.875, 0.125);
		t.draw();
		t.startDrawingQuads();
		this.bindAspectForIndex(prop.aspect.index);
		t.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 0.0);
		t.addVertexWithUV(0.0, 16.0, 0.0, 0.0, 1.0);
		t.addVertexWithUV(16.0, 16.0, 0.0, 1.0, 1.0);
		t.addVertexWithUV(16.0, 0.0, 0.0, 1.0, 0.0);
		t.draw();
		GL11.glPopMatrix();
	}
	
	private void renderInHand(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		Tessellator t = Tessellator.instance;
		ItemRenderer itemRenderer = RenderManager.instance.itemRenderer;
		VeneficianProperties prop = VeneficianProperties.readFromItemStack(item);
		this.bindMaterialForIndex(prop.material.index);
		itemRenderer.renderItemIn2D(t, 1.0625F, -0.0625F, 0.0625F, 0.9375F, 16, 16, 0.0625F);
		this.bindAspectForIndex(prop.aspect.index);
		itemRenderer.renderItemIn2D(t, 1.0F, 0.0F, 0.0F, 1.0F, 16, 16, 0.0625F);
		GL11.glPopMatrix();
	}
	
	private void renderOnGround(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		Tessellator t = Tessellator.instance;
		ItemRenderer itemRenderer = RenderManager.instance.itemRenderer;
		VeneficianProperties prop = VeneficianProperties.readFromItemStack(item);
		this.bindMaterialForIndex(prop.material.index);
		GL11.glTranslatef(-0.5F, 0.0F, 0.0F);
		itemRenderer.renderItemIn2D(t, 1.0625F, -0.0625F, 0.0625F, 0.9375F, 16, 16, 0.0625F);
		this.bindAspectForIndex(prop.aspect.index);
		itemRenderer.renderItemIn2D(t, 1.0F, 0.0F, 0.0F, 1.0F, 16, 16, 0.0625F);
		GL11.glTranslatef(0.5F, 0.0F, 0.0F);
		GL11.glPopMatrix();
	}
	
	private void bindMaterialForIndex(int index) {
		TextureManager tm = Minecraft.getMinecraft().renderEngine;
		ResourceLocation resource = new ResourceLocation("supercraft:textures/items/arcanerod_"+VeneficianProperties.materialResourceNames[index]+".png");
		tm.func_110577_a(resource);
	}
	
	private void bindAspectForIndex(int index) {
		if(index > 15) return;
		TextureManager tm = Minecraft.getMinecraft().renderEngine;
		ResourceLocation resource = new ResourceLocation("supercraft:textures/items/orb_"+VeneficianProperties.aspectNames[index].toLowerCase()+".png");
		tm.func_110577_a(resource);
	}

}
