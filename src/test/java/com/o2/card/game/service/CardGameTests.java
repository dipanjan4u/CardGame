package com.o2.card.game.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.o2card.game.model.CardDetails;
import com.o2card.game.model.CardGameResult;
import com.o2card.game.model.PlayerAndCardDetails;
import com.o2card.game.service.CardGamePlay;

public class CardGameTests {

	CardGamePlay game = new CardGamePlay();
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void cardGamePlayTestMatchDrawn() throws Exception {
		
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();
		PlayerAndCardDetails player2 =new PlayerAndCardDetails();
		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		List<CardDetails> player2Card = new ArrayList<CardDetails>();

		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();
		CardDetails crd4 = new CardDetails();
		CardDetails crd5 = new CardDetails();
		CardDetails crd6 = new CardDetails();
		
		crd1.setCardType("CLUB");
		crd1.setCardNumber("TEN");
		
		crd2.setCardType("HEART");
		crd2.setCardNumber("JACK");
		
		crd3.setCardType("DIAMOND");
		crd3.setCardNumber("JACK");
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		List<String> allP1Cards = new ArrayList<>();
		allP1Cards.add("TEN-CLUB");
		allP1Cards.add("JACK-HEART");
		allP1Cards.add("JACK-DIAMOND");
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);
		
		crd4.setCardType("CLUB");
		crd4.setCardNumber("TEN");
		
		crd5.setCardType("HEART");
		crd5.setCardNumber("JACK");
		
		crd6.setCardType("DIAMOND");
		crd6.setCardNumber("JACK");
		player2Card.add(crd4);
		player2Card.add(crd5);
		player2Card.add(crd6);
		
		List<String> allP2Cards= new ArrayList<>();
		allP2Cards.add("TEN-CLUB");
		allP2Cards.add("JACK-HEART");
		allP2Cards.add("JACK-DIAMOND");
		
		player2.setPlayerName("P2");
		List<String> AllPlayers= new ArrayList<>();
		AllPlayers.add(player1.getPlayerName());
		AllPlayers.add(player2.getPlayerName());
		player2.setCardDetails(player2Card);
		playerAndCardDetailsList.add(player1);
		playerAndCardDetailsList.add(player2);
		
