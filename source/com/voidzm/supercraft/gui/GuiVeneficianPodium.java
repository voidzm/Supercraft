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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.container.ContainerVeneficianPodium;
import com.voidzm.supercraft.handler.PacketHandler.SCServerVeneficiaType;
import com.voidzm.supercraft.util.VenianProperties;

public class GuiVeneficianPodium extends GuiContainer implements ICrafting {

	private ContainerVeneficianPodium container;
	private GuiTextField powerField;
	private GuiTextField rangeField;
	private GuiTextField drainField;
	private InventoryPlayer inventoryPlayer;
	
	private boolean isDisabled = true;
	
	public GuiVeneficianPodium(InventoryPlayer par1, World par2World, int par3, int par4, int par5) {
		super(new ContainerVeneficianPodium(par1, par2World, par3, par4, par5, Minecraft.getMinecraft().thePlayer));
		this.inventoryPlayer = par1;
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
		this.powerField = new GuiTextField(this.fontRenderer, 91, 26, 36, 18);
		this.rangeField = new GuiTextField(this.fontRenderer, 91, 45, 36, 18);
		this.drainField = new GuiTextField(this.fontRenderer, 91, 64, 36, 18);
		this.powerField.setFocused(false);
		this.powerField.setTextColor(14737632);
		this.powerField.setEnabled(false);
		this.rangeField.setFocused(false);
		this.rangeField.setTextColor(14737632);
		this.rangeField.setEnabled(false);
		this.drainField.setFocused(false);
		this.drainField.setTextColor(14737632);
		this.drainField.setEnabled(false);
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
		VenianProperties prop = VenianProperties.readFromItemStack(stack);
		switch(id) {
		case 0:
			if(prop.power > 1) {
				this.container.setPower(prop.power-1);
				this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(0, prop.power-1));
			}
			break;
		case 1:
			this.container.setPower(prop.power+1);
			this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(0, prop.power+1));
			break;
		case 2:
			if(prop.range > 1) {
				this.container.setRange(prop.range-1);
				this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(1, prop.range-1));
			}
			break;
		case 3:
			this.container.setRange(prop.range+1);
			this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(1, prop.range+1));
			break;
		case 4:
			if(prop.drain > 1) {
				this.container.setDrain(prop.drain-1);
				this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(2, prop.drain-1));
			}
			break;
		case 5:
			this.container.setDrain(prop.drain+1);
			this.mc.thePlayer.sendQueue.addToSendQueue(this.createPacket(2, prop.drain+1));
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
		this.powerField.setText(""+this.container.getPower());
		this.powerField.drawTextBox();
		this.rangeField.setText(""+this.container.getRange());
		this.rangeField.drawTextBox();
		this.drainField.setText(""+this.container.getDrain());
		this.drainField.drawTextBox();
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
		this.mc.renderEngine.bindTexture("/mods/supercraft/textures/gui/veneficianpodium.png");
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
