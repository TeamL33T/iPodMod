package TeamL33T.IpodMod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import TeamL33T.IpodMod.AppleDealerRecipes;
import TeamL33T.IpodMod.gui.GuiAppleDeal;

public class InventoryAppleDeal implements IInventory {

	public ItemStack[] inventory = new ItemStack[3];
	
	public InventoryAppleDeal() { }
	
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
		print("decrStackSize() is called");
		ItemStack itemstack = getStackInSlot(i);
		
		if (itemstack != null) {
			print("inventory["+i+"] ain't null!");
			if (itemstack.stackSize <= j) {
				print("inventory["+i+"] is less than or equal to "+j);
				inventory[i] = null;
				onInventoryChanged();
			} else {
				print("Splitting the thing to return by "+j);
				itemstack = itemstack.splitStack(j);
			}
		}
		
		checkTrade();
		print("Returning a thing with id "+itemstack.itemID+" and num of "+itemstack.stackSize);
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemstack = inventory[i];
		setInventorySlotContents(i, null);
		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		print("setInventorySlotContents() is called");
		if (isItemValidForSlot(i, itemstack)) {
			inventory[i] = itemstack;

			if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
				print("I do not care #76");
				itemstack.stackSize = getInventoryStackLimit();
			}
			
			checkTrade();
			onInventoryChanged();
		}
	}

	@Override
	public String getInvName() {
		return "Apple Deal";
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
	public void openChest() { }

	@Override
	public void closeChest() { }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if (i == 2) return false;
		return true;
	}

	@Override
	public void onInventoryChanged() { 
		for (int x = 0; x < getSizeInventory(); x++) {
			if (inventory[x] != null && inventory[x].stackSize == 0) {
				inventory[x] = null;
			}
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}
	
	/**
	 * Tradin' stuff
	 */
	public void checkTrade()	{
		print("checkTrade() entered, assigning vars");
		ItemStack one = getStackInSlot(0);
		ItemStack two = getStackInSlot(1);
		ItemStack three = getStackInSlot(2);
		
		ItemStack left1 = AppleDealerRecipes.getRecipes(GuiAppleDeal.recipeIndex)[0];
		ItemStack left2 = AppleDealerRecipes.getRecipes(GuiAppleDeal.recipeIndex)[1];
		ItemStack right1 = AppleDealerRecipes.getRecipes(GuiAppleDeal.recipeIndex)[2];
		
		if (three != null) {
			print("Three ain't null");
			if (one == null || two == null) {
				print("I do not care #26");
				inventory[2] = null;
				onInventoryChanged();
			}
		} else if (three == null) {
			print("Three is null");
			if (one == null || two == null) {
				return;
			} else if (one.itemID == left1.itemID && two.itemID == left2.itemID) {
				if (one.stackSize >= left1.stackSize && two.stackSize >= left2.stackSize) {
					print("I do not care #101");
					inventory[2] = right1;
					onInventoryChanged();
				}
			}
		}
	}
	
	private void print(String log) {
		System.out.println(log);
	}

}
