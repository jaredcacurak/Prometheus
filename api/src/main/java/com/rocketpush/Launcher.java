package com.rocketpush;

import static com.rocketpush.command.CommandValue.*;
import static com.rocketpush.command.CommandFactory.build;
import com.rocketpush.domain.Cue;
import com.rocketpush.domain.CueList;
import com.rocketpush.domain.Slave;
import com.rocketpush.domain.Time;
import com.rocketpush.domain.TimeStamp;
import com.rocketpush.command.Command;
import com.rocketpush.communication.Communication;

public class Launcher implements Commandable {

	private final Communication communication;
	private final Slave allSlaves = new Slave(0);

	public Launcher(Communication comm) {
		this.communication = comm;
	}

	public void reset(Slave slave) {
		Command command = build(RESET, slave);
		this.communication.send(command);
	}

	public void fireCue(Slave slave, TimeStamp timeStamp, CueList cues) {
		Command command = build(FIRE_CUE, slave, timeStamp, cues);
		this.communication.send(command);
	}

	public void testCue(Slave slave, Cue cue) {
		Command command = build(TEST_CUE, slave, cue);
		this.communication.send(command);
	}

	public void startShow(TimeStamp timeStamp) {
		Command command = build(START_SHOW, allSlaves, timeStamp);
		this.communication.send(command);
	}

	public void endShow(TimeStamp timeStamp) {
		Command command = build(END_SHOW, allSlaves, timeStamp);
		this.communication.send(command);
	}

	public void runSync(TimeStamp timeStamp) {
		Command command = build(RUN_SYNC, allSlaves, timeStamp);
		this.communication.send(command);
	}

	public void testSlaveComm(Slave slave) {
		Command command = build(TEST_SLAVE_COMM, slave);
		this.communication.send(command);
	}

	public void requestSlaveVersion(Slave slave) {
		Command command = build(REQUEST_SLAVE_VERSION, slave);
		this.communication.send(command);
	}

	public void requestFireTime(Slave slave) {
		Command command = build(REQUEST_FIRE_TIME, slave);
		this.communication.send(command);
	}

	public void disarmSlave(Slave slave) {
		Command command = build(DISARM_SLAVE, slave);
		this.communication.send(command);
	}

	public void armSlave(Slave slave) {
		Command command = build(ARM_SLAVE, slave);
		this.communication.send(command);
	}

	public void requestSlaveStatus(Slave slave) {
		Command command = build(REQUEST_SLAVE_STATUS, slave);
		this.communication.send(command);
	}
	
	public void requestBatteryLevel(Slave slave) {
		Command command = build(REQUEST_BATTERY_LEVEL, slave);
		this.communication.send(command);
	}

	public void setCueFireTime(Slave slave, Time time) {
		Command command = build(SET_CUE_FIRE_TIME, slave, time);
		this.communication.send(command);
	}
}