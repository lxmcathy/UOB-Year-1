package sample.calculator;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class CalculatorTest
{
	@Rule
	public Timeout globalTimeout = new Timeout(100, TimeUnit.MILLISECONDS);

	@Test
	public void testAdd()
	{
		Calculator calculator = new Calculator();
		double result = calculator.add(10, 50);
		assertEquals(60, result, 1e-8);
	}
	

	@Test(expected=ArithmeticException.class)
	public void testDivideByZeroException()
	{
		Calculator calculator = new Calculator();
		int result = calculator.intDivide(2, 0);		
	}


	@Test
	public void testDivide()
	{
		Calculator calculator = new Calculator();
		int result = calculator.intDivide(10, 5);		
		assertEquals(2, result, 1e-8);
	}
	

}