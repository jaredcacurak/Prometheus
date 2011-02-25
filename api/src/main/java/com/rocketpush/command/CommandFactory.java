package com.rocketpush.command;

import com.rocketpush.domain.Cue;
import com.rocketpush.domain.CueList;
import com.rocketpush.domain.Slave;
import com.rocketpush.domain.Time;

public class CommandFactory {
	
	private CommandFactory() {		
	}
	
	public static Command build(CommandValue command, Slave slave) {
		int inverseOfCommand = inverseOf(command.value());
		int inverseOfSlaveId = inverseOf(slave.getId());
		int[] message = { command.value(), inverseOfCommand, slave.getId(),	inverseOfSlaveId };

		return new Command(message);
	}
	
	public static Command build(CommandValue command, Slave slave, Time time) {
		int inverseOfCommand = inverseOf(command.value());
		int inverseOfSlave = inverseOf(slave.getId());
		int inverseOfTimeValue = inverseOf(time.getValue());
		int[] message = { command.value(), inverseOfCommand, slave.getId(), inverseOfSlave, time.getValue(), inverseOfTimeValue };

		return new Command(message);
	}

	public static Command build(CommandValue command, Slave slave, Cue cue) {
		int inverseOfCommand = inverseOf(command.value());
		int inverseOfSlaveId = inverseOf(slave.getId());
		int inverseOfCueId = inverseOf(cue.getId());
		int[] message = { command.value(), inverseOfCommand, slave.getId(), inverseOfSlaveId, cue.getId(), inverseOfCueId };

		return new Command(message);
	}
	
	public static Command build(CommandValue command, Slave slave, CueList cues) {
		int inverseOfCommand = inverseOf(command.value());
		int inverseOfSlaveId = inverseOf(slave.getId());
		int[] message = new int[4 + (cues.size() * 2) + 2];
		int[] head = { command.value(), inverseOfCommand, slave.getId(), inverseOfSlaveId };
		int[] tail = { 0x00, 0xFF };
		int[] listOfCues = cues.toArray();
		int[] listOfCuesAndTheirInverses = new int[2 * listOfCues.length];
		
		for (int i = 0; i < listOfCuesAndTheirInverses.length; i++) {
			int j = (i == 0) ? i : i / 2;
			int cue = listOfCues[j];
			listOfCuesAndTheirInverses[i] = cue;
			i += 1;
			listOfCuesAndTheirInverses[i] = inverseOf(cue);
		}
		
		System.arraycopy(head, 0, message, 0, head.length);
		System.arraycopy(listOfCuesAndTheirInverses, 0, message, 4, listOfCuesAndTheirInverses.length);
		System.arraycopy(tail, 0, message, head.length + listOfCuesAndTheirInverses.length, 2);
		
		return new Command(message);
	}

	public static int inverseOf(int number) {
		return number ^ 0xFF;
	}
}
