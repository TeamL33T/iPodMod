package org.TeamL33T.IpodMod.battery;

import org.TeamL33T.IpodMod.Main;


public class BatteryBasic extends IpodBattery {

	public BatteryBasic() {
		super(600, 30, 60);
		this.setMaxStackSize(1);
		this.setCreativeTab(Main.tabIpod);
	}

}
