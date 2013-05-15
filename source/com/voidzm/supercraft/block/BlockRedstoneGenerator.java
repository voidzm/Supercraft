//////////////////////////////////////
//*   BlockRedstoneGenerator.java  *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.voidzm.supercraft.protocol.IGenerator;

public class BlockRedstoneGenerator extends BlockGenerator implements IGenerator {

	protected Icon textureTop, textureSide, textureBottom;
	
	public BlockRedstoneGenerator(int id) {
		super(id);
		this.setInternalName("redstoneconversiongenerator");
		this.setExternalName("Redstone Conversion Generator");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textureTop = par1IconRegister.registerIcon("supercraft:ironboundstone_beveled");
		textureSide = par1IconRegister.registerIcon("supercraft:redstoneconversiongenerator_side");
		textureBottom = par1IconRegister.registerIcon("supercraft:stone_beveled");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		if(side == 0) return textureBottom;
		else if(side == 1) return textureTop;
		else return textureSide;
	}

	@Override
	public int powerOutputAt(World world, int x, int y, int z) {
		return 1;
	}
	
	private boolean shouldOutputPowerAt(World world, int x, int y, int z) {
		if(world.isBlockIndirectlyGettingPowered(x, y, z)) {
			return true;
		}
		else return false;
	}
	
	@Override
	public boolean doesOutputPowerAt(World world, int x, int y, int z) {
		if(this.shouldOutputPowerAt(world, x, y, z) && world.getBlockMetadata(x, y, z) == 1) return true;
		else return false;
	}
	
	@Override
	public void updateState(World par1World, int par2, int par3, int par4) {
		if(this.shouldOutputPowerAt(par1World, par2, par3, par4)) {
			if(par1World.getBlockMetadata(par2, par3, par4) == 0) {
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
			}
		}
		else if(par1World.getBlockMetadata(par2, par3, par4) == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
		}
	}

	@Override
	public boolean shouldConnectAtSide(GeneratorSide side) {
		if(side != GeneratorSide.BOTTOM && side != GeneratorSide.TOP) return true;
		else return false;
	}

}
