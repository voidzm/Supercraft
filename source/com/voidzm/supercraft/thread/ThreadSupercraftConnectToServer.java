package com.voidzm.supercraft.thread;

import java.net.ConnectException;
import java.net.UnknownHostException;

import com.voidzm.supercraft.gui.GuiSupercraftConnecting;
import com.voidzm.supercraft.gui.GuiSupercraftDisconnected;

import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.network.packet.Packet2ClientProtocol;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ThreadSupercraftConnectToServer extends Thread {
	
	public final String ip;
	public final int port;

	public final GuiSupercraftConnecting connectingGui;

	public ThreadSupercraftConnectToServer(GuiSupercraftConnecting par1GuiConnecting, String par2Str, int par3) {
		this.connectingGui = par1GuiConnecting;
		this.ip = par2Str;
		this.port = par3;
	}

	public void run() {
		try {
			GuiSupercraftConnecting.setNetClientHandler(this.connectingGui, new NetClientHandler(GuiSupercraftConnecting.func_74256_a(this.connectingGui), this.ip, this.port));
			if(GuiSupercraftConnecting.isCancelled(this.connectingGui)) return;
			GuiSupercraftConnecting.getNetClientHandler(this.connectingGui).addToSendQueue(new Packet2ClientProtocol(61, GuiSupercraftConnecting.func_74254_c(this.connectingGui).session.username, this.ip, this.port));
		}
		catch(UnknownHostException unknownhostexception){
			if(GuiSupercraftConnecting.isCancelled(this.connectingGui)) return;
			GuiSupercraftConnecting.func_74250_f(this.connectingGui).displayGuiScreen(new GuiSupercraftDisconnected(GuiSupercraftConnecting.func_98097_e(this.connectingGui), "connect.failed", "disconnect.genericReason", GuiSupercraftConnecting.getTick(this.connectingGui), new Object[] {"Unknown host \'" + this.ip + "\'"}));
		}
		catch(ConnectException connectexception) {
			if(GuiSupercraftConnecting.isCancelled(this.connectingGui)) return;
			GuiSupercraftConnecting.func_74251_g(this.connectingGui).displayGuiScreen(new GuiSupercraftDisconnected(GuiSupercraftConnecting.func_98097_e(this.connectingGui), "connect.failed", "disconnect.genericReason", GuiSupercraftConnecting.getTick(this.connectingGui), new Object[] {connectexception.getMessage()}));
		}
		catch(Exception exception) {
			if(GuiSupercraftConnecting.isCancelled(this.connectingGui)) return;
			exception.printStackTrace();
			GuiSupercraftConnecting.func_98096_h(this.connectingGui).displayGuiScreen(new GuiSupercraftDisconnected(GuiSupercraftConnecting.func_98097_e(this.connectingGui), "connect.failed", "disconnect.genericReason", GuiSupercraftConnecting.getTick(this.connectingGui), new Object[] {exception.toString()}));
		}
	}
}

