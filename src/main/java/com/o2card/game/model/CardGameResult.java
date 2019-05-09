package com.o2card.game.model;

import java.util.List;

public class CardGameResult {
	
	private List<String> player; 
	private String result;
	private int Priority;
	private String cards;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getPriority() {
		return Priority;
	}
	public void setPriority(int priority) {
		Priority = priority;
	}
	public String getCards() {
		return cards;
	}
	public void setCards(String cards) {
		this.cards = cards;
	}
	public List<String> getPlayer() {
		return player;
	}
	public void setPlayer(List<String> player) {
		this.player = player;
	}
}
