package com.voidzm.supercraft.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import com.voidzm.supercraft.Supercraft;
import com.voidzm.supercraft.protocol.ISupercraftGui;

import cpw.mods.fml.client.GuiModList;

import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiRenameWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;

public class GuiSupercraftSelectWorld extends GuiScreen implements ISupercraftGui {

	private BufferedImage background;
	private ArrayList<GuiButtonTransparent> buttons = new ArrayList<GuiButtonTransparent>();
	
	private int imageCycleTick = 0;
	
	private static final int imageTime = 200;
	private static final int transitionTime = 100;
	
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
		try {
			background = ImageIO.read(this.getClass().getResourceAsStream("/mods/supercraft/textures/gui/bg.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		imageCycleTick = parentScreen.imageCycleTick;
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
	public void updateScreen() {
		imageCycleTick++;
	}
	
	public void drawScreen(int mouseX, int mouseY, float tick) {
		if(imageCycleTick >= imageTime*4) imageCycleTick = 0;
		float basef = imageCycleTick / imageTime;
		int base = (int)Math.floor(basef);
		int prog = (int)imageCycleTick % imageTime;
		if(base == 0) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg.png"));
		else if(base == 1) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg2.png"));
		else if(base == 2) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg3.png"));
		else if(base == 3) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg4.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int[] locs = this.calcPositions();
		this.drawTexture(locs[0], locs[1], locs[2], locs[3]);
		if(prog >= (imageTime-transitionTime)) {
			GL11.glPushMatrix();
			int alpha = (prog-(imageTime-transitionTime))*2;
			if(base == 0) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg2.png"));
			else if(base == 1) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg3.png"));
			else if(base == 2) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg4.png"));
			else if(base == 3) GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/supercraft/textures/gui/bg.png"));
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, ((float)alpha)/100);
			this.drawTexture(locs[0], locs[1], locs[2], locs[3]);
			GL11.glPopMatrix();
		}
		this.drawRect(0, 0, width, height, 0x88000000);
		this.drawRect(0, 0, width, 48, 0x66000000);
		this.drawRect(0, height-80, width, height, 0x66000000);
		this.drawRect(0, 48, width, 49, 0xAA000000);
		this.drawRect(0, height-79, width, height-80, 0xAA000000);
		for(GuiButtonTransparent iterated : buttons) {
			iterated.draw(mouseX, mouseY);
		}
		this.worldSlotContainer.drawScreen(mouseX, mouseY, tick);
		this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 20, 16777215);
	}
	
	private int[] calcPositions() {
		int[] pos = new int[4];
		float bgRatio = (float)background.getWidth() / (float)background.getHeight();
		if((float)this.width / (float)this.height < bgRatio) {
			pos[2] = (int)(height * bgRatio);
			pos[3] = height;
		}
		else {
			pos[2] = width;
			pos[3] = (int)(width / bgRatio);
		}
		pos[0] = width / 2 - pos[2] / 2;
		pos[1] = height / 2 - pos[3] / 2;
		return pos;
	}
	
	private void drawTexture(int x, int y, int w, int h) {
		Tessellator tes = Tessellator.instance;
		tes.startDrawingQuads();
		tes.addVertexWithUV(x, y + h, 0, 0, 1);
		tes.addVertexWithUV(x + w, y + h, 0, 1, 1);
		tes.addVertexWithUV(x + w, y, 0, 1, 0);
		tes.addVertexWithUV(x, y, 0, 0, 0);
		tes.draw();
	}
	
	public static GuiYesNo getDeleteWorldScreen(GuiScreen par0GuiScreen, String par1Str, int par2) {
		StringTranslate t = StringTranslate.getInstance();
		String s1 = t.translateKey("selectWorld.deleteQuestion");
		String s2 = "\'" + par1Str + "\' " + t.translateKey("selectWorld.deleteWarning");
		String s3 = t.translateKey("selectWorld.deleteButton");
		String s4 = t.translateKey("gui.cancel");
		GuiYesNo confirmDelete = new GuiYesNo(par0GuiScreen, s1, s2, s3, s4, par2);
		return confirmDelete;
	}
	
	static List getSize(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.saveList;
	}
	
	static int onElementSelected(GuiSupercraftSelectWorld par0GuiSelectWorld, int par1) {
		return par0GuiSelectWorld.selectedWorld = par1;
	}

	static int getSelectedWorld(GuiSupercraftSelectWorld par0GuiSelectWorld){
		return par0GuiSelectWorld.selectedWorld;
	}

	static GuiButtonTransparent getSelectButton(GuiSupercraftSelectWorld par0GuiSelectWorld){
		return par0GuiSelectWorld.buttonSelect;
	}

	static GuiButtonTransparent getRenameButton(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.buttonDelete;
	}

	static GuiButtonTransparent getDeleteButton(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.buttonRename;
	}

	static GuiButtonTransparent func_82312_f(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.buttonRecreate;
	}

	static String func_82313_g(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.localizedWorldText;
	}

	static DateFormat func_82315_h(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.dateFormat;
	}

	static String func_82311_i(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.localizedConvertText;
	}

	static String[] func_82314_j(GuiSupercraftSelectWorld par0GuiSelectWorld) {
		return par0GuiSelectWorld.localizedGamemodeText;
	}
	
	public FontRenderer fontRenderer() {
		return this.fontRenderer;
	}

	@Override
	protected void mouseClicked(int mx, int my, int par3) {
		for(GuiButtonTransparent button : buttons) {
			button.clickEvent(mx, my);
		}
	}
	
	public void buttonEvent(int id) {
		switch(id) {
		case 0:
			this.parent.imageCycleTick = this.imageCycleTick;
			this.mc.displayGuiScreen(this.parent);
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 1:
			this.selectWorld(this.selectedWorld);
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 2:
			String s = this.getSaveName(this.selectedWorld);
			if(s != null) {
				this.deleting = true;
				GuiYesNo confirmDelete = getDeleteWorldScreen(this, s, this.selectedWorld);
				this.mc.displayGuiScreen(confirmDelete);
			}
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 3:
			this.mc.displayGuiScreen(new GuiCreateWorld(this));
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 6:
			this.mc.displayGuiScreen(new GuiRenameWorld(this, this.getSaveFileName(this.selectedWorld)));
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			break;
		case 7:
			GuiCreateWorld guicreateworld = new GuiCreateWorld(this);
			ISaveHandler isavehandler = this.mc.getSaveLoader().getSaveLoader(this.getSaveFileName(this.selectedWorld), false);
			WorldInfo worldinfo = isavehandler.loadWorldInfo();
			isavehandler.flush();
			guicreateworld.func_82286_a(worldinfo);
			this.mc.displayGuiScreen(guicreateworld);
			break;
		}
	}

	@Override
	public void drawQuad(int x, int y, int i, int j, int rgb) {
		this.drawRect(x, y, i, j, rgb);
	}
	
}
