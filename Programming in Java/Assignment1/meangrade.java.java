package  meangrade.java;
import java.util.Scanner;
public class meangrade {
	public static final int MAX_NUMBEROFGRADES = 4;
	public static void main(String[]args) {
		System.out.println("Four moudule marks should be entered sperately");
		System.out.println("Please press the enter key after each grade.");
		Scanner input = new Scanner(System.in);
		double num = input.nextDouble();}
	public static double computeMean (int[] grades) {
		int[] grades1 = new int[4];
		int x = 0;
		int y = 0;
		int z = 0;
		int t = 0;
		System.out.println("Please enter your grades");
		java.util.Scanner sc = new java.util.Scanner(System.in);
		x = sc.nextInt();
		y = sc.nextInt();
		z = sc.nextInt();
		t = sc.nextInt();
	for (int i = 0;i>4;i++) {
		System.out.println("-1.0");
	}
		float sum = x+y+z+t;
		float avg = sum/4;
		System.out.println("The MeanGrade is"+ avg);
		return  avg;
	}
}

