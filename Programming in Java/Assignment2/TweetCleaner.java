package com.bham.pij.assignments.twit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TweetCleaner {

	private static ArrayList<String> raw = new ArrayList<String>();
	private static ArrayList<String> cleaned = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {

		new TweetCleaner();
		
		System.out.println("Done.");
	}
	
	public TweetCleaner() throws IOException {

		loadRaw();
		
		 clean();
		
		saveClean();
	}

	private void clean() {
		
		for (String line: raw) {
			
			String cln = clean(line);

			if (cln != null) {
				
				String[] toks = cln.split(" ");
				
				for (String s: toks) {
					addClean(s);			
				}	
			}
		}
	}

	public String clean(String input) {
		
		String[] strList = input.split(" ");
		String result=" ";
		String[] subStrList;
		System.out.print(strList[0]);
		for (int i = 1; i< strList.length;i++) {
			subStrList = strList[i].split(" ");
			if (!strList[i].contains("@")
	                && !strList[i].contains("¡ª")
	                && !strList[i].contains("¡­")
	                && !strList[i].contains("...")
	                && !strList[i].contains("https")
	                && !strList[i].contains("#")
	                && !strList[i].contains("RT")
	                && !strList[i].contains("rt")
	                && !strList[i].contains("0")
	                && !strList[i].contains("1")
	                && !strList[i].contains("2")
	                && !strList[i].contains("3")
	                && !strList[i].contains("4")
	                && !strList[i].contains("5")
	                && !strList[i].contains("6")
	                && !strList[i].contains("7")
	                && !strList[i].contains("8")
	                && !strList[i].contains("9")

	        ) {
				if (strList[i].contains("-")) {
	                strList[i] = strList[i].replace("-", "");
	            }
				strList[i].replaceAll(strList[i], " ");
			for( int i11 = 1;i11<input.length();i11++) {
				char alphabetic=input.charAt(i11);
				if(Character.isAlphabetic(alphabetic)&&input=="!"||input=="?"||input=="'") {
					return input;
				}
				else {
					if(input==","&&input=="."&&input=="?"&&input=="!"&&input==":"&&input==";");
					input.replace( input," ");
				}
			}
		
		return result;}}
		return result;
			}	 
	


	private void addClean(String clean) {
		
		cleaned.add(clean);
	}
	
	private void saveClean() throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter("cleaned.txt");
		
		for (String s: cleaned) {
			pw.print(s + " ");
		}
		
		pw.close();
		
	}
	
	private void loadRaw() throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(new File("donald.txt")));
		
		String line = "";
		
		while ((line = br.readLine())!= null) {
			
			raw.add(line);
		
		}
		
		br.close();
	}
}
