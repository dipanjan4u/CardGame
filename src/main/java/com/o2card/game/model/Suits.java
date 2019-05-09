package com.o2card.game.model;

public enum Suits {
	HEART("40"),
	SPADE("30"),
	DIAMOND("20"),
	CLUB("10");
	
	private final String value;
	Suits(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}