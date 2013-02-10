//////////////////////////////////////
//*       EventBonemeal.java       *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.event;

import com.voidzm.supercraft.block.BlockSupercraftSapling1;
import com.voidzm.supercraft.block.BlockSupercraftSapling2;
import com.voidzm.supercraft.block.BlockSupercraftSaplingBase;
import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class EventBonemeal {

	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event) {
		if(event.ID == BlockHandler.supercraftSapling1.blockID) {
			if(!event.world.isRemote) {
				((BlockSupercraftSapling1)(BlockHandler.supercraftSapling1)).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		else if(event.ID == BlockHandler.supercraftSapling2.blockID) {
			if(!event.world.isRemote) {
				((BlockSupercraftSapling2)(BlockHandler.supercraftSapling2)).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
	}
	
}
