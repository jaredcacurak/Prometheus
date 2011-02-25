package com.rocketpush.ematch;

public class ParallelWireCalculator {
	private static final double DEFAULT_RESISTANCE_OF_EACH_MATCH = 1.5D;
	private static final double DEFAULT_TOTAL_CURRENT_NEEDED_FOR_ONE_MATCH = 0.5D;
    public final int numberOfMatches;
    public final double resistanceOfEachMatch;
    public final double totalCurrentNeededForOneMatch;
    public final int batteryVoltage;
    public final WireGauge wireGauge;
    public final double totalResistanceOfAllMatches;
    public final double totalCurrentNeededForAllMatches;
    public final double resistanceForAmpsAtVolts;
    public final double maxWireResistance;
	public final double maxWireRunRoadTrip;
	public final double maxLengthOfOneSideOfCable;
    
    public ParallelWireCalculator(int numberOfMatches, double resistanceOfEachMatch, double totalCurrentNeededForOneMatch, int batteryVoltage, WireGauge wireGauge) {
    	this.numberOfMatches = numberOfMatches;
    	this.resistanceOfEachMatch = resistanceOfEachMatch;
    	this.totalCurrentNeededForOneMatch = totalCurrentNeededForOneMatch;
    	this.batteryVoltage = batteryVoltage;
    	this.wireGauge = wireGauge;
    	this.totalResistanceOfAllMatches = resistanceOfEachMatch / numberOfMatches;
    	this.totalCurrentNeededForAllMatches = totalCurrentNeededForOneMatch * numberOfMatches;
    	this.resistanceForAmpsAtVolts = batteryVoltage / totalCurrentNeededForAllMatches;
    	this.maxWireResistance = resistanceForAmpsAtVolts - totalResistanceOfAllMatches;
    	this.maxWireRunRoadTrip = wireGauge.getResistance() * maxWireResistance;
    	this.maxLengthOfOneSideOfCable = maxWireRunRoadTrip / 2;
    }
}
