package com.voidzm.supercraft.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityVenianFX extends EntityFX {
	
	public EntityVenianFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		this.motionX = par8;
		this.motionY = par10;
		this.motionZ = par12;
		this.particleMaxAge = 9;
		this.particleRed = 0.9F;
		this.particleGreen = 0.9F;
		this.particleBlue = 0.9F;
		this.particleGravity = 0.0F;
		this.particleScale = 0.8F;
		this.noClip = true;
	}
	
	public int getBrightnessForRender(float par1) {
		return 240;
	}
	
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if(this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}
		this.setParticleTextureIndex(7 - this.particleAge * 6 / this.particleMaxAge);
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }

}
