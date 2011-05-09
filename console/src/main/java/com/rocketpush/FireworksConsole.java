package com.rocketpush;

import com.rocketpush.communication.Communication;
import com.rocketpush.communication.SerialCommunication;
import com.rocketpush.domain.Cue;
import com.rocketpush.domain.CueList;
import com.rocketpush.domain.Slave;
import com.rocketpush.domain.Time;
import com.rocketpush.domain.TimeStamp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FireworksConsole {
	private Communication comm;
	private Launcher launcher;

	public FireworksConsole() {

	}

	public FireworksConsole(Launcher launcher) {
		this.launcher = launcher;
	}

	public static void main(String[] args) {
		System.out.println("Fire away! v.0.1.20110509");
		FireworksConsole console = new FireworksConsole();
		try {
			console.comm = new SerialCommunication(args[0]);
			console.launcher = new Launcher(console.comm);

			if (args.length == 1) {
				console.interactiveMode();
			} else if ((args.length == 2) || (args.length == 3)) {
				console.runScript(args);
			}
		} catch (Exception e) {
			System.out.println("Bad mojo: " + e);
		} finally {
			console.comm.close();
		}
	}

	void runScript(String[] args) throws FileNotFoundException,	InterruptedException {
		String script = args[1];
		int sleepTime = Integer.valueOf(args.length == 3 ? Integer.valueOf(args[2]).intValue() : 20).intValue();
		System.out.println("Running script: " + script + ", with a sleep time of: " + sleepTime + "ms");
		File file = new File(script);
		Scanner in = new Scanner(file);

		while (in.hasNextLine()) {
			evalute(in.nextLine());
			Thread.sleep(sleepTime);
		}
		in.close();
	}

	void interactiveMode() {
		Scanner in = new Scanner(System.in);
		String input = "";

		while (!input.equals("exit")) {
			input = in.nextLine();
			evalute(input);
		}
		in.close();
	}

	void evalute(String value) {
		String regex = ":";
		if (value.startsWith("sync")) {
			String[] values = value.split(regex);
			this.launcher.runSync(forTimeStamp(values, 1));
		} else if (value.startsWith("start show")) {
			String[] values = value.split(regex);
			this.launcher.startShow(forTimeStamp(values, 1));
		} else if (value.startsWith("end show")) {
			String[] values = value.split(regex);
			this.launcher.endShow(forTimeStamp(values, 1));
		} else if (value.startsWith("fire cue")) {
			String[] values = value.split(regex);
			this.launcher.fireCue(slaveFor(values[1]), forTimeStamp(values, 2), cueListFor(values));
		} else if (value.startsWith("test cue")) {
			String[] values = value.split(regex);
			this.launcher.testCue(slaveFor(values[1]), cueFor(values[2]));
		} else if (value.startsWith("reset")) {
			String[] values = value.split(regex);
			this.launcher.reset(slaveFor(values[1]));
		} else if (value.startsWith("arm slave")) {
			String[] values = value.split(regex);
			this.launcher.armSlave(slaveFor(values[1]));
		} else if (value.startsWith("disarm slave")) {
			String[] values = value.split(regex);
			this.launcher.disarmSlave(slaveFor(values[1]));
		} else if (value.startsWith("request fire time")) {
			String[] values = value.split(regex);
			this.launcher.requestFireTime(slaveFor(values[1]));
		} else if (value.startsWith("request slave status")) {
			String[] values = value.split(regex);
			this.launcher.requestSlaveStatus(slaveFor(values[1]));
		} else if (value.startsWith("request slave version")) {
			String[] values = value.split(regex);
			this.launcher.requestSlaveVersion(slaveFor(values[1]));
		} else if (value.startsWith("request battery level")) {
			String[] values = value.split(regex);
			this.launcher.requestBatteryLevel(slaveFor(values[1]));
		} else if (value.startsWith("set cue fire time")) {
			String[] values = value.split(regex);
			this.launcher.setCueFireTime(slaveFor(values[1]), timeFor(values[2]));
		} else if (value.startsWith("test slave comm")) {
			String[] values = value.split(regex);
			this.launcher.testSlaveComm(slaveFor(values[1]));
		} else if (!value.equals("exit")) {
			if (value.equals("help")) {
				System.out.println("Here is a list of commands:\nsync\nstart show\nend show\nfire cue : slave : cue\ntest cue : slave : cue\nreset : slave\narm slave : slave\ndisarm slave : slave\nrequest fire time : slave\nrequest slave status : slave\nrequest slave version : slave\nrequest battery level : slave\nset cue fire time : slave : time\ntest slave comm : slave\nexit\nhelp");
			} else {
				System.out.println("Command not found: " + value + ".  Try help");
			}
		}
	}

	private TimeStamp forTimeStamp(String[] values, int offset) {
		int hours = Integer.valueOf(values[0 + offset]);
		int minutes = Integer.valueOf(values[1 + offset]);
		int seconds = Integer.valueOf(values[2 + offset]);
		int frames = Integer.valueOf(values[3 + offset]);

		return new TimeStamp(hours, minutes, seconds, frames);
	}

	private CueList cueListFor(String[] values) {
		CueList cues = new CueList();
		for (int i = 6; i < values.length; i++) {
			int arg = Integer.valueOf(values[i].trim()).intValue();
			cues.addCue(new Cue(arg));
		}

		return cues;
	}

	private Cue cueFor(String string) {
		return new Cue(Integer.valueOf(string.trim()).intValue());
	}

	private Time timeFor(String string) {
		return new Time(Integer.valueOf(string.trim()).intValue());
	}

	private Slave slaveFor(String string) {
		return new Slave(Integer.valueOf(string.trim()).intValue());
	}
}