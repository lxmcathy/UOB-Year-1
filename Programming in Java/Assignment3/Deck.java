package com.bham.pij.assignments.pontoon;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Deck extends Object{
	protected String suit;
	protected String value;
	public Deck() {
	this.suit = suit;
	this.value = value;
	
	}
	public void reset() {
		Random random = new Random();
		LinkedList list=new LinkedList();
		for (int i = 0; i < 52; i++) {
			
		}

		String[] suits ={"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};
		String[] values ={"TWO", "THREE", "FOUR", "FIVE", "SIX",
		"SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"
};		
	for(int i=0;i<suits.length;i++) {
		for(int j=0;j<values.length;i++) {
			list.add(new Deck());
		}
		}
	
	}
	public Card getCard​() {
		ArrayList list = new ArrayList();
		Card card = new Card(value, suit);
		for (int i=0;i<52;i++) {
			list.add(i);
		}
		return card;
	}
	private ArrayList<Card> deck;
	public Card dealCard(int i){
        Card c = getCard(i);
        if (c == null) {
            return c;
        }
        this.deck.set(i,null);
        return c;
    }
	
	
	private Card getCard(int i) {
		
		return null;
	}
	public int getDeckSize() {
		Deck deck = new Deck();
		int size = deck.getDeckSize();
		if(size == 52);
		return size;
	}

		
		
	}
	


