package com.rocketpush.command.interpreter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.*;

import static com.rocketpush.command.CommandValue.VERSION_RESPONSE;
import com.rocketpush.command.Command;

public class TestVersionResponseInterpreterStrategy {
	VersionResponseInterpreterStrategy strategy;
	final int versionResponse = VERSION_RESPONSE.value();
	final int inverseOfVersion = versionResponse ^ 0xFF;
	final String version = "PROMETHEUS V1.0";
	final char[] charArrayOfVersion = version.toCharArray();

	@Before
	public void setUp() throws Exception {
		this.strategy = new VersionResponseInterpreterStrategy();
	}

	@After
	public void tearDown() throws Exception {
		this.strategy = null;
	}
	
	@Test
	public void can_discover_a_version_response() {		
		int[] values = { versionResponse, inverseOfVersion, 0x01, 0xFE, charArrayOfVersion[0], charArrayOfVersion[1], charArrayOfVersion[2], 
				charArrayOfVersion[3], charArrayOfVersion[4], charArrayOfVersion[5], charArrayOfVersion[6], charArrayOfVersion[7], charArrayOfVersion[8],
				charArrayOfVersion[9], charArrayOfVersion[10], charArrayOfVersion[11], charArrayOfVersion[12], charArrayOfVersion[13], charArrayOfVersion[14],
				0x00, 0xFF, -1 };
		int[] expectedValues = Arrays.copyOfRange(values, 0, values.length -1);		
		Command expectedResponse = new Command(expectedValues);		
		
		Command response = null;
		for (int value : values) {
			if (this.strategy.readValue(value)) {
				response = this.strategy.build();
			}
		}
		
		assertThat(response, is(expectedResponse));		
	}
}
