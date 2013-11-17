package de.diezwei.knotbad.node;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import de.diezwei.knotbad.parser.token.AssocType;

public abstract class Function extends Node
{
    private final List<Node> argument = new ArrayList<>();

    public abstract int getArity();

    @Override
    public AssocType getAssocType()
    {
        return AssocType.NONE;
    }

    @Override
    public int getPrecedence()
    {
        return 20;
    }

    public Node getArgument(int index)
    {
        return argument.get(index);
    }

    public void setArgument(int index, Node argument)
    {
        this.argument.add(index, argument);
    }

    @Override
    public List<Node> getChildren()
    {
        return unmodifiableList(argument);
    }

}
