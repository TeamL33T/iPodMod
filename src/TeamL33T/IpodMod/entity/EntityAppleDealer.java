package TeamL33T.IpodMod.entity;

import TeamL33T.IpodMod.Main;
import TeamL33T.IpodMod.database.GuiIndex;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityAppleDealer extends EntityAnimal {

	public EntityAppleDealer(World par1World) {
		super(par1World);
		this.getNavigator().setAvoidsWater(true);
		this.isImmuneToFire = false;
		this.setSize(1F, 1F);
		this.setHealth(15F);
		
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 0.4F));
		this.tasks.addTask(2, new EntityAIWander(this, 0.3F));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 5.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return null;
	}
	
	@Override
	public boolean interact(EntityPlayer player) {
		player.openGui(Main.instance, GuiIndex.AppleDeal, player.getEntityWorld(), 0, 0, 0);
		return false;
	}

}
