package com.rocketpush.communication;

import java.util.ArrayList;
import java.util.List;

import com.rocketpush.command.Command;

public class FakeCommunication implements Communication {
	
	private final List<Command> commands;
	
	public FakeCommunication() {
		commands = new ArrayList<Command>();
	}

	public void send(Command command) {
		addCommand(command);
	}
	
	public void close() {
	}
	
	public int[] getLastCommand() {
		int last = commands.size() - 1;
		Command lastCommand = commands.get(last);
		
		return lastCommand.value();
	}

	private void addCommand(Command command) {
		commands.add(command);
	}	
}