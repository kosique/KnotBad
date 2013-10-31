package de.diezwei.knotbad.knot;

public class Value extends Knot
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
}