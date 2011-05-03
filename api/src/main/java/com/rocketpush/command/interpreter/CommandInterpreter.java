package com.rocketpush.command.interpreter;

import static com.rocketpush.command.CommandValue.*;
import com.rocketpush.command.Command;

public class CommandInterpreter {
	private final AbstractCommandInterpreterStrategy ackStrategy;
	private final AbstractCommandInterpreterStrategy versionResponseStrategy;
	private final AbstractCommandInterpreterStrategy testCueResponseStrategy;
	private final AbstractCommandInterpreterStrategy batteryLevelResponseStrategy;
	private AbstractCommandInterpreterStrategy strategy;

	public CommandInterpreter() {
		this.ackStrategy = new AckInterpreterStrategy();
		this.versionResponseStrategy = new VersionResponseInterpreterStrategy();
		this.testCueResponseStrategy = new TestCueResponseInterpreterStrategy();
		this.batteryLevelResponseStrategy = new BatteryLevelResponseInterpreterStrategy();
	}

	public boolean readValue(int value) {
		if (isAck(value)) {
			this.strategy = ackStrategy;
		} else if (isVersionResponse(value)) {
			this.strategy = versionResponseStrategy;
		} else if (isTestCueResponse(value)) {
			this.strategy = testCueResponseStrategy;
		} else if (isBatteryLevelResponse(value)) {
			this.strategy = batteryLevelResponseStrategy;
		}

		return (null != this.strategy) ? strategy.readValue(value) : false;
	}

	public Command build() {
		return this.strategy.build();
	}

	private boolean isAck(int i) {
		return i == ACK.value() || i == FIRE_TIME_RESPONSE.value() || i == SLAVE_STATUS_RESPONSE.value();
	}

	private boolean isVersionResponse(int i) {
		return i == VERSION_RESPONSE.value();
	}

	private boolean isTestCueResponse(int i) {
		return i == TEST_CUE_RESPONSE.value();
	}
	
	private boolean isBatteryLevelResponse(int i) {
		return i == SLAVE_BATTERY_RESPONSE.value();
	}
}