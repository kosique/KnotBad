package de.diezwei.knotbad.knot;

public abstract class UnaryKnot extends Knot
{
	private final Knot child1;

	public UnaryKnot(Knot child1)
	{
		super();
		this.child1 = child1;
	}

	public Knot getChild1()
	{
		return child1;
	}
}
