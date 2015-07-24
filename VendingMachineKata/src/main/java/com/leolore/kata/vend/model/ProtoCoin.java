package com.leolore.kata.vend.model;

public class ProtoCoin extends CashObject {
  private String name = null;
  private double value = 0.0;
  private double minWeightInGrams = -1.0d;
  private double maxWeightInGrams = -0.5d;
  private double minRadiusInMillimeters = -1.0d;
  private double maxRadiusInMillimeters = -0.5d;
  

  // Yes, there are other coins. These are the only ones mentioned in the requirements
  public static final ProtoCoin NICKEL = new ProtoCoin("Nickel", 0.05d, 5.0d, 5.5d, 7.0d, 7.01d, false, null); 
  public static final ProtoCoin DIME = new ProtoCoin("Dime", 0.10d, 1.0d, 2.0d, 5.0d, 5.1d, true, null); 
  public static final ProtoCoin QUARTER = new ProtoCoin("Quarter", 0.25d, 10.0d, 11.0d, 14d, 14.01d, true, null);

  
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
