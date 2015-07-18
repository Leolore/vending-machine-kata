package com.leolore.kata.vend.model;

public abstract class CashObject {
	private boolean hasRidges = false;
	private String scanCode = null;

	public CashObject(boolean r, String s) {
		hasRidges = r;
		scanCode = s;
	}
	public String getScanCode() {
		return scanCode;
	}

	public void setScanCode(String scanCode) {
		this.scanCode = scanCode;
	}

	public void setHasRidges(boolean hasRidges) {
		this.hasRidges = hasRidges;
	}

	public boolean hasRidges() {
		return this.hasRidges;
	}
}
