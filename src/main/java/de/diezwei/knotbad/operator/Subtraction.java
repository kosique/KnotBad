package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.knot.Binary;
import de.diezwei.knotbad.knot.Knot;
import de.diezwei.knotbad.parser.token.AssocType;

public class Subtraction extends Binary
{

	public Subtraction()
    {
        super();
    }

    public Subtraction(Knot child1, Knot child2)
	{
		super(child1, child2);
	}

	@Override
	public double resolve()
	{
		return getChild1().resolve() - getChild2().resolve();
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
        return "-";
    }
}