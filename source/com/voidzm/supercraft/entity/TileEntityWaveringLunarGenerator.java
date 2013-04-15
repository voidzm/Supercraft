package com.voidzm.supercraft.entity;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWaveringLunarGenerator extends TileEntity {

	public boolean activated;
	private int internalCounter;
	
	public TileEntityWaveringLunarGenerator() {
		activated = false;
		internalCounter = 0;
	}
	
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
	}
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.customParam1);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		this.activated = tagCompound.getBoolean("Activated");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setBoolean("Activated", this.activated);
	}
	
	public void updateEntity() {
		if(this.worldObj == null) return;
		if(internalCounter < 10) {
			internalCounter++;
			return;
		}
		if(this.shouldBePowered()) {
			if(this.activated == false) {
				this.activated = true;
				this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, BlockHandler.radiantSolarGenerator.blockID);
			}
		}
		else if(this.activated == true) {
			this.activated = false;
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, BlockHandler.radiantSolarGenerator.blockID);
		}
		internalCounter = 0;
	}
	
	private boolean shouldBePowered() {
		if(this.worldObj.provider.hasNoSky) return false;
		if(this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord+1, this.zCoord) && (this.worldObj.getCelestialAngle(0.0F) > 0.375F && this.worldObj.getCelestialAngle(0.0F) < 0.625F)) return true;
		else return false;
	}
	
}
