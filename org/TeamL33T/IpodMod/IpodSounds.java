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
import net.minecraftforge.event.ForgeSubscribe;

public class IpodSounds {
	
	public static ArrayList sounds;
	
	public IpodSounds() {
		for (String ogg : ConfigHandler.oggNames) {
			sounds.add(ogg);
		}
	}
	
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		for (Object ogg : sounds) {
			if (ogg instanceof String) {
				String mainOgg = (String) ogg;
				event.manager.addSound("ipodmod:" + ogg);
			} else {
				continue;
			}
		}
	}
	
}
