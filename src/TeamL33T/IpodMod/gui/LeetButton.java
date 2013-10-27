package TeamL33T.IpodMod.gui;

import net.minecraft.client.gui.Gui;

import org.lwjgl.opengl.GL11;

public abstract class LeetButton {
	
	public int id;
	public int minXPos;
	public int minYPos;
	public int maxXPos;
	public int maxYPos;
	
	public LeetButton(int id, int minXPos, int minYPos, int maxXPos, int maxYPos) {
		this.id = id;
		this.minXPos = minXPos;
		this.minYPos = minYPos;
		this.maxXPos = maxXPos;
		this.maxYPos = maxYPos;
	}
	
	public abstract void onClick();
	
	public void onPoint() { }
	
	public void onLeave() { }
	
}
