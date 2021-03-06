package de.diezwei.knotbad.node;

import static java.util.Collections.emptyList;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;

import de.diezwei.knotbad.parser.token.AssocType;

public class NullOperator extends Operator
{
    @Override
    public double resolve()
    {
        throw new NotImplementedException("This is a NullOperator");
    }

    @Override
    public String getLiteral()
    {
        throw new NotImplementedException("This is a NullOperator");
    }

    @Override
    public AssocType getAssocType()
    {
        throw new NotImplementedException("This is a NullOperator");
    }

    @Override
    public int getPrecedence()
    {
        throw new NotImplementedException("This is a NullOperator");
    }

    @Override
    public int getArity()
    {
        throw new NotImplementedException("This is a NullOperator");
    }

    @Override
    public List<Node> getChildren()
    {
        return emptyList();
    }
}
