package com.rocketpush.command.interpreter;

import java.util.concurrent.atomic.AtomicInteger;

import com.rocketpush.command.Command;

class VersionResponseInterpreterStrategy extends AbstractCommandInterpreterStrategy {
	
	private static final int LENGTH = 21;
	private final AtomicInteger index;
	private int[] values;
	
	VersionResponseInterpreterStrategy() {
		this.values = new int[LENGTH];
		this.index = new AtomicInteger(0);
	}
	
	@Override
	Command build() {
		Command command = new Command(values);
		clear();
		
		return command;
	}

	@Override
	void evaluate() {
		if (current != -1) values[index.getAndIncrement()] = current;
	}

	@Override
	boolean isInValidState() {
		return current == 0xFF && previous == 0x00;		
	}
	
	void clear() {
		values = new int[LENGTH];
		index.set(0);
	}
}