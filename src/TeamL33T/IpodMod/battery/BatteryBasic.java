package TeamL33T.IpodMod.battery;

import net.minecraft.client.renderer.texture.IconRegister;
import TeamL33T.IpodMod.Main;
import TeamL33T.IpodMod.database.IdData;
import TeamL33T.IpodMod.database.UnlocalizedData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BatteryBasic extends IpodBattery {

	public BatteryBasic() {
		super(IdData.BatteryBasic, 30, 60);
		this.setMaxStackSize(1);
		this.setCreativeTab(Main.tabIpod);
		this.setUnlocalizedName(UnlocalizedData.BatteryBasic);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconR) {
		this.itemIcon = iconR.registerIcon("ipodmod:battery_basic");
	}

}
