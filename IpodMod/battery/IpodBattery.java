package TeamL33T.IpodMod.battery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public class IpodBattery extends Item {
	
	private final int chargeSecsIncrease = 45;
	private int consumeSecsDecrease;
	private int idleSecsDecrease;
	private boolean isCharging;
	private boolean isIdle;
	private boolean isConsuming;
	private boolean isDead;
	private int state;
	private int level = 100;
	public Item consumer;
	
	public static final int STATE_DEAD = 0;
	public static final int STATE_IDLE = 1;
	public static final int STATE_ONLINE = 2;
	public static final int STATE_CHARGING = 3;
	
	public IpodBattery(int id, int consumeD, int idleD) {
		super(id);
		this.consumeSecsDecrease = consumeD;
		this.idleSecsDecrease = idleD;
		this.isIdle = true;
		this.setState(1);
	}
	
	/**
	 *  Get the instance 
	 */
	public IpodBattery getInstance() {
		return this;
	}
	
	/** 
	 * Get the battery level of the battery (0-100) 
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 *  Get the current state of the battery 
	 */
	public int getState() {
		return this.state;
	}
	
	/**
	 *  Get the remaining seconds left of the battery
	 */
	public int getSecondsLeft() {
		if (state == 1) {
			return idleSecsDecrease * level;
		} else if (state == 2) {
			return consumeSecsDecrease * level;
		} else {
			return 0;
		}
	}
	
	/**
	 *  Get the consumeSecsDecrease 
	 */
	public int getConsumeSecsDecrease() {
		return this.consumeSecsDecrease;
	}
	
	/** 
	 * Get the idleSecsDecrease 
	 */
	public int getIdleSecsDecrease() {
		return this.idleSecsDecrease;
	}
	
	/**
	 *  Set the state of the battery 
	 */
	public void setState(int state) {
		switch (state) {
		case 0:
			this.state = STATE_DEAD;
			this.isCharging = false;
			this.isConsuming = false;
			this.isIdle = false;
			this.isDead = true;
			break;
		case 1:
			this.state = STATE_IDLE;
			this.isCharging = false;
			this.isConsuming = false;
			this.isIdle = true;
			this.isDead = false;
			break;
		case 2:
			this.state = STATE_ONLINE;
			this.isCharging = false;
			this.isConsuming = true;
			this.isIdle = false;
			this.isDead = false;
			break;
		case 3:
			this.state = STATE_CHARGING;
			this.isCharging = true;
			this.isConsuming = false;
			this.isIdle = false;
			this.isDead = false;
			break;
		}
	}
	
	/**
	 *  Is the battery's level 100%? 
	 */
	public boolean isFullyCharged() {
		return (this.level == 100);
	}
	
	/**
	 *  Set battery level 
	 */
	public void setLevel(int level) {
		if (level >= 0 && level <= 100) {
			this.level = level;
		} else {
			this.level = 0;
		}
	}
	
	/**
	 *  Set the item that consumes the battery
     */
	public void setConsumer(Item item) {
		if (item == null) {
			this.clearConsumer();
		} else {
			this.consumer = item;
		}
	}
	
	/**
	 * Remove the battery consumer
	 */
	public void clearConsumer() {
		this.consumer = (Item)null;
	}
	
}
