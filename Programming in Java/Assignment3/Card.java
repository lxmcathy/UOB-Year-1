package com.bham.pij.assignments.pontoon;

import java.util.ArrayList;
import java.util.Random;

public class Card extends Object{

	protected String suit;
	protected String value;
public Card(String suit, String value) {
		this.suit = suit;
		this.value = value;
	}
	String[] suits ={"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};
	String[] values ={"TWO", "THREE", "FOUR", "FIVE", "SIX",
			"SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"
	}	;
		
	
	public String getSuit() {
		return suit;
	}
	
	public String getValue() {
		return value;
	}
	public ArrayList<Integer> getNumericalValue(){
		ArrayList nv = new ArrayList();
		if(value == values[0] ) {
		nv.add(2);
		}else if (value == values[1]) {
			nv.add(2);
		} else if(value ==values[2]) {
			nv.add(3);
		}else if(value == values[3]) {
			nv.add(4);
		}else if(value == values[4]) {
			nv.add(5);
		}else if(value == values[5]) {
			nv.add(6);
		}else if(value == values[6]) {
			nv.add(7);
		}else if(value == values[8]) {
			nv.add(9);
		}else if(value == values[9]) {
			nv.add(10);
		}else if(value == values[10]) {
			nv.add(11);
		}else if(value == values[11]) {
			nv.add(12);
		}else if(value == values[12]) {
			nv.add(13);
		}else if(value == values[13]) {
			nv.add(11,1);
		}
				return nv;
	}
	public String toString()	{
		String s = "";
		s +="The card is"+suit + value;
				return s;	
}
	public static String getRandomSuit() {
		Random random = new Random();
		String j = new String("suits");
		int suits = (int)(Math.random()*4);
		return j;
			}
	public static String getRandomValue() {
		Random random = new Random();
		String m = new String("values");
		int suits = (int)(Math.random()*13);
		return m;

		
	}
}