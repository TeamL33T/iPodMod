package TeamL33T.IpodMod;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import net.minecraft.client.Minecraft;

public class WavPlayer extends Thread {

	/* States
	 * 0 - Stopped
	 * 1 - Paused
	 * 2 - Playing
	 */
	
	private String filename;
	private Object loopPlay = new Object();
	private AudioInputStream in;
	private AudioInputStream din;
	private AudioFormat baseFormat;
	private AudioFormat decodedFormat;
	private boolean isPaused = false;
	private boolean isStopped = true;
	public final int MP3_STATE_STOPPED = 0;
	public final int MP3_STATE_PAUSED = 1;
	public final int MP3_STATE_PLAYING = 2;
	public boolean called = false;
	private int currentSongIndex = 0;
	private String[] soundList;
	public static boolean secondCall = false;
	
	// Universal Flags
	public boolean running = true;
	public boolean override = false;
	
	public WavPlayer() {
		try {
			soundList = Main.SLR.getSoundList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			playSound();
			checkFlag();
			System.out.println("Done! Going for the next one :)");
			
			if (!isStopped && !override) {
				print("Am really gettin' the next one!");
				setFilenameAsNext();
			}
			
			override = false;
		}
	}
	
	public void playSound()
	{
	  System.out.println("playMP3 entered");
	  try {
		System.out.println("Try branch of playmp3 entered, getting inputstream");
	    in = AudioSystem.getAudioInputStream(getInputStream(filename));
	    System.out.println("Now setting up mp3 prefs");
	    baseFormat = in.getFormat();
	    decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
	    	baseFormat.getSampleRate(),
	    	16,
	    	baseFormat.getChannels(),
	    	baseFormat.getChannels() * 2,
	    	baseFormat.getSampleRate(),
	        false);
	    din = AudioSystem.getAudioInputStream(decodedFormat, in);

	    internalPlay(decodedFormat, din);
	    in.close();
	  } catch (Exception e) {
		  Minecraft.getMinecraft().thePlayer.addChatMessage("'"+filename+"' cannot be played because its properties aren't supported. Sorry for that :(");
		  e.printStackTrace();
		  this.stopSound();
	  }
	} 
	
	public void stopSound() {
		print("Stopzoids");
		if (getPlayerState() == 1 || getPlayerState() == 2) {
			isStopped = true;
			running = false;
		}
	}
	
	public void pauseSound() {
		print("Pauszoids");
		if (getPlayerState() == 2) {
			isPaused = true;
			running = false;
		}
	}
	
	public void resumeSound() {
		print("Resumazoids");
		running = true;
		isPaused = false;
	}
	
	public void overrideSound() {
		print("Overzoids");
		override = true;
		isStopped = true;
		running = true;
	}
	
	public void replaySound() {
		if (this.getPlayerState() != 0) {
			stopSound();
			playSound();
		}
	}
	
	public void changeVolume(int volume) {
		if (!(volume >= 0 && volume <= 100)) return;
	}

	private void internalPlay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException
	{
	  System.out.println("internalPlay entered!");
	  byte[] data = new byte[4096];
	  SourceDataLine line = getLine(targetFormat); 
	  if (line != null)
	  {
	    // Start
		isPaused = false;
		isStopped = false;
	    line.start();
	    int nBytesRead = 0, nBytesWritten = 0;
	    synchronized (loopPlay) {
	    	while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
	    		if (isPaused && !isStopped) {
	    			print("Pausing from internal...");
	    			line.stop();
	    			checkFlag();
	    		}
	    		
	    		if (isStopped) {
	    			print("Stopping from internal...");
	    			break;
	    		}
	    	
	    		if (!line.isRunning()) {
	    			line.start();
	    		}
	    		
	    		line.write(data, 0, nBytesRead);
	    	}
	    }
	    
	    // Stop
	    System.out.println("Stopping wav...");
	    line.drain();
	    line.stop();
	    line.close();
	    din.close();
	    
	    isPaused = false;
	    called = false;
	    
	    if (override) {
	    	isStopped = false;
	    }
	  } 
	}

	private static SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
	{
	  SourceDataLine res = null;
	  DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	  res = (SourceDataLine) AudioSystem.getLine(info);
	  res.open(audioFormat);
	  return res;
	} 
	
	private InputStream getInputStream(String filename) {
		InputStream is = getClass().getResourceAsStream("/assets/ipodmod/sound/" + filename);
		InputStream bis = new BufferedInputStream(is);
		System.out.println("InputStream found from --> /assets/ipodmod/sound/" + filename);
		System.out.println("Returning 'is' var");
		return bis;
	}
	
	public int getPlayerState() {
		if (isStopped) {
			return 0;
		} else if (isPaused) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public void setFilename(String fN, int index) {
		this.filename = fN;
		this.currentSongIndex = index;
	}
	
	private void checkFlag() {
		print("Checking flag");
		boolean one = true;
		while (running == false) {
			if (one) {
				print("Thread is PAUSED YEAAAAAAAHHHHHHHHHHH!!!");
				one = false;
			}
		}
		print("Thread is Back :P");
	}
	
	private void setFilenameAsNext() {
		int predict = currentSongIndex + 1;
		
		if (predict >= soundList.length) {
			currentSongIndex = 0;
		} else {
			currentSongIndex = predict;
		}
		
		setFilename(soundList[currentSongIndex], currentSongIndex);
	}
	
	private boolean hasSounds() {
		return soundList.length <= 0 ? false : true;
	}
	
	private void print(String s) {
		System.out.println(s);
	}
	
}
