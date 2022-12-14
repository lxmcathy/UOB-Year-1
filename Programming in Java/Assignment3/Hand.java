package com.bham.pij.assignments.pontoon;

import java.util.ArrayList;
import java.util.Random;

public class Hand extends Object{
	private static final String[] values = {"TWO", "THREE", "FOUR", "FIVE", "SIX",
			"SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"
	};
	
    private static final String[] suits = {"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};
    ArrayList c = new ArrayList();
	public Hand() {

		Random random = new Random();
		for(int i = 0;i<100;i++) {
			int a = random.nextInt(c.size());
			Hand a1 = (Hand) c.get(a);
			c.set(a, a1);
		}
	}
	public int getHandSize() {
		return c.size();
		
	}
	public Card getCard​(int i) {
		return (Card) c.get(i);
	}
	public String showHand() {
		return c.toString();
	}
	public ArrayList<Integer> getNumericalValue() {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList handCardsList = null;
		Card card1 = (Card) handCardsList.get(0);
        ArrayList<Integer> card1NumericalValue = card1.getNumericalValue();
        if (card1NumericalValue.size() == 2) {
            result.add(card1NumericalValue.get(0));
            result.add(card1NumericalValue.get(1));
        } else {
            result.add(card1NumericalValue.get(0));
        }
        for (int i = 1; i < handCardsList.size(); i++) {
            Card card = (Card) handCardsList.get(i);
            ArrayList<Integer> cardValue = card.getNumericalValue();
            if (cardValue.size() == 1) {
                for (int j = 0; j < result.size(); j++) {
                    result.set(j, result.get(j) + cardValue.get(0));
                }
            } else {
                for (int j = 0; j < result.size(); j++) {
                    result.set(j, result.get(j) + cardValue.get(0));
                }
                result.add(result.get(result.size()-1)-10);
            }
        }
        return result;

    }
}
