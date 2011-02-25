package com.rocketpush.command.interpreter;

import java.util.concurrent.atomic.AtomicInteger;

import static com.rocketpush.command.CommandValue.*;
import com.rocketpush.command.Command;

class AckInterpreterStrategy extends AbstractCommandInterpreterStrategy {
	private static final int LENGTH = 6;
	private final AtomicInteger counter;
	private int[] sixValues = new int[6];
	
	AckInterpreterStrategy() {
		this.counter = new AtomicInteger(0);
		this.sixValues = new int[LENGTH];
	}

	@Override
	Command build() {
		int[] values = trimable(sixValues);
		Command command = new Command(values);
		clear();

		return command;
	}

	@Override
	void evaluate() {
		if ((counter.get() == 0 && isCommandValue(current) && current != -1) || (counter.get() > 0 && !isCommandValue(current) && current != -1)) {
			sixValues[counter.getAndIncrement()] = current;
		}
	}

	@Override
	boolean isInValidState() {
		return counter.get() == 6 || (counter.get() == 4 && (isCommandValue(current) || current == -1));
	}

	private int[] trimable(int[] ints) {
		int[] value = { ints[0], ints[1], ints[2], ints[3] };

		return (ints[4] == 0 && ints[5] == 0) ? value : ints;
	}

	int getCommand() {
		return getValue(0);
	}

	int getInverseOfCommand() {
		return getValue(1);
	}

	int getFirstValue() {
		return getValue(2);
	}

	int getInverseOfFirstValue() {
		return getValue(3);
	}

	int getSecondValue() {
		return getValue(4);
	}

	int getInverseOfSecondValue() {
		return getValue(5);
	}

	int getValue(int i) {
		return sixValues[i];
	}

	private void clear() {
		counter.set(0);
		sixValues = new int[6];
	}

	private boolean isCommandValue(int i) {
		return i == ACK.value() || i == FIRE_TIME_RESPONSE.value() || i == SLAVE_STATUS_RESPONSE.value();
	}
}