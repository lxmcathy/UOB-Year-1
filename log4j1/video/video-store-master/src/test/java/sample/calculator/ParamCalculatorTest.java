package sample.calculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Test;

@RunWith(value = Parameterized.class)
public class ParamCalculatorTest
{
	@Parameter(0)
	public double	expected;
	@Parameter(1)
	public double	valueOne;
	@Parameter(2)
	public double	valueTwo;

	@Parameters(name="{index}: {0} = add({1}, {2})")
	public static Collection<Double[]> getTestParameters()
	{
		return Arrays.asList(new Double[][]
			{
				{ 2d, 1d, 1d }, // expected, valueOne, valueTwo
				{ 3d, 2d, 1d }, // expected, valueOne, valueTwo
				{ 4d, 3d, 1d }, // expected, valueOne, valueTwo
			});
	}
	
//	public ParamCalculatorTest(double expected, double valueOne, double valueTwo)
//	{
//		this.expected = expected;
//		this.valueOne = valueOne;
//		this.valueTwo = valueTwo;
//	}
	
	@Test
	public void sum()
	{
		Calculator calc = new Calculator();
		assertEquals(expected, calc.add(valueOne, valueTwo), 1e-8);
	}
}
