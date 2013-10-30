package de.diezwei.knotbad.knot;

public abstract class TernaryKnot extends BinaryKnot
{
	private final Knot child3;

	public TernaryKnot(Knot child1, Knot child2, Knot child3)
	{
		super(child1, child2);
		this.child3 = child3;
	}

	public Knot getChild3()
	{
		return child3;
	}
}
