package com.voidzm.supercraft.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.handler.BlockHandler;
import com.voidzm.supercraft.util.VeneficianProperties;
import com.voidzm.supercraft.util.VeneficianProperties.VeneficiaMaterial;
import com.voidzm.supercraft.util.VeneficianProperties.VeneficiaType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemVeneficiaCell extends ItemSupercraft {
	
	public ItemVeneficiaCell(int par1) {
		super(par1, "supercraft:veneficiacell_lithium");
		this.setMaxStackSize(1);
		this.setInternalName("veneficiacell");
		this.setExternalName("Veneficia Cell");
		this.setCreativeTab(Supercraft.veneficiaTab);
		this.setMaxDamage(0);
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack) {
		VeneficianProperties prop = VeneficianProperties.readFromItemStack(par1ItemStack);
		int vitality = VeneficianProperties.getVitalityIndex(prop.vitality);
		int perception = VeneficianProperties.getPerceptionIndex(prop.perception);
		int energy = VeneficianProperties.getEnergyIndex(prop.energy);
		if(prop.aspect == VeneficiaType.EMPTY){
			return VeneficianProperties.materialNames[prop.material.index] + " Veneficia Cell";
		}
		return VeneficianProperties.materialNames[prop.material.index] + " Cell of " + VeneficianProperties.aspectNames[prop.aspect.index];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		ItemStack aluminum = new ItemStack(itemID, 1, 0);
		VeneficianProperties prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.ALUMINUM);
		list.add(prop.applyProperties(aluminum));
		
		ItemStack copper = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.COPPER);
		list.add(prop.applyProperties(copper));
		
		ItemStack silver = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.SILVER);
		list.add(prop.applyProperties(silver));
		
		ItemStack gold = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.GOLD);
		list.add(prop.applyProperties(gold));
		
		ItemStack electrum = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.ELECTRUM);
		list.add(prop.applyProperties(electrum));
		
		ItemStack nisil = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.NISIL);
		list.add(prop.applyProperties(nisil));
		
		ItemStack cobalt = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.COBALT);
		list.add(prop.applyProperties(cobalt));
		
		ItemStack platinum = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.PLATINUM);
		list.add(prop.applyProperties(platinum));
		
		ItemStack lithium = new ItemStack(itemID, 1, 0);
		prop = VeneficianProperties.newEmptyProperties(VeneficiaMaterial.LITHIUM);
		list.add(prop.applyProperties(lithium));
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		VeneficianProperties prop = VeneficianProperties.readFromItemStack(par1ItemStack);
		if(prop == null) return par1ItemStack;
		prop.runStabilityUpdate();
		prop.applyProperties(par1ItemStack);
		return par1ItemStack;
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		int block = par3World.getBlockId(par4, par5, par6);
		if(block == BlockHandler.deadSpawner.blockID) { // Attempt Veneficia injection.
			VeneficianProperties prop = VeneficianProperties.readFromItemStack(par1ItemStack);
			if(prop == null) {
				return false;
			}
			if(prop.aspect == VeneficiaType.EMPTY) {
				return false;
			}
			if(prop.aspect == VeneficiaType.VOID) {
				prop.aspect = VeneficiaType.EMPTY;
				prop.applyProperties(par1ItemStack);
				if(par3World.isRemote) par2EntityPlayer.sendChatToPlayer("Vacuum dispersed.");
				return true;
			}
			par3World.setBlock(par4, par5, par6, Block.mobSpawner.blockID);
			MobSpawnerBaseLogic baseLogic = ((TileEntityMobSpawner)par3World.getBlockTileEntity(par4, par5, par6)).func_98049_a();
			baseLogic.mobID = prop.aspect.entityName;
			baseLogic.spawnCount = prop.vitality;
			baseLogic.maxNearbyEntities = (int)((double)prop.vitality * 1.5);
			baseLogic.spawnRange = prop.perception;
			baseLogic.activatingRangeFromPlayer = prop.perception * 4;
			int medianSpawnTime = prop.energy * 50;
			int minSpawnTime = medianSpawnTime - 300;
			int maxSpawnTime = medianSpawnTime + 300;
			if(minSpawnTime < 1) minSpawnTime = 1;
			baseLogic.minSpawnDelay = minSpawnTime;
			baseLogic.maxSpawnDelay = maxSpawnTime;
			if(par3World.isRemote) par2EntityPlayer.sendChatToPlayer("Veneficia successfully injected.");
			VeneficianProperties newProp = VeneficianProperties.newEmptyProperties(prop.material);
			newProp.applyProperties(par1ItemStack);
			return true;
		}
		else if(block == Block.mobSpawner.blockID) { // Attempt Veneficia extraction.
			VeneficianProperties prop = VeneficianProperties.readFromItemStack(par1ItemStack);
			if(prop == null) {
				return false;
			}
			if(prop.aspect == VeneficiaType.EMPTY) {
				if(par3World.isRemote) par2EntityPlayer.sendChatToPlayer("Veneficia requires a vacuum in order to be extracted.");
				return false;
			}
			if(prop.aspect == VeneficiaType.VOID) {
				MobSpawnerBaseLogic baseLogic = ((TileEntityMobSpawner)par3World.getBlockTileEntity(par4, par5, par6)).func_98049_a();
				boolean typeLocated = false;
				for(VeneficiaType type : VeneficiaType.values()) {
					if(type.entityName.equals(baseLogic.mobID)) {
						prop.aspect = type;
						typeLocated = true;
						break;
					}
				}
				if(typeLocated == false) {
					if(par3World.isRemote) par2EntityPlayer.sendChatToPlayer("This type of Veneficia could not be extracted.");
					return false;
				}
				prop.vitality = baseLogic.spawnCount;
				prop.perception = baseLogic.spawnRange;
				int medianSpawnTime = (baseLogic.maxSpawnDelay + baseLogic.minSpawnDelay) / 2;
				prop.energy = medianSpawnTime / 50;
				prop.stability = 0;
				if(par3World.isRemote) par2EntityPlayer.sendChatToPlayer("Veneficia successfully extracted.");
				prop.applyProperties(par1ItemStack);
				return true;
			}
			else {
				if(par3World.isRemote) par2EntityPlayer.sendChatToPlayer("This cell is already filled with Veneficia.");
				return false;
			}
		}
		else if(block == Block.bedrock.blockID) {
			if(par5 == 0 || par5 == 127) {
				VeneficianProperties prop = VeneficianProperties.readFromItemStack(par1ItemStack);
				if(prop == null) {
					return false;
				}
				if(prop.aspect == VeneficiaType.EMPTY) {
					prop.aspect = VeneficiaType.VOID;
					prop.applyProperties(par1ItemStack);
					if(par3World.isRemote) par2EntityPlayer.sendChatToPlayer("Matter destroyed.");
					return true;
				}
				if(prop.aspect == VeneficiaType.VOID) {
					if(par3World.isRemote) par2EntityPlayer.sendChatToPlayer("This cell is already devoid of matter.");
					return true;
				}
				else {
					if(par3World.isRemote) par2EntityPlayer.sendChatToPlayer("This cell is already filled with Veneficia.");
					return false;
				}
			}
		}
		return true;
	}
	
}
