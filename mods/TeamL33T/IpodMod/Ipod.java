package mods.TeamL33T.IpodMod;

import cpw.mods.fml.common.Mod.EventHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class Ipod extends Item {

	public boolean isActived = false;
	
	public Ipod(int par1) {
		super(par1);
	}
	
	// Initiate GUI when iPod is right clicked
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		isActived = (isActived ? false : true);
		return par1ItemStack;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerSleepInBedEvent ev) {
		if (isActived==false) { return; }
		ev.entityPlayer.getFoodStats().setFoodLevel(0);
	}
	
}
