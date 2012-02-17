package com.rocketpush.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.rocketpush.command.Command;
import com.rocketpush.command.interpreter.CommandInterpreter;

import gnu.io.*;
import static gnu.io.SerialPort.*;

public final class SerialCommunication implements Communication {
	
	private final CommPort commPort;
	private final Queue<Command> commandQueue; 
	private final AtomicBoolean isConnected = new AtomicBoolean(false);

	public SerialCommunication(String portName) {		
		commandQueue = new ConcurrentLinkedQueue<Command>();
		CommPortIdentifier identifiedPort = tryToIdentifyPort(portName);
		this.commPort = tryToGetOpenCommPort(identifiedPort);		
		
		OutputStream outputStream = tryToGetOutputStream();
		Runnable commandQueueProcessor = new CommandQueueProcessor(outputStream);
		new Thread(commandQueueProcessor, "CommandQueueProcessor").start();
		
		InputStream inputStream = tryToGetInputStream();
		Runnable inputStreamReader = new SerialInputReader(inputStream);
		new Thread(inputStreamReader, "InputStreamReader").start();
	}

	@Override
	public void send(Command command) {
		commandQueue.add(command);
	}

	@Override
	public void close() {
		this.isConnected.set(false);
		this.commPort.close();		
	}

	private CommPortIdentifier tryToIdentifyPort(String portName) {
		CommPortIdentifier identifiedPort = null;
		try {
			identifiedPort = CommPortIdentifier.getPortIdentifier(portName);
		} catch (NoSuchPortException noPort) {
			System.out.println("Can't Find: " + portName);
		}

		return identifiedPort;
	}

	private CommPort tryToGetOpenCommPort(CommPortIdentifier portIdentifier) {
		CommPort openPort = null;
		if (!this.isConnected.get()) {
			try {
				openPort = getOpenCommPort(portIdentifier);
				this.isConnected.set(true);
			} catch (UnsupportedCommOperationException e) {
				e.printStackTrace();
			} catch (PortInUseException inUse) {
				System.out.println("Port in use: " + portIdentifier.getName());
			}
		}
		
		return openPort;
	}
	
	private CommPort getOpenCommPort(CommPortIdentifier portIdentifier) throws UnsupportedCommOperationException, PortInUseException {
		String ownersName = "Fireworks";
		int time = 2000;
		SerialPort openPort = (SerialPort) portIdentifier.open(ownersName, time);
		openPort.setSerialPortParams(9600, DATABITS_8, STOPBITS_1, PARITY_NONE);
		
		return openPort;
	}

	private InputStream tryToGetInputStream() {
		InputStream inputStream = null;
		try {			
			inputStream = this.commPort.getInputStream();
		} catch (IOException e) {
			System.out.println("Can't get input from: "	+ this.commPort.getName());
		}

		return inputStream;
	}

	private OutputStream tryToGetOutputStream() {
		OutputStream outputStream = null;
		try {
			outputStream = this.commPort.getOutputStream();
		} catch (IOException e) {
			System.out.println("Can't get output from: " + this.commPort.getName());
		}

		return outputStream;
	}	
	
	private class CommandQueueProcessor implements Runnable {
		private final OutputStream outputStream;
		
		public CommandQueueProcessor(OutputStream outputStream) {
		    this.outputStream = outputStream;
		}
		
		@Override
		public void run() {
			try {
				while(isConnected.get()) {					
					if (commandQueue.peek() == null) {
												
					} else {
						Command polledCommand = commandQueue.poll();
						System.out.println("Sending command: " + polledCommand.toString());
						this.outputStream.write(polledCommand.toByteArray());
					}					
				}
				this.outputStream.close();
			} catch (Exception e) {
				System.out.println("Something bad happend in the Command Queue Processor: " + e);
			} finally {
				System.out.println("OutputStream is Done!");
			}
		}
	}

	private class SerialInputReader implements Runnable {

		private final InputStream inputStream;
		private final CommandInterpreter interpreter;

		SerialInputReader(InputStream inputStream) {
			this.inputStream = inputStream;
			this.interpreter = new CommandInterpreter();
		}

		@Override
		public void run() {	
			try {
				while (isConnected.get()) {
					if (interpreter.readValue(inputStream.read())) {
						Command command = this.interpreter.build();
						System.out.println("Response received: " + command.toString());
					}
				}				
				this.inputStream.close();
			} catch (Exception e) {
				System.out.println("Something bad happend in the Serial Input Reader: " + e);
			} finally {
				System.out.println("InputStream is Done!");
			}
		}
	}
}