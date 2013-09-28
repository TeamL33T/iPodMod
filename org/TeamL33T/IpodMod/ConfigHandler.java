package org.TeamL33T.IpodMod;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public static Configuration config;
	public static String[] oggNames;
	
	public static void init(File configFile) {
		config = new Configuration(configFile);
		
		config.load();
		
		String oggNamesUnsplit = config.get(Configuration.CATEGORY_GENERAL, "OggNames", "").getString();
		oggNames = oggNamesUnsplit.split(",");
		
		for (String ogg : oggNames) {
			System.out.println(ogg);
		}
		
		config.save();
	}
	
}
