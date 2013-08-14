//**
//**  SupercraftTransformer.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.asm;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

public class SupercraftTransformer extends AccessTransformer {

	private static SupercraftTransformer instance;
	private static List<String> mapFiles = new LinkedList<String>();
	
	public SupercraftTransformer() throws IOException {
		super();
		instance = this;
		mapFiles.add("supercraft_at.cfg");
		for(String map : mapFiles) {
			this.readMapFile(map);
		}
		mapFiles = null;
	}

	private void readMapFile(String map) {
		System.out.println("Adding Access Transformer: "+map);
		try {
			Method parentMap = AccessTransformer.class.getDeclaredMethod("readMapFile", String.class);
			parentMap.setAccessible(true);
			parentMap.invoke(this, map);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		try {
			bytes = this.transformGenLayerRiverMix(name, bytes);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		return bytes;
	}
	
	private byte[] transformGenLayerRiverMix(String name, byte[] bytes) { // Forces FrozenRiver generation in frozen biomes, since that usually only happens in Ice Plains.
		String className = SupercraftPlugin.isDevEnvironment ? "net.minecraft.world.gen.layer.GenLayerRiverMix" : ReobfuscationMappingHelper.getInstance().attemptRemapClassName("net.minecraft.world.gen.layer.GenLayerRiverMix");
		if(name.equals(className)) {
			ClassReader cr = new ClassReader(bytes);
			ClassWriter cw = new ClassWriter(0);
			String methodName = SupercraftPlugin.isDevEnvironment ? "getInts" : ReobfuscationMappingHelper.getInstance().attemptRemapMethodName("net.minecraft.world.gen.layer.GenLayerRiverMix/getInts");
			String methodDesc = "(IIII)[I";
			ClassMethodVisitor cv = new ClassMethodVisitor(cw, methodName, methodDesc, RiverGetIntsMethodVisitor.class);
			cr.accept(cv, 0);
			bytes = cw.toByteArray();
		}
		return bytes;
	}

}
