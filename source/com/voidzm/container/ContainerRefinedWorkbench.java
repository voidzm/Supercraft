//////////////////////////////////////
//* ContainerRefinedWorkbench.java *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.container;

import com.voidzm.supercraft.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class ContainerRefinedWorkbench extends ContainerWorkbench {

	private World world;
	private int xPos;
	private int yPos;
	private int zPos;
	
	public ContainerRefinedWorkbench(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
		super(par1InventoryPlayer, par2World, par3, par4, par5);
		this.world = par2World;
		this.xPos = par3;
		this.yPos = par4;
		this.zPos = par5;
		System.out.println("Created container.");
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		boolean test = this.world.getBlockId(this.xPos, this.yPos, this.zPos) != BlockHandler.refinedCraftingTable.blockID ? false : par1EntityPlayer.getDistanceSq((double)this.xPos + 0.5D, (double)this.yPos + 0.5D, (double)this.zPos + 0.5D) <= 64.0D;
		System.out.println("Checking interaction: "+test);
		return test;
	}

}
