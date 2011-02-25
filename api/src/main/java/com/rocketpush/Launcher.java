package com.rocketpush;

import static com.rocketpush.command.CommandFactory.build;
import static com.rocketpush.command.CommandValue.*;
import com.rocketpush.domain.*;
import com.rocketpush.command.Command;
import com.rocketpush.communication.Communication;

public class Launcher implements Commandable {

	private final Communication communication;

	public Launcher(Communication comm) {
		this.communication = comm;
	}

	@Override
	public void reset(Slave slave) {
		Command command = build(RESET, slave);
		this.communication.send(command);
	}

	@Override
	public void fireCue(Slave slave, CueList cues) {
		Command command = build(FIRE_CUE, slave, cues);
		this.communication.send(command);
	}

	@Override
	public void testCue(Slave slave, Cue cue) {
		Command command = build(TEST_CUE, slave, cue);
		this.communication.send(command);
	}

	@Override
	public void startShow() {
		Slave allSlaves = new Slave(0);
		Command command = build(START_SHOW, allSlaves);
		this.communication.send(command);
	}

	@Override
	public void endShow() {
		Slave allSlaves = new Slave(0);
		Command command = build(END_SHOW, allSlaves);
		this.communication.send(command);
	}

	@Override
	public void runSync() {
		Slave allSlaves = new Slave(0);
		Command command = build(RUN_SYNC, allSlaves);
		this.communication.send(command);
	}

	@Override
	public void testSlaveComm(Slave slave) {
		Command command = build(TEST_SLAVE_COMM, slave);
		this.communication.send(command);
	}

	@Override
	public void requestSlaveVersion(Slave slave) {
		Command command = build(REQUEST_SLAVE_VERSION, slave);
		this.communication.send(command);
	}

	@Override
	public void requestFireTime(Slave slave) {
		Command command = build(REQUEST_FIRE_TIME, slave);
		this.communication.send(command);
	}

	@Override
	public void disarmSlave(Slave slave) {
		Command command = build(DISARM_SLAVE, slave);
		this.communication.send(command);
	}

	@Override
	public void armSlave(Slave slave) {
		Command command = build(ARM_SLAVE, slave);
		this.communication.send(command);
	}

	@Override
	public void requestSlaveStatus(Slave slave) {
		Command command = build(REQUEST_SLAVE_STATUS, slave);
		this.communication.send(command);
	}

	@Override
	public void setCueFireTime(Slave slave, Time time) {
		Command command = build(SET_CUE_FIRE_TIME, slave, time);
		this.communication.send(command);
	}
}