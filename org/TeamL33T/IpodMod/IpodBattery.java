package org.TeamL33T.IpodMod;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public class IpodBattery extends Item {

	/* States:
	 * 0 - Dead
	 * 1 - Idle
	 * 2 - Consuming
	 * 3 - Charging
	 */
	
	private TimerTask mainTask;
	private TimerTask chargeTask;
	private Timer idleTimer;
	private Timer consumeTimer;
	private Timer chargeTimer;
	private final int chargeSecsIncrease = 45;
	private int consumeSecsDecrease;
	private int idleSecsDecrease;
	private boolean isCharging;
	private boolean isIdle;
	private boolean isConsuming;
	private boolean isDead;
	private int state;
	private int level = 100;
	
	/* Constructor will instantiate the battery and its timers and tasks */
	public IpodBattery(int id, int consumeD, int idleD) {
		super(id);
		this.consumeSecsDecrease = consumeD;
		this.idleSecsDecrease = idleD;
		this.isIdle = true;
		
		idleTimer = new Timer();
		consumeTimer = new Timer();
		chargeTimer = new Timer();
		
		mainTask = new TimerTask() {
			@Override
			public void run() {
				if (level >= 2) {
					decreaseLevel(1);
				} else if (level <= 1) {
					setState(0);
				}
			}
		};
		
		chargeTask = new TimerTask() {
			@Override
			public void run() {
				if (level > 0 && level <= 99) {
					increaseLevel(1);
				}
			}
		};
		
		this.setState(1);
	}
	
	/* Get the instance */
	public IpodBattery getInstance() {
		return this;
	}
	
	/* Get the battery level of the battery (0-100) */
	public int getLevel() {
		return this.level;
	}
	
	/* Get the current state of the battery */
	public int getState() {
		return this.state;
	}
	
	/* Get the remaining seconds left of the battery */
	public int getSecondsLeft() {
		if (state == 1) {
			return idleSecsDecrease * level;
		} else if (state == 2) {
			return consumeSecsDecrease * level;
		} else {
			return 0;
		}
	}
	
	/* Get the consumeSecsDecrease */
	public int getConsumeSecsDecrease() {
		return this.consumeSecsDecrease;
	}
	
	/* Get the idleSecsDecrease */
	public int getIdleSecsDecrease() {
		return this.idleSecsDecrease;
	}
	
	/* Set the state of the battery */
	public void setState(int state) {
		switch (state) {
		case 0:
			setDead();
			break;
		case 1:
			this.state = 1;
			this.isCharging = false;
			this.isConsuming = false;
			this.isIdle = true;
			this.isDead = false;
			this.setLevelManagementByState();
			break;
		case 2:
			this.state = 2;
			this.isCharging = false;
			this.isConsuming = true;
			this.isIdle = false;
			this.isDead = false;
			this.setLevelManagementByState();
			break;
		case 3:
			this.state = 0;
			this.isCharging = true;
			this.isConsuming = false;
			this.isIdle = false;
			this.isDead = false;
			this.setLevelManagementByState();
			break;
		}
	}
	
	/* DO NOT USE! USE SETSTATE(0) INSTEAD! */
	private void setDead() {
		this.state = 0;
		this.isCharging = false;
		this.isConsuming = false;
		this.isIdle = false;
		this.isDead = true;
		this.level = 0;
		this.setLevelManagementByState();
	}
	
	/* Reload state, automatically set by setState() */
	private void setLevelManagementByState() {
		if (state == 0) {
			chargeTimer.cancel();
			consumeTimer.cancel();
			idleTimer.cancel();
		} else if (state == 1) {
			chargeTimer.cancel();
			consumeTimer.cancel();
			idleTimer.schedule(mainTask, 0, idleSecsDecrease*1000);
		} else if (state == 2) {
			chargeTimer.cancel();
			idleTimer.cancel();
			consumeTimer.schedule(mainTask, 0, consumeSecsDecrease*1000);
		} else if (state == 3) {
			consumeTimer.cancel();
			idleTimer.cancel();
			chargeTimer.schedule(chargeTask, 0, 45*1000);
		}
	}
	
	/* Is the battery's level 100%? */
	public boolean isFullyCharged() {
		return (this.level == 100);
	}
	
	/* Decrease battery level */
	public void decreaseLevel(int percent) {
		if ((level - percent) < 0) {
			level = 0;
		} else {
			level -= percent;
		}
	}
	
	/* Increase battery level */
	public void increaseLevel(int percent) {
		if ((level + percent) > 100) {
			level = 100;
		} else {
			level += percent;
		}
	}
	
	/* TESTING USE */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		int nextState = (this.state == 3 ? 0 : (this.state + 1));
		par3EntityPlayer.addChatMessage(
				"DEBUG USE (WILL BE REMOVED IN 1.0.0)\n" +
				"Battery Level: " + this.level + "%" +
				"Current State: " + this.state +
				"Seconds Left: " + this.getSecondsLeft() +
				"Changing State to " + nextState
				);
		this.setState(nextState);
		return par1ItemStack;
	}
	
}
