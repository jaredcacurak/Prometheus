package com.rocketpush.ematch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class TestParallelWireCalculator {

	ParallelWireCalculator calculator;

	@Before
	public void init() {
		int numberOfMatches = 10;
		double resistanceOfEachMatch = 1.5D;
		double totalCurrentNeededForOneMatch = 0.5D;
		int batteryVoltage = 20;
		WireGauge wireGauge = WireGauge.TWENTYTWO;

		this.calculator = new ParallelWireCalculator(numberOfMatches,
				resistanceOfEachMatch, totalCurrentNeededForOneMatch,
				batteryVoltage, wireGauge);
	}

	@Test
	public void testCalculatedValues() {
		assertThat(this.calculator.totalResistanceOfAllMatches, is(0.15D));
		assertThat(this.calculator.totalCurrentNeededForAllMatches, is(5D));
		assertThat(this.calculator.resistanceForAmpsAtVolts, is(4D));
		assertThat(this.calculator.maxWireResistance, is(3.85D));
		assertThat(this.calculator.maxWireRunRoadTrip, is(116.655D));
		assertThat(this.calculator.maxLengthOfOneSideOfCable, is(58.3275D));
	}

	@After
	public void teardown() {
		this.calculator = null;
	}
}