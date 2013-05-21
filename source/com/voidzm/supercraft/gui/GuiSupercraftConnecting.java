package com.voidzm.supercraft.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.StringTranslate;

import com.voidzm.supercraft.thread.ThreadSupercraftConnectToServer;

public class GuiSupercraftConnecting extends GuiSupercraftScreen {

	private NetClientHandler netHandler;
	private GuiSupercraftScreen parent;
	private boolean cancelled = false;

	public GuiSupercraftConnecting(GuiSupercraftScreen par1GuiScreen, Minecraft par2Minecraft, ServerData par3ServerData) {
		this.mc = par2Minecraft;
		this.parent = par1GuiScreen;
		this.imageTick = par1GuiScreen.imageTick;
		ServerAddress serveraddress = ServerAddress.func_78860_a(par3ServerData.serverIP);
		par2Minecraft.setServerData(par3ServerData);
		this.spawnNewServerThread(serveraddress.getIP(), serveraddress.getPort());
	}

	public GuiSupercraftConnecting(GuiSupercraftScreen par1GuiScreen, Minecraft par2Minecraft, String par3Str, int par4) {
		this.mc = par2Minecraft;
		this.parent = par1GuiScreen;
		this.imageTick = par1GuiScreen.imageTick;
		this.spawnNewServerThread(par3Str, par4);
	}

	private void spawnNewServerThread(String par1Str, int par2) {
		this.mc.getLogAgent().logInfo("Connecting to " + par1Str + ", " + par2);
		(new ThreadSupercraftConnectToServer(this, par1Str, par2)).start();
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if(this.netHandler != null) {
			this.netHandler.processReadPackets();
		}
	}
	
	@Override
	protected void keyTyped(char par1, int par2) {}

	@Override
	public void initGui() {
		StringTranslate t = StringTranslate.getInstance();
		this.buttons.clear();
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 16, 0, t.translateKey("gui.cancel")));
	}

	@Override
	public void buttonEvent(int id) {
		switch(id) {
		case 0:
			this.cancelled = true;
			if(this.netHandler != null) {
				this.netHandler.disconnect();
			}
			this.parent.imageTick = this.imageTick;
			this.mc.displayGuiScreen(this.parent);
		}
	}

	@Override
	public void drawScreenForeground(int par1, int par2, float par3) {
		this.drawRect(0, 0, width, height, 0x88000000);
		this.drawRect(0, 0, width, height, 0x77000000);
		super.drawScreenForeground(par1, par2, par3);
		StringTranslate t = StringTranslate.getInstance();
		if(this.netHandler == null) {
			this.drawCenteredString(this.fontRenderer, t.translateKey("connect.connecting"), this.width / 2, this.height / 2 - 50, 16777215);
			this.drawCenteredString(this.fontRenderer, "", this.width / 2, this.height / 2 - 10, 16777215);
		}
		else {
			this.drawCenteredString(this.fontRenderer, t.translateKey("connect.authorizing"), this.width / 2, this.height / 2 - 50, 16777215);
			this.drawCenteredString(this.fontRenderer, this.netHandler.field_72560_a, this.width / 2, this.height / 2 - 10, 16777215);
		}
	}

	public static NetClientHandler setNetClientHandler(GuiSupercraftConnecting par0GuiConnecting, NetClientHandler par1NetClientHandler) {
		return par0GuiConnecting.netHandler = par1NetClientHandler;
	}

	public static Minecraft func_74256_a(GuiSupercraftConnecting par0GuiConnecting) {
		return par0GuiConnecting.mc;
	}

	public static boolean isCancelled(GuiSupercraftConnecting par0GuiConnecting) {
		return par0GuiConnecting.cancelled;
	}

	public static Minecraft func_74254_c(GuiSupercraftConnecting par0GuiConnecting) {
		return par0GuiConnecting.mc;
	}

	public static NetClientHandler getNetClientHandler(GuiSupercraftConnecting par0GuiConnecting) {
		return par0GuiConnecting.netHandler;
	}

	public static GuiSupercraftScreen func_98097_e(GuiSupercraftConnecting par0GuiConnecting) {
		return par0GuiConnecting.parent;
	}

	public static Minecraft func_74250_f(GuiSupercraftConnecting par0GuiConnecting) {
		return par0GuiConnecting.mc;
	}

	public static Minecraft func_74251_g(GuiSupercraftConnecting par0GuiConnecting) {
		return par0GuiConnecting.mc;
	}

	public static Minecraft func_98096_h(GuiSupercraftConnecting par0GuiConnecting) {
		return par0GuiConnecting.mc;
	}

	public static int getTick(GuiSupercraftConnecting par0GuiConnecting) {
		return par0GuiConnecting.imageTick;
	}
	
	public static void forceTermination(GuiSupercraftConnecting gui) {
		gui.cancelled = true;
		gui.netHandler = null;
	}

}
