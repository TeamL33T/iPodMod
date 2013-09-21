package mods.TeamL33T.IpodMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Ipod extends Item {

	public boolean isActived = false;
	
	public Ipod(int par1) {
		super(par1);
		this.setCreativeTab(Main.tabIpod);
		this.setUnlocalizedName("Ipod");
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
	
	// Register Texture
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		
		this.itemIcon = par1IconRegister.registerIcon("ipodmod:ipod");
		
	}
	
}
