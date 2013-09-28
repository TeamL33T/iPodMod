package org.TeamL33T.IpodMod.tileentity;

import java.util.Timer;
import java.util.TimerTask;

import org.TeamL33T.IpodMod.battery.IpodBattery;
import org.lwjgl.input.Mouse;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityIpodCharger extends TileEntity implements ISidedInventory {
	
	/* States:
	 * 0 - Idle
	 * 1 - Normal
	 * 2 - Critical
	 * 3 - Overheating
	 */
	
	/* ItemStack Array (chargerItemStacks)
	 * 0 - Electricity Source
	 * 1 - Battery to Charge
	 */
	
	private TimerTask task;
	private TimerTask ohTask;
	private TimerTask cdTask;
	private Timer timer = new Timer();
	private Timer ohTimer = new Timer();
	private ItemStack[] chargerItemStacks = new ItemStack[1];
	private final int chargeIncrSecs = 45;
	public IpodBattery battery;
	public int batteryLevel;
	public int overheatLevel;
	public int state = 0;
	private boolean isOverheating;
	private boolean isCharging;
	private int chargeTimeSecs;
	private final int decrOverheat = 30;
	
	public TileEntityIpodCharger(int chargeIncrSecs) {
		task = new TimerTask() {
			@Override
			public void run() {
				if (battery.getLevel() >= 100) {
					initOverheater();
					this.cancel();
				} else {
					battery.increaseLevel(1);
				}
			}
		};
		
		ohTask = new TimerTask() {
			@Override
			public void run() {
				if (battery.getLevel() >= 100) {
					overheatLevel++;
				}
			}
		};
	}
	
	@Override
	public int getSizeInventory() {
		return this.chargerItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.chargerItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.chargerItemStacks[par1] != null) {
			ItemStack itemstack;
			
			if (this.chargerItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.chargerItemStacks[par1];
                this.chargerItemStacks[par1] = null;
                
                if (par1 == 0) {
                	this.cancelChargeOperation();
                }
                
                return itemstack;
            }
            else
            {
                itemstack = this.chargerItemStacks[par1].splitStack(par2);
                
                if (this.chargerItemStacks[par1].stackSize == 0)
                {
                    this.chargerItemStacks[par1] = null;
                    
                    if (par1 == 0) {
                    	this.cancelChargeOperation();
                    }
                }
                
                return itemstack;
            }
			
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}

	@Override
	/* Check if they're putting the right stuff */
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		int id = itemstack.itemID;
		
		if (i == 0) {
			
		} else if (i == 1) {
			if (id >= 600 && id <= 604) {
				this.chargerItemStacks[i] = itemstack;
			}
		}
		
	}

	@Override
	public String getInvName() {
		return "iPod Charger";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
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
		if (i == 0) {
			if (this.isFuelSource(itemstack.itemID)) {
				return true;
			}
		} else if (i == 1) {
			if (this.isBattery(itemstack.itemID)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return false;
	}
	
	//---------------Non-Interface Methods---------------//
	
	public boolean isFuelSource(int itemID) {
		if (itemID == Item.coal.itemID) {
			return true;
		} else if (itemID == Item.bucketLava.itemID) {
			return true;
		} else if (itemID == 173) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isBattery(int itemID) {
		if (itemID >= 600 && itemID <= 604) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getNumberOfUses(int itemID) {
		if (this.isFuelSource(itemID)) {
			if (itemID == Item.coal.itemID) {
				return 1;
			} else if (itemID == Item.bucketLava.itemID) {
				return 100;
			} else if (itemID == 173) {
				return 10;
			}
		}
		return 0;
	}
	
	public void cancelChargeOperation() {
		isCharging = false;
		stopTimers();
	}
	
	public void doChargeOperation() {
		// Do some charging :D
		
		// Check if it's overheating
		if (state >= 2) {
			
		}
	}
	
	public boolean isCharging() {
		return isCharging;
	}
	
	public void setBattery(IpodBattery battery) {
		this.battery = battery;
	}
	
	private void initIncreaser() {
		timer.schedule(task, 0, chargeIncrSecs);
	}
	
	private void initOverheater() {
		isOverheating = true;
		ohTimer.schedule(ohTask, 0, decrOverheat);
	}
	
	private void stopTimers() {
		timer.cancel();
		ohTimer.cancel();
	}
	
	/* Cool the charger down (only if state 1-3) */
	public void cooldown() {
		if (state >= 1 && state <= 3) {
			if (state == 1) {
				
			}
		}
	}


}