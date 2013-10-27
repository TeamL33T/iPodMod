package TeamL33T.IpodMod.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import TeamL33T.IpodMod.IpodCraftingManager;
import TeamL33T.IpodMod.gui.SlotBlocked;
import TeamL33T.IpodMod.tileentity.InventoryIpodCraftResult;
import TeamL33T.IpodMod.tileentity.InventoryIpodCrafting;

public class ContainerIpodCraftingTable extends Container {

	private InventoryIpodCrafting craftMatrix;
	private IInventory craftResult;
	
	public ContainerIpodCraftingTable(InventoryPlayer inventory) {
		this.craftMatrix = new InventoryIpodCrafting(this);
		this.craftResult = new InventoryIpodCraftResult();
		
		System.out.print("If you are reading this, no memory hogs were detected! YAY!");
		int index = 8;
		
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; y++) {
				index++;
				this.addSlotToContainer(new Slot(inventory, index, 8 + x * 18, 106 + y * 18));
			}
		}
		
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(inventory, x, 8 + x * 18, 164));
		}
		
		index = -1;
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				index++;
				this.addSlotToContainer(new Slot(craftMatrix, index, 8 + x * 18, 9 + y * 18));
			}
		}
		
		this.addSlotToContainer(new SlotBlocked(craftResult, 0, 139, 44));
		this.onCraftMatrixChanged(craftMatrix);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return craftMatrix.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
	}
	
	public void onCraftMatrixChanged(IInventory inv) {
		System.out.println("Now changing craft result if not superfluous");
		craftResult.setInventorySlotContents(0, IpodCraftingManager.instance.findMatchingRecipe(inv));
	}

}
