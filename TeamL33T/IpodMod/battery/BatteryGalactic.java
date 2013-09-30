package org.TeamL33T.IpodMod.battery;

import org.TeamL33T.IpodMod.Main;

public class BatteryGalactic extends IpodBattery {

	public BatteryGalactic() {
		super(604, 300, 350);
		this.setMaxStackSize(1);
		this.setCreativeTab(Main.tabIpod);
	}

}
