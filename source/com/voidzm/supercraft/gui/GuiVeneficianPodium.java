package com.voidzm.supercraft.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.container.ContainerVeneficianPodium;
import com.voidzm.supercraft.handler.PacketHandler.SCServerVeneficiaType;
import com.voidzm.supercraft.util.VeneficianProperties;

public class GuiVeneficianPodium extends GuiContainer implements ICrafting {

	private ContainerVeneficianPodium container;
	private GuiTextField vitalityField;
	private GuiTextField perceptionField;
	private GuiTextField energyField;
	private boolean isDisabled = true;
	
	private static final ResourceLocation VENEFICIANPODIUM_BACKGROUND = new ResourceLocation("supercraft:textures/gui/veneficianpodium.png");
	
	public GuiVeneficianPodium(InventoryPlayer par1, World par2World, int par3, int par4, int par5) {
		super(new ContainerVeneficianPodium(par1, par2World, par3, par4, par5, Minecraft.getMinecraft().thePlayer));
		this.container = (ContainerVeneficianPodium)this.inventorySlots;
		this.ySize = 186;
	}

	public void initGui() {
		super.initGui();
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.buttonList.add(new GuiSupercraftButton(this, 0, i+70, j+25, 20, 20, "-"));
		this.buttonList.add(new GuiSupercraftButton(this, 1, i+128, j+25, 20, 20, "+"));
		this.buttonList.add(new GuiSupercraftButton(this, 2, i+70, j+44, 20, 20, "-"));
		this.buttonList.add(new GuiSupercraftButton(this, 3, i+128, j+44, 20, 20, "+"));
		this.buttonList.add(new GuiSupercraftButton(this, 4, i+70, j+63, 20, 20, "-"));
		this.buttonList.add(new GuiSupercraftButton(this, 5, i+128, j+63, 20, 20, "+"));
		for(GuiButton button : (List<GuiButton>)this.buttonList) {
			button.enabled = false;
		}
		this.vitalityField = new GuiTextField(this.fontRenderer, 91, 26, 36, 18);
		this.perceptionField = new GuiTextField(this.fontRenderer, 91, 45, 36, 18);
		this.energyField = new GuiTextField(this.fontRenderer, 91, 64, 36, 18);
		this.vitalityField.setFocused(false);
		this.vitalityField.setDisabledTextColour(14737632);
		this.vitalityField.setEnabled(false);
		this.perceptionField.setFocused(false);
		this.perceptionField.setDisabledTextColour(14737632);
		this.perceptionField.setEnabled(false);
		this.energyField.setFocused(false);
		this.energyField.setDisabledTextColour(14737632);
		this.energyField.setEnabled(false);
		this.inventorySlots.removeCraftingFromCrafters(this);
		this.inventorySlots.addCraftingToCrafters(this);
	}
	
	public void onGuiClosed() {
		super.onGuiClosed();
		this.inventorySlots.removeCraftingFromCrafters(this);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		for(GuiSupercraftButton button : (List<GuiSupercraftButton>)this.buttonList) {
			button.clickEvent(par1, par2);
		}
	}
	
	public void buttonEvent(int id) {
		ItemStack stack = this.container.getItemStack();
		if(stack == null) return;
		VeneficianProperties prop = VeneficianProperties.readFromItemStack(stack);
		switch(id) {
		case 0:
			if(prop.vitality > 1) {
				this.container.setPower(prop.vitality-1);
				this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(0, prop.vitality-1));
			}
			break;
		case 1:
			if(prop.vitality < 128) {
				this.container.setPower(prop.vitality+1);
				this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(0, prop.vitality+1));
			}
			break;
		case 2:
			if(prop.perception > 4) {
				this.container.setRange(prop.perception-1);
				this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(1, prop.perception-1));
			}
			break;
		case 3:
			if(prop.perception < 64) {
				this.container.setRange(prop.perception+1);
				this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(1, prop.perception+1));
			}
			break;
		case 4:
			if(prop.energy > 1) {
				this.container.setDrain(prop.energy-1);
				this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(2, prop.energy-1));
			}
			break;
		case 5:
			if(prop.energy < 256) {
				this.container.setDrain(prop.energy+1);
				this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(2, prop.energy+1));
			}
			break;
		default:
			break;
		}
	}
	
	private Packet250CustomPayload createPacket(int field, int value) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream(12);
		DataOutputStream dataStream = new DataOutputStream(byteArray);
		try {
			dataStream.writeInt(SCServerVeneficiaType.VENEFICIANPODIUM.index);
			dataStream.writeInt(field);
			dataStream.writeInt(value);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "SCS|Veneficia";
		packet.data = byteArray.toByteArray();
		packet.length = byteArray.size();
		return packet;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glDisable(GL11.GL_LIGHTING);
		this.fontRenderer.drawString("Venefician Podium", 8, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
		this.vitalityField.setText(""+this.container.getPower());
		this.vitalityField.drawTextBox();
		this.perceptionField.setText(""+this.container.getRange());
		this.perceptionField.drawTextBox();
		this.energyField.setText(""+this.container.getDrain());
		this.energyField.drawTextBox();
		if(this.container.getItemStack() != null && this.isDisabled) {
			this.isDisabled = false;
			for(GuiButton button : (List<GuiButton>)this.buttonList) {
				button.enabled = true;
			}
		}
		if(this.container.getItemStack() == null && !this.isDisabled) {
			this.isDisabled = true;
			for(GuiButton button : (List<GuiButton>)this.buttonList) {
				button.enabled = false;
			}
		}
		GL11.glEnable(GL11.GL_LIGHTING);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_110577_a(VENEFICIANPODIUM_BACKGROUND);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
	@Override
	public void sendContainerAndContentsToPlayer(Container container, List list) {
		this.sendSlotContents(container, 0, container.getSlot(0).getStack());
	}

	@Override
	public void sendSlotContents(Container container, int i, ItemStack itemstack) {}

	@Override
	public void sendProgressBarUpdate(Container container, int i, int j) {}

}
