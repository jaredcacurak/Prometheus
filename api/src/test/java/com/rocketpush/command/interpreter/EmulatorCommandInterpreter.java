package com.rocketpush.command.interpreter;

import static com.rocketpush.command.CommandValue.*;
import com.rocketpush.command.Command;

public class EmulatorCommandInterpreter extends CommandInterpreter {

	@Override
	public Command build() {
		// TODO Auto-generated method stub
		return null;
	}

	void evaluate() {
		// TODO Auto-generated method stub

	}

	boolean isInValidState() {
		// TODO Auto-generated method stub
		return false;
	}

	boolean isCommandValue(int i) {
		return i == END_SHOW.value() || i == FIRE_CUE.value() || i == TEST_CUE.value() || i == START_SHOW.value() || i == RUN_SYNC.value();
	}
}