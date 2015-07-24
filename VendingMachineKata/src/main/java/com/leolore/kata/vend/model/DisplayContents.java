package com.leolore.kata.vend.model;

public enum DisplayContents {
	INSERT("Insert Coins"),
	EXACT("Exact Change");
	
	private String content = null;
	
	private DisplayContents(String s) {
		content = s;
	}
	
	public String toString() { return content; }
}
