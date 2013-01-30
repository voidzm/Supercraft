//////////////////////////////////////
//*     TileEntityConduit.java     *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import com.voidzm.supercraft.block.BlockConduit;
import com.voidzm.supercraft.handler.BlockHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityConduit extends TileEntity {

	public enum CONDUIT_TYPE { 
		WOOD, STONE, IRON, COPPER, ALUMINUM, SILVER,
		GOLD, ELECTRUM, DIAMOND, COBALT, PLATINUM, TANTALUM, LITHIUM
	}
	
	public enum PACKET_ELINVAR {
		PROPAGATE, RENDER
	}
	
	private int powerLevel;
	
	private boolean initialized = false;
	private boolean shouldUpdateRender = false;
	
	private int updateTicks = 0;
	
	private CONDUIT_TYPE conduitType;
	
	public TileEntityConduit() {
		this(CONDUIT_TYPE.WOOD);
	}
	
	public TileEntityConduit(CONDUIT_TYPE type) {
		super();
		this.powerLevel = 0;
		this.conduitType = type;
	}
	
	public void setPower(int power) {
		this.powerLevel = power;
	}
	
	public int powerLevel() {
		return this.powerLevel;
	}

	public void setConduitType(CONDUIT_TYPE type) {
		this.conduitType = type;
	}
	
	public CONDUIT_TYPE conduitType() {
		return this.conduitType;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if(nbt.hasKey("power")) {
			this.powerLevel = nbt.getInteger("power");
		}
		else this.powerLevel = 0;
		if(nbt.hasKey("type")) {
			this.conduitType = CONDUIT_TYPE.valueOf(nbt.getString("type"));
		}
		else this.conduitType = CONDUIT_TYPE.WOOD;
	}
	
	@Override
	public void updateEntity() {
		if(updateTicks < 50) updateTicks++;
		else {
			updateTicks = 0;
			shouldUpdateRender = true;
		}
		if(!initialized) {
			this.initialize();
			initialized = true;
		}
		if(shouldUpdateRender && !this.worldObj.playerEntities.isEmpty()) {
			if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
				this.dispatchConduitRenderPacket(this.xCoord, this.yCoord, this.zCoord, this.powerLevel, this.conduitType);
			}	
			shouldUpdateRender = false;
		}
	}
	
	private void initialize() {
		this.shouldUpdateRender = true;
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("power", this.powerLevel);
		nbt.setString("type", this.conduitType.name());
	}
	
	public static int limitForType(CONDUIT_TYPE type) {
		switch(type) {
		case WOOD:
			return 4;
		case STONE:
			return 8;
		case IRON:
			return 16;
		case COPPER:
			return 32;
		case ALUMINUM:
			return 64;
		case SILVER:
			return 128;
		case GOLD:
			return 256;
		case ELECTRUM:
			return 512;
		case DIAMOND:
			return 1024;
		case COBALT:
			return 2048;
		case PLATINUM:
			return 4096;
		case TANTALUM:
			return 8192;
		case LITHIUM:
			return 16384;
		default:
			return 4;
		}
	}
	
	private void dispatchConduitRenderPacket(int x, int y, int z, int correctVal, CONDUIT_TYPE correctType) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream(24);
		DataOutputStream dataStream = new DataOutputStream(byteArray);
		try {
			dataStream.writeInt(PACKET_ELINVAR.RENDER.ordinal());
			dataStream.writeInt(x);
			dataStream.writeInt(y);
			dataStream.writeInt(z);
			dataStream.writeInt(correctVal);
			dataStream.writeInt(correctType.ordinal());
		} catch(Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "SCElinvar";
		packet.data = byteArray.toByteArray();
		packet.length = byteArray.size();
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(side == Side.SERVER) {
			PacketDispatcher.sendPacketToAllInDimension(packet, this.worldObj.provider.dimensionId);
		}
	}
	
}
