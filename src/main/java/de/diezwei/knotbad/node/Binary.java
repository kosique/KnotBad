package de.diezwei.knotbad.node;

import static java.util.Arrays.asList;

import java.util.List;

public abstract class Binary extends Operator
{
    private Node rightArgument;
    private Node leftArgument;

    public Binary(Node leftArgument, Node rightArgument)
    {
        super();
        this.leftArgument = leftArgument;
        this.rightArgument = rightArgument;
    }

    public Binary()
    {
        this(new NullNode(), new NullNode());
    }

    public Node getLeftArgument()
    {
        return leftArgument;
    }

    public void setLeftArgument(Node leftArgument)
    {
        this.leftArgument = leftArgument;
    }

    public Node getRightArgument()
    {
        return rightArgument;
    }

    public void setRightArgument(Node rightArgument)
    {
        this.rightArgument = rightArgument;
    }

    @Override
    public List<Node> getChildren()
    {
        return asList(new Node[] { getLeftArgument(), getRightArgument() });
    }

    @Override
    public int getArity()
    {
        return 2;
    }
}
