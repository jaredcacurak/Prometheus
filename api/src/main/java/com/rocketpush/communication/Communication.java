package com.rocketpush.communication;

import java.io.Closeable;

import com.rocketpush.command.Command;

public interface Communication extends Closeable {

	void send(Command command);
	
	void close();
}
