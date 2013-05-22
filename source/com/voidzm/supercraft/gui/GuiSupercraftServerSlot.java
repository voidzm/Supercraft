package com.voidzm.supercraft.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.LanServer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.thread.ThreadSupercraftPollServers;

public class GuiSupercraftServerSlot extends GuiSupercraftSlot {

	private final GuiSupercraftMultiplayer parent;
	
	public GuiSupercraftServerSlot(GuiSupercraftMultiplayer screen) {
		super(screen.getMinecraft(), screen.width, screen.height, 32, screen.height - 64, 36);
		this.parent = screen;
	}

	@Override
	protected int getSize() {
		return GuiSupercraftMultiplayer.getInternetServerList(this.parent).countServers() + GuiSupercraftMultiplayer.getListOfLanServers(this.parent).size() + 1;
	}

	@Override
	protected void elementClicked(int par1, boolean par2) {
		if(par1 < GuiSupercraftMultiplayer.getInternetServerList(this.parent).countServers() + GuiSupercraftMultiplayer.getListOfLanServers(this.parent).size()) {
			int j = GuiSupercraftMultiplayer.getSelectedServer(this.parent);
			GuiSupercraftMultiplayer.getAndSetSelectedServer(this.parent, par1);
			ServerData serverdata = GuiSupercraftMultiplayer.getInternetServerList(this.parent).countServers() > par1 ? GuiSupercraftMultiplayer.getInternetServerList(this.parent).getServerData(par1) : null;
			boolean flag1 = GuiSupercraftMultiplayer.getSelectedServer(this.parent) >= 0 && GuiSupercraftMultiplayer.getSelectedServer(this.parent) < this.getSize() && (serverdata == null || serverdata.field_82821_f == 61);
			boolean flag2 = GuiSupercraftMultiplayer.getSelectedServer(this.parent) < GuiSupercraftMultiplayer.getInternetServerList(this.parent).countServers();
			GuiSupercraftMultiplayer.getButtonSelect(this.parent).enabled = flag1;
			GuiSupercraftMultiplayer.getButtonEdit(this.parent).enabled = flag2;
			GuiSupercraftMultiplayer.getButtonDelete(this.parent).enabled = flag2;
			if(par2 && flag1) {
				GuiSupercraftMultiplayer.func_74008_b(this.parent, par1);
			}
			else if (flag2 && GuiScreen.isShiftKeyDown() && j >= 0 && j < GuiSupercraftMultiplayer.getInternetServerList(this.parent).countServers()) {
				GuiSupercraftMultiplayer.getInternetServerList(this.parent).swapServers(j, GuiSupercraftMultiplayer.getSelectedServer(this.parent));
			}
		}
	}

	@Override
	protected boolean isSelected(int i) {
		return i == GuiSupercraftMultiplayer.getSelectedServer(this.parent);
	}

	@Override
	protected int getContentHeight() {
		return this.getSize() * 36;
	}

