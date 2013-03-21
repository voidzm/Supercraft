package com.voidzm.supercraft.event;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class EventDepths {

	@ForgeSubscribe
	public void onBucketFilled(FillBucketEvent event) {
		int targetedID = event.world.getBlockId(event.target.blockX, event.target.blockY, event.target.blockZ);
		if(targetedID == BlockHandler.ghostlyVaporStill.blockID) {
			event.setCanceled(true);
		}
	}
	
}
