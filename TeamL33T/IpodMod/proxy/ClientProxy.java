package org.TeamL33T.IpodMod.proxy;

import org.TeamL33T.IpodMod.IpodSounds;

import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerIpodSounds() {
		MinecraftForge.EVENT_BUS.register(new IpodSounds());
	}
	
}
