package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.knot.Node;
import de.diezwei.knotbad.knot.Unary;
import de.diezwei.knotbad.parser.token.AssocType;

public class Factorial extends Unary
{
    public Factorial()
    {
        super();
    }
    
	public Factorial(Node child1)
	{
		super(child1);
	}

	@Override
	public double resolve()
	{
		return factorialRecursive(getArgument().resolve());
	}

	private double factorialRecursive(final double value)
	{
		if (value <= 1)
		{
			return 1;
		}

		return (value * factorialRecursive(value - 1));
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
        return "!";
    }
}