package com.rocketpush.command.interpreter;


import java.util.ArrayList;
import java.util.List;

import static com.rocketpush.command.CommandValue.*;
import com.rocketpush.command.Command;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;

public class TestCommandInterpreter {
	private final int ack = ACK.value();
	private final int inverseOfAck = ack ^0xFF;
	private final int fireTimeResponse = FIRE_TIME_RESPONSE.value();
	private final int inverseOfFireTimeResponse = fireTimeResponse ^ 0xFF;
	private final int slaveStatusResponse = SLAVE_STATUS_RESPONSE.value();
	private final int inverseOfSlaveStatusResponse = slaveStatusResponse ^ 0xFF;
	private final int versionResponse = VERSION_RESPONSE.value();
	private final int inverseOfVersion = versionResponse ^ 0xFF;
	private final String version = "PROMETHEUS V1.0";
	private final int filler = -1;
	
	private CommandInterpreter builder;
	private Command actualCommand;
	private Command expectedCommand;
	
	@Before
	public void setUp() throws Exception {
		this.builder = new CommandInterpreter();		
	}
	
	@After
	public void tearDown() throws Exception {
		this.builder = null;
		this.expectedCommand = null;
		this.actualCommand = null;
	}
	
	@Test
	public void build_a_command_from_an_input() {			
		int[] values = { ack, inverseOfAck, 0x02, 0xFD, 0x10, 0xEF, ack };		
		int[] commandValues = new int[6];
				
		System.arraycopy(values, 0, commandValues, 0, 6);
		this.expectedCommand = new Command(commandValues);		
		this.actualCommand = simulateStreamingValuesToBuilder(values);
		
		assertThat(actualCommand, is(expectedCommand));
	}
	
	@Test
	public void build_a_command_from_an_input_with_a_leading_value_from_previous_command() {
		int[] values = { 0xE9, ack, inverseOfAck, 0x02, 0xFD, 0x10, 0xEF, ack };
		int[] commandValues = new int[6];
		
		System.arraycopy(values, 1, commandValues, 0, 6);
		this.expectedCommand = new Command(commandValues);
		this.actualCommand = simulateStreamingValuesToBuilder(values);
				
		assertThat(actualCommand, is(expectedCommand));
	}
	
	@Test
	public void discover_request_fire_time_response() {				
		int[] values = { fireTimeResponse, inverseOfFireTimeResponse, 0x04, 0xFB, 0x20, 0xDF, ack };
		int[] commandValues = new int[6];
		
		System.arraycopy(values, 0, commandValues, 0, 6);
		this.expectedCommand = new Command(commandValues);
		this.actualCommand = simulateStreamingValuesToBuilder(values);
		
		assertThat(actualCommand, is(expectedCommand));
	}
	
	@Test
	public void discover_request_slave_status_response() {				
		int[] values = { slaveStatusResponse, inverseOfSlaveStatusResponse, 0x04, 0xFB, 0x03, 0xFC, filler };
		int[] commandValues = new int[6];
		
		System.arraycopy(values, 0, commandValues, 0, 6);
		this.expectedCommand = new Command(commandValues);
		this.actualCommand = simulateStreamingValuesToBuilder(values);
		
		assertThat(actualCommand, is(expectedCommand));
	}
	
	@Test
	public void discover_fire_command_for_slave_one_cue_one() {
		int values[] = { ack, inverseOfAck, 0x01, 0xFE, 0x01, 0xFE, filler };
		int commandValues[] = new int[6];
		
		System.arraycopy(values, 0, commandValues, 0, 6);
		this.expectedCommand = new Command(commandValues);		
		this.actualCommand = simulateStreamingValuesToBuilder(values);
		
		assertThat(actualCommand, is(expectedCommand));
	}
	
