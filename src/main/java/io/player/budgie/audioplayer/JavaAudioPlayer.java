package io.player.budgie.audioplayer;

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
	private static Logger LOG = LoggerFactory.getLogger(JavaAudioPlayer.class);
	
	private double volume;

	@Value("classpath:static/audio/budgie.mp3")
	private Resource audioFileResource;
	
	public JavaAudioPlayer(@Value("${app.player.starting-volume}") double startingVolume) {
		new JFXPanel();
		this.volume = startingVolume;
	}

	@Override
	public void playAudio() {
		Optional<URI> audioFileURI = Optional.empty();
		try {
			audioFileURI = Optional.ofNullable(audioFileResource.getURI());
		} catch (IOException e) {
			LOG.error(String.format("Audio file not found '%s'", audioFileURI), e);
		}
		
		// this is the equivalent of the 3 commented lines below
		audioFileURI.map(URI::toString).map(Media::new).map(MediaPlayer::new).ifPresent((player) -> setPlayerVolumeAndPlay(player));
		
//		final Media media = new Media(audioFileURI.get().toString());
//		final MediaPlayer mediaPlayer = new MediaPlayer(media);		
//		mediaPlayer.setVolume(volume);
//		mediaPlayer.play();
	}

	@Override
	public double getVolume() {
		return volume;
	}

	@Override
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	private void setPlayerVolumeAndPlay(final MediaPlayer player) {
		player.setVolume(volume);
		player.play();
	}
}
