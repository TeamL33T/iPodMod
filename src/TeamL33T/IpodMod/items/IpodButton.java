package TeamL33T.IpodMod.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import TeamL33T.IpodMod.Main;
import TeamL33T.IpodMod.database.IdData;
import TeamL33T.IpodMod.database.UnlocalizedData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IpodButton extends Item {

	public IpodButton() {
		super(IdData.iPodButton);
		this.setCreativeTab(Main.tabIpod);
		this.setUnlocalizedName(UnlocalizedData.iPodButton);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconR) {
		this.itemIcon = iconR.registerIcon("ipodmod:ipod_button");
	}
	
}
