//////////////////////////////////////
//*        GuiHandler.java         *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

package com.voidzm.supercraft.handler;

import com.voidzm.supercraft.container.ContainerAlloyInductor;
import com.voidzm.supercraft.container.ContainerEssentialReducer;
import com.voidzm.supercraft.container.ContainerRefinedWorkbench;
import com.voidzm.supercraft.entity.TileEntityAlloyInductor;
import com.voidzm.supercraft.entity.TileEntityEssentialReducer;
import com.voidzm.supercraft.gui.GuiAlloyInductor;
import com.voidzm.supercraft.gui.GuiEssentialReducer;
import com.voidzm.supercraft.gui.SCGuiCrafting;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
			return new ContainerRefinedWorkbench(player.inventory, world, x, y, z);
		case 1:
			TileEntityEssentialReducer teEssentialReducer = (TileEntityEssentialReducer)world.getBlockTileEntity(x, y, z);
			return new ContainerEssentialReducer(player.inventory, teEssentialReducer);
		case 2:
			TileEntityAlloyInductor teAlloyInductor = (TileEntityAlloyInductor)world.getBlockTileEntity(x, y, z);
			return new ContainerAlloyInductor(player.inventory, teAlloyInductor);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
			return new SCGuiCrafting(player.inventory, world, x, y, z);
		case 1:
			TileEntityEssentialReducer teEssentialReducer = (TileEntityEssentialReducer)world.getBlockTileEntity(x, y, z);
			return new GuiEssentialReducer(player.inventory, teEssentialReducer);
		case 2:
			TileEntityAlloyInductor teAlloyInductor = (TileEntityAlloyInductor)world.getBlockTileEntity(x, y, z);
			return new GuiAlloyInductor(player.inventory, teAlloyInductor);
		default:
			return null;
		}
	}

}
