package TeamL33T.IpodMod.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import TeamL33T.IpodMod.AppleDealerRecipes;
import TeamL33T.IpodMod.container.ContainerAppleDeal;
import TeamL33T.IpodMod.tileentity.InventoryAppleDeal;

public class GuiAppleDeal extends GuiContainer {

	private final ResourceLocation texture = new ResourceLocation("ipodmod", "textures/gui/apple_deal.png");
	private final int mgXSize = 175;
	private final int mgYSize = 165;
	private final int xSize = 256;
	private final int ySize = 256;
	private final int xPos = (int) this.guiLeft + ((Display.getWidth() - this.mgXSize) / 2);
	private final int yPos = (int) this.guiTop + ((Display.getHeight() - this.mgYSize) / 2);
	public static int recipeIndex = 0;
	private InventoryAppleDeal te;
	
	private boolean btnRecUpd;
	private boolean acceptable = true;

	public GuiAppleDeal(InventoryPlayer player, InventoryAppleDeal tileentity) {
		super(new ContainerAppleDeal(player, tileentity));
		this.te = tileentity;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		this.buttonList.add(new GuiButton(0, xPos+14, yPos+52, 17, 17, "<"));
		this.buttonList.add(new GuiButton(1, xPos+137, yPos+52, 17, 17, ">"));
	}
	
	@Override
	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();
		
		RenderHelper.enableGUIStandardItemLighting();
		
		itemRenderer.zLevel = 100F;
		itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), AppleDealerRecipes.getRecipes(recipeIndex)[0], xPos+35, yPos+34);
		itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), AppleDealerRecipes.getRecipes(recipeIndex)[1], xPos+61, yPos+34);
		itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), AppleDealerRecipes.getRecipes(recipeIndex)[2], xPos+116, yPos+34);
		itemRenderer.zLevel = 0F;
		
		RenderHelper.enableStandardItemLighting();
		updateIdentArrow();
		
		super.drawScreen(x, y, f);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) { 
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(xPos, yPos, 0, 0, mgXSize, mgYSize);
		
		this.drawString(mc.fontRenderer, AppleDealerRecipes.getName(recipeIndex), xPos+6, yPos+6, 0xFFFFFF);
		this.drawString(mc.fontRenderer, "Inventory", xPos+7, yPos+72, 0xFFFFFF);
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		itemRenderer.zLevel = 100.0F;
		itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.getTextureManager(), AppleDealerRecipes.getRecipes(recipeIndex)[0], xPos+35, yPos+34);
		itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.getTextureManager(), AppleDealerRecipes.getRecipes(recipeIndex)[1], xPos+61, yPos+34);
		itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.getTextureManager(), AppleDealerRecipes.getRecipes(recipeIndex)[2], xPos+116, yPos+34);
		itemRenderer.zLevel = 0.0F;
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();

		updateIdentArrow();
		if (btnRecUpd) return;
		
		if (recipeIndex > 0 && recipeIndex < AppleDealerRecipes.recipesCount) {
			updateButtons(2);
		} else if (recipeIndex == 0) {
			updateButtons(1);
		} else if (recipeIndex == AppleDealerRecipes.recipesCount) {
			updateButtons(0);
		}
		
		te.checkTrade();
		btnRecUpd = true;
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if (recipeIndex > 0) recipeIndex--;
			break;
		case 1:
			if (recipeIndex < AppleDealerRecipes.recipesCount) recipeIndex++;
			break;
		}

		btnRecUpd = false;
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	/**
	 * Update the navigation buttons (clear and show both)
	 */
	protected void updateButtons() {
		this.buttonList.clear();
		
		this.buttonList.add(new GuiButton(0, xPos+14, yPos+52, 17, 17, "<"));
		this.buttonList.add(new GuiButton(1, xPos+137, yPos+52, 17, 17, ">"));
	}
	
	/**
	 * Update navigation buttons by removing one
	 * @param index - 0 (<) or 1 (>) or else, both
	 */
	protected void updateButtons(int index) {
		this.buttonList.clear();
		
		if (index == 0) {
			this.buttonList.add(new GuiButton(0, xPos+14, yPos+52, 17, 17, "<"));
		} else if (index == 1) {
			this.buttonList.add(new GuiButton(1, xPos+137, yPos+52, 17, 17, ">"));
		} else {
			updateButtons();
		}
	}
	
	/**
	 * Update the arrow indicator
	 */
	protected void updateIdentArrow() {
		if (te.getStackInSlot(2) != null) {
			if (acceptable) return;
			
			mc.renderEngine.bindTexture(texture);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			
			this.drawTexturedModalRect(xPos, yPos, 0, 0, mgXSize, mgYSize);
			acceptable = true;
		} else if (te.getStackInSlot(2) == null) {
			if (!acceptable) return;
			
			mc.renderEngine.bindTexture(texture);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			
            this.drawTexturedModalRect(xPos+86, yPos+54, 176, 0, 21, 14);
            acceptable = false;
		}
	}
	
}
