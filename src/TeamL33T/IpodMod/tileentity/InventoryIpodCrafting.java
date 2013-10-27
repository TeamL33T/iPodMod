package TeamL33T.IpodMod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import TeamL33T.IpodMod.container.ContainerIpodCraftingTable;

public class InventoryIpodCrafting implements IInventory {
	
	private ItemStack[] inventory;
	private ContainerIpodCraftingTable evHandler;
	
	public InventoryIpodCrafting(ContainerIpodCraftingTable container) {
		System.out.println("Now instantiating the huge itemstack array. WATCH OUT FOR MEMORY HOG!!!");
		inventory = new ItemStack[25];
		this.evHandler = container;
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack itemstack = getStackInSlot(i);
		
		if (itemstack != null) {
			if (itemstack.stackSize <= j) {
				inventory[i] = null;
				onInventoryChanged();
			} else {
				itemstack = itemstack.splitStack(j);
			}
		}
		
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemstack = getStackInSlot(i);
		inventory[i] = null;
		onInventoryChanged();
		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if (isItemValidForSlot(i, itemstack)) {
			inventory[i] = itemstack;
			
			if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
				itemstack.stackSize = getInventoryStackLimit();
			}
			
			onInventoryChanged();
		}
	}

	@Override
	public String getInvName() {
		return "iPod Crafting Table";
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}

	@Override
	public void openChest() { }
	
	@Override
	public void closeChest() { }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	
	@Override
	public void onInventoryChanged() {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].stackSize == 0) {
				inventory[i] = null;
			}
		}
		
		evHandler.onCraftMatrixChanged(this);
	}

}
