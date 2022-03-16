package io.budgie.audioplayer;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

@Component
public class JavaAudioPlayer implements AudioPlayer {
	private final static Logger LOG = LoggerFactory.getLogger(JavaAudioPlayer.class);

	private final MediaPlayer mediaPlayer;

	public JavaAudioPlayer(@Value("${app.player.starting-volume}") final double startingVolume,
			@Value("classpath:static/audio/budgie.mp3") final Resource audioFileResource) {		
		Optional<URI> audioFileURI = Optional.empty();
		try {
			audioFileURI = Optional.ofNullable(audioFileResource.getURI());
		} catch (IOException e) {
			LOG.error(String.format("Audio file not found '%s'", audioFileURI), e);
		}

		// needed for the media player to be able to play audio
		new JFXPanel(); 
		
		this.mediaPlayer = audioFileURI.map(URI::toString).map(Media::new).map(MediaPlayer::new).orElseThrow();
		this.mediaPlayer.setVolume(startingVolume);
	}

	@Override
	public void playAudio() {
		//System.out.println("Playing audio at " + getVolume() * 100 + "% volume");
		
		mediaPlayer.stop();
		mediaPlayer.play();
	}
	
	@Override
	public void stopAudio() {
		mediaPlayer.stop();
	}

	@Override
	public double getVolume() {
		return mediaPlayer.getVolume();
	}

	@Override
	public void setVolume(double volume) {
		mediaPlayer.setVolume(volume);
	}
}
