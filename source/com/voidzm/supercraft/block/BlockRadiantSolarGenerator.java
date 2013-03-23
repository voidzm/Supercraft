package com.voidzm.supercraft.block;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.entity.TileEntityRadiantSolarGenerator;
import com.voidzm.supercraft.protocol.IGenerator;
import com.voidzm.supercraft.protocol.IGenerator.GeneratorSide;

public class BlockRadiantSolarGenerator extends BlockContainerGenerator implements IGenerator {

	protected Icon textureTop, textureSide, textureBottom;
	
	public BlockRadiantSolarGenerator(int id) {
		super(id);
		this.setUnlocalizedName("radiantSolarGenerator");
	}

	public void registerIcons(IconRegister par1IconRegister) {
		textureTop = par1IconRegister.registerIcon("supercraft:radiantsolargenerator_top");
		textureSide = par1IconRegister.registerIcon("supercraft:radiantsolargenerator_side");
		textureBottom = par1IconRegister.registerIcon("supercraft:copperboundstone_beveled");
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		if(side == 0) return textureBottom;
		else if(side == 1) return textureTop;
		else return textureSide;
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
