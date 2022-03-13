package io.player;

import java.util.Timer;

import io.player.task.AudioPlayerTask;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		final long delay = 1000 * 60 * 10;
		//final long delay = 1000 * 30;
		
		new Timer().scheduleAtFixedRate(new AudioPlayerTask(), 0, delay);
		
		while(true) {
		    Thread.sleep(delay);
		}
	}

}
