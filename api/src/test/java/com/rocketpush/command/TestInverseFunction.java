package com.rocketpush.command;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

import static com.rocketpush.command.CommandFactory.inverseOf;

public class TestInverseFunction {
	
	@Test
	public void test_the_inverse_of() {
		assertThat(0xFE, is(inverseOf(0x01)));
		assertThat(0xFD, is(inverseOf(0x02)));
		assertThat(0x00, is(inverseOf(0xFF)));
		assertThat(0xC4, is(inverseOf(0x3B)));
		assertThat(0x2E, is(inverseOf(0xD1)));
    }
}
