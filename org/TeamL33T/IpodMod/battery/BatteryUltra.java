package org.TeamL33T.IpodMod.battery;

import org.TeamL33T.IpodMod.Main;

public class BatteryUltra extends IpodBattery {

	public BatteryUltra() {
		super(603, 180, 230);
		this.setMaxStackSize(1);
		this.setCreativeTab(Main.tabIpod);
	}

}
