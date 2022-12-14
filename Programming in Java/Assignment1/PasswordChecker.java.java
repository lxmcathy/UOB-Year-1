package passwordchecker.java;
import java.util.Scanner;
public class PasswordChecker {
	public static void main(String[] args) {
		System.out.println("Please enter your password");
	boolean isLetterDigit;String str; {
		boolean isDigit = false;
		boolean isLetter = false;
		boolean isUnderscore = false;
		for(int i = 0;i<str.length();i++) {
			if(Character.isDigit(str.charAt(i))) {
				isDigit = true;
			if(Character.isLetter(str.charAt(i))) {
				isLetter = true;
			}
			}
			String regex = "[a-zA-Z0-9] {8,12}$";
			boolean isOK = isDigit&&isLetter&&str.matches(regex);
			if(i!=a-z&&A-Z&&0-9&&_);
			System.out.println("Wrong Character");
			if(i==0-9);
			System.out.println("LEADING DIGIT");}
			int counter1 = 0;
			int counter2 = 0;
			if(counter1<1&&counter2<1);
			System.out.println("NOT MIXED CASE");
		}
		Scanner input = new Scanner(System.in);
		String pass = input.next();
		int length = pass.length();
		if(length>=8&&length<=12) {
			System.out.println("OK");
		}
		else if(length<8);
			System.out.println("TOO SHORT");
		if(length>12);
		System.out.println("TOO LONG");
	}
}
