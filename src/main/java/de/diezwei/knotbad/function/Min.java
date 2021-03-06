package de.diezwei.knotbad.function;

import de.diezwei.knotbad.node.Function;

public class Min extends Function
{
	@Override
	public double resolve()
	{
		return Math.min(getArgument(0).resolve(), getArgument(1).resolve());
	}

	@Override
	public String getLiteral()
	{
		return "min";
	}

	@Override
	public int getArity()
	{
		return 2;
	}

}
