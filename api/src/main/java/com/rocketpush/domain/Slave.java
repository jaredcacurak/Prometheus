package com.rocketpush.domain;

// slave id #1-63 (01h...3Fh) followed by inverse of slave number (FEh...C0h) 
// Slave #0 is global command to all slaves reserved for Sync command and Global 
// Disarm, Start and End Show, Set Fire Time and Reset commands.
public class Slave {
	
	private final int id;

	public Slave(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "Slave: " + this.id;
	}
}