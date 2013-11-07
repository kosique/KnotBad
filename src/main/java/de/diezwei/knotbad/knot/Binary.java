package de.diezwei.knotbad.knot;

public abstract class Binary extends Operator
{
	private final Knot child2;
    private final Knot child1;

	public Binary(Knot child1, Knot child2)
	{
		super();
		this.child1 = child1;
		this.child2 = child2;
	}

	public Binary()
    {
	    this(new NoOp(), new NoOp());
    }
	
    public Knot getChild1()
    {
        return child1;
    }

    public Knot getChild2()
	{
		return child2;
	}
}
