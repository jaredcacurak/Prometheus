package com.rocketpush.command;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class TestCommand {

	@Test
	@Ignore ("not ready yet")
	public void new_command_from_int_array() {
		int[] values = {};
		Command command = new Command(values);

		assertThat(command.value(), is(values));
	}

	@Test
	@Ignore ("not ready yet")
	public void new_command_from_byte_array() {
		byte[] values = {};
		Command command = new Command(values);

		assertThat(command.toByteArray(), is(values));
	}

	@Test
	@Ignore ("not ready yet")
	public void test_command_toString() {
		int[] values = {};
		Command command = new Command(values);		

		assertThat(command.toString(), is("Command"));
	}
}
