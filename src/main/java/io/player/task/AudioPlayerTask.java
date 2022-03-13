package io.player.task;

import java.util.Random;
import java.util.TimerTask;

import io.player.audio.AudioPlayer;
import io.player.audio.VLCPlayer;

public class AudioPlayerTask extends TimerTask{
	private AudioPlayer audioPlayer;
	
	public AudioPlayerTask() {
		audioPlayer = new VLCPlayer();
	}
	
	@Override
    public void run() {
		Random r = new Random();
		
		boolean toPlay = r.nextBoolean();
		
		System.out.println("toPlay is: " + toPlay);
		
		if(toPlay)
			audioPlayer.playAudio();
    }
}
