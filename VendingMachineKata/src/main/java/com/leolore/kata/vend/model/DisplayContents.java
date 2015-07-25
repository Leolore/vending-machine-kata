package com.leolore.kata.vend.model;

public enum DisplayContents {
	INSERT("INSERT COIN"),
	EXACT("EXACT CHANGE"),
	SOLDOUT("SOLD OUT"),
	THANKS("THANK YOU"),
	MONEY("Money");
	
	private String content = null;
	
	private DisplayContents(String s) {
		content = s;
	}
	
	public String toString() { return content; }
}
