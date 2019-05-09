package com.o2card.game.model;

public enum Cards {
	
	ONE("ACE",14),
	TWO("TWO",2),
	THREE("THREE",3),
	FOUR("FOUR",4),
	FIVE("FIVE",5),
	SIX("SIX",6),
	SEVEN("SEVEN",7),
	EIGHT("EIGHT",8),
	NINE("NINE",9),
	TEN("TEN",10),
	ELEVEN("JACK",11),
	TWELVE("QUEEN",12),
	THARTEEN("KING",13);
	
	private final String value;
	private final int val;
	
	Cards(String value,int val) {
		this.value = value;
		this.val = val;
	}

	public String getValue() {
		return value;
	}

	public int getVal() {
		return val;
	}
};
