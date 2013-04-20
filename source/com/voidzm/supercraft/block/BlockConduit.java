//////////////////////////////////////
//*       BlockConduit.java        *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.block;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.item.ItemBlockSupercraft;
import com.voidzm.supercraft.item.ItemConduit;
import com.voidzm.supercraft.protocol.IGenerator;
import com.voidzm.supercraft.protocol.IGenerator.GeneratorSide;
import com.voidzm.supercraft.tileentity.TileEntityConduit;
import com.voidzm.supercraft.tileentity.TileEntityConduit.CONDUIT_TYPE;
import com.voidzm.supercraft.tileentity.TileEntityConduit.PACKET_ELINVAR;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;

public class BlockConduit extends BlockSupercraft {
	
	public Icon[] textures = new Icon[13];
	
	public static String[] texturePaths = new String[] {"conduitwood.png", "conduitstone.png", "conduitiron.png", "conduitcopper.png", "conduitaluminum.png",
		"conduitsilver.png", "conduitgold.png", "conduitelectrum.png", "conduitdiamond.png",
		"conduitcobalt.png", "conduitplatinum.png", "conduittantalum.png", "conduitlithium.png"};
	
	private ArrayList<String> names = new ArrayList<String>();
	
	public BlockConduit(int par1) {
		super(par1, Material.iron);
		this.populateNames();
		this.setHardness(1.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setInternalName("conduit");
		this.setExternalName("Conduit");
		this.makeMultiblock(this.names, ItemBlockSupercraft.class);
		this.setCreativeTab(Supercraft.elinvarTab);
		this.setLightOpacity(0);
		this.useNeighborBrightness[par1] = true;
		this.setTickRandomly(true);
	}
	
	private void populateNames() {
		this.names.add("Wooden Conduit");
		this.names.add("Stone Conduit");
		this.names.add("Iron Conduit");
		this.names.add("Copper Conduit");
		this.names.add("Aluminum Conduit");
		this.names.add("Silver Conduit");
		this.names.add("Golden Conduit");
		this.names.add("Electrum Conduit");
		this.names.add("Diamond Conduit");
		this.names.add("Cobalt Conduit");
		this.names.add("Platinum Conduit");
		this.names.add("Tantalum Conduit");
		this.names.add("Lithium Conduit");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textures[0] = par1IconRegister.registerIcon("supercraft:conduitwood");
		textures[1] = par1IconRegister.registerIcon("supercraft:conduitstone");
		textures[2] = par1IconRegister.registerIcon("supercraft:conduitiron");
		textures[3] = par1IconRegister.registerIcon("supercraft:conduitcopper");
		textures[4] = par1IconRegister.registerIcon("supercraft:conduitaluminum");
		textures[5] = par1IconRegister.registerIcon("supercraft:conduitsilver");
		textures[6] = par1IconRegister.registerIcon("supercraft:conduitgold");
		textures[7] = par1IconRegister.registerIcon("supercraft:conduitelectrum");
		textures[8] = par1IconRegister.registerIcon("supercraft:conduitdiamond");
		textures[9] = par1IconRegister.registerIcon("supercraft:conduitcobalt");
		textures[10] = par1IconRegister.registerIcon("supercraft:conduitplatinum");
		textures[11] = par1IconRegister.registerIcon("supercraft:conduittantalum");
		textures[12] = par1IconRegister.registerIcon("supercraft:conduitlithium");
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return this.textures[par2];
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(!par1World.isRemote) {
			super.updateTick(par1World, par2, par3, par4, par5Random);
			TileEntityConduit te = (TileEntityConduit)par1World.getBlockTileEntity(par2, par3, par4);
			if(te == null) return;
			if(te.powerLevel() > te.limitForType(te.conduitType())) {
				par1World.removeBlockTileEntity(par2, par3, par4);
				par1World.setBlockToAir(par2, par3, par4);
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
		if(this.isConduitConnectable(world.getBlockId(x+1, y, z), GeneratorSide.EAST)) {
			highX = 1.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x-1, y, z), GeneratorSide.WEST)) {
			lowX = 0.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x, y+1, z), GeneratorSide.TOP)) {
			highY = 1.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x, y-1, z), GeneratorSide.BOTTOM)) {
			lowY = 0.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x, y, z+1), GeneratorSide.SOUTH)) {
			highZ = 1.0F;
		}
		if(this.isConduitConnectable(world.getBlockId(x, y, z-1), GeneratorSide.NORTH)) {
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
		if(te == null) {
			return;
		}
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
			else if(Block.blocksList[neighbor] instanceof IGenerator) {
				IGenerator gen = (IGenerator)Block.blocksList[neighbor];
				int output = gen.powerOutputAt(par1World, par2 + (i == 0 ? 1 : (i == 1 ? -1 : 0)), par3 + (i == 4 ? 1 : (i == 5 ? -1 : 0)), par4 + (i == 2 ? 1 : (i == 3 ? -1 : 0)));
				boolean doPowerConnect = false;
				switch(i) {
				case 0:
					if(gen.shouldConnectAtSide(GeneratorSide.EAST)) doPowerConnect = true;
					break;
				case 1:
					if(gen.shouldConnectAtSide(GeneratorSide.WEST)) doPowerConnect = true;
					break;
				case 2:
					if(gen.shouldConnectAtSide(GeneratorSide.SOUTH)) doPowerConnect = true;
					break;
				case 3:
					if(gen.shouldConnectAtSide(GeneratorSide.NORTH)) doPowerConnect = true;
					break;
				case 4:
					if(gen.shouldConnectAtSide(GeneratorSide.TOP)) doPowerConnect = true;
					break;
				case 5:
					if(gen.shouldConnectAtSide(GeneratorSide.BOTTOM)) doPowerConnect = true;
					break;
				}
				if(gen.doesOutputPowerAt(par1World, par2 + (i == 0 ? 1 : (i == 1 ? -1 : 0)), par3 + (i == 4 ? 1 : (i == 5 ? -1 : 0)), par4 + (i == 2 ? 1 : (i == 3 ? -1 : 0))) && doPowerConnect) {
					if(output >= currentPower && output > maxPowerFound) {
						maxPowerFound = output;
					}
				}
			}
			/*else if(this.isGenerator(neighbor)) {
				int output = 16;
				if(output >= currentPower && output > maxPowerFound) {
					maxPowerFound = output;
				}
			}*/
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
	
	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		switch(metadata) {
		case 0:
			return new TileEntityConduit(CONDUIT_TYPE.WOOD);
		case 1:
			return new TileEntityConduit(CONDUIT_TYPE.STONE);
		case 2:
			return new TileEntityConduit(CONDUIT_TYPE.IRON);
		case 3:
			return new TileEntityConduit(CONDUIT_TYPE.COPPER);
		case 4:
			return new TileEntityConduit(CONDUIT_TYPE.ALUMINUM);
		case 5:
			return new TileEntityConduit(CONDUIT_TYPE.SILVER);
		case 6:
			return new TileEntityConduit(CONDUIT_TYPE.GOLD);
		case 7:
			return new TileEntityConduit(CONDUIT_TYPE.ELECTRUM);
		case 8:
			return new TileEntityConduit(CONDUIT_TYPE.DIAMOND);
		case 9:
			return new TileEntityConduit(CONDUIT_TYPE.COBALT);
		case 10:
			return new TileEntityConduit(CONDUIT_TYPE.PLATINUM);
		case 11:
			return new TileEntityConduit(CONDUIT_TYPE.TANTALUM);
		case 12:
			return new TileEntityConduit(CONDUIT_TYPE.LITHIUM);
		default:
			break;
		}
		return null;
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
		if(Block.blocksList[blockID] instanceof IGenerator) return true;
		else return false;
	}
	
	public static boolean isConduit(int blockID) {
		Block blockType = Block.blocksList[blockID];
		if(blockType == null) return false;
		if(blockType instanceof BlockConduit) return true;
		else return false;
	}
	
	public static boolean isMachine(int blockID) {
		if(blockID == BlockHandler.essentialReducer.blockID) return true;
		else if(blockID == BlockHandler.alloyInductor.blockID) return true;
		else if(blockID == BlockHandler.electroplationEngine.blockID) return true;
		else return false;
	}
	
	public static boolean isConduitConnectable(int blockID, GeneratorSide side) {
		if(BlockConduit.isConduit(blockID)) return true;
		else if(BlockConduit.isGenerator(blockID)) {
			IGenerator block = (IGenerator)Block.blocksList[blockID];
			if(block == null) return false;
			if(block.shouldConnectAtSide(side)) return true;
			else return false;
		}
		else if(BlockConduit.isMachine(blockID)) return true;
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

	public int damageDropped(int par1) {
		return par1;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
		par3List.add(new ItemStack(par1, 1, 4));
		par3List.add(new ItemStack(par1, 1, 5));
		par3List.add(new ItemStack(par1, 1, 6));
		par3List.add(new ItemStack(par1, 1, 7));
		par3List.add(new ItemStack(par1, 1, 8));
		par3List.add(new ItemStack(par1, 1, 9));
		par3List.add(new ItemStack(par1, 1, 10));
		par3List.add(new ItemStack(par1, 1, 11));
		par3List.add(new ItemStack(par1, 1, 12));
	}

}
