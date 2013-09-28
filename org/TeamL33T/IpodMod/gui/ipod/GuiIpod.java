package org.TeamL33T.IpodMod.gui.ipod;

import org.TeamL33T.IpodMod.ModInfo;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiIpod extends GuiScreen {
	
	public IpodGuiVars vr = new IpodGuiVars();
	public static final ResourceLocation texture = new ResourceLocation(ModInfo.ID.toLowerCase(), "textures/gui/ipod-classic-main.png");
	public static final int X_SIZE = 202;
	public static final int Y_SIZE = 339;
	public int xPos;
	public int yPos;
	
	public GuiIpod() {
		xPos = (this.width - X_SIZE) / 2;
		yPos = (this.height - Y_SIZE) / 2;
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().func_110434_K().func_110577_a(texture);
		
		drawTexturedModalRect(xPos, yPos, 0, 0, X_SIZE, Y_SIZE);
		super.drawScreen(x, y, f);
		
		while (Mouse.next()) {
			int xM = Mouse.getEventX();
			int yM = Mouse.getEventX();
			
			if ((xM >= vr.PLAY_MIN_X && xM <= vr.PLAY_MAX_X) && (yM >= vr.PLAY_MIN_Y && yM <= vr.PLAY_MAX_Y)) {
				
			}
		}
	}
	
}
