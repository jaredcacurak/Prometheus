package com.rocketpush.command;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

import com.rocketpush.domain.Cue;
import com.rocketpush.domain.CueList;
import com.rocketpush.domain.Slave;
import com.rocketpush.domain.TimeStamp;
import com.rocketpush.domain.Time;

public class TestCommandFactory {
	
	@Test
	public void testBuildingASlaveCommand() {
		CommandValue commandValue = CommandValue.RESET;
		Slave slave = new Slave(4);
		
		Command command = CommandFactory.build(commandValue, slave);
		
		int[] expectedValue = new int[] { 0x81, 0x7E, 0x04, 0xFB };
		assertThat(command.value(), is(expectedValue));
	}
	
	@Test
	public void testBuildingASlaveTimeCommand() {
		CommandValue commandValue = CommandValue.SET_CUE_FIRE_TIME;
		Slave slave = new Slave(5);
		Time time = new Time(16);
		
		Command command = CommandFactory.build(commandValue, slave, time);
		
		int[] expectedValue = new int[] { 0x86, 0x79, 0x05, 0xFA, 0x10, 0xEF };
		assertThat(command.value(), is(expectedValue));
	}
	
	@Test
	public void testBuildingASlaveTimeStampCommand() {
		CommandValue commandVale = CommandValue.START_SHOW;
		Slave allSlaves = new Slave(0);
		TimeStamp timeStamp = new TimeStamp(0, 0, 0, 0);
		
		Command command = CommandFactory.build(commandVale, allSlaves, timeStamp);
		
		int[] expectedValue = new int[] { 0x8C, 0x73, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF };
		assertThat(command.value(), is(expectedValue));
	}
	
	@Test
	public void testBuildingSlaveCueCommand() {
		CommandValue commandValue = CommandValue.TEST_CUE;
		Slave slave = new Slave(3);
		Cue cue = new Cue(4);
		
		Command command = CommandFactory.build(commandValue, slave, cue);
		
		int[] expectedValue = new int[] { 0x83, 0x7C, 0x03, 0xFC, 0x04, 0xFB };
		assertThat(command.value(), is(expectedValue));
	}

	@Test
	public void testBuildingACueListCommand() {
		CommandValue commandValue = CommandValue.FIRE_CUE;
		Slave slave = new Slave(1);
		TimeStamp timeStamp = new TimeStamp(1, 2, 3, 4);
		CueList cues = new CueList();
		Cue cue1 = new Cue(1);
		Cue cue2 = new Cue(2);
		Cue cue3 = new Cue(3);
		cues.addCue(cue2);
		cues.addCue(cue1);
		cues.addCue(cue3);
		
		Command command = CommandFactory.build(commandValue, slave, timeStamp, cues);		
		
		int[] expectedValue = new int[] { 0x82, 0x7D, 0x01, 0xFE, 0x01, 0xFE, 0x02, 0xFD, 0x03, 0xFC, 0x04, 0xFB, 0x03, 0xFC, 0x02, 0xFD, 0x01, 0xFE, 0x00, 0xFF };
		assertThat(command.value(), is(expectedValue));
	}
}
