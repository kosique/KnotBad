package de.diezwei.knotbad.parser.token;

import de.diezwei.knotbad.knot.Operator;


public class OperatorToken extends Token
{
    private Operator knot;
    private final char rawOp; 

    public OperatorToken(char ttype)
    {
        this.rawOp = ttype;
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
    public String getRawOp()
    {
        return String.valueOf(rawOp);
    }
    
    

}
