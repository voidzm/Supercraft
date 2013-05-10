package com.voidzm.supercraft.asm;

import java.io.IOException;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;
import cpw.mods.fml.relauncher.IClassTransformer;

public class SupercraftTransformer extends AccessTransformer {

	public SupercraftTransformer() throws IOException {
		super();
	}

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		return bytes;
	}

}
