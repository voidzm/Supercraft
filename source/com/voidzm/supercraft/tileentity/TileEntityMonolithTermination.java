package com.voidzm.supercraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.voidzm.supercraft.handler.BlockHandler;

public class TileEntityMonolithTermination extends TileEntity {

	public boolean activated;
	private int internalTick;
	
	public TileEntityMonolithTermination() {
		this.activated = false;
		this.internalTick = 0;
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
		if(this.worldObj.isRemote) return;
		if(this.worldObj == null) return;
		if(internalTick < 10) {
			internalTick++;
			return;
		}
		if(this.shouldBePowered()) {
			if(this.activated == false) {
				this.activated = true;
				this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, BlockHandler.monolithTerminationActivated.blockID, 0, 3);
			}
		}
		else if(this.activated == true) {
			this.activated = false;
			this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, BlockHandler.monolithTermination.blockID, 0, 3);
		}
		internalTick = 0;
	}
	
	private boolean shouldBePowered() {
		if(this.worldObj.getBlockId(this.xCoord+1, this.yCoord, this.zCoord) == BlockHandler.gravenStone.blockID && this.worldObj.getBlockMetadata(this.xCoord+1, this.yCoord, this.zCoord) == 3) {
			if(this.worldObj.getBlockId(this.xCoord-1, this.yCoord, this.zCoord) == BlockHandler.gravenStone.blockID && this.worldObj.getBlockMetadata(this.xCoord-1, this.yCoord, this.zCoord) == 3) {
				if(this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord+1) == BlockHandler.gravenStone.blockID && this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord+1) == 3) {
					if(this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord-1) == BlockHandler.gravenStone.blockID && this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord-1) == 3) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean shouldRefresh(int oldID, int newID, int oldMeta, int newMeta, World world, int x, int y, int z) {
		if(oldID == BlockHandler.monolithTermination.blockID || oldID == BlockHandler.monolithTerminationActivated.blockID) {
			if(newID == BlockHandler.monolithTermination.blockID || newID == BlockHandler.monolithTerminationActivated.blockID) {
				return false;
			}
		}
		return true;
	}

}