	@Test	
	public void discover_fire_cue_response_for_slave_two_cue_three() {
		int values[] = { ack, inverseOfAck, 0x02, 0xFE, 0x03, 0xFD, ack };
		int commandValues[] = new int[6];
		
		System.arraycopy(values, 0, commandValues, 0, 6);
		Command expectedCommand = new Command(commandValues);
		Command actualCommand = simulateStreamingValuesToBuilder(values);
		
		assertThat(actualCommand, is(expectedCommand));
	}

	@Test
	public void discover_a_version_response() {
		char[] charArrayOfVersion = version.toCharArray();		
		int[] values = { versionResponse, inverseOfVersion, 0x01, 0xFE, 
			charArrayOfVersion[0], charArrayOfVersion[1], charArrayOfVersion[2], 
			charArrayOfVersion[3], charArrayOfVersion[4], charArrayOfVersion[5], 
			charArrayOfVersion[6], charArrayOfVersion[7], charArrayOfVersion[8],
			charArrayOfVersion[9], charArrayOfVersion[10], charArrayOfVersion[11],
			charArrayOfVersion[12], charArrayOfVersion[13], charArrayOfVersion[14], 
			0x00, 0xFF };
		int[] commandValues = values;
		
		this.expectedCommand = new Command(commandValues);
		this.actualCommand = simulateStreamingValuesToBuilder(values);
		
		assertThat(actualCommand, is(expectedCommand));
	}
	
	@Test
	public void discover_an_ack_followed_by_another_ack_response() {		
		int[] values = { ack, inverseOfAck, 0x02, 0xFD, 0x10, 0xEF, ack, inverseOfAck, 0x03, 0xFC, 0x10, 0xEF, filler };
		int[] commandValues = new int[6];
		int[] testCommandValue = new int[6];
		
		System.arraycopy(values, 0, commandValues, 0, 6);
		System.arraycopy(values, 6, testCommandValue, 0, 6);
		Command expectedAckCommand = new Command(commandValues);
		Command expectedTestCueCommand = new Command(testCommandValue);
		
		int i = 0;
		List<Command> commands = new ArrayList<Command>();
		while (i < values.length) {
			if (builder.readValue(values[i])) {				
				commands.add(builder.build());
			}
			i += 1;
		}
		
		assertEquals(expectedAckCommand, commands.get(0));
		assertEquals(expectedTestCueCommand, commands.get(1));
	}
	
	@Test
	public void discover_an_ack_followed_by_a_filler_then_another_ack() {			
		int[] values = { ack, inverseOfAck, 0x02, 0xFD, 0x10, 0xEF, filler, ack, inverseOfAck, 0x03, 0xFC, 0x10, 0xEF, filler };
		int[] commandValues = new int[6];
		int[] testCommandValue = new int[6];
		
		System.arraycopy(values, 0, commandValues, 0, 6);
		System.arraycopy(values, 7, testCommandValue, 0, 6);
		Command expectedAckCommand = new Command(commandValues);
		Command expectedTestCueCommand = new Command(testCommandValue);
		
		int i = 0;
		List<Command> commands = new ArrayList<Command>();
		while (i < values.length) {
			if (builder.readValue(values[i])) {				
				commands.add(builder.build());
			}
			i += 1;
		}
		
		assertEquals(expectedAckCommand, commands.get(0));
		assertEquals(expectedTestCueCommand, commands.get(1));
	}
	
	@Test
	public void discover_test_cue_response() {
		int[] values = { 0x63, 0x9C, 0x01, 0xFE, 0x01, 0xFE, 0x00, 0xFF };
		int[] commandValues = new int[8];
		System.arraycopy(values, 0, commandValues, 0, 8);
			
		Command command = simulateStreamingValuesToBuilder(values);
		
		assertThat(command.value(), is(commandValues));
	}
	
	private Command simulateStreamingValuesToBuilder(int[] values) {
		int i = 0;
		Command command = null;
		while (i < values.length) {
			if (builder.readValue(values[i])) {
				command = builder.build();
			}
			i += 1;
		}
		
		return command;
	}
}