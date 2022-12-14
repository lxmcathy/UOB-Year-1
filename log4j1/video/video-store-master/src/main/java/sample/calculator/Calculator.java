package sample.calculator;

import org.apache.log4j.Logger;

public class Calculator
{
	private static final Logger logger = Logger.getLogger(Calculator.class);

	public double add(double number1, double number2)
	{
		logger.debug("Adding " + number1 + " + " + number2);

		return number1 + number2;
	}

	public int intDivide(int number1, int number2)
	{
		return number1 / number2;
	}
}