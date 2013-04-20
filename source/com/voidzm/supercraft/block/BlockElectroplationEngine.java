package com.voidzm.supercraft.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.tileentity.TileEntityAlloyInductor;
import com.voidzm.supercraft.tileentity.TileEntityElectroplationEngine;
import com.voidzm.supercraft.tileentity.TileEntityEssentialReducer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockElectroplationEngine extends BlockSupercraft {

	protected Icon textureTop, textureSide, textureBottom;
	
	public BlockElectroplationEngine(int id) {
		super(id, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
		this.setStepSound(soundStoneFootstep);
		this.setInternalName("electroplationengine");
		this.setExternalName("Electroplation Engine");
		this.setCreativeTab(Supercraft.elinvarTab);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var1, float var2, float var3, float var4) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te == null || player.isSneaking()) {
			return false;
		}
		player.openGui(Supercraft.instance, 3, world, x, y, z);
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		this.dropContents(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	private void dropContents(World world, int x, int y, int z) {
		Random rand = new Random();
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te == null || !(te instanceof IInventory)) {
			return;
		}
		IInventory inv = (IInventory)te;
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if(stack != null && stack.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;
				EntityItem entityItem = new EntityItem(world, x+rx, y+ry, z+rz, new ItemStack(stack.itemID, stack.stackSize, stack.getItemDamage()));
				if(stack.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
				}
				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				stack.stackSize = 0;
			}
		}
	}
	
	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityElectroplationEngine();
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		this.doChecks(par1World, par2, par3, par4);
	}
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		this.doChecks(par1World, par2, par3, par4);
	}
	
	public void doChecks(World par1World, int par2, int par3, int par4) {
		TileEntityElectroplationEngine te = (TileEntityElectroplationEngine)par1World.getBlockTileEntity(par2, par3, par4);
		if(te == null) return;
		te.doUpdateCheck = true;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		textureTop = par1IconRegister.registerIcon("supercraft:electroplationengine_top");
		textureSide = par1IconRegister.registerIcon("supercraft:electroplationengine_side");
		textureBottom = par1IconRegister.registerIcon("supercraft:goldboundstone_beveled");
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		if(side == 0) return textureBottom;
		else if(side == 1) return textureTop;
		else return textureSide;
	}
	
}
