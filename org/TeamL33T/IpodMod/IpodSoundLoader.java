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

public class IpodSoundLoader {
	
	private String primePath, path, lines;
	public static ArrayList sounds;
	
	public IpodSoundLoader() throws IOException {
		
		// Get string from the sounds.txt externally
		primePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		path = URLDecoder.decode(primePath, "UTF_8");
		ZipFile zip = new ZipFile(path);
		InputStream is = zip.getInputStream(zip.getEntry("org/TeamL33T/IpodMod/sounds.txt"));
		lines = getStringFromInputStream(is);
		zip.close();
		
		// Get each line of 'lines' and assign to oggFiles
		ArrayList oggFiles = new ArrayList();
		int x = 0, y = 0;
		
		for (int i=0; i<=lines.length(); i++) {
			if (lines.substring(i, 1) == "\n") {
				oggFiles.add(lines.substring(x, y));
				x = i + 1;
				y = 0;
			}
			y++;
		}
		
		// Assign oggFiles to sounds list for public access
		sounds = oggFiles;
		
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
	
	private String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
	}
	
}
