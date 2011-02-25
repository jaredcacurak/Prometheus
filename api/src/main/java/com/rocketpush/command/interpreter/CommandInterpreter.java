package com.rocketpush.command.interpreter;

import static com.rocketpush.command.CommandValue.*;
import com.rocketpush.command.Command;

public class CommandInterpreter {
	private final AbstractCommandInterpreterStrategy ackStrategy;
	private final AbstractCommandInterpreterStrategy versionResponseStrategy;
	private final AbstractCommandInterpreterStrategy testCueResponseStrategy;
	private AbstractCommandInterpreterStrategy strategy;

	public CommandInterpreter() {
		this.ackStrategy = new AckInterpreterStrategy();
		this.versionResponseStrategy = new VersionResponseInterpreterStrategy();
		this.testCueResponseStrategy = new TestCueResponseInterpreterStrategy();
	}

	public boolean readValue(int value) {
		if (isAck(value)) {
			this.strategy = ackStrategy;
		} else if (isVersionResponse(value)) {
			this.strategy = versionResponseStrategy;
		} else if (isTestCueResponse(value)) {
			this.strategy = testCueResponseStrategy;
		}

		return (this.strategy != null) ? strategy.readValue(value) : false;
	}

	public Command build() {
		return this.strategy.build();
	}

	private boolean isAck(int i) {
		return i == ACK.value() || i == FIRE_TIME_RESPONSE.value()
				|| i == SLAVE_STATUS_RESPONSE.value();
	}

	private boolean isVersionResponse(int i) {
		return i == VERSION_RESPONSE.value();
	}

	private boolean isTestCueResponse(int i) {
		return i == TEST_CUE_RESPONSE.value();
	}
}