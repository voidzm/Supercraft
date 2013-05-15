//////////////////////////////////////
//*       PacketHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.handler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

import com.voidzm.supercraft.container.ContainerVeneficianPodium;
import com.voidzm.supercraft.tileentity.TileEntityAlloyInductor;
import com.voidzm.supercraft.tileentity.TileEntityConduit;
import com.voidzm.supercraft.tileentity.TileEntityConduit.CONDUIT_TYPE;
import com.voidzm.supercraft.tileentity.TileEntityElectroplationEngine;
import com.voidzm.supercraft.tileentity.TileEntityEssentialReducer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {
	
	public enum SCClientElinvarType {
		PROPAGATE(0), RENDER(1), ESSENTIALREDUCER(2), ALLOYINDUCTOR(3), ELECTROPLATIONENGINE(4);
		public int index;
		private SCClientElinvarType(int val) {
			this.index = val;
		}
	}
	
	public enum SCServerVeneficiaType {
		VENEFICIANPODIUM(0);
		public int index;
		private SCServerVeneficiaType(int val) {
			this.index = val;
		}
	}
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		String channel = packet.channel;
		String side = channel.substring(0, 3);
		String type = channel.substring(4);
		if(side.equals("SCC")) this.handleClientPacket(packet, player, type);
		else if(side.equals("SCS")) this.handleServerPacket(packet, player, type);
	}
	
	private void handleClientPacket(Packet250CustomPayload packet, Player player, String clientType) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) return;
		if(clientType.equals("Elinvar")) {
			this.handleElinvarClientPacket(packet, player);
		}
	}
	
	private void handleElinvarClientPacket(Packet250CustomPayload packet, Player player) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		int typeID;
		try {
			typeID = inputStream.readInt();
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
		if(typeID == SCClientElinvarType.PROPAGATE.index) {
			int x, y, z, newVal;
			try {
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				newVal = inputStream.readInt();
			} catch(Exception e) {
				e.printStackTrace();
				return;
			}
			this.handleElinvarPropagationUpdate((EntityPlayer)player, x, y, z, newVal);
		}
		else if(typeID == SCClientElinvarType.RENDER.index) {
			int x, y, z, correctVal, correctType;
			try {
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				correctVal = inputStream.readInt();
				correctType = inputStream.readInt();
			} catch(Exception e) {
				e.printStackTrace();
				return;
			}
			this.handleConduitRender((EntityPlayer)player, x, y, z, correctVal, correctType);
		}
		else if(typeID == SCClientElinvarType.ESSENTIALREDUCER.index) {
			int x, y, z, time;
			boolean reducing, powered;
			try {
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				reducing = inputStream.readBoolean();
				powered = inputStream.readBoolean();
				time = inputStream.readInt();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			this.handleEssentialReducerUpdate((EntityPlayer)player, x, y, z, reducing, powered, time);
		}
		else if(typeID == SCClientElinvarType.ALLOYINDUCTOR.index) {
			int x, y, z, time, essence;
			boolean inducting, powered;
			try {
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				inducting = inputStream.readBoolean();
				powered = inputStream.readBoolean();
				time = inputStream.readInt();
				essence = inputStream.readInt();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			this.handleAlloyInductorUpdate((EntityPlayer)player, x, y, z, inducting, powered, time, essence);
		}
		else if(typeID == SCClientElinvarType.ELECTROPLATIONENGINE.index) {
			int x, y, z, time;
			boolean electroplating, powered;
			try {
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				electroplating = inputStream.readBoolean();
				powered = inputStream.readBoolean();
				time = inputStream.readInt();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			this.handleElectroplationEngineUpdate((EntityPlayer)player, x, y, z, electroplating, powered, time);
		}
	}
	
	private void handleServerPacket(Packet250CustomPayload packet, Player player, String serverType) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) return;
		if(serverType.equals("Veneficia")) {
			this.handleVeneficiaServerPacket(packet, player);
		}
	}
	
	private void handleVeneficiaServerPacket(Packet250CustomPayload packet, Player player) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		int typeID;
		try {
			typeID = inputStream.readInt();
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
		if(typeID == SCServerVeneficiaType.VENEFICIANPODIUM.index) {
			int field, val;
			try {
				field = inputStream.readInt();
				val = inputStream.readInt();
			} catch(Exception e) {
				e.printStackTrace();
				return;
			}
			this.handleVeneficianPodiumUpdate((EntityPlayer)player, field, val);
		}
	}
	
	private void handleElinvarPropagationUpdate(EntityPlayer player, int x, int y, int z, int newVal) {
		World world = player.worldObj;
		TileEntityConduit te = (TileEntityConduit)world.getBlockTileEntity(x, y, z);
		if(te == null) {
			return;
		}
		te.setPower(newVal);
	}
	
	private void handleConduitRender(EntityPlayer player, int x, int y, int z, int correctVal, int correctType) {
		World world = player.worldObj;
		TileEntityConduit te = (TileEntityConduit)world.getBlockTileEntity(x, y, z);
		if(te == null) {
			return;
		}
		te.setPower(correctVal);
		te.setConduitType(CONDUIT_TYPE.values()[correctType]);
	}
	
	private void handleEssentialReducerUpdate(EntityPlayer player, int x, int y, int z, boolean reducing, boolean powered, int timeLeft) {
		TileEntityEssentialReducer te = (TileEntityEssentialReducer)player.worldObj.getBlockTileEntity(x, y, z);
		if(te == null) {
			return;
		}
		te.setReducing(reducing);
		te.powered = powered;
		te.timeUntilReduction = timeLeft;
	}
	
	private void handleAlloyInductorUpdate(EntityPlayer player, int x, int y, int z, boolean inducting, boolean powered, int timeLeft, int essenceLeft) {
		TileEntityAlloyInductor te = (TileEntityAlloyInductor)player.worldObj.getBlockTileEntity(x, y, z);
		if(te == null) {
			return;
		}
		te.setInducting(inducting);
		te.powered = powered;
		te.timeUntilInduction = timeLeft;
		te.essenceRemaining = essenceLeft;
	}
	
	private void handleElectroplationEngineUpdate(EntityPlayer player, int x, int y, int z, boolean electroplating, boolean powered, int timeLeft) {
		TileEntityElectroplationEngine te = (TileEntityElectroplationEngine)player.worldObj.getBlockTileEntity(x, y, z);
		if(te == null) {
			return;
		}
		te.setElectroplating(electroplating);
		te.powered = powered;
		te.timeUntilElectroplation = timeLeft;
	}
	
	private void handleVeneficianPodiumUpdate(EntityPlayer player, int fieldID, int newVal) {
		if(player.openContainer instanceof ContainerVeneficianPodium) {
			ContainerVeneficianPodium container = (ContainerVeneficianPodium)player.openContainer;
			container.setField(fieldID, newVal);
		}
	}

}
