package com.rocketpush.ematch;

public enum WireGauge {
	TEN(10, 490.2, 30),
	TWELEVE(12, 308.7, 20),
	FOURTEEN(14, 193.8, 15),
	SIXTEEN(16, 122.3, 10),
	EIGHTTEEN(18, 76.8, 5),
	TWENTY(20, 48.1, 3.3),
	TWENTYTWO(22, 30.30, 2.1),
	TWENTYFOUR(24, 19.1, 1.3),
	TWENTYSIX(26, 12, .8),
	TWENTYEIGHT(28, 7.55, .5);
	
	int gauge;
	double resistance;
	double ampacity;
	
	WireGauge(int gauge, double resistance, double ampacity) {
		this.gauge = gauge;
		this.resistance = resistance;
		this.ampacity = ampacity;
	}
	
	int getGauge() {
		return this.gauge;
	}
	
	double getResistance() {
		return this.resistance;
	}
	
	double getAmpacity() {
		return this.ampacity;
	}
}