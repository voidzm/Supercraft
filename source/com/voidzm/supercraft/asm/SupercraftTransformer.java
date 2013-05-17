//**
//**  SupercraftTransformer.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.asm;

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
	
	private byte[] transformMinecraft(String name, byte[] bytes) { // Prevents GuiMainMenu from ever displaying, using GuiSupercraftMainMenu instead.
		String className = SupercraftPlugin.isDevEnvironment ? "net.minecraft.client.Minecraft" : ReobfuscationMappingHelper.getInstance().attemptRemapClassName("net.minecraft.client.Minecraft");
		if(name.equals(className)) {
			ClassReader cr = new ClassReader(bytes);
			ClassWriter cw = new ClassWriter(0);
			String methodName = SupercraftPlugin.isDevEnvironment ? "displayGuiScreen" : ReobfuscationMappingHelper.getInstance().attemptRemapMethodName("net.minecraft.client.Minecraft/displayGuiScreen");
			String methodDesc = SupercraftPlugin.isDevEnvironment ? "(Lnet/minecraft/client/gui/GuiScreen;)V" : "(L"+ReobfuscationMappingHelper.getInstance().attemptRemapClassName("net.minecraft.client.gui.GuiScreen")+";)V";
			ClassMethodVisitor cv = new ClassMethodVisitor(cw, methodName, methodDesc, DisplayGuiScreenMethodVisitor.class);
			cr.accept(cv, 0);
			bytes = cw.toByteArray();
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
