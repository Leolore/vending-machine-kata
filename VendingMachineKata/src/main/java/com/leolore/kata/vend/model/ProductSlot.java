package com.leolore.kata.vend.model;

public class ProductSlot {
	private String productSelectionCode = null;
	private String productDispensingCode = null;
	private double cost = 0.0d;
	
	public ProductSlot(String s, String d, double c) {
		productSelectionCode = s;
		productDispensingCode = d;
		cost = c;
	}
	
	public String getProductSelectionCode() {
		return productSelectionCode;
	}
	public void setProductSelectionCode(String productSelectionCode) {
		this.productSelectionCode = productSelectionCode;
	}
	public String getProductDispensingCode() {
		return productDispensingCode;
	}
	public void setProductDispensingCode(String productDispensingCode) {
		this.productDispensingCode = productDispensingCode;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
