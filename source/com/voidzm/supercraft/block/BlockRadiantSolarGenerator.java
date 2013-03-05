package com.voidzm.supercraft.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.entity.TileEntityRadiantSolarGenerator;
import com.voidzm.supercraft.protocol.IGenerator;
import com.voidzm.supercraft.protocol.IGenerator.GeneratorSide;

public class BlockRadiantSolarGenerator extends BlockContainerGenerator implements IGenerator {

	public BlockRadiantSolarGenerator(int id) {
		super(id, 124);
		this.setBlockName("radiantSolarGenerator");
	}

	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		if(side == 0) return 126;
		else if(side == 1) return 124;
		else return 125;
	}
	
	@Override
	public boolean doesOutputPowerAt(World world, int x, int y, int z) {
		return ((TileEntityRadiantSolarGenerator)world.getBlockTileEntity(x, y, z)).activated;
	}

	@Override
	public int powerOutputAt(World world, int x, int y, int z) {
		return 8;
	}

	@Override
	public boolean shouldConnectAtSide(GeneratorSide side) {
		if(side != GeneratorSide.BOTTOM && side != GeneratorSide.TOP) return true;
		else return false;
	}
	
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityRadiantSolarGenerator();
	}
}
