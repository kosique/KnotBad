package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.node.Node;
import de.diezwei.knotbad.node.Unary;
import de.diezwei.knotbad.parser.token.AssocType;

public class SquareRoot extends Unary
{
    public SquareRoot()
    {
        super();
    }

    public SquareRoot(Node child1)
    {
        super(child1);
    }

    @Override
    public double resolve()
    {
        return Math.sqrt(getArgument().resolve());
    }

    @Override
    public AssocType getAssocType()
    {
        return AssocType.LEFT;
    }

    @Override
    public int getPrecedence()
    {
        return 30;
    }

    @Override
    public String getLiteral()
    {
        return "sqrt";
    }
}