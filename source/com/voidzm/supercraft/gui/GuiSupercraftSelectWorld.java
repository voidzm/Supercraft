//**
//**  GuiSupercraftSelectWorld.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;

public class GuiSupercraftSelectWorld extends GuiSupercraftScreen {
	
	private final DateFormat dateFormat = new SimpleDateFormat();
	private GuiSupercraftMainMenu parent;
	private String title;
	private boolean selected = false;
	private int selectedWorld;
	private List saveList;
	private GuiSupercraftWorldSlot worldSlotContainer;
	
	private String localizedWorldText;
	private String localizedConvertText;
	
	private String[] localizedGamemodeText = new String[3];
	
	private boolean deleting;
	
	private GuiButtonTransparent buttonDelete;
	private GuiButtonTransparent buttonSelect;
	private GuiButtonTransparent buttonRename;
	private GuiButtonTransparent buttonRecreate;
	
	public GuiSupercraftSelectWorld(GuiSupercraftMainMenu parentScreen) {
		this.parent = parentScreen;
		imageTick = parentScreen.imageTick;
	}
	
	@Override
	public void initGui() {
		StringTranslate t = StringTranslate.getInstance();
		this.title = t.translateKey("selectWorld.title");
		try {
			this.loadSaves();
		} catch(AnvilConverterException e) {
			e.printStackTrace();
			this.mc.displayGuiScreen(new GuiErrorScreen("Unable to load worlds!", e.getMessage()));
			return;
		}
		this.localizedWorldText = t.translateKey("selectWorld.world");
		this.localizedConvertText = t.translateKey("selectWorld.conversion");
		this.localizedGamemodeText[EnumGameType.SURVIVAL.getID()] = t.translateKey("gameMode.survival");
		this.localizedGamemodeText[EnumGameType.CREATIVE.getID()] = t.translateKey("gameMode.creative");
		this.localizedGamemodeText[EnumGameType.ADVENTURE.getID()] = t.translateKey("gameMode.adventure");
		this.worldSlotContainer = new GuiSupercraftWorldSlot(this);
		this.worldSlotContainer.registerScrollButtons(this.buttonList, 4, 5);
		this.initButtons();
	}
	
	private void loadSaves() throws AnvilConverterException {
		ISaveFormat format = this.mc.getSaveLoader();
		this.saveList = format.getSaveList();
		Collections.sort(this.saveList);
		this.selectedWorld = -1;
	}
	
	protected String getSaveFileName(int par1) {
		return ((SaveFormatComparator)this.saveList.get(par1)).getFileName();
	}

	protected String getSaveName(int par1) {
		String s = ((SaveFormatComparator)this.saveList.get(par1)).getDisplayName();
		if(s == null || MathHelper.stringNullOrLengthZero(s)) {
			StringTranslate t = StringTranslate.getInstance();
			s = t.translateKey("selectWorld.world")+" "+(par1 + 1);
		}
		return s;
	}
	
