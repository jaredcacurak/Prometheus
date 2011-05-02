package com.rocketpush.command;

public enum CommandValue { 
	ACK(0x61),	
	TEST_CUE_RESPONSE(0x63),
	VERSION_RESPONSE(0x65),
	FIRE_TIME_RESPONSE(0x67),
	SLAVE_STATUS_RESPONSE(0x6A),
	RESET(0x81),	
	FIRE_CUE(0x82),
	TEST_CUE(0x83),
	TEST_SLAVE_COMM(0x84), 
	REQUEST_SLAVE_VERSION(0x85),
	SET_CUE_FIRE_TIME(0x86),
	REQUEST_FIRE_TIME(0x87), 
	DISARM_SLAVE(0x88), 
	ARM_SLAVE(0x89),
	REQUEST_SLAVE_STATUS(0x8A),
	REQUEST_BATTERY_LEVEL(0x8B),
	START_SHOW(0x8C),
	END_SHOW(0x8D),
	RUN_SYNC(0x8E);
		
	private final int value;
	
	CommandValue(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
}