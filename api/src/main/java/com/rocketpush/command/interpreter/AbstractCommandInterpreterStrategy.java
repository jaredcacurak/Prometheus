package com.rocketpush.command.interpreter;

import com.rocketpush.command.Command;
import com.rocketpush.command.CommandFactory;

abstract class AbstractCommandInterpreterStrategy {

	protected int previous;
	protected int current;

	abstract Command build();

	abstract void evaluate();

	abstract boolean isInValidState();

	boolean readValue(int i) {
		this.previous = current;
		this.current = i;
		evaluate();

		return isInValidState();
	}

	boolean isInverse(int x, int y) {
		return CommandFactory.inverseOf(x) == y;
	}

	boolean hasValue(int i) {
		return i != -1;
	}
}