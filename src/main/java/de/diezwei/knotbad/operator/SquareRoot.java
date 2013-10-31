package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.knot.Knot;
import de.diezwei.knotbad.knot.UnaryKnot;

public class SquareRoot extends UnaryKnot
{
	public SquareRoot(Knot child1)
	{
		super(child1);
	}

	@Override
	public double resolve()
	{
		return Math.sqrt(getChild1().resolve());
	}
}