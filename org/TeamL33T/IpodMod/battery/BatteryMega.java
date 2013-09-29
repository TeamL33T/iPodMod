package org.TeamL33T.IpodMod.battery;

import org.TeamL33T.IpodMod.Main;

public class BatteryMega extends IpodBattery {

	public BatteryMega() {
		super(602, 120, 175);
		this.setMaxStackSize(1);
		this.setCreativeTab(Main.tabIpod);
	}

}
