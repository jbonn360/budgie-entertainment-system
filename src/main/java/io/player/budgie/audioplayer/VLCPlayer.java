package io.player.budgie.audioplayer;

import java.io.IOException;

public class VLCPlayer implements AudioPlayer {

	@Override
	public void playAudio() {
		try {
			new ProcessBuilder("killall", "vlc").start();

			Thread.sleep(500);

			new ProcessBuilder("vlc", "--intf", "dummy", "file:///home/pi/budgie-audio-player/budgie.mp3").start();
			// new ProcessBuilder("vlc", "file:///home/darrow/Desktop/budgie.mp3").start();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
