package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.knot.Knot;
import de.diezwei.knotbad.knot.UnaryKnot;
import de.diezwei.knotbad.parser.token.AssocType;

public class SquareRoot extends UnaryKnot
{
	public SquareRoot()
    {
        super();
    }

    public SquareRoot(Knot child1)
	{
		super(child1);
	}

	@Override
	public double resolve()
	{
		return Math.sqrt(getChild1().resolve());
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
        return "sqrt";
    }
}