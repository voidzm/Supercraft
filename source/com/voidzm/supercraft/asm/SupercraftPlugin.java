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

@TransformerExclusions(value={"com.voidzm.supercraft.asm"})
@MCVersion(value="1.5.2")
public class SupercraftPlugin implements IFMLLoadingPlugin, IFMLCallHook {

	public static File location;
	public static boolean isDevEnvironment = true;
	
	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"com.voidzm.supercraft.asm.SupercraftTransformer"};
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
	}

	@Override
	public Void call() throws Exception {
		return null;
	}

}
