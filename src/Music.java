

import java.io.*;
import javax.sound.sampled.*;

public class Music{
	
	File audio;
	AudioInputStream ais;
	Clip clip;

	public Music(String audioFile) {
		try {

			audio = new File(audioFile);
			ais = AudioSystem.getAudioInputStream(audio);
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}
	
	public void stop() {
		clip.close();
	}

}
