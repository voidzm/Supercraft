package com.voidzm.supercraft.util;

import java.util.ArrayList;

import net.minecraft.item.ItemBlock;

public class RegisterData {

	public String internalName;
	public String externalName;
	
	public boolean isMultiblock = false;
	
	public ArrayList<String> externalNames = null;
	public Class<? extends ItemBlock> itemBlockClass = null;
	
}
