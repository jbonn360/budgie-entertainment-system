package io.player.budgie.task;

import java.util.Random;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.player.budgie.audioplayer.AudioPlayer;

@Component
public class AudioPlayerTask extends TimerTask {
	private final AudioPlayer audioPlayer;
	private final Random random;

	public AudioPlayerTask(@Autowired AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
		this.random = new Random();
	}

	@Override
	public void run() {
		//boolean roll = random.nextBoolean();
		boolean roll = true;

		if (roll) {
			System.out.println("Playing audio at " + audioPlayer.getVolume() * 100 +"% volume");
			audioPlayer.playAudio();
		}
	}
}
