package com.voidzm.supercraft.gen;

import java.util.Random;

import com.voidzm.supercraft.handler.BiomeHandler;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.ItemHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGoldenwoodShrine extends WorldGenerator {

	public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		if(!(var1.provider.getBiomeGenForCoords(var3, var5).equals(BiomeHandler.goldenwoodForest))) return false;
		if(var1.getBlockMaterial(var3, var4-1, var5) == Material.water) return false;
		int[] groundHeights = new int[25];
		int i = 0;
		for(int ix = -2; ix < 3; ix++) {
			for(int iz = -2; iz < 3; iz++) {
				int height = var1.getTopSolidOrLiquidBlock(var3+ix, var5+iz);
				groundHeights[i] = height;
				i++;
			}
		}
		int basis = groundHeights[12];
		int highest = 0;
		for(int index : groundHeights) {
			if(index > highest) highest = index;
			if(index != basis && index != basis+1 && index != basis-1) return false;
		}
		if(highest == basis+1) var4++;
		for(int ix = -2; ix < 3; ix++) {
			for(int iz = -2; iz < 3; iz++) {
				if(Math.abs(ix) == Math.abs(iz) && Math.abs(ix) == 2) continue;
				this.setBlock(var1, var3+ix, var4-1, var5+iz, this.randomlySelectIndefiniteBlock(var2));
			}
		}
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				if(Math.abs(ix) == Math.abs(iz) && Math.abs(ix) == 1) continue;
				this.setBlock(var1, var3+ix, var4-1, var5+iz, this.randomlySelectDefiniteBlock(var2));
			}
		}
		for(int ix = -2; ix < 3; ix++) {
			for(int iz = -2; iz < 3; iz++) {
				if(Math.abs(ix) == Math.abs(iz) && Math.abs(ix) == 2) continue;
				this.setBlock(var1, var3+ix, var4-2, var5+iz, (var1.getBlockId(var3+ix, var4-1, var5+iz) == Block.grass.blockID ? Block.dirt.blockID : BlockHandler.palestone.blockID));
			}
		}
		this.setBlock(var1, var3, var4-1, var5, BlockHandler.inscribedPalestone.blockID);
		int openSide = var2.nextInt(4);
		if(openSide != 0) {
			int height = var2.nextInt(3) + 1;
			for(int j = 1; j < 4; j++) {
				this.setBlock(var1, var3+3, var4-j, var5, BlockHandler.palestoneBricks.blockID);
			}
			for(int j = 0; j < height; j++) {
				this.setBlock(var1, var3+3, var4+j, var5, (j == height-1 ? BlockHandler.overgrownPalestoneBricks.blockID : BlockHandler.palestoneBricks.blockID));
			}
		}
		if(openSide != 1) {
			int height = var2.nextInt(3) + 1;
			for(int j = 1; j < 4; j++) {
				this.setBlock(var1, var3-3, var4-j, var5, BlockHandler.palestoneBricks.blockID);
			}
			for(int j = 0; j < height; j++) {
				this.setBlock(var1, var3-3, var4+j, var5, (j == height-1 ? BlockHandler.overgrownPalestoneBricks.blockID : BlockHandler.palestoneBricks.blockID));
			}
		}
		if(openSide != 2) {
			int height = var2.nextInt(3) + 1;
			for(int j = 1; j < 4; j++) {
				this.setBlock(var1, var3, var4-j, var5+3, BlockHandler.palestoneBricks.blockID);
			}
			for(int j = 0; j < height; j++) {
				this.setBlock(var1, var3, var4+j, var5+3, (j == height-1 ? BlockHandler.overgrownPalestoneBricks.blockID : BlockHandler.palestoneBricks.blockID));
			}
		}
		if(openSide != 3) {
			int height = var2.nextInt(3) + 1;
			for(int j = 1; j < 4; j++) {
				this.setBlock(var1, var3, var4-j, var5-3, BlockHandler.palestoneBricks.blockID);
			}
			for(int j = 0; j < height; j++) {
				this.setBlock(var1, var3, var4+j, var5-3, (j == height-1 ? BlockHandler.overgrownPalestoneBricks.blockID : BlockHandler.palestoneBricks.blockID));
			}
		}
		int height = var2.nextInt(3) + 1;
		for(int j = 1; j < 4; j++) {
			this.setBlock(var1, var3+2, var4-j, var5+2, BlockHandler.palestoneBricks.blockID);
		}
		for(int j = 0; j < height; j++) {
			this.setBlock(var1, var3+2, var4+j, var5+2, (j == height-1 ? BlockHandler.overgrownPalestoneBricks.blockID : BlockHandler.palestoneBricks.blockID));
		}
		height = var2.nextInt(3) + 1;
		for(int j = 1; j < 4; j++) {
			this.setBlock(var1, var3-2, var4-j, var5-2, BlockHandler.palestoneBricks.blockID);
		}
		for(int j = 0; j < height; j++) {
			this.setBlock(var1, var3-2, var4+j, var5-2, (j == height-1 ? BlockHandler.overgrownPalestoneBricks.blockID : BlockHandler.palestoneBricks.blockID));
		}
		height = var2.nextInt(3) + 1;
		for(int j = 1; j < 4; j++) {
			this.setBlock(var1, var3+2, var4-j, var5-2, BlockHandler.palestoneBricks.blockID);
		}
		for(int j = 0; j < height; j++) {
			this.setBlock(var1, var3+2, var4+j, var5-2, (j == height-1 ? BlockHandler.overgrownPalestoneBricks.blockID : BlockHandler.palestoneBricks.blockID));
		}
		height = var2.nextInt(3) + 1;
		for(int j = 1; j < 4; j++) {
			this.setBlock(var1, var3-2, var4-j, var5+2, BlockHandler.palestoneBricks.blockID);
		}
		for(int j = 0; j < height; j++) {
			this.setBlock(var1, var3-2, var4+j, var5+2, (j == height-1 ? BlockHandler.overgrownPalestoneBricks.blockID : BlockHandler.palestoneBricks.blockID));
		}
		this.setBlock(var1, var3, var4, var5, Block.chest.blockID);
		TileEntityChest chestTE = (TileEntityChest)var1.getBlockTileEntity(var3, var4, var5);
		int lootTableLength = var2.nextInt(4) + 4;
		ItemStack[] loot = new ItemStack[lootTableLength];
		for(int j = 0; j < lootTableLength; j++) {
			loot[j] = this.selectWeightedChestLoot(var2);
		}
		for(ItemStack stack : loot) {
			chestTE.setInventorySlotContents(var2.nextInt(chestTE.getSizeInventory()), stack);
		}
		if(var2.nextInt(2) == 0) chestTE.setInventorySlotContents(var2.nextInt(chestTE.getSizeInventory()), this.getOvergrowthBook());
		else chestTE.setInventorySlotContents(var2.nextInt(chestTE.getSizeInventory()), this.getGoldenwoodBook());
		return true;
	}
	
	private int randomlySelectDefiniteBlock(Random rand) {
		int selection = rand.nextInt(4);
		if(selection == 0) return BlockHandler.palestone.blockID;
		else if(selection != 3) return BlockHandler.overgrownPalestoneBricks.blockID;
		else return BlockHandler.palestoneBricks.blockID;
	}
	
	private int randomlySelectIndefiniteBlock(Random rand) {
		int selection = rand.nextInt(4);
		if(selection == 0) return BlockHandler.palestone.blockID;
		else if(selection == 1) return BlockHandler.overgrownPalestoneBricks.blockID;
		else if(selection == 2) return BlockHandler.palestoneBricks.blockID;
		else return Block.grass.blockID;
	}
	
	private ItemStack selectWeightedChestLoot(Random rand) {
		int selector = rand.nextInt(100);
		if(selector < 10) {
			return new ItemStack(ItemHandler.electrumIngot, rand.nextInt(3)+1);
		}
		else if(selector < 40) {
			return new ItemStack(ItemHandler.electrumBit, rand.nextInt(8)+1);
		}
		else if(selector < 70) {
			return new ItemStack(BlockHandler.palestoneBricks, rand.nextInt(4)+1);
		}
		else {
			return new ItemStack(BlockHandler.supercraftLog1, rand.nextInt(4)+1, 1);
		}
	}
	
	private ItemStack getOvergrowthBook() {
		ItemStack book = new ItemStack(Item.writtenBook, 1);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("author", "unknown");
		tag.setString("title", "Tome of Overgrowth");
		NBTTagList pages = new NBTTagList();
		NBTTagString page = new NBTTagString("page");
		page.data = "These bricks made from the pale stone seem to have aquired a faint moss. Perhaps this water has something to do with it.";
		pages.appendTag(page);
		tag.setTag("pages", pages);
		book.stackTagCompound = tag;
		return book;
	}
	
	private ItemStack getGoldenwoodBook() {
		ItemStack book = new ItemStack(Item.writtenBook, 1);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("author", "unknown");
		tag.setString("title", "Book of Goldenwood");
		NBTTagList pages = new NBTTagList();
		NBTTagString page = new NBTTagString("page");
		page.data = "When the mossy stones surround the four sides of the inscribed stone, and the wood of the golden trees finishes the square, the tiniest bit of the silvery gold will complete the matrix.";
		pages.appendTag(page);
		tag.setTag("pages", pages);
		book.stackTagCompound = tag;
		return book;
	}
	
}
