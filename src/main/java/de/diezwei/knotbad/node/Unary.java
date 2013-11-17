package de.diezwei.knotbad.node;

import static java.util.Collections.singletonList;

import java.util.List;

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
    public List<Node> getChildren()
    {
        return singletonList(getArgument());
    }

    @Override
    public int getArity()
    {
        return 1;
    }
}
