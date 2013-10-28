package TeamL33T.IpodMod.items.ipod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import TeamL33T.IpodMod.Main;
import TeamL33T.IpodMod.WavPlayer;
import TeamL33T.IpodMod.database.IdData;
import TeamL33T.IpodMod.database.UnlocalizedData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IpodClassic extends Item {
	
	public IpodClassic() {
		super(IdData.iPodClassic);
		this.setCreativeTab(Main.tabIpod);
		this.setUnlocalizedName(UnlocalizedData.iPodClassic);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.openGui(Main.instance, 0, par2World, 0, 0, 0);
		return par1ItemStack;
	}
	
	// Register Texture
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("ipodmod:ipod");
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		Main.Player.stopSound();
		return true;
	}
	
}