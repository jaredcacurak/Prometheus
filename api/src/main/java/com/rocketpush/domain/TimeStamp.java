//<timestamp> is Show Time Stamp as hour:min:sec:frame Each byte is transmitted
//followed by the inverse. hour has a range of 0-24 (00h..18h), min has a range
//of 0-59 (00h..3Bh), sec has a range of 0- 59 (00h..3Bh) and frame has a range
//of 0-19 (00h..13h). Note each frame is 1/20 of a second or 50ms. A complete 
//time stamp is 8 bytes in length.
package com.rocketpush.domain;

public class TimeStamp {
	
	private final int hours;
	private final int minutes;
	private final int seconds;
	private final int frames;
	
	public TimeStamp(int hours, int minutes, int seconds, int frames) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.frames = frames;
	}
	
	public int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public int getFrames() {
		return frames;
	}
	
	public String toString() {
		return String.format("%d:%d:%d:%d", hours, minutes, seconds, frames);
	}
}
