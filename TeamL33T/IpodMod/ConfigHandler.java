package org.TeamL33T.IpodMod;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public static Configuration config;
	public static String[] sounds;
	
	public static void init(File configFile) {
		config = new Configuration(configFile);
		
		config.load();
		
		sounds = config.get(Configuration.CATEGORY_GENERAL, "OggNames", "80s - Aha - Take On Me.ogg,B.ogg").getString().split(",");
		
		config.save();
	}
	
}
