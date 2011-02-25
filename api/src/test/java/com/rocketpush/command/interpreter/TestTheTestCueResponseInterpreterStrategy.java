package com.rocketpush.command.interpreter;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;

import com.rocketpush.command.Command;

public class TestTheTestCueResponseInterpreterStrategy {
	TestCueResponseInterpreterStrategy strategy;

	@Before
	public void setUp() throws Exception {
		this.strategy = new TestCueResponseInterpreterStrategy();
	}

	@After
	public void tearDown() throws Exception {
		this.strategy = null;
	}
	
	@Test
	public void sample_test_cue_response() {
		int[] values = { 0x63, 0x9C, 0x01, 0xFE, 0x01, 0xFE, 0x00, 0xFF };
		int[] expected = new int[8];
		System.arraycopy(values, 0, expected, 0, 8);
			
		Command command = null;
		for (int i : values) {
			if (this.strategy.readValue(i)) {
				command = this.strategy.build();
			}
		}
		
		assertThat(command.value(), is(expected));
	}
}
