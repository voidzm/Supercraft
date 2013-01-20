//////////////////////////////////////
//*       EventBonemeal.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.event;

import com.voidzm.supercraft.block.BlockSupercraftSapling;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class EventBonemeal {

	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event) {
		if(event.ID == BlockHandler.supercraftSapling.blockID) {
			if(!event.world.isRemote) {
				((BlockSupercraftSapling)(BlockHandler.supercraftSapling)).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
	}
	
}
