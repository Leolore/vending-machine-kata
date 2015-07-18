package com.leolore.kata.vend.model;

public class Coin extends CashObject {
	private double weightInGrams = 0.0d;
	private double radiusInMillimeters = 0.0d;
	
	public Coin(double w, double ra, boolean ri, String s) {
		super(ri, s);
		weightInGrams = w;
		radiusInMillimeters = ra;
	}
	public double getWeightInGrams() {
		return weightInGrams;
	}
	public void setWeightInGrams(double weightInGrams) {
		this.weightInGrams = weightInGrams;
	}
	public double getRadiusInMillimeters() {
		return radiusInMillimeters;
	}
	public void setRadiusInMillimeters(double radiusInMillimeters) {
		this.radiusInMillimeters = radiusInMillimeters;
	}
}
