
package TeamL33T.IpodMod.gui.ipod.nano;

import java.util.ArrayList;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import TeamL33T.IpodMod.gui.LeetButton;

public class GuiIpodNanoMain1 extends GuiScreen {
	
	private EntityPlayer thePlayer;
	private final ResourceLocation texture = new ResourceLocation("ipodmod", "textures/gui/ipod/nano2g/main.png");
	private final int mgXSize = 125;
	private final int mgYSize = 238;
	private final int xSize = 256;
	private final int ySize = 256;
	private final int xPos = (int) (Display.getWidth() - mgXSize) / 2;
	private final int yPos = (int) (Display.getHeight() - mgYSize) / 2;
	private ArrayList<LeetButton> btnList;
	
	public GuiIpodNanoMain1(EntityPlayer player) {
		btnList = new ArrayList<LeetButton>();
		this.thePlayer = player;
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		mc.renderEngine.bindTexture(texture);
		
		drawTexturedModalRect(xPos, yPos, 0, 0, mgXSize, mgYSize);
		super.drawScreen(x, y, f);
	}
	
	@Override
	public void initGui() {
		MP_NanoMain1 mp = new MP_NanoMain1();
		
		btnList.add(new LeetButton(0, mp.MUSIC_MIN_X, mp.MUSIC_MIN_Y, mp.MUSIC_MAX_X, mp.MUSIC_MAX_Y) {
			@Override
			public void onClick() {
				System.out.println("Music");
			}
		});
		
		btnList.add(new LeetButton(1, mp.VIDEO_MIN_X, mp.VIDEO_MIN_Y, mp.VIDEO_MAX_X, mp.VIDEO_MAX_Y) {
			@Override
			public void onClick() {
				System.out.println("Video");
			}
		});
		
		btnList.add(new LeetButton(2, mp.GAMES_MIN_X, mp.GAMES_MIN_Y, mp.GAMES_MAX_X, mp.GAMES_MAX_Y) {
			@Override
			public void onClick() {
				System.out.println("Games");
			}
		});
		
		btnList.add(new LeetButton(3, mp.TNT_MIN_X, mp.TNT_MIN_Y, mp.TNT_MAX_X, mp.TNT_MAX_Y) {
			@Override
			public void onClick() {
				System.out.println("Tnt");
			}
		});
		
		btnList.add(new LeetButton(4, mp.PHOTOS_MIN_X, mp.PHOTOS_MIN_Y, mp.PHOTOS_MAX_X, mp.PHOTOS_MAX_Y) {
			@Override
			public void onClick() {
				System.out.println("Photos");
			}
		});
		
		btnList.add(new LeetButton(5, mp.SETS_MIN_X, mp.SETS_MIN_Y, mp.SETS_MAX_X, mp.SETS_MAX_Y) {
			@Override
			public void onClick() {
				System.out.println("Settings");
			}
		});
		
	}
	
	@Override
	public void mouseClicked(int par1, int par2, int par3) {
		if (par3 == 0)
        {
			System.out.println("Mouse was clicked! At "+par1+","+par2);
			System.out.println("GUIrX: At "+((par1-xPos) < 1 ? 0 : par1-xPos)+","+((par2-yPos) < 1 ? 0 : par2-yPos));
            for (int l = 0; l < this.buttonList.size(); ++l)
            {
                GuiButton guibutton = (GuiButton) this.buttonList.get(l);
                
                if (guibutton.mousePressed(this.mc, par1, par2))
                {
                	System.out.println("Nope, just a vanilla btn");
                    this.selectedButton = guibutton;
                    this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                    this.actionPerformed(guibutton);
                }
            }
            
            for (int x = 0; x < btnList.size(); x++) {
            	System.out.println("Leet's loop at "+x);
            	LeetButton curBtn = btnList.get(x);
            	
            	int tmpMinX = curBtn.minXPos + xPos; //533
            	int tmpMaxX = curBtn.maxXPos + xPos; //567
            	int tmpMinY = curBtn.minYPos + yPos; //331
            	int tmpMaxY = curBtn.maxYPos + yPos; //365
            	
            	if ((par1 >= tmpMinX && par1 <= tmpMaxX) && 
            		(par2 >= tmpMinY && par2 >= tmpMaxY)) 
            	{
            		System.out.println("A LeetButton was rly clicked! Cool!");
            		curBtn.onClick();
            		return;
            	}
            }
        }
	}
	
}
