package de.diezwei.knotbad.node;

import de.diezwei.knotbad.parser.token.AssocType;

public class Value extends Node
{
	private final double value;

	public Value(final int value)
	{
		this.value = value;
	}

	public Value(final double value)
	{
		this.value = value;
	}

	@Override
	public double resolve()
	{
		return value;
	}

    @Override
    public String getLiteral()
    {
        return String.valueOf(value);
    }

    @Override
    public AssocType getAssocType()
    {
        return AssocType.NONE;
    }

    @Override
    public int getPrecedence()
    {
        return 0;
    }
}