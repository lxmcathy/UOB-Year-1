package com.bham.pij.assignments.twit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TweetGenerator {
    
    private static final int TWEET_LENGTH = 30;
    private static ArrayList<Word> words;
    private static Random random = new Random();
    
    public static void main(String[] args) throws IOException {

    	new TweetGenerator();
    	
        System.out.println("Done.");
    }
    
    public TweetGenerator() throws IOException {

        ArrayList<String> cleaned = loadData();
        
        words = findWords(cleaned);
        
        System.out.println(createTweet(TWEET_LENGTH));
    }
    
    private ArrayList<String> loadData() throws IOException {
        
        ArrayList<String> data = new ArrayList<String>();
        
        BufferedReader br = new BufferedReader(new FileReader(new File("cleaned.txt")));
        
        String line = "";
        
        while ((line = br.readLine())!= null) {
            
            String[] tokens = line.split(" ");
            
            for (String t: tokens) {
                data.add(t);
            }
        }
        
        br.close();
        
        return data;
        
    }
    
    public String createTweet(int numWords) {
        StringBuffer s = new StringBuffer();
        int i = random.nextInt(words.size());
        Word word = words.get(i);
        s.append(word.getWord());
        String next = word.getRandomFollower();
        s.append(" "+next);
        for (int j = 2; j < TWEET_LENGTH; j++) {
            for (Word w : words) {
                if(w.getWord().equalsIgnoreCase(next)){
                    String randomFollower = w.getRandomFollower();
                    s.append(" "+randomFollower);
                    next=randomFollower;
                    break;
                }
            }
        }
        return s.toString();
    }
    
    private Word getWord(String word) {
        
        for (Word w: words) {
            if (w.getWord().equalsIgnoreCase(word)) {
                return w;
            }
        }
        return null;
    }
    
    
    
    public ArrayList<Word> findWords(ArrayList<String> cleaned) {
        ArrayList<Word> result = new ArrayList<>();
        ArrayList<String> mark = new ArrayList<>();
        for (int i=0; i<cleaned.size();i++) {
            if (mark.contains(cleaned.get(i))) {
                for (Word word : result) {
                    if (word.getWord().equalsIgnoreCase(cleaned.get(i))) {
                        word.incrementFrequency();
                        if (i < cleaned.size() - 1) {
                            word.addFollower(cleaned.get(i + 1));
                        }
                    }
                }
                
                
            } else {
                Word word = new Word(cleaned.get(i));
                word.setFrequency(1);
                if (i < cleaned.size() - 1) {
                    word.addFollower(cleaned.get(i + 1));
                }
                mark.add(cleaned.get(i));
                result.add(word);
            }
        }
        return result;
    }    
}
