//**
//**  BiomeProperties.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.biome;

import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;

import com.voidzm.supercraft.biome.BiomeStructureGen.StructureGenType;

public enum BiomeProperties {

	EXTREMEFOREST(1, 0, 2, 0.3F, 1.7F, 0.6F, 0.7F, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 5), new BiomeStructureGen(StructureGenType.BIRCHTREE, true, 1)),
	INSANEHILLS(1, 0, 2, 0.1F, 2.0F, 0.2F, 0.2F, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 1), new BiomeStructureGen(StructureGenType.BIRCHTREE, false, 5)),
	GRASSYSUMMITS(15, 0, 2, 1.8F, 1.8F, 0.7F, 0.3F, 0x94BA5B, 0x94BA5B, new BiomeStructureGen[]{}),
	WINTERFOREST(-999, 0, 2, 0.2F, 0.4F, 0.1F, 0.8F, 0x98D9D4, 0x98D9D4, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 5), new BiomeStructureGen(StructureGenType.BIGTREE, false, 2)),
	ALPHA(1, 12, 5, -0.2F, 0.2F, 0.6F, 0.9F, 0x6FDE50, 0x48B518, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 2)),
	SAVANNA(9, -999, 1, 0.1F, 0.12F, 0.7F, 0.2F, 0xADA263, 0xADA263, new BiomeStructureGen(StructureGenType.OLIVETREE, false, 5)),
	SANDYPEAKS(-999, 10, -999, 0.4F, 1.8F, 1.5F, 0.0F, new BiomeStructureGen[]{}),
	ICYRIDGES(-999, 0, 2, 0.5F, 1.8F, 0.1F, 0.8F, new BiomeStructureGen(StructureGenType.SPRUCETREE1, true, 4), new BiomeStructureGen(StructureGenType.SPRUCETREE2, true, 2)),
	GOLDENWOODFOREST(2, 8, -999, 0.4F, 0.55F, 0.5F, 0.5F, 0xEAEDD8, 0xCCE69A, new BiomeStructureGen(StructureGenType.GOLDENWOODSHRINE, false, 256), new BiomeStructureGen(StructureGenType.GOLDENWOODTREE, true, 5), new BiomeStructureGen(StructureGenType.BLUEBELLS, true, 8), new BiomeStructureGen(StructureGenType.DAISIES, true, 8)),
	TENEBRALWOODS(2, 1, -999, 0.4F, 0.5F, 0.5F, 0.5F, 0x525A5B, 0x525A5B, new BiomeStructureGen(StructureGenType.TENEBRALPITFALL, false, 128), new BiomeStructureGen(StructureGenType.TENEBRIATREE, true, 7), new BiomeStructureGen(StructureGenType.SNAPDRAGON, true, 12)),
	CORDIFERHILLS(2, 10, 3, 0.3F, 0.7F, 0.5F, 0.5F, new BiomeStructureGen(StructureGenType.BIRCHTREE, true, 6)),
	TEMPERATEFOREST(1, 10, 4, 0.2F, 0.5F, 0.4F, 0.6F, new BiomeStructureGen(StructureGenType.BIRCHTREE, true, 3), new BiomeStructureGen(StructureGenType.SMALLTREE, true, 6), new BiomeStructureGen(StructureGenType.BIGTREE, true, 1), new BiomeStructureGen(StructureGenType.SPRUCETREE2, true, 4)),
	GLACIALWASTELAND(-999, -999, -999, 0.1F, 0.3F, 0.0F, 0.3F, new BiomeStructureGen(StructureGenType.ICYSPIKES, false, 96)),
	JUNGLEMOUNTAINS(25, 2, 4, 0.8F, 2.0F, 1.2F, 0.9F, new BiomeStructureGen(StructureGenType.BIGJUNGLETREE, true, 4), new BiomeStructureGen(StructureGenType.JUNGLETREE, true, 7), new BiomeStructureGen(StructureGenType.BIGTREE, true, 3), new BiomeStructureGen(StructureGenType.JUNGLESHRUB, true, 30)),
	CONIFEROUSWOODS(2, 0, 1, 0.1F, 0.4F, 0.2F, 0.7F, new BiomeStructureGen(StructureGenType.SPRUCETREE1, true, 5), new BiomeStructureGen(StructureGenType.SPRUCETREE2, true, 5));
	
	public int grass;
	public int sugarCane;
	public int flowers;
	
	public float minHeight;
	public float maxHeight;
	public float temperature;
	public float rainfall;
	
	public int grassColor;
	public int leavesColor;
	
	public BiomeStructureGen[] gens;
	
	private BiomeProperties(int grassSpawn, int sugarCaneSpawn, int flowerSpawn, float min, float max, float temp, float rain, int grassCol, int foliageCol, BiomeStructureGen... generators) {
		this.grass = grassSpawn;
		this.sugarCane = sugarCaneSpawn;
		this.flowers = flowerSpawn;
		this.minHeight = min;
		this.maxHeight = max;
		this.temperature = temp;
		this.rainfall = rain;
		this.grassColor = grassCol;
		this.leavesColor = foliageCol;
		this.gens = generators;
	}
	
	private BiomeProperties(int grassSpawn, int sugarCaneSpawn, int flowerSpawn, float min, float max, float temp, float rain, BiomeStructureGen... generators) {
		this.grass = grassSpawn;
		this.sugarCane = sugarCaneSpawn;
		this.flowers = flowerSpawn;
		this.minHeight = min;
		this.maxHeight = max;
		this.temperature = temp;
		this.rainfall = rain;
		double d0 = (double)MathHelper.clamp_float(temp, 0.0F, 1.0F);
		double d1 = (double)MathHelper.clamp_float(rain, 0.0F, 1.0F);
		this.grassColor = ColorizerGrass.getGrassColor(d0, d1);
		this.leavesColor = ColorizerFoliage.getFoliageColor(d0, d1);	
		this.gens = generators;
	}
	
}
