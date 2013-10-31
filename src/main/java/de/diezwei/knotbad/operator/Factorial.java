package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.knot.Knot;
import de.diezwei.knotbad.knot.UnaryKnot;

public class Factorial extends UnaryKnot
{
	public Factorial(Knot child1)
	{
		super(child1);
	}

	@Override
	public double resolve()
	{
		return factorialRecursive(getChild1().resolve());
	}

	private double factorialRecursive(final double value)
	{
		if (value <= 1)
		{
			return 1;
		}

		return (value * factorialRecursive(value - 1));
	}
}