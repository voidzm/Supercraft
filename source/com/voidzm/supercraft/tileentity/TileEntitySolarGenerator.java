package com.voidzm.supercraft.tileentity;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

import com.voidzm.supercraft.util.Timespan;

public class TileEntitySolarGenerator extends TileEntity {

	public boolean activated;
	private int internalCounter;
	
	private ArrayList<Timespan> validActivationTimes;
	
	public TileEntitySolarGenerator() {
		this(new ArrayList<Timespan>());
	}
	
	public TileEntitySolarGenerator(ArrayList<Timespan> spans) {
		activated = false;
		internalCounter = 0;
		this.validActivationTimes = spans;
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
		NBTTagList activationTimes = tagCompound.getTagList("Times");
		ArrayList<Timespan> timespanList = new ArrayList<Timespan>();
		for(int i = 0; i < activationTimes.tagCount(); i++) {
			NBTTagCompound entry = (NBTTagCompound)activationTimes.tagAt(i);
			int estart = entry.getInteger("Start");
			int eend = entry.getInteger("End");
			Timespan espan = new Timespan(estart, eend);
			timespanList.add(espan);
		}
		this.validActivationTimes = timespanList;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setBoolean("Activated", this.activated);
		NBTTagList activationTimes = new NBTTagList();
		for(Timespan span : this.validActivationTimes) {
			NBTTagCompound entry = new NBTTagCompound();
			entry.setInteger("Start", span.getStart());
			entry.setInteger("End", span.getEnd());
			activationTimes.appendTag(entry);
		}
		tagCompound.setTag("Times", activationTimes);
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
				this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord));
			}
		}
		else if(this.activated == true) {
			this.activated = false;
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord));
		}
		internalCounter = 0;
	}
	
	private boolean shouldBePowered() {
		if(this.worldObj.provider.hasNoSky) return false;
		if(!this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord+1, this.zCoord)) return false;
		boolean inValidTime = false;
		float currentTime = this.worldObj.getWorldInfo().getWorldTime();
		for(Timespan span : this.validActivationTimes) {
			if(currentTime > span.getStart() && currentTime < span.getEnd()) {
				inValidTime = true;
				break;
			}
		}
		return inValidTime;
	}
	
}
