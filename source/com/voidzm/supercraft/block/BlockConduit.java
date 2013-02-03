//////////////////////////////////////
//*       BlockConduit.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.entity.TileEntityConduit;
import com.voidzm.supercraft.entity.TileEntityConduit.PACKET_ELINVAR;
import com.voidzm.supercraft.handler.BlockHandler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;

public class BlockConduit extends BlockContainer {
	
	public BlockConduit(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setBlockName("conduit");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setLightOpacity(0);
		this.useNeighborBrightness[par1] = true;
		this.setTickRandomly(true);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(!par1World.isRemote) {
			super.updateTick(par1World, par2, par3, par4, par5Random);
			TileEntityConduit te = (TileEntityConduit)par1World.getBlockTileEntity(par2, par3, par4);
			if(te == null) return;
			if(te.powerLevel() > te.limitForType(te.conduitType())) {
				par1World.removeBlockTileEntity(par2, par3, par4);
				par1World.setBlockWithNotify(par2, par3, par4, 0);
				par1World.createExplosion(new EntityItem(par1World), (double)par2, (double)par3, (double)par4, 1.0F, true);
			}
		}
	}
	
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		return false;
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		float lowX = 0.25F;
		float lowY = 0.25F;
		float lowZ = 0.25F;
		float highX = 0.75F;
		float highY = 0.75F;
		float highZ = 0.75F;
		if(this.isConduitConnectable(world.getBlockId(x+1, y, z))) {
			highX = 1.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x-1, y, z))) {
			lowX = 0.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x, y+1, z))) {
			highY = 1.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x, y-1, z))) {
			lowY = 0.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x, y, z+1))) {
			highZ = 1.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x, y, z-1))) {
			lowZ = 0.0F;
		}
		this.setBlockBounds(lowX, lowY, lowZ, highX, highY, highZ);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		this.performElinvarUpdateChecks(par1World, par2, par3, par4, 0);
	}
	
	public void performElinvarUpdateChecks(World par1World, int par2, int par3, int par4, int par5) {
		int[] neighborIDs = new int[6];
		neighborIDs[0] = par1World.getBlockId(par2+1, par3, par4);
		neighborIDs[1] = par1World.getBlockId(par2-1, par3, par4);
		neighborIDs[2] = par1World.getBlockId(par2, par3, par4+1);
		neighborIDs[3] = par1World.getBlockId(par2, par3, par4-1);
		if(par3 != 255) neighborIDs[4] = par1World.getBlockId(par2, par3+1, par4);
		else neighborIDs[4] = 0;
		if(par3 != 0) neighborIDs[5] = par1World.getBlockId(par2, par3-1, par4);
		else neighborIDs[5] = 0;
		
		// New power logic. Spazzes out occasionally if you have a ridiculously complicated and recursive
		// net of conduits, but always recovers itself within 1-2 secs.
		
		TileEntityConduit te = (TileEntityConduit)par1World.getBlockTileEntity(par2, par3, par4);
		int i = 0;
		int currentPower = te.powerLevel();
		int maxPowerFound = 0;
		for(int neighbor : neighborIDs) {
			if(this.isConduit(neighbor)) {
				TileEntityConduit nTE = (TileEntityConduit)par1World.getBlockTileEntity(par2 + (i == 0 ? 1 : (i == 1 ? -1 : 0)), par3 + (i == 4 ? 1 : (i == 5 ? -1 : 0)), par4 + (i == 2 ? 1 : (i == 3 ? -1 : 0)));
				if(nTE.powerLevel() - 1 >= currentPower && nTE.powerLevel() - 1 > maxPowerFound) {
					maxPowerFound = nTE.powerLevel() - 1;
				}
			}
			else if(this.isGenerator(neighbor)) {
				int output = 16;
				if(output >= currentPower && output > maxPowerFound) {
					maxPowerFound = output;
				}
			}
			i++;
		}	
		if(maxPowerFound > currentPower) {
			te.setPower(maxPowerFound);
			this.dispatchElinvarUpdatePacket(par1World, par2, par3, par4, maxPowerFound);
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
		}
		else if(maxPowerFound < currentPower) {
			te.setPower(0);
			this.dispatchElinvarUpdatePacket(par1World, par2, par3, par4, 0);
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
		}
		else {
			int j = 0;
			for(int neighbor : neighborIDs) {
				if(this.isConduit(neighbor)) {
					TileEntityConduit nTE = (TileEntityConduit)par1World.getBlockTileEntity(par2 + (j == 0 ? 1 : (j == 1 ? -1 : 0)), par3 + (j == 4 ? 1 : (j == 5 ? -1 : 0)), par4 + (j == 2 ? 1 : (j == 3 ? -1 : 0)));
					if(nTE.powerLevel() < currentPower - 1) {
						nTE.setPower(currentPower - 1);
						this.dispatchElinvarUpdatePacket(par1World, nTE.xCoord, nTE.yCoord, nTE.zCoord, currentPower - 1);
						par1World.notifyBlocksOfNeighborChange(nTE.xCoord, nTE.yCoord, nTE.zCoord, this.blockID);
					}
				}
				j++;
			}
		}
		
		// Old power logic. Would crash the game into an infinte loop sometimes, hence the new code ^
		
		/*int highestPowerFound = 0;
		for(int neighbor : neighborIDs) {
			if(this.isConduit(neighbor)) {
				TileEntityConduit nTE = (TileEntityConduit)par1World.getBlockTileEntity(par2 + (i == 0 ? 1 : (i == 1 ? -1 : 0)), par3 + (i == 4 ? 1 : (i == 5 ? -1 : 0)), par4 + (i == 2 ? 1 : (i == 3 ? -1 : 0)));
				if(nTE.powerLevel() - 1 > te.powerLevel()) {
					System.out.println("The new power level of " + (nTE.powerLevel() - 1) + " is higher than ours of " + te.powerLevel() + ".");
					te.setPower(nTE.powerLevel() - 1);
					this.dispatchElinvarUpdatePacket(par1World, par2, par3, par4, nTE.powerLevel() - 1);
					par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
				}
				else if(nTE.powerLevel() < te.powerLevel() - 1) {
					par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
				}
				if(nTE.powerLevel() - 1 > highestPowerFound) highestPowerFound = nTE.powerLevel() - 1;
			}
			else if(this.isGenerator(neighbor)) {
				if(16 > te.powerLevel()) {
					System.out.println("The new power level of " + 16 + " is higher than ours of " + te.powerLevel() + ".");
					te.setPower(16);
					this.dispatchElinvarUpdatePacket(par1World, par2, par3, par4, 16);
					par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
				}
				if(16 > highestPowerFound) highestPowerFound = 16;
			}
			i++;
		}
		if(highestPowerFound < te.powerLevel()) {
			te.setPower(0);
			this.dispatchElinvarUpdatePacket(par1World, par2, par3, par4, 0);
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
		}*/
		
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		this.performElinvarUpdateChecks(par1World, par2, par3, par4, par5);
	}

	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	public TileEntity createNewTileEntity(World par1World) {
		try {
			return new TileEntityConduit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		par1World.removeBlockTileEntity(par2, par3, par4);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}
	
	public int getRenderType() {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) return -1; // Added this to stop crashing the server.
		else return ClientProxy.conduitInvRenderID;
	}
	
	public static boolean isGenerator(int blockID) {
		if(blockID == BlockHandler.elinvarBlock.blockID) return true;
		else return false;
	}
	
	public static boolean isConduit(int blockID) {
		if(blockID == BlockHandler.woodenConduit.blockID) return true;
		else if(blockID == BlockHandler.stoneConduit.blockID) return true;
		else if(blockID == BlockHandler.ironConduit.blockID) return true;
		else if(blockID == BlockHandler.copperConduit.blockID) return true;
		else if(blockID == BlockHandler.aluminumConduit.blockID) return true;
		else if(blockID == BlockHandler.silverConduit.blockID) return true;
		else if(blockID == BlockHandler.goldenConduit.blockID) return true;
		else if(blockID == BlockHandler.electrumConduit.blockID) return true;
		else if(blockID == BlockHandler.diamondConduit.blockID) return true;
		else return false;
	}
	
	public static boolean isConduitConnectable(int blockID) {
		if(isConduit(blockID)) return true;
		else if (isGenerator(blockID)) return true;
		else return false;
	}
	
	private void dispatchElinvarUpdatePacket(World world, int x, int y, int z, int newVal) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream(20);
		DataOutputStream dataStream = new DataOutputStream(byteArray);
		try {
			dataStream.writeInt(PACKET_ELINVAR.PROPAGATE.ordinal());
			dataStream.writeInt(x);
			dataStream.writeInt(y);
			dataStream.writeInt(z);
			dataStream.writeInt(newVal);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "SCElinvar";
		packet.data = byteArray.toByteArray();
		packet.length = byteArray.size();
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(side == Side.SERVER) {
			PacketDispatcher.sendPacketToAllInDimension(packet, world.provider.dimensionId);
		}
	}

}
