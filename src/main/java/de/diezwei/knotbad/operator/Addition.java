package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.knot.Binary;
import de.diezwei.knotbad.knot.Node;
import de.diezwei.knotbad.parser.token.AssocType;

public class Addition extends Binary
{
    public Addition()
    {
        super();
    }
    
    public Addition(Node child1, Node child2)
    {
        super(child1, child2);
    }

    @Override
    public double resolve()
    {
        return getLeftArgument().resolve() + getRightArgument().resolve();
    }

    @Override
    public AssocType getAssocType()
    {
        return AssocType.LEFT;
    }

    @Override
    public int getPrecedence()
    {
        return 5;
    }

    @Override
    public String getLiteral()
    {
        return "+";
    }
}