package io.player.budgie.service;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.player.budgie.audioplayer.AudioPlayer;
import io.player.budgie.task.AudioPlayerTask;

@Service
public class AudioPlayerService {
	private final AudioPlayer audioPlayer;
	private final long timerPeriodMillis;

	private Timer timer;

	private boolean isPlayerTaskScheduled;

	public AudioPlayerService(@Autowired AudioPlayer audioPlayer,
			@Value("${app.player.timer.task-period-mins}") int timerPeriod) {
		this.audioPlayer = audioPlayer;
		this.timerPeriodMillis = 1000 * 60 * timerPeriod;
		this.isPlayerTaskScheduled = false;
	}

	public void registerAudioPlayerTask() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new AudioPlayerTask(audioPlayer), timerPeriodMillis, timerPeriodMillis);

		// always play track instantly first time round as a form of user feedback
		audioPlayer.playAudio();

		this.isPlayerTaskScheduled = true;
	}

	public void deregisterAudioPlayerTask() {
		timer.cancel();

		this.isPlayerTaskScheduled = false;
	}

	public void switchStateTo(boolean state) {
		if (state && !isPlayerTaskScheduled)
			registerAudioPlayerTask();
		else if (!state && isPlayerTaskScheduled) {
			audioPlayer.stopAudio();
			deregisterAudioPlayerTask();
		}
			
	}

	public double getPlayerVolume() {
		return audioPlayer.getVolume();
	}

	public void setPlayerVolume(double volume) {
		audioPlayer.setVolume(volume);
	}

	public boolean isPlayerTaskScheduled() {
		return isPlayerTaskScheduled;
	}
}
