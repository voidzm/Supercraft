package com.voidzm.supercraft.thread;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.EnumChatFormatting;

import com.voidzm.supercraft.gui.GuiSupercraftMultiplayer;
import com.voidzm.supercraft.gui.GuiSupercraftServerSlot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ThreadSupercraftPollServers extends Thread {

	private final ServerData pollServersServerData;
	private final GuiSupercraftServerSlot serverSlotContainer;

	public ThreadSupercraftPollServers(GuiSupercraftServerSlot par1GuiSlotServer, ServerData par2ServerData) {
		this.serverSlotContainer = par1GuiSlotServer;
		this.pollServersServerData = par2ServerData;
	}

	public void run() {
		boolean flag = false;
		label183: {
			label184: {
			label185: {
			label186: {
			label187: {
			try {
				flag = true;
				this.pollServersServerData.serverMOTD = EnumChatFormatting.DARK_GRAY + "Polling..";
				long i = System.nanoTime();
				GuiSupercraftMultiplayer.func_82291_a(this.pollServersServerData);
				long j = System.nanoTime();
				this.pollServersServerData.pingToServer = (j - i) / 1000000L;
				flag = false;
				break label183;
			}
			catch(UnknownHostException unknownhostexception) {
				this.pollServersServerData.pingToServer = -1L;
				this.pollServersServerData.serverMOTD = EnumChatFormatting.DARK_RED + "Can\'t resolve hostname";
				flag = false;
			}
			catch(SocketTimeoutException sockettimeoutexception) {
				this.pollServersServerData.pingToServer = -1L;
				this.pollServersServerData.serverMOTD = EnumChatFormatting.DARK_RED + "Can\'t reach server";
				flag = false;
				break label187;
			}
			catch(ConnectException connectexception) {
				this.pollServersServerData.pingToServer = -1L;
				this.pollServersServerData.serverMOTD = EnumChatFormatting.DARK_RED + "Can\'t reach server";
				flag = false;
				break label186;
			}
			catch(IOException ioexception) {
				this.pollServersServerData.pingToServer = -1L;
				this.pollServersServerData.serverMOTD = EnumChatFormatting.DARK_RED + "Communication error";
				flag = false;
				break label185;
			}
			catch(Exception exception) {
				this.pollServersServerData.pingToServer = -1L;
				this.pollServersServerData.serverMOTD = "ERROR: " + exception.getClass();
				flag = false;
				break label184;
			}
			finally {
				if(flag) {
					synchronized(GuiSupercraftMultiplayer.getLock()) {
						GuiSupercraftMultiplayer.decreaseThreadsPending();
					}
				}
			}
			synchronized (GuiSupercraftMultiplayer.getLock()) {
				GuiSupercraftMultiplayer.decreaseThreadsPending();
				return;
			}
		}
		synchronized(GuiSupercraftMultiplayer.getLock()) {
			GuiSupercraftMultiplayer.decreaseThreadsPending();
			return;
		}
		}
		synchronized(GuiSupercraftMultiplayer.getLock()) {
			GuiSupercraftMultiplayer.decreaseThreadsPending();
			return;
		}
		}
		synchronized(GuiSupercraftMultiplayer.getLock()) {
			GuiSupercraftMultiplayer.decreaseThreadsPending();
			return;
		}
		}
		synchronized(GuiSupercraftMultiplayer.getLock()) {
			GuiSupercraftMultiplayer.decreaseThreadsPending();
			return;
		}
		}
		synchronized(GuiSupercraftMultiplayer.getLock()) {
			GuiSupercraftMultiplayer.decreaseThreadsPending();
		}
	}
}

