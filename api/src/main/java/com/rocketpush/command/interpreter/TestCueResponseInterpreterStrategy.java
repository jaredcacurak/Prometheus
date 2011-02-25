package com.rocketpush.command.interpreter;

import java.util.concurrent.atomic.AtomicInteger;

import com.rocketpush.command.Command;

class TestCueResponseInterpreterStrategy extends AbstractCommandInterpreterStrategy {
	private static final int LENGTH = 8;
	private final AtomicInteger index;
	private int[] values;
	
	TestCueResponseInterpreterStrategy() {
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
		return this.index.get() == LENGTH;
	}
	
	void clear() {
		values = new int[LENGTH];
		index.set(0);
	}
}