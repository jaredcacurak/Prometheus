package com.rocketpush.domain;

// cue #1-32 (01h...19h) followed by inverse of cue number (FEh...E6h)
public class Cue {

	private final int id;

	public Cue(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "Cue: " + this.id;
	}
}
