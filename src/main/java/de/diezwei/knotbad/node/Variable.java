package de.diezwei.knotbad.node;

import static java.util.Collections.emptyList;

import java.util.List;

import de.diezwei.knotbad.exception.VariableNotBoundException;
import de.diezwei.knotbad.parser.token.AssocType;

public class Variable extends Node
{

    private Double boundValue;
    private final String literal;

    public Variable(String literal)
    {
        super();
        this.literal = literal;
    }

    @Override
    public double resolve()
    {
        if (boundValue != null) { return boundValue; }

        throw new VariableNotBoundException(this);
    }

    @Override
    public String getLiteral()
    {
        return literal;
    }

    @Override
    public AssocType getAssocType()
    {
        return AssocType.NONE;
    }

    @Override
    public int getPrecedence()
    {
        return 0;
    }

    public void bind(double value)
    {
        this.boundValue = value;
    }

    @Override
    public List<Node> getChildren()
    {
        return emptyList();
    }
}
