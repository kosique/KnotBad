package de.diezwei.knotbad.knot;

public abstract class UnaryKnot extends Operator
{
	private final Knot child1;

	public UnaryKnot(Knot child1)
	{
		super();
		this.child1 = child1;
	}

	public UnaryKnot()
    {
        this(new NoOp());
    }

    public Knot getChild1()
	{
		return child1;
	}
}
