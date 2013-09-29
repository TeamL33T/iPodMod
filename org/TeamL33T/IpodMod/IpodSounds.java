package org.TeamL33T.IpodMod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.zip.ZipFile;

import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.ForgeSubscribe;

public class IpodSounds {
	
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		for (Object ogg : ConfigHandler.sounds) {
			if (ogg instanceof String) {
				String mainOgg = (String) ogg;
				event.manager.addSound("ipodmod:" + ogg);
			} else {
				continue;
			}
		}
	}
	
}
