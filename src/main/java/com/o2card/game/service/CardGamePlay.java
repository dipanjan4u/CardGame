package com.o2card.game.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.o2card.game.PokerTable;
import com.o2card.game.model.CardDetails;
import com.o2card.game.model.CardGameResult;
import com.o2card.game.model.PlayerAndCardDetails;

public class CardGamePlay {
	
	public CardGameResult playCardGame(List<PlayerAndCardDetails> playerAndCardDetailsList) throws Exception {
		
		Map<String, List<String>> playerAndCardDetailsMap = new HashMap<String, List<String>>();
		CardGameResult cardGameResult = new CardGameResult();
		for (PlayerAndCardDetails playerAndCardDetails : playerAndCardDetailsList) {
			
			List<String> cardDetailsStr = new ArrayList<>();
			String playerName = playerAndCardDetails.getPlayerName();
			List<CardDetails> cardDetailsList = playerAndCardDetails.getCardDetails();
			if(cardDetailsList.size()>3) {
				throw new Exception("A player can't have more than three card in a single game");
			}
			for (CardDetails cardDetails : cardDetailsList) {
				String cardType = cardDetails.getCardType();
				String cardNumber =cardDetails.getCardNumber();
				cardDetailsStr.add(cardNumber+"-"+cardType);
			}
			playerAndCardDetailsMap.put(playerName, cardDetailsStr);
		}
		PokerTable pt = new PokerTable();
		cardGameResult=pt.getGameResult(playerAndCardDetailsMap);
		return cardGameResult;
	}
}
