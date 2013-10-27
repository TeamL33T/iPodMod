package TeamL33T.IpodMod.items.ipod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import TeamL33T.IpodMod.Main;
import TeamL33T.IpodMod.database.GuiIndex;
import TeamL33T.IpodMod.database.IdData;
import TeamL33T.IpodMod.database.UnlocalizedData;

public class IpodNano extends Item {

	public IpodNano() {
		super(IdData.iPodNano);
		this.setMaxStackSize(1);
		this.setCreativeTab(Main.tabIpod);
		this.setUnlocalizedName(UnlocalizedData.iPodNano);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		player.openGui(Main.instance, GuiIndex.IpodNanoMainOne, world, 0, 0, 0);
		return itemstack;
	}

}
