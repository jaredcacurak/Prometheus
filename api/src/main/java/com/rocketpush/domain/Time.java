package com.rocketpush.domain;

// is Fire time as 1-63ms (01h...3Fh) followed by inverse of byte (FEh...C0h)
public class Time {
	
	private final int value;
	
	public Time(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return "Time: " + this.value;
	}
}
