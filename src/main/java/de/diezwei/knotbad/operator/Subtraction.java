package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.node.Binary;
import de.diezwei.knotbad.node.Node;
import de.diezwei.knotbad.parser.token.AssocType;

public class Subtraction extends Binary
{

    public Subtraction()
    {
        super();
    }

    public Subtraction(Node child1, Node child2)
    {
        super(child1, child2);
    }

    @Override
    public double resolve()
    {
        return getLeftArgument().resolve() - getRightArgument().resolve();
    }

    @Override
    public AssocType getAssocType()
    {
        return AssocType.LEFT;
    }

    @Override
    public int getPrecedence()
    {
        return 10;
    }

    @Override
    public String getLiteral()
    {
        return "-";
    }
}