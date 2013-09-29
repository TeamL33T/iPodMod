package org.TeamL33T.IpodMod.battery;

import org.TeamL33T.IpodMod.Main;

public class BatteryPro extends IpodBattery {

	public BatteryPro() {
		super(601, 60, 100);
		this.setMaxStackSize(1);
		this.setCreativeTab(Main.tabIpod);
	}
	
}
