package com.voidzm.supercraft.gui;

import java.util.ArrayList;
import java.util.Iterator;

import com.voidzm.supercraft.util.SorterSupercraftStatsBlock;

import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;

public class GuiSupercraftStatsBlockSlot extends GuiSupercraftStatsSlot {

	private GuiSupercraftStats parent;

	protected GuiSupercraftStatsBlockSlot(GuiSupercraftStats par1GuiStats) {
		super(par1GuiStats);
		this.parent = par1GuiStats;
		this.list = new ArrayList();
		Iterator iterator = StatList.objectMineStats.iterator();
		while(iterator.hasNext()) {
			StatCrafting statcrafting = (StatCrafting)iterator.next();
			boolean flag = false;
			int i = statcrafting.getItemID();
			if(GuiSupercraftStats.getStatsFileWriter(par1GuiStats).writeStat(statcrafting) > 0) {
				flag = true;
			}
			else if(StatList.objectUseStats[i] != null && GuiSupercraftStats.getStatsFileWriter(par1GuiStats).writeStat(StatList.objectUseStats[i]) > 0) {
				flag = true;
			}
			else if(StatList.objectCraftStats[i] != null && GuiSupercraftStats.getStatsFileWriter(par1GuiStats).writeStat(StatList.objectCraftStats[i]) > 0) {
				flag = true;
			}
			if(flag) {
				this.list.add(statcrafting);
			}
		}
		this.comparator = new SorterSupercraftStatsBlock(this, par1GuiStats);
	}

	protected void func_77222_a(int par1, int par2, Tessellator par3Tessellator) {
		super.func_77222_a(par1, par2, par3Tessellator);
		if(this.intG == 0) {
			GuiSupercraftStats.drawSprite(this.parent, par1 + 115 - 18 + 1, par2 + 1 + 1, 18, 18);
		}
		else {
			GuiSupercraftStats.drawSprite(this.parent, par1 + 115 - 18, par2 + 1, 18, 18);
		}
		if(this.intG == 1) {
			GuiSupercraftStats.drawSprite(this.parent, par1 + 165 - 18 + 1, par2 + 1 + 1, 36, 18);
		}
		else {
			GuiSupercraftStats.drawSprite(this.parent, par1 + 165 - 18, par2 + 1, 36, 18);
		}
		if(this.intG == 2) {
			GuiSupercraftStats.drawSprite(this.parent, par1 + 215 - 18 + 1, par2 + 1 + 1, 54, 18);
		}
		else {
			GuiSupercraftStats.drawSprite(this.parent, par1 + 215 - 18, par2 + 1, 54, 18);
		}
	}

	protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
		StatCrafting statcrafting = this.func_77257_d(par1);
		int i1 = statcrafting.getItemID();
		GuiSupercraftStats.drawItemSprite(this.parent, par2 + 40, par3, i1);
		this.func_77260_a((StatCrafting)StatList.objectCraftStats[i1], par2 + 115, par3, par1 % 2 == 0);
		this.func_77260_a((StatCrafting)StatList.objectUseStats[i1], par2 + 165, par3, par1 % 2 == 0);
		this.func_77260_a(statcrafting, par2 + 215, par3, par1 % 2 == 0);
	}

	protected String func_77258_c(int par1) {
		return par1 == 0 ? "stat.crafted" : (par1 == 1 ? "stat.used" : "stat.mined");
	}

}
