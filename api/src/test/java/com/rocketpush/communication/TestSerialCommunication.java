package com.rocketpush.communication;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rocketpush.Commandable;
import com.rocketpush.Launcher;
import com.rocketpush.domain.Cue;
import com.rocketpush.domain.CueList;
import com.rocketpush.domain.Slave;
import com.rocketpush.domain.TimeStamp;

public class TestSerialCommunication {
//	static Commandable launcher;
//	static Communication comm;
//	static Communication emulator;
//	
//	@BeforeClass
//	public static void startMeUp() {
//		comm = new SerialCommunication("COM11");
//		launcher = new Launcher(comm);
//		emulator = new SerialCommunicationEmulator("COM10");
//	}
//	
//	@Test
//	@Ignore
//	public void send_reset_command_to_slave_one() {
//		Slave slave = new Slave(1);
//		launcher.reset(slave);		
//	}
//	
//	@Test
//	@Ignore
//	public void run_sync_command() {
//		TimeStamp timeStamp = new TimeStamp(0, 0, 0, 0);
//		launcher.runSync(timeStamp);		
//	}
//	
//	@Test
//	@Ignore
//	public void run_start_show_command() {
//		TimeStamp timeStamp = new TimeStamp(0, 0, 0, 0);
//		launcher.startShow(timeStamp);
//	}
//	
//	@Test
//	@Ignore
//	public void run_fire_cue_command_for_slave_one_cue_one() {
//		Slave slave = new Slave(1);
//		TimeStamp timeStamp = new TimeStamp(0, 0, 0, 0);
//		CueList cues = new CueList();
//		cues.addCue(new Cue(1));
//		launcher.fireCue(slave, timeStamp, cues);
//	}
//	
//	@AfterClass
//	public static void buzzKill() throws InterruptedException {
//		Thread.sleep(500);
//		comm.close();
//		emulator.close();
//		launcher = null;
//	}
}
