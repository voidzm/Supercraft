//**
//**  ItemBlockSupercraft.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.item;

import java.util.ArrayList;

import com.voidzm.supercraft.block.BlockSupercraft;
import com.voidzm.supercraft.protocol.IRegisterable;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSupercraft extends ItemBlock {

	private int blockID;
	
	public ItemBlockSupercraft(int par1) {
		super(par1);
		this.blockID = par1 + 256;
		setHasSubtypes(true);
		setUnlocalizedName("conduit");
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	} 
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		Block block = Block.blocksList[this.blockID];
		if(block == null) return null;
		if(block instanceof BlockSupercraft) {
			BlockSupercraft supercraftBlock = (BlockSupercraft)block;
			ArrayList<String> names = supercraftBlock.fetchExternalNames();
			return this.getUnlocalizedName() + "." + names.get(itemstack.getItemDamage());
		}
		if(block instanceof IRegisterable) {
			ArrayList<String> names = ((IRegisterable)block).getRegisterData().externalNames;
			return this.getUnlocalizedName() + "." + names.get(itemstack.getItemDamage());
		}
		return null;
	}
	
}
