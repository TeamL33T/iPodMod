package TeamL33T.IpodMod;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import java.io.IOException;

public class SoundListReader {
	
	public String[] getSoundList() throws IOException {
		// Get InputStream of sounds.txt then acquire its string version
		InputStream is = getClass().getResourceAsStream("/TeamL33T/IpodMod/sounds.txt");
		String soundUnsp = IOUtils.toString(is, "UTF-8");
		
		// Split soundUnsp line by line and return it
		String[] lines = soundUnsp.split("[\\r\\n]+");
		return lines;
	}
	
}
