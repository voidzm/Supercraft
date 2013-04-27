package com.voidzm.supercraft.misc;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;

public class DamageSourceVeneficia extends DamageSource {

	public DamageSourceVeneficia() {
		super("veneficia");
		this.setDamageBypassesArmor();
	}
	
	public String getDeathMessage(EntityLiving par1EntityLiving) {
		String string = par1EntityLiving.getEntityName();
		return string + " was torn apart by the currents of Veneficia.";
	}

}
