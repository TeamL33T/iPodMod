package TeamL33T.IpodMod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import TeamL33T.IpodMod.container.ContainerIpodCraftingTable;

public class GuiIpodCraftingTable extends GuiContainer {

	private final static ResourceLocation texture = new ResourceLocation("ipodmod", "textures/gui/tech_craft.png");
	private final static int xSize = 256;
	private final static int ySize = 256;
	public static final int mgXSize = 175;
	public static final int mgYSize = 188;
	public static final int xPos = (int) ((Display.getWidth() - mgXSize) / 2);
	public static final int yPos = (int) ((Display.getHeight() - mgYSize) / 2);
	
	public GuiIpodCraftingTable(InventoryPlayer inventory, World world, int x, int y, int z) {
		super(new ContainerIpodCraftingTable(inventory, world, x, y, z));
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(xPos, yPos, 0, 0, mgXSize, mgYSize);
	}

}
