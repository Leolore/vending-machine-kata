package com.leolore.kata.vend.model;

public class ProtoCoin extends CashObject {
  private String name = null;
  private double value = 0.0;
  private double minWeightInGrams = -1.0d;
  private double maxWeightInGrams = -0.5d;
  private double minRadiusInMillimeters = -1.0d;
  private double maxRadiusInMillimeters = -0.5d;
  
	public ProtoCoin(String n, double v, double mnw, double mxw, double mnr, double mxr, boolean r,
			String s) {
		super(r,s);
		name = n;
		value = v;
		minWeightInGrams = mnw;
		maxWeightInGrams = mxw;
		minRadiusInMillimeters = mnr;
		maxRadiusInMillimeters = mxr;
	}

	public boolean match(double weight, double radius, boolean r, String s) {
		boolean match = (hasRidges() == r);
		if (match && (null != getScanCode())) {
			match = (null == s) ? false : getScanCode().equals(s);
		}
		if (match) {
			match = (weight >= minWeightInGrams) && (weight < maxWeightInGrams);
		}
		if (match) {
			match = (radius >= minRadiusInMillimeters) && (radius < maxRadiusInMillimeters);
		}
		return match;
	}

	public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public double getMinWeightInGrams() {
		return minWeightInGrams;
	}

	public double getMaxWeightInGrams() {
		return maxWeightInGrams;
	}
  
}
