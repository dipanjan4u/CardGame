package com.o2card.game.model;

import java.util.List;

public class PlayerAndCardDetails {

	private String playerName;
	private List<CardDetails> cardDetails;
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public List<CardDetails> getCardDetails() {
		return cardDetails;
	}
	public void setCardDetails(List<CardDetails> cardDetails) {
		this.cardDetails = cardDetails;
	}
	
}
