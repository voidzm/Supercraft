package com.voidzm.supercraft.dimension;

import java.util.Random;

import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.util.DeepPortalLocation;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterOverworld extends Teleporter {

	private WorldServer serverWorld;
	
	public TeleporterOverworld(WorldServer par1WorldServer) {
		super(par1WorldServer);
		this.serverWorld = par1WorldServer;
	}
	
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8) {
		System.out.println("Attempting to place in portal at ("+par2+", "+par4+", "+par6+").");
		int ex = MathHelper.floor_double(par2);
		int ey = MathHelper.floor_double(par4);
		int ez = MathHelper.floor_double(par6);
		
		DeepPortalLocation portalLoc = this.locateExistingOverworldSpawn(ex, ez);
		if(portalLoc != null) {
			par1Entity.setLocationAndAngles((double)(portalLoc.x)+0.5, (double)(portalLoc.y)+1.0, (double)(portalLoc.z)+0.5, par1Entity.rotationYaw, 0.0F);
			return;
		}
		portalLoc = this.findNewSpawnLocation(ex, ez);
		for(int ix = -3; ix < 4; ix++) {
			for(int iy = -1; iy < 5; iy++) {
				for(int iz = -3; iz < 4; iz++) {
					if(Math.abs(ix) == 3 && Math.abs(iz) > 0) continue;
					if(Math.abs(ix) == 2 && Math.abs(iz) > 1) continue;
					if(Math.abs(ix) == 1 && Math.abs(iz) > 2) continue;
					if(iy == -1) {
						if(ix == 0 && iz == 0) this.replaceBlockForSpawn(serverWorld, portalLoc.x+ix, portalLoc.y+iy, portalLoc.z+iz, BlockHandler.monolithInceptionActivated.blockID, 0);
						else this.replaceBlockForSpawn(serverWorld, portalLoc.x+ix, portalLoc.y+iy, portalLoc.z+iz, BlockHandler.gravenStone.blockID, 1);
					}
					else {
						this.replaceBlockForSpawn(serverWorld, portalLoc.x+ix, portalLoc.y+iy, portalLoc.z+iz, 0, 0);
					}
				}
			}
		}
		par1Entity.setLocationAndAngles((double)(portalLoc.x)+0.5, (double)(portalLoc.y)+1.0, (double)(portalLoc.z)+0.5, par1Entity.rotationYaw, 0.0F);
	}
	
	public DeepPortalLocation locateExistingOverworldSpawn(int xPos, int zPos) {
		int radius = 50;
		for(int ix = -radius; ix < radius; ix++) {
			for(int iz = -radius; iz < radius; iz++) {
				for(int iy = 0; iy < 120; iy++) {
					if(this.serverWorld.getBlockId(xPos+ix, iy, zPos+iz) == BlockHandler.monolithInceptionActivated.blockID) {
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
			for(int iy = 120; iy > 0; iy--) {
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
	
	private void replaceBlockForSpawn(World world, int x, int y, int z, int id, int meta) {
		int replacedID = world.getBlockId(x, y, z);
		if(replacedID == 0) world.setBlock(x, y, z, id, meta, 0);
		if(replacedID == Block.stone.blockID) world.setBlock(x, y, z, id, meta, 0);
		if(replacedID == Block.dirt.blockID) world.setBlock(x, y, z, id, meta, 0);
		if(replacedID == Block.gravel.blockID) world.setBlock(x, y, z, id, meta, 0);
	}

}
