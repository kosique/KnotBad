package de.diezwei.knotbad.parser.token;

import de.diezwei.knotbad.knot.Operator;


public class OperatorToken extends Token
{
    private Operator knot;
    private final String literal; 

    public OperatorToken(String literal)
    {
        this.literal = literal;
    }

    @Override
    public boolean isOperator()
    {
        return true;
    }

    @Override
    public AssocType getAssocType()
    {
        return this.knot.getAssocType();
    }

    @Override
    public int getPrecedence()
    {
        return this.knot.getPrecedence();
    }

    @Override
    public String getLiteral()
    {
        return literal;
    }
    
    

}
