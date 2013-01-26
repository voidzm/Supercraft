package com.voidzm.supercraft.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityConduit extends TileEntity {

	private int powerLevel;
	
	public void setPower(int power) {
		this.powerLevel = power;
	}
	
	public int powerLevel() {
		return this.powerLevel;
	}

	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if(nbt.hasKey("power")) {
			this.powerLevel = nbt.getInteger("power");
		}
		else this.powerLevel = 0;
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("power", this.powerLevel);
	}
	
}
