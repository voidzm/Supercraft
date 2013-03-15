package com.voidzm.supercraft.block;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.entity.TileEntityWaveringLunarGenerator;
import com.voidzm.supercraft.protocol.IGenerator;
import com.voidzm.supercraft.protocol.IGenerator.GeneratorSide;

public class BlockWaveringLunarGenerator extends BlockContainerGenerator implements IGenerator {

	protected Icon textureTop, textureSide, textureBottom;
	
	public BlockWaveringLunarGenerator(int id) {
		super(id);
		this.setUnlocalizedName("waveringLunarGenerator");
	}

	public void func_94332_a(IconRegister par1IconRegister) {
		textureTop = par1IconRegister.func_94245_a("supercraft:waveringlunargenerator_top");
		textureSide = par1IconRegister.func_94245_a("supercraft:waveringlunargenerator_side");
		textureBottom = par1IconRegister.func_94245_a("supercraft:silverboundstone_beveled");
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		if(side == 0) return textureBottom;
		else if(side == 1) return textureTop;
		else return textureSide;
	}
	
	@Override
	public boolean doesOutputPowerAt(World world, int x, int y, int z) {
		return ((TileEntityWaveringLunarGenerator)world.getBlockTileEntity(x, y, z)).activated;
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
		return new TileEntityWaveringLunarGenerator();
	}
}
