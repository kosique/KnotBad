package de.diezwei.knotbad.knot;

public abstract class BinaryKnot extends UnaryKnot
{
	private final Knot child2;

	public BinaryKnot(Knot child1, Knot child2)
	{
		super(child1);
		this.child2 = child2;
	}

	public BinaryKnot()
    {
	    this(new NoOp(), new NoOp());
    }

    public Knot getChild2()
	{
		return child2;
	}
}
