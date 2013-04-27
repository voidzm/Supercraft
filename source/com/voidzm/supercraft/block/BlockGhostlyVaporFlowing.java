package com.voidzm.supercraft.block;

import com.voidzm.supercraft.protocol.IRegisterable;
import com.voidzm.supercraft.util.RegisterData;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockGhostlyVaporFlowing extends BlockFlowing implements IRegisterable {

	public static Icon stillVapor;
	public static Icon flowingVapor;
	
	private RegisterData rdata = new RegisterData();
	
	public BlockGhostlyVaporFlowing(int par1) {
		super(par1, Material.water);
		this.setHardness(100.0F);
		this.setLightValue(0.375F);
		this.setUnlocalizedName("ghostlyvaporflowing");
		this.disableStats();
		this.rdata.internalName = "ghostlyvaporflowing";
		this.rdata.externalName = "Ghostly Vapor";
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		stillVapor = par1IconRegister.registerIcon("supercraft:ghostlyvapor");
		flowingVapor = par1IconRegister.registerIcon("supercraft:ghostlyvapor_flow");
	}
	
	@SideOnly(Side.CLIENT)
	public static Icon func_94424_b(String par0Str) {
		return flowingVapor;
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		return par1 != 0 && par1 != 1 ? flowingVapor : stillVapor;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(par5Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)par5Entity;
			if(player.capabilities.isCreativeMode) return;
			player.addPotionEffect(new PotionEffect(14, 100, 0));
			player.addPotionEffect(new PotionEffect(15, 100, 0));
		}
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}

}
