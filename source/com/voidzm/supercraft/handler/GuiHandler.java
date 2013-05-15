//**
//**  GuiHandler.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.container.ContainerAlloyInductor;
import com.voidzm.supercraft.container.ContainerElectroplationEngine;
import com.voidzm.supercraft.container.ContainerEssentialReducer;
import com.voidzm.supercraft.container.ContainerRefinedWorkbench;
import com.voidzm.supercraft.container.ContainerVeneficianPodium;
import com.voidzm.supercraft.gui.GuiAlloyInductor;
import com.voidzm.supercraft.gui.GuiElectroplationEngine;
import com.voidzm.supercraft.gui.GuiEssentialReducer;
import com.voidzm.supercraft.gui.GuiVeneficianPodium;
import com.voidzm.supercraft.gui.SCGuiCrafting;
import com.voidzm.supercraft.tileentity.TileEntityAlloyInductor;
import com.voidzm.supercraft.tileentity.TileEntityElectroplationEngine;
import com.voidzm.supercraft.tileentity.TileEntityEssentialReducer;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {

	private static GuiHandler instance;

	public static GuiHandler instance() {
		if(instance == null) {
			instance = new GuiHandler();
		}
		return instance;
	}

	public static void init() {
		NetworkRegistry.instance().registerGuiHandler(Supercraft.instance, instance());
	}

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
		case 3:
			TileEntityElectroplationEngine teElectroplationEngine = (TileEntityElectroplationEngine)world.getBlockTileEntity(x, y, z);
			return new ContainerElectroplationEngine(player.inventory, teElectroplationEngine);
		case 4:
			return new ContainerVeneficianPodium(player.inventory, world, x, y, z, player);
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
		case 3:
			TileEntityElectroplationEngine teElectroplationEngine = (TileEntityElectroplationEngine)world.getBlockTileEntity(x, y, z);
			return new GuiElectroplationEngine(player.inventory, teElectroplationEngine);
		case 4:
			return new GuiVeneficianPodium(player.inventory, world, x, y, z);
		default:
			return null;
		}
	}

}
