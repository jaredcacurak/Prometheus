package com.rocketpush.communication;

import static com.rocketpush.command.CommandValue.ACK;
import com.rocketpush.command.Command;
import com.rocketpush.command.interpreter.CommandInterpreter;
import com.rocketpush.command.interpreter.EmulatorCommandInterpreter;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SerialCommunicationEmulator implements Communication {
	
	private final CommPort commPort;
	private final Queue<Command> commandQueue; 
	private final AtomicBoolean isConnected = new AtomicBoolean(false);

	public SerialCommunicationEmulator(String portName) {		
		commandQueue = new ConcurrentLinkedQueue<Command>();
		CommPortIdentifier identifiedPort = tryToIdentifyPort(portName);
		this.commPort = tryToGetOpenCommPort(identifiedPort);
		
		OutputStream outputStream = tryToGetOutputStream();
		Runnable commandQueueProcessor = new CommandQueueProcessor(outputStream);
		new Thread(commandQueueProcessor, "EmulatorCommandQueueProcessor").start();
		
		InputStream inputStream = tryToGetInputStream();
		Runnable inputStreamReader = new SerialInputReader(inputStream);
		new Thread(inputStreamReader, "EmulatorInputStream").start();
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
		String ownersName = "Fireworks Emulator";
		int time = 2000;
		SerialPort openPort = (SerialPort) portIdentifier.open(ownersName, time);
		openPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		
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
		
		CommandQueueProcessor(OutputStream outputStream) {
			this.outputStream = outputStream;
		}

		@Override
		public void run() {
			try {
				while(isConnected.get()) {
					if (commandQueue.peek() != null) {
						Command polledCommand = commandQueue.poll();
						outputStream.write(polledCommand.toByteArray());
					}
				}
				outputStream.close();
			} catch (Exception e) {
				System.out.println("Something bad happend in the Emulator's Command Queue Processor: " + e);
			} finally {
				System.out.println("Emulator OutputStream is Done!");
			}
		}
	}

	private class SerialInputReader implements Runnable {

		private final InputStream inputStream;
		private final CommandInterpreter interpreter;

		SerialInputReader(InputStream inputStream) {
			this.inputStream = inputStream;
			this.interpreter = new EmulatorCommandInterpreter();
		}

		@Override
		public void run() {
			try {
				while (isConnected.get()) {
					if (interpreter.readValue(inputStream.read())) {
						Command command = interpreter.build();
						commandQueue.add(fakeResponseFor(command));
					}
				}
				inputStream.close();
			} catch (Exception e) {
				System.out.println("Something bad happend in the Emulator's Serial Input Reader: " + e);
			} finally {
				System.out.println("Emulator InputStream is Done!");
			}
		}

		private Command fakeResponseFor(Command command) {
			//int values[] = {CommandValue.FIRE_CUE.value(), (CommandValue.FIRE_CUE.value() ^ 0xFF), 0x01, 0xFE, 0x01, 0xFE };
			//Command fireCueCommandForSlaveOneCueOne = new Command(values);
			Command response = null;
			int responseValues[] = { ACK.value(), ACK.value() ^ 0xFF, 0x01, 0xFE, 0x01, 0xFE };
			response = new Command(responseValues);			
			return response;
		}
	}
}