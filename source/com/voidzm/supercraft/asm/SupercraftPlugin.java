//**
//**  SupercraftPlugin.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.asm;

import java.io.File;
import java.util.Map;

import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

import com.voidzm.novamenu.asm.NovamenuPlugin;

@TransformerExclusions(value={"com.voidzm.supercraft.asm", "com.voidzm.supercraft.gui", "com.voidzm.novamenu.asm", "com.voidzm.novamenu.gui"})
@MCVersion(value="1.6.2")
public class SupercraftPlugin implements IFMLLoadingPlugin, IFMLCallHook {

	public static File location;
	public static boolean isDevEnvironment = true;
	
	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"com.voidzm.supercraft.asm.SupercraftTransformer", "com.voidzm.novamenu.asm.NovamenuTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return "com.voidzm.supercraft.asm.SupercraftModContainer";
	}

	@Override
	public String getSetupClass() {
		return "com.voidzm.supercraft.asm.SupercraftPlugin";
	}

	@Override
	public void injectData(Map<String, Object> data) {
		if(data.containsKey("coremodLocation")) location = (File)data.get("coremodLocation");
		if(data.containsKey("runtimeDeobfuscationEnabled")) isDevEnvironment = !(Boolean)data.get("runtimeDeobfuscationEnabled");
		NovamenuPlugin.instance().injectData(data);
	}

	@Override
	public Void call() throws Exception {
		return null;
	}

}