	public void initButtons() {
		this.buttons.clear();
		StringTranslate t = StringTranslate.getInstance();
		this.buttons.add(this.buttonSelect = new GuiButtonTransparent(this, this.width / 2 - 154, this.height - 60, 150, 16, 1, t.translateKey("selectWorld.select")));
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 + 4, this.height - 60, 150, 16, 3, t.translateKey("selectWorld.create")));
		this.buttons.add(this.buttonRename = new GuiButtonTransparent(this, this.width / 2 - 154, this.height - 36, 72, 16, 6, t.translateKey("selectWorld.rename")));
		this.buttons.add(this.buttonDelete = new GuiButtonTransparent(this, this.width / 2 - 76, this.height - 36, 72, 16, 2, t.translateKey("selectWorld.delete")));
		this.buttons.add(this.buttonRecreate = new GuiButtonTransparent(this, this.width / 2, this.height - 36, 80, 16, 7, t.translateKey("selectWorld.recreate")));
		this.buttons.add(new GuiButtonTransparent(this, this.width / 2 + 82, this.height - 36, 72, 16, 0, t.translateKey("gui.cancel")));
		this.buttonSelect.enabled = false;
		this.buttonDelete.enabled = false;
		this.buttonRename.enabled = false;
		this.buttonRecreate.enabled = false;
	}
	
	public void selectWorld(int par1) {
		this.mc.displayGuiScreen((GuiScreen)null);
		if(!this.selected) {
			this.selected = true;
			String s = this.getSaveFileName(par1);
			if(s == null) {
				s = "World" + par1;
			}
			String s1 = this.getSaveName(par1);
			if(s1 == null) {
				s1 = "World"+par1;
			}
			if(this.mc.getSaveLoader().canLoadWorld(s)) {
				this.mc.launchIntegratedServer(s, s1, (WorldSettings)null);
			}
		}
	}
	
	@Override
	public void confirmClicked(boolean par1, int par2) {
		if(this.deleting) {
			this.deleting = false;
			if(par1) {
				ISaveFormat isaveformat = this.mc.getSaveLoader();
				isaveformat.flushCache();
				isaveformat.deleteWorldDirectory(this.getSaveFileName(par2));
				try {
					this.loadSaves();
				} catch (AnvilConverterException anvilconverterexception) {
					anvilconverterexception.printStackTrace();
				}
			}
			this.mc.displayGuiScreen(this);
		}
	}
	
	@Override
	public void drawScreenForeground(int mouseX, int mouseY, float tick) {
		this.drawRect(0, 0, width, height, 0x88000000);
		this.drawRect(0, 0, width, 48, 0x77000000);
		this.drawRect(0, height-80, width, height, 0x77000000);
		this.drawRect(0, 48, width, 49, 0xAA000000);
		this.drawRect(0, height-79, width, height-80, 0xAA000000);
		this.worldSlotContainer.drawScreen(mouseX, mouseY, tick);
		this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 20, 16777215);
		super.drawScreenForeground(mouseX, mouseY, tick);
	}
	
	public static GuiSupercraftYesNo getDeleteWorldScreen(GuiSupercraftScreen par0GuiScreen, String par1Str, int par2) {
		StringTranslate t = StringTranslate.getInstance();
		String s1 = t.translateKey("selectWorld.deleteQuestion");
		String s2 = "\'" + par1Str + "\' " + t.translateKey("selectWorld.deleteWarning");
		String s3 = t.translateKey("selectWorld.deleteButton");
		String s4 = t.translateKey("gui.cancel");
		GuiSupercraftYesNo confirmDelete = new GuiSupercraftYesNo(par0GuiScreen, s1, s2, s3, s4, par2);
		return confirmDelete;
	}
	
	public static List getSize(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.saveList;
	}
	
	public static int onElementSelected(GuiSupercraftSelectWorld par0GuiSelectWorld, int par1) {
		return par0GuiSelectWorld.selectedWorld = par1;
	}

	public static int getSelectedWorld(GuiSupercraftSelectWorld par0GuiSelectWorld){
		return par0GuiSelectWorld.selectedWorld;
	}

	public static GuiButtonTransparent getSelectButton(GuiSupercraftSelectWorld par0GuiSelectWorld){
		return par0GuiSelectWorld.buttonSelect;
	}

	public static GuiButtonTransparent getRenameButton(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.buttonDelete;
	}

	public static GuiButtonTransparent getDeleteButton(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.buttonRename;
	}

	public static GuiButtonTransparent func_82312_f(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.buttonRecreate;
	}

	public static String func_82313_g(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.localizedWorldText;
	}

	public static DateFormat func_82315_h(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.dateFormat;
	}

	public static String func_82311_i(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.localizedConvertText;
	}

	public static String[] func_82314_j(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.localizedGamemodeText;
	}
	
	public FontRenderer fontRenderer() {
		return this.fontRenderer;
	}
	
	@Override
	public void buttonEvent(int id) {
		switch(id) {
		case 0:
			this.parent.imageTick = this.imageTick;
			this.mc.displayGuiScreen(this.parent);
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 1:
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			this.selectWorld(this.selectedWorld);
			break;
		case 2:
			String s = this.getSaveName(this.selectedWorld);
			if(s != null) {
				this.deleting = true;
				GuiSupercraftYesNo confirmDelete = getDeleteWorldScreen(this, s, this.selectedWorld);
				this.mc.displayGuiScreen(confirmDelete);
			}
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 3:
			this.mc.displayGuiScreen(new GuiSupercraftCreateWorld(this));
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 6:
			this.mc.displayGuiScreen(new GuiSupercraftRenameWorld(this, this.getSaveFileName(this.selectedWorld)));
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 7:
			GuiSupercraftCreateWorld guicreateworld = new GuiSupercraftCreateWorld(this);
			ISaveHandler isavehandler = this.mc.getSaveLoader().getSaveLoader(this.getSaveFileName(this.selectedWorld), false);
			WorldInfo worldinfo = isavehandler.loadWorldInfo();
			isavehandler.flush();
			guicreateworld.func_82286_a(worldinfo);
			this.mc.displayGuiScreen(guicreateworld);
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		}
	}

}
