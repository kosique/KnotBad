package de.diezwei.knotbad.knot;


public class Value extends Knot
{
	private final Integer value;

	public Value(final int value)
	{
		this.value = value;
	}

	@Override
	public Integer resolve()
	{
		return value;
	}
}