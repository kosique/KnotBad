package de.diezwei.knotbad.node;

import org.apache.commons.lang.NotImplementedException;

import de.diezwei.knotbad.parser.token.AssocType;

public class NullFunction extends Function
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
		return 0;
	}
}
