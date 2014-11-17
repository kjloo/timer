package main;
import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.apple.mrj.macos.carbon.Timer;

public class Alarm {

	String DIR = "sounds/";
	private Clip clip;

	public Alarm(String filename) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(Alarm.class.getResource(DIR + filename));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception ex) {
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