	@Override
	protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
		if(par1 < GuiSupercraftMultiplayer.getInternetServerList(this.parent).countServers()) {
			this.func_77247_d(par1, par2, par3, par4, par5Tessellator);
		}
		else if (par1 < GuiSupercraftMultiplayer.getInternetServerList(this.parent).countServers() + GuiSupercraftMultiplayer.getListOfLanServers(this.parent).size()) {
			this.func_77248_b(par1, par2, par3, par4, par5Tessellator);
		}
		else {
			this.func_77249_c(par1, par2, par3, par4, par5Tessellator);
		}
	}

	private void func_77248_b(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
		LanServer lanserver = (LanServer)GuiSupercraftMultiplayer.getListOfLanServers(this.parent).get(par1 - GuiSupercraftMultiplayer.getInternetServerList(this.parent).countServers());
		this.parent.drawString(this.parent.getFontRenderer(), StatCollector.translateToLocal("lanServer.title"), par2 + 2, par3 + 1, 16777215);
		this.parent.drawString(this.parent.getFontRenderer(), lanserver.getServerMotd(), par2 + 2, par3 + 12, 8421504);
		if(this.parent.getMinecraft().gameSettings.hideServerAddress) {
			this.parent.drawString(this.parent.getFontRenderer(), StatCollector.translateToLocal("selectServer.hiddenAddress"), par2 + 2, par3 + 12 + 11, 3158064);
		}
		else {
			this.parent.drawString(this.parent.getFontRenderer(), lanserver.getServerIpPort(), par2 + 2, par3 + 12 + 11, 3158064);
		}
	}

	private void func_77249_c(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
		this.parent.drawCenteredString(this.parent.getFontRenderer(), StatCollector.translateToLocal("lanServer.scanning"), this.parent.width / 2, par3 + 1, 16777215);
		String s;
		switch(GuiSupercraftMultiplayer.getTicksOpened(this.parent) / 3 % 4) {
		case 0:
		default:
			s = "O o o";
			break;
		case 1:
		case 3:
			s = "o O o";
			break;
		case 2:
			s = "o o O";
		}
		this.parent.drawCenteredString(this.parent.getFontRenderer(), s, this.parent.width / 2, par3 + 12, 8421504);
	}

	private void func_77247_d(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
		ServerData serverdata = GuiSupercraftMultiplayer.getInternetServerList(this.parent).getServerData(par1);
		synchronized(GuiSupercraftMultiplayer.getLock()) {
			if(GuiSupercraftMultiplayer.getThreadsPending() < 5 && !serverdata.field_78841_f) {
				serverdata.field_78841_f = true;
				serverdata.pingToServer = -2L;
				serverdata.serverMOTD = "";
				serverdata.populationInfo = "";
				GuiSupercraftMultiplayer.increaseThreadsPending();
				(new ThreadSupercraftPollServers(this, serverdata)).start();
			}
		}
		boolean flag = serverdata.field_82821_f > 61;
		boolean flag1 = serverdata.field_82821_f < 61;
		boolean flag2 = flag || flag1;
		this.parent.drawString(this.parent.getFontRenderer(), serverdata.serverName, par2 + 2, par3 + 1, 16777215);
		this.parent.drawString(this.parent.getFontRenderer(), serverdata.serverMOTD, par2 + 2, par3 + 12, 8421504);
		this.parent.drawString(this.parent.getFontRenderer(), serverdata.populationInfo, par2 + 215 - this.parent.getFontRenderer().getStringWidth(serverdata.populationInfo), par3 + 12, 8421504);
		if(flag2) {
			String s = EnumChatFormatting.DARK_RED + serverdata.gameVersion;
			this.parent.drawString(this.parent.getFontRenderer(), s, par2 + 200 - this.parent.getFontRenderer().getStringWidth(s), par3 + 1, 8421504);
		}
		if(!this.parent.getMinecraft().gameSettings.hideServerAddress && !serverdata.isHidingAddress()) {
			this.parent.drawString(this.parent.getFontRenderer(), serverdata.serverIP, par2 + 2, par3 + 12 + 11, 5263440);
		}
		else {
			this.parent.drawString(this.parent.getFontRenderer(), StatCollector.translateToLocal("selectServer.hiddenAddress"), par2 + 2, par3 + 12 + 11, 5263440);
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.parent.getMinecraft().renderEngine.bindTexture("/gui/icons.png");
		byte b0 = 0;
		String s1 = "";
		int i1;
		if(flag2) {
			s1 = flag ? "Client out of date!" : "Server out of date!";
			i1 = 5;
		}
		else if(serverdata.field_78841_f && serverdata.pingToServer != -2L) {
			if(serverdata.pingToServer < 0L) {
				i1 = 5;
			}
			else if(serverdata.pingToServer < 150L) {
				i1 = 0;
			}
			else if(serverdata.pingToServer < 300L) {
				i1 = 1;
			}
			else if(serverdata.pingToServer < 600L) {
				i1 = 2;
			}
			else if(serverdata.pingToServer < 1000L) {
				i1 = 3;
			}
			else {
				i1 = 4;
			}
			if(serverdata.pingToServer < 0L) {
				s1 = "(no connection)";
			}
			else {
				s1 = serverdata.pingToServer + "ms";
			}
		}
		else {
			b0 = 1;
			i1 = (int)(Minecraft.getSystemTime() / 100L + (long)(par1 * 2) & 7L);
			if(i1 > 4) {
				i1 = 8 - i1;
			}
			s1 = "Polling..";
		}
		this.parent.drawTexturedModalRect(par2 + 205, par3, 0 + b0 * 10, 176 + i1 * 8, 10, 8);
		byte b1 = 4;
		if(this.mouseX >= par2 + 205 - b1 && this.mouseY >= par3 - b1 && this.mouseX <= par2 + 205 + 10 + b1 && this.mouseY <= par3 + 8 + b1) {
			GuiSupercraftMultiplayer.getAndSetLagTooltip(this.parent, s1);
		}
	}

}
