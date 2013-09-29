package org.TeamL33T.IpodMod.tileentity;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.TeamL33T.IpodMod.battery.IpodBattery;

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
	private Timer cdTimer = new Timer();
	private ItemStack[] chargerItemStacks = new ItemStack[1];
	private final int chargeIncrSecs = 45*1000;
	private final int decrOverheat = 30*1000;
	public IpodBattery battery;
	public int batteryLevel;
	public int overheatLevel;
	public int state = 0;
	public boolean isOverheating;
	public boolean isCharging;
	public boolean isCooling;
	private int fuelLevel;
	protected int x, y, z;
	
	public TileEntityIpodCharger() {
		
		task = new TimerTask() {
			@Override
			public void run() {
				if (battery.getLevel() >= 100) {
					initOverheater();
					timer.cancel();
				} else {
					if (fuelLevel > 0) {
						useFuel();
						battery.increaseLevel(1);
					} else {
						battery.setState(1);
						initCooler();
						timer.cancel();
					}
				}
			}
		};
		
		ohTask = new TimerTask() {
			@Override
			public void run() {
				if (battery.getLevel() >= 100) {
					if (fuelLevel > 0) {
						useFuel();
						overheatLevel++;
					} else {
						battery.setState(1);
						initCooler();
						ohTimer.cancel();
						return;
					}
					
					if (overheatLevel == 25) {
						state = 4;
					} else if (overheatLevel == 50) {
						explode();
					}
				}
			}
		};
		
		cdTask = new TimerTask() {
			@Override
			public void run() {
				battery.setState(1);
				if (state == 1) {
					state = 0;
					stopTimers();
				} else if (state == 2 || state == 3) {
					overheatLevel--;
					if (overheatLevel == 0) {
						state = 1;
					}
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
                
                if (chargerItemStacks[0] == null) {
                	this.cancelChargeOperation();
                } else if (chargerItemStacks[1] == null) {
                	battery.setState(1);
                	this.battery = null;
                }
                
                return itemstack;
            }
            else
            {
                itemstack = this.chargerItemStacks[par1].splitStack(par2);
                
                if (this.chargerItemStacks[par1].stackSize == 0)
                {
                    this.chargerItemStacks[par1] = null;
                    
                    if (chargerItemStacks[0] == null) {
                    	this.cancelChargeOperation();
                    } else if (chargerItemStacks[1] == null) {
                    	battery.setState(1);
                    	this.battery = null;
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
	/* Quite unreadable, eh? */
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		int id = itemstack.itemID;
		
		if (i == 0) {
			if (isItemValidForSlot(i, itemstack)) {
				if (this.chargerItemStacks[0] == null) {
					this.chargerItemStacks[0] = itemstack;
				} else {
					if (this.chargerItemStacks[0].itemID == id) {
						int num = itemstack.stackSize + chargerItemStacks[0].stackSize;
						if (num >= 1 && num <= 64) {
							itemstack = new ItemStack(itemstack.getItem(), num);
							this.chargerItemStacks[0] = itemstack;
							updateFuelLevel();
						}
					}
				}
			}
		} else if (i == 1) {
			if (isItemValidForSlot(i, itemstack) && this.chargerItemStacks[1] == null) {
				this.chargerItemStacks[1] = itemstack;
				this.battery = (IpodBattery) itemstack.getItem();
				if (fuelLevel <= 0) {
					this.battery.setState(1);
				} else {
					this.battery.setState(3);
				}
				
				doChargeOperation();
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
			}
		}
		return 0;
	}
	
	public void cancelChargeOperation() {
		stopTimers();
		
		if (state > 0) {
			initCooler();
		}
	}
	
	public void doChargeOperation() {
		if (state == 0 || state == 1) {
			initIncreaser();
		} else if (state == 2 || state == 3) {
			initOverheater();
		}
	}
	
	public void setBattery(IpodBattery battery) {
		this.battery = battery;
	}
	
	private void initIncreaser() {
		isCharging = true;
		isOverheating = false;
		isCooling = false;
		
		timer.schedule(task, 0, chargeIncrSecs);
	}
	
	private void initOverheater() {
		isCharging = false;
		isOverheating = true;
		isCooling = false;
		
		ohTimer.schedule(ohTask, 0, decrOverheat);
	}
	
	private void initCooler() {
		isCharging = false;
		isOverheating = false;
		isCooling = true;
		
		cdTimer.schedule(cdTask, 0, decrOverheat);
	}
	
	private void stopTimers() {
		isCharging = false;
		isOverheating = false;
		isCooling = false;
		
		timer.cancel();
		ohTimer.cancel();
		cdTimer.cancel();
	}

	/* Update fuelLevel based on the stack size of fuel in the slot */
	private void updateFuelLevel() {
		this.fuelLevel = this.chargerItemStacks[0].stackSize;
	}
	
	/* Consume 1 fuel */
	private void useFuel() {
		this.useFuel(1);
	}
	
	/* Consume (percent) fuel */
	private void useFuel(int percent) {
		updateFuelLevel();
		int predict = fuelLevel - percent;
		
		if (predict < 0) {
			return;
		} else if (predict == 0) {
			this.chargerItemStacks[0] = null;
		} else if (predict >= 1) {
			ItemStack itemstack = new ItemStack(this.chargerItemStacks[0].getItem(), this.chargerItemStacks[0].stackSize - 1);
			this.chargerItemStacks[0] = itemstack;
		}
		
		updateFuelLevel();
	}
	
	public void explode() {
		// SOON :P
	}
	
}