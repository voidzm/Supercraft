//**
//**  SupercraftTransformer.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.asm;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;
import cpw.mods.fml.relauncher.FMLRelauncher;

public class SupercraftTransformer extends AccessTransformer {

	public SupercraftTransformer() throws IOException {
		super();
	}

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		try {
			if(FMLRelauncher.side().equals("CLIENT")) {
				bytes = this.transformMinecraft(name, bytes);
			}
			bytes = this.transformGenLayerRiverMix(name, bytes);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		return bytes;
	}
	
	private byte[] transformMinecraft(String name, byte[] bytes) { // Prevents GuiMainMenu from ever displaying, using SCMainMenu instead.
		if(name.equals("net.minecraft.client.Minecraft")) {
			ClassReader cr = new ClassReader(bytes);
			ClassWriter cw = new ClassWriter(COMPUTE_FRAMES | COMPUTE_MAXS);
			ClassMethodVisitor cv = new ClassMethodVisitor(cw, "displayGuiScreen", DisplayGuiScreenMethodVisitor.class);
			cr.accept(cv, 0);
			bytes = cw.toByteArray();
		}
		return bytes;
	}
	
	private byte[] transformGenLayerRiverMix(String name, byte[] bytes) { // Forces FrozenRiver generation in frozen biomes, since that usually only happens in Ice Plains.
		if(name.equals("net.minecraft.world.gen.layer.GenLayerRiverMix")) {
			ClassReader cr = new ClassReader(bytes);
			ClassWriter cw = new ClassWriter(COMPUTE_FRAMES | COMPUTE_MAXS);
			ClassMethodVisitor cv = new ClassMethodVisitor(cw, "getInts", RiverGetIntsMethodVisitor.class);
			cr.accept(cv, 0);
			bytes = cw.toByteArray();
		}
		return bytes;
	}

}
