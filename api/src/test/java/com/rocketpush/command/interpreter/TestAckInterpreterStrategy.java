package com.rocketpush.command.interpreter;

import static com.rocketpush.command.CommandValue.ACK;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;

public class TestAckInterpreterStrategy {

	private AckInterpreterStrategy strategy;
	private final int ack = ACK.value();
	private final int inverseOfAck = ack ^ 0xFF;

	@Before
	public void setUp() throws Exception {
		this.strategy = new AckInterpreterStrategy();
	}

	@After
	public void tearDown() throws Exception {
		this.strategy = null;
	}

	@Test
	public void is_inverse() {
		boolean isInverse = strategy.isInverse(0x00, 0xFF);

		assertTrue(isInverse);
	}

	@Test
	public void is_not_inverse() {
		boolean isInverse = strategy.isInverse(0x01, 0xFF);

		assertFalse(isInverse);
	}

	@Test
	public void discover_ack() {
		strategy.readValue(ack);

		assertThat(strategy.getCommand(), is(ack));
	}

	@Test
	public void discover_ack_after_some_other_value() {
		int otherValue = 0xFE;

		strategy.readValue(otherValue);
		strategy.readValue(ack);

		assertThat(strategy.getCommand(), is(ack));
	}

	@Test
	public void discover_inverse_of_ack() {
		strategy.readValue(ack);
		strategy.readValue(inverseOfAck);

		assertThat(strategy.getCommand(), is(ack));
		assertThat(strategy.getInverseOfCommand(), is(inverseOfAck));
	}

	@Test
	public void discover_ack_is_a_command() {
		strategy.readValue(ack);
		strategy.readValue(inverseOfAck);

		assertThat(strategy.getCommand(), is(ack));
		assertThat(strategy.getInverseOfCommand(), is(inverseOfAck));
	}

	@Test
	public void discover_acks_slave() {
		int slave = 0x02;

		strategy.readValue(ack);
		strategy.readValue(inverseOfAck);
		strategy.readValue(slave);

		assertThat(strategy.getCommand(), is(ack));
		assertThat(strategy.getInverseOfCommand(), is(inverseOfAck));
		assertThat(strategy.getFirstValue(), is(slave));
	}

	@Test
	public void discover_inverse_of_slave() {
		int slave = 0x02;
		int inverseOfSlave = 0xFD;

		strategy.readValue(ack);
		strategy.readValue(inverseOfAck);
		strategy.readValue(slave);
		strategy.readValue(inverseOfSlave);

		assertThat(strategy.getCommand(), is(ack));
		assertThat(strategy.getInverseOfCommand(), is(inverseOfAck));
		assertThat(strategy.getFirstValue(), is(slave));
		assertThat(strategy.getInverseOfFirstValue(), is(inverseOfSlave));
	}

	@Test
	public void discover_the_second_value() {
		int slave = 0x02;
		int inverseOfSlave = 0xFD;
		int cue = 0x10;

		strategy.readValue(ack);
		strategy.readValue(inverseOfAck);
		strategy.readValue(slave);
		strategy.readValue(inverseOfSlave);
		strategy.readValue(cue);

		assertThat(strategy.getCommand(), is(ack));
		assertThat(strategy.getInverseOfCommand(), is(inverseOfAck));
		assertThat(strategy.getFirstValue(), is(slave));
		assertThat(strategy.getInverseOfFirstValue(), is(inverseOfSlave));
		assertThat(strategy.getSecondValue(), is(cue));
	}

	@Test
	public void discover_the_invere_of_the_second_value() {
		int slave = 0x02;
		int inverseOfSlave = 0xFD;
		int cue = 0x10;
		int inverseOfCue = 0xEF;

		strategy.readValue(ack);
		strategy.readValue(inverseOfAck);
		strategy.readValue(slave);
		strategy.readValue(inverseOfSlave);
		strategy.readValue(cue);
		strategy.readValue(inverseOfCue);

		assertThat(strategy.getCommand(), is(ack));
		assertThat(strategy.getInverseOfCommand(), is(inverseOfAck));
		assertThat(strategy.getFirstValue(), is(slave));
		assertThat(strategy.getInverseOfFirstValue(), is(inverseOfSlave));
		assertThat(strategy.getSecondValue(), is(cue));
		assertThat(strategy.getInverseOfSecondValue(), is(inverseOfCue));
	}
}