//**
//**  SupercraftModContainer.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.asm;

import java.io.File;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.eventbus.EventBus;
import com.voidzm.supercraft.Supercraft;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.MetadataCollection;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import cpw.mods.fml.common.versioning.InvalidVersionSpecificationException;
import cpw.mods.fml.common.versioning.VersionRange;

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
		return Supercraft.supercraftVersion;
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
		meta.version = Supercraft.supercraftVersion;
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
		return Supercraft.supercraftVersion;
	}

	@Override
	public VersionRange acceptableMinecraftVersionRange() {
		try {
			return VersionRange.createFromVersionSpec(Supercraft.minecraftVersion);
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
