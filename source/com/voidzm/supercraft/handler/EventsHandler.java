//**
//**  EventHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import com.voidzm.supercraft.block.BlockSupercraftMushroom;
import com.voidzm.supercraft.block.BlockSupercraftSapling1;
import com.voidzm.supercraft.block.BlockSupercraftSapling2;

public class EventsHandler {

	private static EventsHandler instance;
	
	public static EventsHandler instance() {
		if(instance == null) {
			instance = new EventsHandler();
		}
		return instance;
	}
	
	public static void init() {
		MinecraftForge.EVENT_BUS.register(instance());
	}
	
	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event) {
		if(event.world.isRemote) return;
		if(event.ID == BlockHandler.supercraftSapling1.blockID) {
			((BlockSupercraftSapling1)(BlockHandler.supercraftSapling1)).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
		}
		else if(event.ID == BlockHandler.supercraftSapling2.blockID) {
			((BlockSupercraftSapling2)(BlockHandler.supercraftSapling2)).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
		}
		else if(event.ID == BlockHandler.inisiaMushroom.blockID || event.ID == BlockHandler.valensienMushroom.blockID || event.ID == BlockHandler.mortaliaMushroom.blockID) {
			BlockSupercraftMushroom.doGrow(event.world, event.X, event.Y, event.Z, event.world.rand);
		}
	}
	
	@ForgeSubscribe
	public void onBucketFilled(FillBucketEvent event) {
		int targetedID = event.world.getBlockId(event.target.blockX, event.target.blockY, event.target.blockZ);
		if(targetedID == BlockHandler.ghostlyVaporStill.blockID) {
			event.setCanceled(true);
		}
	}
	
}
