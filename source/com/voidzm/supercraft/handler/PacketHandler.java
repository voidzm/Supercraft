//////////////////////////////////////
//*       PacketHandler.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.handler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.entity.TileEntityEssentialReducer;
import com.voidzm.supercraft.entity.TileEntityConduit.CONDUIT_TYPE;
import com.voidzm.supercraft.entity.TileEntityConduit.PACKET_ELINVAR;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if(packet.channel.equals("SCElinvar")) {
			if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
				DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
				int typeID;
				try {
					typeID = inputStream.readInt();
				} catch(Exception e) {
					e.printStackTrace();
					return;
				}
				if(typeID == PACKET_ELINVAR.PROPAGATE.ordinal()) { // Elinvar propagation update
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
				else if(typeID == PACKET_ELINVAR.RENDER.ordinal()) { // Conduit TileEntity render
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
			}
		}
		else if(packet.channel.equals("SCMachineUpdates")) {
			if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
				DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
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
		}
	}
	
	private void handleElinvarPropagationUpdate(EntityPlayer player, int x, int y, int z, int newVal) {
		World world = player.worldObj;
		TileEntityConduit te = (TileEntityConduit)world.getBlockTileEntity(x, y, z);
		if(te == null) {
			System.out.println("[Supercraft] Could not find a client-side TileEntity at {"+x+", "+y+", "+z+"} for propagation.");
			return;
		}
		te.setPower(newVal);
	}
	
	private void handleConduitRender(EntityPlayer player, int x, int y, int z, int correctVal, int correctType) {
		World world = player.worldObj;
		TileEntityConduit te = (TileEntityConduit)world.getBlockTileEntity(x, y, z);
		if(te == null) {
			// This is sometimes a legitimate error, but usually if the chunk a CTE is in hasn't fully loaded yet,
			// this packet will arrive on the clients before the chunk data and be erroneously interpreted as a missing TE.
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

}
