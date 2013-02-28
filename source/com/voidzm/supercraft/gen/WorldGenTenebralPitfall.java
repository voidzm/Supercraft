package com.voidzm.supercraft.gen;

import java.util.Random;

import com.voidzm.supercraft.biome.BiomeGenGoldenwoodForest;
import com.voidzm.supercraft.biome.BiomeGenTenebralWoods;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.ItemHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTenebralPitfall extends WorldGenerator {

	public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		if(!(var1.provider.getBiomeGenForCoords(var3, var5) instanceof BiomeGenTenebralWoods)) return false;
		if(var1.getBlockMaterial(var3, var4-1, var5) == Material.water) return false;
		int[] groundHeights = new int[9];
		int i = 0;
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				int height = var1.getTopSolidOrLiquidBlock(var3+ix, var5+iz);
				groundHeights[i] = height;
				i++;
			}
		}
		int basis = groundHeights[4];
		for(int index : groundHeights) {
			if(index != basis && index != basis+1 && index != basis-1) return false;
		}
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				int depth = 16;
				if(Math.abs(ix) == 1 && Math.abs(iz) == 1) depth = var2.nextInt(5) + 4; 
				else if(Math.abs(ix) != 0 || Math.abs(iz) != 0) depth = var2.nextInt(5) + 9;
				for(int iy = -15; iy < 1; iy++) {
					if(depth == 16) {
						var1.setBlock(var3+ix, var4-16, var5+iz, BlockHandler.inscribedNightrock.blockID);
					}
					if(iy+depth < 0) var1.setBlock(var3+ix, var4+iy, var5+iz, BlockHandler.nightrockBricks.blockID);
					else if(iy+depth == 0) var1.setBlock(var3+ix, var4+iy, var5+iz, BlockHandler.burnedNightrock.blockID);
					else var1.setBlock(var3+ix, var4+iy, var5+iz, 0);
				}
			}
		}
		for(int iy = -15; iy < 1; iy++) {
			for(int iz = -1; iz < 2; iz++) {
				if(var1.getBlockId(var3+2, var4+iy, var5+iz) == Block.stone.blockID && var2.nextInt(2) == 0) {
					var1.setBlock(var3+2, var4+iy, var5+iz, BlockHandler.burnedNightrock.blockID);
				}
			}
		}
		for(int iy = -15; iy < 1; iy++) {
			for(int iz = -1; iz < 2; iz++) {
				if(var1.getBlockId(var3-2, var4+iy, var5+iz) == Block.stone.blockID && var2.nextInt(2) == 0) {
					var1.setBlock(var3-2, var4+iy, var5+iz, BlockHandler.burnedNightrock.blockID);
				}
			}
		}
		for(int iy = -15; iy < 1; iy++) {
			for(int ix = -1; ix < 2; ix++) {
				if(var1.getBlockId(var3+ix, var4+iy, var5+2) == Block.stone.blockID && var2.nextInt(2) == 0) {
					var1.setBlock(var3+ix, var4+iy, var5+2, BlockHandler.burnedNightrock.blockID);
				}
			}
		}
		for(int iy = -15; iy < 1; iy++) {
			for(int ix = -1; ix < 2; ix++) {
				if(var1.getBlockId(var3+ix, var4+iy, var5-2) == Block.stone.blockID && var2.nextInt(2) == 0) {
					var1.setBlock(var3+ix, var4+iy, var5-2, BlockHandler.burnedNightrock.blockID);
				}
			}
		}
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				if(!(ix == 0 && iz == 0)) var1.setBlock(var3+ix, var4-1, var5+iz, BlockHandler.burnedNightrock.blockID);
			}
		}
		var1.setBlock(var3, var4-15, var5, Block.chest.blockID);
		TileEntityChest chestTE = (TileEntityChest)var1.getBlockTileEntity(var3, var4-15, var5);
		int lootTableLength = var2.nextInt(4) + 4;
		ItemStack[] loot = new ItemStack[lootTableLength];
		for(int j = 0; j < lootTableLength; j++) {
			loot[j] = this.selectWeightedChestLoot(var2);
		}
		for(ItemStack stack : loot) {
			chestTE.setInventorySlotContents(var2.nextInt(chestTE.getSizeInventory()), stack);
		}
		chestTE.setInventorySlotContents(var2.nextInt(chestTE.getSizeInventory()), this.getTenebralBook());
		return true;
	}
	
	private ItemStack selectWeightedChestLoot(Random rand) {
		int selector = rand.nextInt(100);
		if(selector < 10) {
			return new ItemStack(ItemHandler.nisilIngot, rand.nextInt(3)+1);
		}
		else if(selector < 20) {
			return new ItemStack(ItemHandler.bloodAmber, rand.nextInt(3)+1);
		}
		else if(selector < 40) {
			return new ItemStack(ItemHandler.nisilShard, rand.nextInt(8)+1);
		}
		else if(selector < 70) {
			return new ItemStack(BlockHandler.nightrockBricks, rand.nextInt(4)+1);
		}
		else {
			return new ItemStack(BlockHandler.supercraftLog, rand.nextInt(4)+1, 2);
		}
	}
	
	private ItemStack getTenebralBook() {
		ItemStack book = new ItemStack(Item.writtenBook, 1);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("author", "unknown");
		tag.setString("title", "Tenebral Volume");
		NBTTagList pages = new NBTTagList();
		NBTTagString page = new NBTTagString("page");
		page.data = "...four bricks of darkened rock, surrounding a black monolith inscribed with ancient runes of Aeina. These are framed in a square with...of the dark trees. A piece of the forest's blood was inserted into the stone and...";
		pages.appendTag(page);
		tag.setTag("pages", pages);
		book.stackTagCompound = tag;
		return book;
	}
	
}
