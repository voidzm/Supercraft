package com.voidzm.supercraft.dimension;

import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.util.DeepPortalLocation;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterDeep extends Teleporter {

	private WorldServer serverWorld;
	
	public TeleporterDeep(WorldServer par1WorldServer) {
		super(par1WorldServer);
		this.serverWorld = par1WorldServer;
	}
	
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8) {
		int ex = MathHelper.floor_double(par2);
		int ey = MathHelper.floor_double(par4);
		int ez = MathHelper.floor_double(par6);
		
		DeepPortalLocation portalLoc = this.locateExistingDeepSpawn(ex, ey);
		if(portalLoc != null) {
			par1Entity.setLocationAndAngles((double)(portalLoc.x)+0.5, (double)(portalLoc.y)+1.0, (double)(portalLoc.z)+0.5, par1Entity.rotationYaw, 0.0F);
			return;
		}
		portalLoc = this.findNewSpawnLocation(ex, ey);
		for(int ix = -2; ix < 3; ix++) {
			for(int iy = -1; iy < 5; iy++) {
				for(int iz = -2; iz < 3; iz++) {
					if(iy == -1) {
						if(ix == 0 && iz == 0) this.serverWorld.func_94575_c(portalLoc.x+ix, portalLoc.y+iy, portalLoc.z+iz, BlockHandler.cobaltBlock.blockID);
						else this.serverWorld.func_94575_c(portalLoc.x+ix, portalLoc.y+iy, portalLoc.z+iz, BlockHandler.palestone.blockID);
					}
					else {
						this.serverWorld.func_94575_c(portalLoc.x+ix, portalLoc.y+iy, portalLoc.z+iz, 0);
					}
				}
			}
		}
		par1Entity.setLocationAndAngles((double)(portalLoc.x)+0.5, (double)(portalLoc.y)+1.0, (double)(portalLoc.z)+0.5, par1Entity.rotationYaw, 0.0F);
	}
	
	public DeepPortalLocation locateExistingDeepSpawn(int xPos, int zPos) {
		int radius = 50;
		for(int ix = -radius; ix < radius; ix++) {
			for(int iz = -radius; iz < radius; iz++) {
				for(int iy = 0; iy < 120; iy++) {
					if(this.serverWorld.getBlockId(xPos+ix, iy, zPos+iz) == BlockHandler.cobaltBlock.blockID) {
						return new DeepPortalLocation(xPos+ix, iy, zPos+iz);
					}
				}
			}
		}
		return null;
	}
	
	public DeepPortalLocation findNewSpawnLocation(int xPos, int zPos) {
		Random rand = new Random();
		boolean didFindSpawn = false;
		DeepPortalLocation location = null;
		while(!didFindSpawn) {
			for(int iy = 0; iy < 120; iy++) {
				if(this.serverWorld.getBlockId(xPos, iy, zPos) == 0) {
					if(this.serverWorld.getBlockId(xPos, iy-1, zPos) == Block.stone.blockID) {
						didFindSpawn = true;
						location = new DeepPortalLocation(xPos, iy-1, zPos);
					}
				}
			}
			if(!didFindSpawn) {
				xPos += (rand.nextInt(100) - 50);
				zPos += (rand.nextInt(100) - 50);
			}
		}
		return location;
	}

}
