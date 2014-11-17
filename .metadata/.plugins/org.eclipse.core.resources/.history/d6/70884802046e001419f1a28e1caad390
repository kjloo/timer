import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Alarm {

	String DIR = "sounds/";
	private Clip clip;
	
	
	public Alarm(String filename) {
	  try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(DIR + filename).getAbsoluteFile());
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	    } catch(Exception ex) {
	        System.out.println("Error with opening sound file.");
	        ex.printStackTrace();
	    }
	}
	
	public void play() {
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}

}
