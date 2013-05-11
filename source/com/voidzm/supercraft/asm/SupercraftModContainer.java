package com.voidzm.supercraft.asm;

import java.io.File;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.voidzm.supercraft.CommonProxy;
import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.client.ClientProxy;
import com.voidzm.supercraft.gen.WorldGenOre;
import com.voidzm.supercraft.handler.BiomeHandler;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.handler.CraftingHandler;
import com.voidzm.supercraft.handler.DimensionHandler;
import com.voidzm.supercraft.handler.EventHandler;
import com.voidzm.supercraft.handler.FuelHandler;
import com.voidzm.supercraft.handler.GuiHandler;
import com.voidzm.supercraft.handler.ItemHandler;
import com.voidzm.supercraft.handler.ServerTickHandler;
import com.voidzm.supercraft.handler.TileEntityHandler;
import com.voidzm.supercraft.util.StartupStats;
import com.voidzm.supercraft.util.SupercraftConfiguration;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.MetadataCollection;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import cpw.mods.fml.common.versioning.InvalidVersionSpecificationException;
import cpw.mods.fml.common.versioning.VersionRange;
import cpw.mods.fml.relauncher.Side;

public class SupercraftModContainer extends DummyModContainer {
	
	private ArtifactVersion processedVersion;
	
	@Override
	public String getModId() {
		return "SupercraftCore";
	}

	@Override
	public String getName() {
		return "Supercraft Core";
	}

	@Override
	public String getVersion() {
		return "0.4.0";
	}

	@Override
	public File getSource() {
		return SupercraftPlugin.location;
	}

	@Override
	public ModMetadata getMetadata() {
		ModMetadata meta = new ModMetadata();
		meta.modId = "SupercraftCore";
		meta.name = "Supercraft Core";
		meta.version = "0.4.0";
		meta.authorList = Arrays.asList("voidzm");
		meta.description = "The coremod functionality of Supercraft.";
		meta.credits = "Designed and coded by voidzm.";
		return meta;
	}

	@Override
	public void bindMetadata(MetadataCollection mc) {}

	@Override
	public void setEnabledState(boolean enabled) {}

	@Override
	public Set<ArtifactVersion> getRequirements() {
		return Collections.emptySet();
	}

	@Override
	public List<ArtifactVersion> getDependencies() {
		return Collections.emptyList();
	}

	@Override
	public List<ArtifactVersion> getDependants() {
		return Collections.emptyList();
	}

	@Override
	public String getSortingRules() {
		return "";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Override
	public boolean matches(Object mod) {
		return false;
	}

	@Override
	public Object getMod() {
		return null;
	}

	@Override
	public ArtifactVersion getProcessedVersion() {
		if(processedVersion == null) {
			processedVersion = new DefaultArtifactVersion(getModId(), getVersion());
		}
		return processedVersion;
	}

	@Override
	public boolean isImmutable() {
		return false;
	}

	@Override
	public boolean isNetworkMod() {
		return true;
	}

	@Override
	public String getDisplayVersion() {
		return "0.4.0";
	}

	@Override
	public VersionRange acceptableMinecraftVersionRange() {
		try {
			return VersionRange.createFromVersionSpec("1.5.2");
		} catch (InvalidVersionSpecificationException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Certificate getSigningCertificate() {
		return null;
	}

}
