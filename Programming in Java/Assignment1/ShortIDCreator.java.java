package ShoutIDCreator.java;
import java.util.*;
public class ShortIDCreator {
	public static void main(String[] args) { 
		System.out.println("Please enter your name");
		char a = 65;
		System.out.println("a="+a);
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		String id = creatID(name);
		System.out.println("id="+id);
		System.exit(1);
	}
	public static String creatID(String input) {
		String[]split = input.split("\\s+");
		if(split.length==2) {
			char c1 = split[0].charAt(0);
			char c2 = split[1].charAt(0);
			String result = c1+"x"+c2;
			return result;
		}
		if(split.length==3) {
			char c1 = split[0].charAt(0);
			char c2 = split[0].charAt(0);
			char c3 = split[0].charAt(0);
			String result=""+c1+c2+c3;
			return result;
		}
		return null;
			}
}
