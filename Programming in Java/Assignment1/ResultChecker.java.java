package ResultChecker.java;
import java.util.Scanner;
public class ResultChecker {
	public static void main(String[] args) {
	int g1 = 0;
	int g2 = 0;
	int g3 = 0;
	int g4 = 0;
	int g5 = 0;
	int g6 = 0;
	int g7 = 0;
	int g8 = 0;
	int project = 0;
	int index = 0;
	Scanner input = new Scanner(System.in);
	System.out.println("Please rnter your eight moudule grsdes and the project mark");
	java.util.Scanner sc = new java.util.Scanner(System.in);
	g1 = sc.nextInt();
	g2 = sc.nextInt();
	g3 = sc.nextInt();
	g4 = sc.nextInt();
	g5 = sc.nextInt();
	g6 = sc.nextInt();
	g7 = sc.nextInt();
	g8 = sc.nextInt();
	project = sc.nextInt();
	System.out.println("Avg:"+(g1+g2+g3+g4+g5+g6+g7+g8+project)/9.0f + ".");
	}
	void isValid(int grade) {
		if(grade>100&&grade<0);{
			System.out.println("ERROR");
		if(grade<40);{
			System.out.println("FAIL");
		for(grade = 0; grade<50;grade++);
		System.out.println("PASS");
		for(grade = 0; grade>=50;grade++);
		System.out.println("MERIT");
		}
		}
	}
	public String getResult(int[] grades,int projrctGrade) {
		return ("ERROR||FAIL||PASS||MERIT");
	}
}
