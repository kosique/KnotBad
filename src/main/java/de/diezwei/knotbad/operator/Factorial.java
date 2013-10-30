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
	public Integer resolve() throws Exception
	{
		return factorialRecursive(getChild1().resolve());
	}

	private Integer factorialRecursive(final Integer value)
	{
		if (value <= 1)
		{
			return 1;
		}

		return (value * factorialRecursive(value - 1));
	}
}