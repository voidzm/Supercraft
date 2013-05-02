package com.voidzm.supercraft.util;

public class TransformationMatrix {

	private int targetBlockID;
	private int targetBlockMetadata;
	private int secondaryBlockID;
	private int secondaryBlockMetadata;
	private int tertiaryBlockID;
	private int tertiaryBlockMetadata;
	
	private int newTargetBlockID;
	private int newTargetBlockMetadata;
	private int newSecondaryBlockID;
	private int newSecondaryBlockMetadata;
	private int newTertiaryBlockID;
	private int newTertiaryBlockMetadata;
	
	public TransformationMatrix(int par1, int par2, int par3, int par4, int par5, int par6, int par7, int par8, int par9, int par10, int par11, int par12) {
		this.targetBlockID = par1;
		this.targetBlockMetadata = par2;
		this.secondaryBlockID = par3;
		this.secondaryBlockMetadata = par4;
		this.tertiaryBlockID = par5;
		this.tertiaryBlockMetadata = par6;
		this.newTargetBlockID = par7;
		this.newTargetBlockMetadata = par8;
		this.newSecondaryBlockID = par9;
		this.newSecondaryBlockMetadata = par10;
		this.newTertiaryBlockID = par11;
		this.newTertiaryBlockMetadata = par12;
	}

	public int getTargetBlockID() {
		return targetBlockID;
	}

	public int getTargetBlockMetadata() {
		return targetBlockMetadata;
	}

	public int getSecondaryBlockID() {
		return secondaryBlockID;
	}

	public int getSecondaryBlockMetadata() {
		return secondaryBlockMetadata;
	}

	public int getTertiaryBlockID() {
		return tertiaryBlockID;
	}

	public int getTertiaryBlockMetadata() {
		return tertiaryBlockMetadata;
	}

	public int getNewTargetBlockID() {
		return newTargetBlockID;
	}

	public int getNewTargetBlockMetadata() {
		return newTargetBlockMetadata;
	}

	public int getNewSecondaryBlockID() {
		return newSecondaryBlockID;
	}

	public int getNewSecondaryBlockMetadata() {
		return newSecondaryBlockMetadata;
	}

	public int getNewTertiaryBlockID() {
		return newTertiaryBlockID;
	}

	public int getNewTertiaryBlockMetadata() {
		return newTertiaryBlockMetadata;
	}
	
}
