package TeamL33T.IpodMod.gui.ipod;

import java.io.IOException;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import TeamL33T.IpodMod.Main;
import TeamL33T.IpodMod.database.ModInfo;
import TeamL33T.IpodMod.items.ipod.IpodClassic;

public class GuiIpod extends GuiScreen {
	
	public static final ResourceLocation texture = new ResourceLocation("ipodmod", "textures/gui/wip-ipod-texture.png");
	public static final int xSize = 256;
	public static final int ySize = 256;
	public int xPos = 50;
    public int yPos = 50;
	private final int btnXSize = 143;
	private final int btnYSize = 20;
	private String[] soundList;
	private int currentSongIndex = 0;
	public static String wavName;
	
	public GuiIpod() {
		try {
			soundList = Main.SLR.getSoundList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		mc.renderEngine.bindTexture(texture);
		
		drawTexturedModalRect(xPos, yPos, 0, 0, xSize, ySize);
		drawString(mc.fontRenderer, wavName, xPos - 20, yPos - 20, 0xFFFFFF);
		super.drawScreen(x, y, f);
	}
	
	@Override
	public void initGui() {
		this.updateBtnList();
	}
	
	private void showPauseBtn() {
		buttonList.add(new GuiButton(1, xPos+9, yPos+134, btnXSize, btnYSize, "Pause"));
		buttonList.add(new GuiButton(2, xPos+9, yPos+161, btnXSize, btnYSize, "Stop"));
		buttonList.add(new GuiButton(3, xPos+9, yPos+188, btnXSize, btnYSize, "Next"));
		buttonList.add(new GuiButton(4, xPos+9, yPos+215, btnXSize, btnYSize, "Previous"));
	}
	
	private void showPlayBtn() {
		buttonList.add(new GuiButton(0, xPos+9, yPos+134, btnXSize, btnYSize, "Play"));
		buttonList.add(new GuiButton(2, xPos+9, yPos+161, btnXSize, btnYSize, "Stop"));
		buttonList.add(new GuiButton(3, xPos+9, yPos+188, btnXSize, btnYSize, "Next"));
		buttonList.add(new GuiButton(4, xPos+9, yPos+215, btnXSize, btnYSize, "Previous"));
	}
	
	private void editWavName(String name) {
		if (name.length() >= 20) {
			wavName = name.substring(0, 19) + "...";
		} else if (name == null || name == "") {
			this.wavName = "";
		} else {
			wavName = name;
		}
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		System.out.println(button.id + " button of GUI clicked!");
		
		switch (button.id) {
		case 0:
			if (!hasSounds()) {
				mc.thePlayer.addChatMessage("No sounds found!");
				break;
			}
			
			if (IpodClassic.Player.getPlayerState() == 0 && IpodClassic.Player.secondCall) {
				print("Stopped (not first-time) is resumed");
				IpodClassic.Player.resumeSound();
				updateBtnList(false);
			} else if (IpodClassic.Player.getPlayerState() == 1) {
				print("Paused sound resumed");
				IpodClassic.Player.resumeSound();
				updateBtnList(false);
			} else {
				print("First-time stopped resumation occurred");
				setWavFile(0);
				playIpod();
				IpodClassic.Player.secondCall = true;
				updateBtnList(false);
			}
			break;
		case 1:
			print("Pausing...");
			IpodClassic.Player.pauseSound();
			updateBtnList(true);
			break;
		case 2:
			print("Stopping...");
			IpodClassic.Player.stopSound();
			updateBtnList(true);
			break;
		case 3:
			if (!hasSounds()) {
				mc.thePlayer.addChatMessage("No sounds found!");
				return;
			}
			
			print("Playing next one...");
			setWavFile(true);
			IpodClassic.Player.overrideSound();
			updateBtnList(false);
			break;
		case 4:
			if (!hasSounds()) {
				mc.thePlayer.addChatMessage("No sounds found!");
				return;
			}
			
			print("Playing previous one...");
			setWavFile(false);
			IpodClassic.Player.overrideSound();
			updateBtnList(false);
			break;
		}
		
	}
	
	private void setWavFile(boolean isNext) {
		int predict = isNext ? (currentSongIndex + 1) : (currentSongIndex - 1);
		
		if (predict >= soundList.length) {
			currentSongIndex = 0;
		} else if (predict < 0) {
			currentSongIndex = (soundList.length - 1);
		} else {
			currentSongIndex = predict;
		}
		
		IpodClassic.Player.setFilename(soundList[currentSongIndex], currentSongIndex);
		editWavName(soundList[currentSongIndex]);
	}
	
	private void setWavFile(int index) {
		if (index < 0 || index >= soundList.length) {
			index = 0;
		}
		
		IpodClassic.Player.setFilename(soundList[index], index);
		editWavName(soundList[index]);
	}
	
	private void playIpod() {
		print("playIpod() entered");
		if (IpodClassic.Player.getPlayerState() == 1) {
			print("Really resuming sound");
			IpodClassic.Player.resumeSound();
		} else {
			print("Really starting main thread... (MUST BE ONLY CALLED ONCE)");
			IpodClassic.Player.start();
		}
	}
	
	private void pauseIpod() {
		
	}
	
	private void stopIpod() {
		
	}
	
	private void updateBtnList() {
		int pState = IpodClassic.Player.getPlayerState();
		this.buttonList.clear();
		
		if (pState == 0) {
			showPlayBtn();
		} else if (pState == 1) {
			showPlayBtn();
		} else if (pState == 2) {
			showPauseBtn();
		}
	}
	
	private void updateBtnList(boolean updateAsPlay) {
		this.buttonList.clear();
		
		if (updateAsPlay) {
			showPlayBtn();
		} else {
			showPauseBtn();
		}
	}
	
	private boolean hasSounds() {
		return soundList.length <= 0 ? false : true;
	}
	
	private void print(String s) {
		System.out.println(s);
	}
	
}
