package no.jgdx.perseus.client.audio;

import java.io.File;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public final class SoundSystem {

	private final static SoundSystem SINGLETON_INSTANCE = new SoundSystem();

	private Mood currentMood = Mood.CALM;
	private int volume;

	/**
	 * Returns the SINGLETON instance of this class.
	 * 
	 * @return a soundsystem. The only sound system.
	 */
	public synchronized static SoundSystem getSoundSystem() {
		return SINGLETON_INSTANCE;
	}

	private SoundSystem() {
		System.out.println("SoundSystem supports: " + Arrays.toString(AudioSystem.getAudioFileTypes()));
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void changeMood(Mood mood) {
		if (mood == currentMood) {
			System.out.println("Tried changing mood to the same mood! " + mood);
		}
		if (mood == Mood.WAR) {
//			playSound("track01.wav");
		}

		System.out.println("Update mood:" + currentMood + " to " + mood);
		currentMood = mood;
	}

	private static synchronized void playSound(final String url) {
		Thread t = null;
		t = new Thread(new Runnable() {
			public void run() {
				try {

					File soundFile = new File(url);
					AudioInputStream soundIn = AudioSystem.getAudioInputStream(soundFile);

					System.out.println(soundIn.getFormat());

					AudioFormat format = soundIn.getFormat();

					DataLine.Info info = new DataLine.Info(Clip.class, format);

					Clip clip = (Clip) AudioSystem.getLine(info);
					clip.open(soundIn);
					clip.start();

				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
				}
			}
		});
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}
}
