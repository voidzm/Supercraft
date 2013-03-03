package com.voidzm.supercraft.block;

import java.util.Random;

import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.entity.TileEntityEssentialReducer;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEssentialReducer extends BlockContainer {

	public BlockEssentialReducer(int id) {
		super(id, 119, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
		this.setStepSound(soundStoneFootstep);
		this.setBlockName("essentialReducer");
		this.setCreativeTab(Supercraft.elinvarTab);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var1, float var2, float var3, float var4) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te == null || player.isSneaking()) {
			return false;
		}
		player.openGui(Supercraft.instance, 1, world, x, y, z);
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
					entityItem.func_92014_d().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
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
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityEssentialReducer();
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.BLOCKS_PNG;
	}
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		this.doChecks(par1World, par2, par3, par4);
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		this.doChecks(par1World, par2, par3, par4);
	}
	
	public void doChecks(World par1World, int par2, int par3, int par4) {
		TileEntityEssentialReducer te = (TileEntityEssentialReducer)par1World.getBlockTileEntity(par2, par3, par4);
		if(te == null) return;
		te.doUpdateCheck = true;
	}
	
}
