package com.voidzm.supercraft.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.util.VeneficianProperties;

public class InventoryVeneficiaCellRender implements IItemRenderer {
	
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
		t.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 0.0);
		t.addVertexWithUV(0.0, 16.0, 0.0, 0.0, 1.0);
		t.addVertexWithUV(16.0, 16.0, 0.0, 1.0, 1.0);
		t.addVertexWithUV(16.0, 0.0, 0.0, 1.0, 0.0);
		t.draw();
		t.startDrawingQuads();
		this.bindVeneficiaForIndex(prop.aspect.index);
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
		itemRenderer.renderItemIn2D(t, 1.0F, 0.0F, 0.0F, 1.0F, 16, 16, 0.0625F);
		this.bindVeneficiaForIndex(prop.aspect.index);
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
		itemRenderer.renderItemIn2D(t, 1.0F, 0.0F, 0.0F, 1.0F, 16, 16, 0.0625F);
		this.bindVeneficiaForIndex(prop.aspect.index);
		itemRenderer.renderItemIn2D(t, 1.0F, 0.0F, 0.0F, 1.0F, 16, 16, 0.0625F);
		GL11.glTranslatef(0.5F, 0.0F, 0.0F);
		GL11.glPopMatrix();
	}
	
	private void bindMaterialForIndex(int index) {
		switch(index) {
		case 0:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_aluminum.png"));
			break;
		case 1:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_copper.png"));
			break;
		case 2:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_silver.png"));
			break;
		case 3:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_gold.png"));
			break;
		case 4:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_electrum.png"));
			break;
		case 5:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_nisil.png"));
			break;
		case 6:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_cobalt.png"));
			break;
		case 7:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_platinum.png"));
			break;
		case 8:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_lithium.png"));
			break;
		default:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficiacell_lithium.png"));
			break;
		}
	}
	
	private void bindVeneficiaForIndex(int index) {
		switch(index) {
		case 0:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_decay.png"));
			break;
		case 1:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_atrophy.png"));
			break;
		case 2:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_arachnia.png"));
			break;
		case 3:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_detonation.png"));
			break;
		case 4:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_lightning.png"));
			break;
		case 5:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_blazing.png"));
			break;
		case 6:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_rifting.png"));
			break;
		case 7:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_rage.png"));
			break;
		case 8:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_despair.png"));
			break;
		case 9:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_breaching.png"));
			break;
		case 10:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_withering.png"));
			break;
		case 11:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_disparity.png"));
			break;
		case 12:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_eruption.png"));
			break;
		case 13:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_infection.png"));
			break;
		case 14:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_irrigation.png"));
			break;
		case 15:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_freezing.png"));
			break;
		case 16:
			break;
		case 17:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/supercraft/textures/items/veneficia_void.png"));
			break;
		default:
			break;
		}
	}

}
