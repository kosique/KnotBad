package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.knot.BinaryKnot;
import de.diezwei.knotbad.knot.Knot;

public class Subtraction extends BinaryKnot
{
	public Subtraction(Knot child1, Knot child2)
	{
		super(child1, child2);
	}

	@Override
	public Integer resolve() throws Exception
	{
		return getChild1().resolve() - getChild2().resolve();
	}
}