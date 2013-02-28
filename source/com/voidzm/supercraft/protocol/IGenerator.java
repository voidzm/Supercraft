//////////////////////////////////////
//*        IGenerator.java         *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.protocol;

import net.minecraft.world.World;

public interface IGenerator {

	public enum GeneratorSide {
		BOTTOM, TOP, NORTH, SOUTH, EAST, WEST
	}
	
	public boolean doesOutputPowerAt(World world, int x, int y, int z);
	
	public int powerOutputAt(World world, int x, int y, int z);
	
	public boolean shouldConnectAtSide(GeneratorSide side);
	
}
