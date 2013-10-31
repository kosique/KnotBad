package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.knot.BinaryKnot;
import de.diezwei.knotbad.knot.Knot;

public class Addition extends BinaryKnot
{
	public Addition(Knot child1, Knot child2)
	{
		super(child1, child2);
	}

	@Override
	public double resolve()
	{
		return getChild1().resolve() + getChild2().resolve();
	}
}