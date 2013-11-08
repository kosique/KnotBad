package de.diezwei.knotbad.node;

public abstract class Unary extends Operator
{
	private Node argument;

	public Unary(Node argument)
	{
		super();
		this.argument = argument;
	}

	public Unary()
    {
        this(new NullNode());
    }

    public Node getArgument()
	{
		return argument;
	}
    
    public void setArgument(Node argument)
    {
        this.argument = argument;
    }

    @Override
    public int getArity()
    {
        return 1; 
    }
}
