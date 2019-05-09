package com.o2card.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.o2card.game.model.CardGameResult;
import com.o2card.game.model.Cards;
import com.o2card.game.model.Game;
import com.o2card.game.model.Players;
import com.o2card.game.model.Suits;

public class PokerTable {

	Deck deck = null;
	Players pl = null;

	List<String> randCardsList = new ArrayList<>();
	List<String> playerList = new ArrayList<>();
	List<String> cardList = new ArrayList<>();
	Map<String, List<String>> finalList = new HashMap<>();
	List<Game> result = new ArrayList<Game>();
	
	public PokerTable() {
		super();
		deck = new Deck();
		pl = new Players();
	}

	public void addPlayers(String name) {
		pl.addPlayers(name);
	}
	
	public List<String> getRandCardsList() {
		return randCardsList;
	}
		
	public List<Game> getResult() {
		return result;
	}

	public void setNoOfPlayerAndCard(Map<String, List<String>> playersAndCardDetails) throws Exception {
		
		int noOfPlayers = playersAndCardDetails.keySet().size();
		
		if(noOfPlayers < 2) {
			throw new Exception("For this card game at least 2 people required");
		}
		
		finalList.putAll(playersAndCardDetails);
		System.out.println(finalList);
	}
	
	public int cardValue(String cardVal) {
		int c = 0;
		for(Cards val:Cards.values()){
			String card = val.getValue();
			if(card.equals(cardVal)) {
				c=val.getVal();
			}
		}
		return c;
	}
	
	public void cardIdentify() {
		int k=0;
		int seq[] = new int[3];
		String orinalCard;
		
		for(Map.Entry<String, List<String>> entry : finalList.entrySet()) {
			String player = entry.getKey();
			List<String> playerCards = entry.getValue();
			
			orinalCard = finalList.get(player).toString();
			String first = playerCards.get(0);
			String second = playerCards.get(1);
			String third = playerCards.get(2);
			
			String c1 = first.split("-")[0];
			String c2 = second.split("-")[0];
			String c3 = third.split("-")[0];
			
			String color1 = first.split("-")[1];
			String color2 = second.split("-")[1];
			String color3 = third.split("-")[1];
			
			Integer cs1 = Integer.parseInt(Suits.valueOf(first.split("-")[1]).getValue());
			Integer cs2 = Integer.parseInt(Suits.valueOf(second.split("-")[1]).getValue());
			Integer cs3 = Integer.parseInt(Suits.valueOf(third.split("-")[1]).getValue());
			
			int totalColourSum = cs1 + cs2 + cs3;
			
			Integer d1 = cardValue(c1);
			Integer d2 = cardValue(c2);
			Integer d3 = cardValue(c3);
						
			seq[0] = d1;
			seq[1] = d2;
			seq[2] = d3;
	
			Arrays.sort(seq);
			
			if(seq[2] == 14 && seq[0] == 2 && seq[1] == 3) {
				seq[0] = 1;
				seq[1] = 2;
				seq[2] = 3;
			}
			int total= totalColourSum;
			int sum = 0;
			if (d1.equals(d2) && d2.equals(d3)) { //For Triple
				total = total + 5000000;
				sum = priority(seq,total,sum);
				System.out.println("Reason : Triple");
				
			} else if(seq[0]+1 == seq[1] && seq[0]+2 == seq[2]) { //For Sequence
				total = total + 3000000;				
				if(seq[0] == 1 && seq[1] == 2 && seq[2] == 3) {
					seq[0] = 2;
					seq[1] = 3;
					seq[2] = 14;
				}				
				if(color1.equals(color2) && color2.equals(color3) && color3.equals(color1)) {
					total = total + 2500000;
					sum = priority(seq,total,sum);
					System.out.println("Reason : Pure Sequence");
				}else {
					System.out.println("Reason : Sequence");	
				}
				sum = priority(seq,total,sum);
							
			} else if(color1.equals(color2) && color2.equals(color3) && color3.equals(color1)) { // For Color
				total = total + 1000000;
				sum = priority(seq,total,sum);
				System.out.println("Reason : Color");				
			} else if(d1.equals(d2) || d2.equals(d3) || d3.equals(d1)) {
				total = total + 500000;
				k = seq[1];
				for(int i = 1; i <= 14; i++) {
					if(k == i) {
						sum = total + seq[0] + seq[1] + seq[2] + (k * k * k);
						break;
					}
				}
				System.out.println("Reason : Double");				
			} else {
				sum = priority(seq,total,sum);
				System.out.println("Reason : Higher Order");
			}
			System.out.println("Player : " + player);
			System.out.println("Priority : " + total);
			System.out.println("Sum : "+ sum);
			System.out.println("=========================================");
			result.add(new Game(player,sum,orinalCard));
		}	
		
	}
	
	public int priority(int seq[],int total,int sum) {
		int minSeq = 2;
		int maxSeq = 14;
		int[] seqValues = {600, 800, 1000, 2000, 4000, 6000, 8000, 10000, 20000, 40000, 60000, 80000, 100000};
		total = total + seq[0] + seq[1] + seq[2];
		
		for(int i = minSeq; i <= maxSeq; i++) {
			if (seq[2] == i) {
				total += seqValues[i-minSeq];
				break;
			}
		}
		return total;
	}
	
	public CardGameResult result() {
		result.sort(Comparator.comparing(g -> g.getPriority()));
		Collections.reverse(result);
		Set<Integer> DrawChecker = new HashSet<Integer>();
		List<String> drawPlayers = new ArrayList<>();
		CardGameResult cardGameResult = new CardGameResult();
		for (Game game : result) {
			DrawChecker.add(game.getPriority());
			drawPlayers.add(game.getWinnerName());
		}
		
		Game winner = result.get(0);
		if(DrawChecker.size()==1)
		{
			System.out.println("Match Drawn");
			System.out.println("All the Player have same set of cards");
			System.out.println("===========================================");
			cardGameResult.setResult("Match Drawn");
			cardGameResult.setPriority(winner.getPriority());
			cardGameResult.setCards(winner.getCards());
			cardGameResult.setPlayer(drawPlayers);
		}else {
			System.out.println("Winner Player");
			System.out.println("===========================================");
			System.out.println("Name : " + winner.getWinnerName());
			System.out.println("Priority : " + winner.getPriority());
			System.out.println("Card : " + winner.getCards());
			cardGameResult.setResult("Match Own");
			cardGameResult.setPriority(winner.getPriority());
			cardGameResult.setCards(winner.getCards());
			List<String> winnerName = new ArrayList<>();
			winnerName.add(winner.getWinnerName());
			cardGameResult.setPlayer(winnerName);
		}
		return cardGameResult;
		

	}
	
	public CardGameResult winner() {
		CardGameResult cardGameResult = new CardGameResult();
		cardIdentify();
		cardGameResult=result();
		return cardGameResult;
	}
	
	public CardGameResult getGameResult(Map<String, List<String>> playersAndCardDetails) throws Exception {
		CardGameResult cardGameResult = new CardGameResult();
		setNoOfPlayerAndCard(playersAndCardDetails);
		cardGameResult = winner();
		return cardGameResult;
	}
}