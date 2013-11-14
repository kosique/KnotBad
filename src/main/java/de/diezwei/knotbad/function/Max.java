package de.diezwei.knotbad.function;

import de.diezwei.knotbad.node.Function;

public class Max extends Function
{
	@Override
	public double resolve()
	{
		return Math.max(getArgument(0).resolve(), getArgument(1).resolve());
	}

	@Override
	public String getLiteral()
	{
		return "max";
	}

	@Override
	public int getArity()
	{
		return 2;
	}

}
