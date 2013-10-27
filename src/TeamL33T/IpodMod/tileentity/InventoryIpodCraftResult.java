package TeamL33T.IpodMod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryIpodCraftResult implements IInventory {

	private ItemStack[] inventory;
	
	public InventoryIpodCraftResult() {
		System.out.println("Now instantiating the cute array of ipodcraftres");
		inventory = new ItemStack[1];
		System.out.println("Did instantiate the cute array");
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[0];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack itemstack = null;
		
		if (getStackInSlot(i) != null) {
			itemstack = getStackInSlot(i);
			inventory[i] = null;
			onInventoryChanged();
		}
		
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemstack = getStackInSlot(i);
		setInventorySlotContents(i, null);
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
		return "IpodCraftResultINNER23";
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
	public void onInventoryChanged() {
		if (inventory[0] != null && inventory[0].stackSize == 0) {
			setInventorySlotContents(0, null);
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() { }

	@Override
	public void closeChest() { }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
