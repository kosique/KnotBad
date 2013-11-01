package de.diezwei.knotbad.parser.token;

import de.diezwei.knotbad.exception.UnexpectedKnotBadException;
import de.diezwei.knotbad.knot.Value;


public class ValueToken extends Token
{
    public ValueToken(double value)
    {
        setKnot(new Value(value));
    }

    @Override
    public boolean isOperator()
    {
        return false;
    }

    @Override
    public AssocType getAssocType()
    {
        return AssocType.NONE;
    }

    @Override
    public int getPrecedence()
    {
        throw new UnexpectedKnotBadException("Getting precedence on value token is nonsense");
    }

    @Override
    public String getRawOp()
    {
        throw new UnexpectedKnotBadException("Getting precedence on value token is nonsense");
    }
}
