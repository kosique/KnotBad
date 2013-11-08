package de.diezwei.knotbad.node;

import org.apache.commons.lang.NotImplementedException;

import de.diezwei.knotbad.parser.token.AssocType;

public class NullNode extends Node
{
    @Override
    public double resolve()
    {
        throw new NotImplementedException("This is a NoOp Operation");
    }

    @Override
    public String getLiteral()
    {
        return null;
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
}