		CardGameResult cgr = new CardGameResult();
		cgr = game.playCardGame(playerAndCardDetailsList);
		Collections.reverse(cgr.getPlayer());
		assertEquals("Match Drawn", cgr.getResult());
		assertEquals(allP1Cards.toString(), cgr.getCards());
		assertEquals(allP2Cards.toString(), cgr.getCards());
		assertEquals(AllPlayers.toString(),cgr.getPlayer().toString());

	}
	
	@Test
	public void cardGamePlayTestMatchOwnByDouble() throws Exception {
		
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();
		PlayerAndCardDetails player2 =new PlayerAndCardDetails();
		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		List<CardDetails> player2Card = new ArrayList<CardDetails>();

		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();
		CardDetails crd4 = new CardDetails();
		CardDetails crd5 = new CardDetails();
		CardDetails crd6 = new CardDetails();
		
		crd1.setCardType("CLUB");
		crd1.setCardNumber("TEN");
		
		crd2.setCardType("HEART");
		crd2.setCardNumber("JACK");
		
		crd3.setCardType("DIAMOND");
		crd3.setCardNumber("JACK");
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		List<String> allP1Cards = new ArrayList<>();
		allP1Cards.add("TEN-CLUB");
		allP1Cards.add("JACK-HEART");
		allP1Cards.add("JACK-DIAMOND");
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);
		
		crd4.setCardType("CLUB");
		crd4.setCardNumber("NINE");
		
		crd5.setCardType("HEART");
		crd5.setCardNumber("THREE");
		
		crd6.setCardType("DIAMOND");
		crd6.setCardNumber("EIGHT");
		player2Card.add(crd4);
		player2Card.add(crd5);
		player2Card.add(crd6);
		
		List<String> allP2Cards= new ArrayList<>();
		allP2Cards.add("NINE-CLUB");
		allP2Cards.add("THREE-HEART");
		allP2Cards.add("EIGHT-DIAMOND");
		
		player2.setPlayerName("P2");

		player2.setCardDetails(player2Card);
		playerAndCardDetailsList.add(player1);
		playerAndCardDetailsList.add(player2);
		
		CardGameResult cgr = new CardGameResult();
		cgr =game.playCardGame(playerAndCardDetailsList);
		
		assertEquals("Match Own", cgr.getResult());
		assertEquals(1, cgr.getPlayer().size());
		assertEquals("P1", cgr.getPlayer().get(0));
		assertEquals(allP1Cards.toString(), cgr.getCards());
	}
	
	@Test
	public void cardGamePlayTestMatchOwnByTriple() throws Exception {
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();
		PlayerAndCardDetails player2 =new PlayerAndCardDetails();
		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		List<CardDetails> player2Card = new ArrayList<CardDetails>();

		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();
		CardDetails crd4 = new CardDetails();
		CardDetails crd5 = new CardDetails();
		CardDetails crd6 = new CardDetails();
		
		crd1.setCardType("CLUB");
		crd1.setCardNumber("JACK");
		
		crd2.setCardType("SPADE");
		crd2.setCardNumber("JACK");
		
		crd3.setCardType("DIAMOND");
		crd3.setCardNumber("JACK");
		
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		List<String> allP1Cards = new ArrayList<>();
		allP1Cards.add("JACK-CLUB");
		allP1Cards.add("JACK-SPADE");
		allP1Cards.add("JACK-DIAMOND");
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);
		
		crd4.setCardType("SPADE");
		crd4.setCardNumber("ACE");
		
		crd5.setCardType("HEART");
		crd5.setCardNumber("KING");
		
		crd6.setCardType("DIAMOND");
		crd6.setCardNumber("THREE");
		
		player2Card.add(crd4);
		player2Card.add(crd5);
		player2Card.add(crd6);
		
		List<String> allP2Cards= new ArrayList<>();
		allP2Cards.add("ACE-SPADE");
		allP2Cards.add("KING-HEART");
		allP2Cards.add("THREE-DIAMOND");
		
		player2.setPlayerName("P2");

		player2.setCardDetails(player2Card);
		playerAndCardDetailsList.add(player1);
		playerAndCardDetailsList.add(player2);		
		CardGameResult cgr = new CardGameResult();
		cgr = game.playCardGame(playerAndCardDetailsList);
		
		assertEquals("Match Own", cgr.getResult());
		assertEquals(1, cgr.getPlayer().size());
		assertEquals("P1", cgr.getPlayer().get(0));
		assertEquals(allP1Cards.toString(), cgr.getCards());
	}
	
	@Test
	public void cardGamePlayTestMatchOwnByTripleHigher() throws Exception {
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();
		PlayerAndCardDetails player2 =new PlayerAndCardDetails();
		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		List<CardDetails> player2Card = new ArrayList<CardDetails>();

		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();
		CardDetails crd4 = new CardDetails();
		CardDetails crd5 = new CardDetails();
		CardDetails crd6 = new CardDetails();
		
		crd1.setCardType("CLUB");
		crd1.setCardNumber("JACK");
		
		crd2.setCardType("SPADE");
		crd2.setCardNumber("JACK");
		
		crd3.setCardType("DIAMOND");
		crd3.setCardNumber("JACK");
		
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		List<String> allP1Cards = new ArrayList<>();
		allP1Cards.add("JACK-CLUB");
		allP1Cards.add("JACK-SPADE");
		allP1Cards.add("JACK-DIAMOND");
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);
		
		crd4.setCardType("SPADE");
		crd4.setCardNumber("ACE");
		
		crd5.setCardType("CLUB");
		crd5.setCardNumber("ACE");
		
		crd6.setCardType("DIAMOND");
		crd6.setCardNumber("ACE");
		
		player2Card.add(crd4);
		player2Card.add(crd5);
		player2Card.add(crd6);
		
		List<String> allP2Cards= new ArrayList<>();
		allP2Cards.add("ACE-SPADE");
		allP2Cards.add("ACE-CLUB");
		allP2Cards.add("ACE-DIAMOND");
		
		player2.setPlayerName("P2");

		player2.setCardDetails(player2Card);
		playerAndCardDetailsList.add(player1);
		playerAndCardDetailsList.add(player2);
		
		CardGameResult cgr = new CardGameResult();
		cgr = game.playCardGame(playerAndCardDetailsList);
		
		assertEquals("Match Own", cgr.getResult());
		assertEquals(1, cgr.getPlayer().size());
		assertEquals("P2", cgr.getPlayer().get(0));
		assertEquals(allP2Cards.toString(), cgr.getCards());
	}
	
	@Test
	public void cardGamePlayTestMatchOwnByPureSequence() throws Exception {
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();
		PlayerAndCardDetails player2 =new PlayerAndCardDetails();
		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		List<CardDetails> player2Card = new ArrayList<CardDetails>();

		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();
		CardDetails crd4 = new CardDetails();
		CardDetails crd5 = new CardDetails();
		CardDetails crd6 = new CardDetails();
		
		crd1.setCardType("DIAMOND");
		crd1.setCardNumber("ACE");
		
		crd2.setCardType("DIAMOND");
		crd2.setCardNumber("TWO");
		
		crd3.setCardType("DIAMOND");
		crd3.setCardNumber("THREE");
		
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		List<String> allP1Cards = new ArrayList<>();
		allP1Cards.add("ACE-DIAMOND");
		allP1Cards.add("TWO-DIAMOND");
		allP1Cards.add("THREE-DIAMOND");
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);
		
		crd4.setCardType("SPADE");
		crd4.setCardNumber("THREE");
		
		crd5.setCardType("CLUB");
		crd5.setCardNumber("ACE");
		
		crd6.setCardType("DIAMOND");
		crd6.setCardNumber("JACK");
		
		player2Card.add(crd4);
		player2Card.add(crd5);
		player2Card.add(crd6);
		
		List<String> allP2Cards= new ArrayList<>();
		allP2Cards.add("THREE-SPADE");
		allP2Cards.add("ACE-CLUB");
		allP2Cards.add("JACK-DIAMOND");
		
		player2.setPlayerName("P2");

		player2.setCardDetails(player2Card);
		playerAndCardDetailsList.add(player1);
		playerAndCardDetailsList.add(player2);
		
		CardGameResult cgr = new CardGameResult();
		cgr = game.playCardGame(playerAndCardDetailsList);
		
		assertEquals("Match Own", cgr.getResult());
		assertEquals(1, cgr.getPlayer().size());
		assertEquals("P1", cgr.getPlayer().get(0));
		assertEquals(allP1Cards.toString(), cgr.getCards());
	}
	
	@Test
	public void cardGamePlayTestMatchOwnByNormalSequence() throws Exception {
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();
		PlayerAndCardDetails player2 =new PlayerAndCardDetails();
		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		List<CardDetails> player2Card = new ArrayList<CardDetails>();

		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();
		CardDetails crd4 = new CardDetails();
		CardDetails crd5 = new CardDetails();
		CardDetails crd6 = new CardDetails();
		
		crd1.setCardType("HEART");
		crd1.setCardNumber("ACE");
		
		crd2.setCardType("SPADE");
		crd2.setCardNumber("TWO");
		
		crd3.setCardType("DIAMOND");
		crd3.setCardNumber("THREE");
		
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		List<String> allP1Cards = new ArrayList<>();
		allP1Cards.add("ACE-HEART");
		allP1Cards.add("TWO-SPADE");
		allP1Cards.add("THREE-DIAMOND");
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);
		
		crd4.setCardType("SPADE");
		crd4.setCardNumber("THREE");
		
		crd5.setCardType("CLUB");
		crd5.setCardNumber("ACE");
		
		crd6.setCardType("DIAMOND");
		crd6.setCardNumber("JACK");
		
		player2Card.add(crd4);
		player2Card.add(crd5);
		player2Card.add(crd6);
		
		List<String> allP2Cards= new ArrayList<>();
		allP2Cards.add("THREE-SPADE");
		allP2Cards.add("ACE-CLUB");
		allP2Cards.add("JACK-DIAMOND");
		
		player2.setPlayerName("P2");

		player2.setCardDetails(player2Card);
		playerAndCardDetailsList.add(player1);
		playerAndCardDetailsList.add(player2);
		
		CardGameResult cgr = new CardGameResult();
		cgr = game.playCardGame(playerAndCardDetailsList);
		
		assertEquals("Match Own", cgr.getResult());
		assertEquals(1, cgr.getPlayer().size());
		assertEquals("P1", cgr.getPlayer().get(0));
		assertEquals(allP1Cards.toString(), cgr.getCards());
	}
	
	@Test
	public void cardGamePlayTestMatchOwnByColor() throws Exception {
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();
		PlayerAndCardDetails player2 =new PlayerAndCardDetails();
		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		List<CardDetails> player2Card = new ArrayList<CardDetails>();

		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();
		CardDetails crd4 = new CardDetails();
		CardDetails crd5 = new CardDetails();
		CardDetails crd6 = new CardDetails();
		
		crd1.setCardType("HEART");
		crd1.setCardNumber("ACE");
		
		crd2.setCardType("HEART");
		crd2.setCardNumber("TEN");
		
		crd3.setCardType("HEART");
		crd3.setCardNumber("THREE");
		
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		List<String> allP1Cards = new ArrayList<>();
		allP1Cards.add("ACE-HEART");
		allP1Cards.add("TEN-HEART");
		allP1Cards.add("THREE-HEART");
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);
		
		crd4.setCardType("SPADE");
		crd4.setCardNumber("THREE");
		
		crd5.setCardType("CLUB");
		crd5.setCardNumber("ACE");
		
		crd6.setCardType("DIAMOND");
		crd6.setCardNumber("JACK");
		
		player2Card.add(crd4);
		player2Card.add(crd5);
		player2Card.add(crd6);
		
		List<String> allP2Cards= new ArrayList<>();
		allP2Cards.add("THREE-SPADE");
		allP2Cards.add("ACE-CLUB");
		allP2Cards.add("JACK-DIAMOND");
		
		player2.setPlayerName("P2");

		player2.setCardDetails(player2Card);
		playerAndCardDetailsList.add(player1);
		playerAndCardDetailsList.add(player2);
		
		CardGameResult cgr = new CardGameResult();
		cgr = game.playCardGame(playerAndCardDetailsList);
		
		assertEquals("Match Own", cgr.getResult());
		assertEquals(1, cgr.getPlayer().size());
		assertEquals("P1", cgr.getPlayer().get(0));
		assertEquals(allP1Cards.toString(), cgr.getCards());
	}
	
	@Test
	public void cardGamePlayTestMatchOwnByHigheOrder() throws Exception {
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();
		PlayerAndCardDetails player2 =new PlayerAndCardDetails();
		PlayerAndCardDetails player3 =new PlayerAndCardDetails();
		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		List<CardDetails> player2Card = new ArrayList<CardDetails>();
		List<CardDetails> player3Card = new ArrayList<CardDetails>();

		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();
		
		CardDetails crd4 = new CardDetails();
		CardDetails crd5 = new CardDetails();
		CardDetails crd6 = new CardDetails();
		
		CardDetails crd7 = new CardDetails();
		CardDetails crd8 = new CardDetails();
		CardDetails crd9 = new CardDetails();
		
		crd1.setCardType("CLUB");
		crd1.setCardNumber("TEN");
		
		crd2.setCardType("HEART");
		crd2.setCardNumber("THREE");
		
		crd3.setCardType("DIAMOND");
		crd3.setCardNumber("JACK");
		
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		List<String> allP1Cards = new ArrayList<>();
		allP1Cards.add("TEN-CLUB");
		allP1Cards.add("THREE-HEART");
		allP1Cards.add("JACK-DIAMOND");
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);
		
		crd4.setCardType("CLUB");
		crd4.setCardNumber("NINE");
		
		crd5.setCardType("HEART");
		crd5.setCardNumber("THREE");
		
		crd6.setCardType("SPADE");
		crd6.setCardNumber("JACK");
		
		player2Card.add(crd4);
		player2Card.add(crd5);
		player2Card.add(crd6);
		
		List<String> allP2Cards= new ArrayList<>();
		allP2Cards.add("NINE-CLUB");
		allP2Cards.add("THREE-HEART");
		allP2Cards.add("JACK-SPADE");
		
		player2.setPlayerName("P2");

		player2.setCardDetails(player2Card);
		
		crd7.setCardType("CLUB");
		crd7.setCardNumber("THREE");
		
		crd8.setCardType("HEART");
		crd8.setCardNumber("SIX");
		
		crd9.setCardType("HEART");
		crd9.setCardNumber("JACK");		
		
		player3Card.add(crd7);
		player3Card.add(crd8);
		player3Card.add(crd9);
		
		List<String> allP3Cards = new ArrayList<>();
		allP3Cards.add("THREE-CLUB");
		allP3Cards.add("SIX-HEART");
		allP3Cards.add("JACK-HEART");
		
		player3.setPlayerName("P3");

		player3.setCardDetails(player3Card);
		
		playerAndCardDetailsList.add(player1);
		playerAndCardDetailsList.add(player2);
		playerAndCardDetailsList.add(player3);
		
		CardGameResult cgr = new CardGameResult();
		cgr = game.playCardGame(playerAndCardDetailsList);
		
		assertEquals("Match Own", cgr.getResult());
		assertEquals(1, cgr.getPlayer().size());
		assertEquals("P3", cgr.getPlayer().get(0));
		assertEquals(allP3Cards.toString(), cgr.getCards());
	}
	
	@Test
	public void cardGamePlayTestExceptionForLessThanTwoPlayer() throws Exception {
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();

		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();		
		crd1.setCardType("HEART");
		crd1.setCardNumber("ACE");		
		crd2.setCardType("HEART");
		crd2.setCardNumber("TEN");		
		crd3.setCardType("HEART");
		crd3.setCardNumber("THREE");		
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);		
		playerAndCardDetailsList.add(player1);
		exceptionRule.expect(Exception.class);
		exceptionRule.expectMessage("For this card game at least 2 people required");
		CardGameResult cgr = new CardGameResult();
		cgr = game.playCardGame(playerAndCardDetailsList);
	}
	
	@Test
	public void cardGamePlayTestExceptionForMoreThanThreeCardAssignedToSinglePlayer() throws Exception {
		List<PlayerAndCardDetails> playerAndCardDetailsList = new ArrayList<PlayerAndCardDetails>();
		PlayerAndCardDetails player1 =new PlayerAndCardDetails();
		PlayerAndCardDetails player2 =new PlayerAndCardDetails();

		List<CardDetails> player1Card = new ArrayList<CardDetails>();
		List<CardDetails> player2Card = new ArrayList<CardDetails>();
		CardDetails crd1 = new CardDetails();
		CardDetails crd2 = new CardDetails();
		CardDetails crd3 = new CardDetails();
		CardDetails crd4 = new CardDetails();		
		crd1.setCardType("HEART");
		crd1.setCardNumber("ACE");		
		crd2.setCardType("HEART");
		crd2.setCardNumber("TEN");		
		crd3.setCardType("HEART");
		crd3.setCardNumber("THREE");
		crd4.setCardType("CLUB");
		crd4.setCardNumber("NINE");		
		player1Card.add(crd1);
		player1Card.add(crd2);
		player1Card.add(crd3);
		player1Card.add(crd4);
		player1.setPlayerName("P1");
		player1.setCardDetails(player1Card);

		CardDetails crd5 = new CardDetails();
		CardDetails crd6 = new CardDetails();		
		CardDetails crd7 = new CardDetails();
		
		crd5.setCardType("HEART");
		crd5.setCardNumber("THREE");		
		crd6.setCardType("SPADE");
		crd6.setCardNumber("JACK");
		crd7.setCardType("CLUB");
		crd7.setCardNumber("THREE");
		player2Card.add(crd5);
		player2Card.add(crd6);
		player2Card.add(crd7);		
		player2.setPlayerName("P2");
		player2.setCardDetails(player2Card);		
		
		playerAndCardDetailsList.add(player1);
		playerAndCardDetailsList.add(player2);
		exceptionRule.expect(Exception.class);
		exceptionRule.expectMessage("A player can't have more than three card in a single game");
		CardGameResult cgr = new CardGameResult();
		cgr = game.playCardGame(playerAndCardDetailsList);
	}

}
