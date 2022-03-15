package io.player.budgie.audioplayer;

public interface AudioPlayer {
	public void playAudio();
	
	public void stopAudio();
	
	public double getVolume();
	
	public void setVolume(double volume);
}
