//**
//**  GuiSupercraftCreateFlatWorld.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.gui;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.StatCollector;
import net.minecraft.world.gen.FlatGeneratorInfo;

public class GuiSupercraftCreateFlatWorld extends GuiSupercraftScreen {

	private static RenderItem renderItem = new RenderItem();
	private final GuiSupercraftCreateWorld parent;
	private FlatGeneratorInfo genInfo = FlatGeneratorInfo.getDefaultFlatGenerator();
	private String customizationTitle;
	private String layerMaterialLabel;
	private String heightLabel;
	private GuiSupercraftFlatWorldListSlot listSlot;
	private GuiButtonTransparent buttonRemoveLayer;
	
	public GuiSupercraftCreateFlatWorld(GuiSupercraftCreateWorld createWorld, String string) {
		this.parent = createWorld;
		this.imageTick = createWorld.imageTick;
		this.setFlatGeneratorInfo(string);
	}

	public String getFlatGeneratorInfo() {
		return this.genInfo.toString();
	}

	public void setFlatGeneratorInfo(String par1Str) {
		this.genInfo = FlatGeneratorInfo.createFlatGeneratorFromString(par1Str);
	}
	
	@Override
	public void initGui() {
		this.buttons.clear();
		this.customizationTitle = StatCollector.translateToLocal("createWorld.customize.flat.title");
		this.layerMaterialLabel = StatCollector.translateToLocal("createWorld.customize.flat.tile");
		this.heightLabel = StatCollector.translateToLocal("createWorld.customize.flat.height");
		this.listSlot = new GuiSupercraftFlatWorldListSlot(this);
		this.listSlot.boxshift = 1;
		this.buttons.add(this.buttonRemoveLayer = new GuiButtonTransparent(this, this.width / 2 - 155, this.height - 52, 150, 20, 4, StatCollector.translateToLocal("createWorld.customize.flat.removeLayer")));
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 - 155, this.height - 28, 150, 16, 0, StatCollector.translateToLocal("gui.done")));
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 + 5, this.height - 52, 150, 16, 5, StatCollector.translateToLocal("createWorld.customize.presets")));
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 + 5, this.height - 28, 150, 16, 1, StatCollector.translateToLocal("gui.cancel")));
		this.genInfo.func_82645_d();
		this.func_82270_g();
	}

	@Override
	public void buttonEvent(int id) {
		int i = this.genInfo.getFlatLayers().size() - this.listSlot.field_82454_a - 1;
		switch(id) {
		case 0:
			this.parent.genOptions = this.getFlatGeneratorInfo();
			this.parent.imageTick = this.imageTick;
			this.mc.displayGuiScreen(this.parent);
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 1:
			this.parent.imageTick = this.imageTick;
			this.mc.displayGuiScreen(this.parent);
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 4:
			if(this.func_82272_i()) {
				this.genInfo.getFlatLayers().remove(i);
				this.listSlot.field_82454_a = Math.min(this.listSlot.field_82454_a, this.genInfo.getFlatLayers().size() - 1);
			}
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 5:
			this.mc.displayGuiScreen(new GuiSupercraftFlatPresets(this));
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
		}
		this.genInfo.func_82645_d();
		this.func_82270_g();
	}
	
	public void func_82270_g() {
		boolean flag = this.func_82272_i();
		this.buttonRemoveLayer.enabled = flag;
	}

	private boolean func_82272_i() {
		return this.listSlot.field_82454_a > -1 && this.listSlot.field_82454_a < this.genInfo.getFlatLayers().size();
	}

	@Override
	public void drawScreenForeground(int par1, int par2, float par3) {
		this.drawRect(0, 0, width, height, 0x88000000);
		this.drawRect(0, 0, width, 43, 0x77000000);
		this.drawRect(0, height-60, width, height, 0x77000000);
		this.drawRect(0, 43, width, 44, 0xAA000000);
		this.drawRect(0, height-59, width, height-60, 0xAA000000);
		super.drawScreenForeground(par1, par2, par3);
		this.listSlot.drawScreen(par1, par2, par3);
		this.drawCenteredString(this.fontRenderer, this.customizationTitle, this.width / 2, 8, 16777215);
		int k = this.width / 2 - 92 - 16;
		this.drawString(this.fontRenderer, this.layerMaterialLabel, k, 32, 16777215);
		this.drawString(this.fontRenderer, this.heightLabel, k + 2 + 213 - this.fontRenderer.getStringWidth(this.heightLabel), 32, 16777215);
	}

	public static RenderItem getRenderItem(){
		return renderItem;
	}

	public static FlatGeneratorInfo func_82271_a(GuiSupercraftCreateFlatWorld par0GuiCreateFlatWorld) {
		return par0GuiCreateFlatWorld.genInfo;
	}
}
