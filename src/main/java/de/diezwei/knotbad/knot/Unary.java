package de.diezwei.knotbad.knot;

public abstract class Unary extends Operator
{
	private final Knot child1;

	public Unary(Knot child1)
	{
		super();
		this.child1 = child1;
	}

	public Unary()
    {
        this(new NoOp());
    }

    public Knot getChild1()
	{
		return child1;
	}
}
