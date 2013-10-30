package de.diezwei.knotbad;

import de.diezwei.knotbad.knot.Knot;
import de.diezwei.knotbad.knot.Value;
import de.diezwei.knotbad.knot.UnaryKnot;
import de.diezwei.knotbad.operator.Addition;
import de.diezwei.knotbad.operator.Division;
import de.diezwei.knotbad.operator.Multiplication;
import de.diezwei.knotbad.operator.Subtraction;

public class KnotBuilder
{
	private Knot knot;

	public KnotBuilder(int value)
	{
		this.knot = new Value(value);
	}

	public KnotBuilder(KnotBuilder builder)
	{
		this.knot = builder.getKnot();
	}

	public KnotBuilder(Class<? extends UnaryKnot> type, int value)
	{
		try
		{
			this.knot = type.getConstructor(Knot.class).newInstance(new Value(value));
		}
		catch (final ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
	}

	public KnotBuilder(Class<? extends UnaryKnot> type, KnotBuilder builder)
	{
		try
		{
			this.knot = type.getConstructor(Knot.class).newInstance(builder.getKnot());
		}
		catch (final ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
	}

	public Knot getKnot()
	{
		return this.knot;
	}

	public KnotBuilder plus(int value)
	{
		this.knot = new Addition(this.knot, new Value(value));

		return this;
	}

	public KnotBuilder plus(KnotBuilder builder)
	{
		this.knot = new Addition(this.knot, builder.getKnot());

		return this;
	}

	public KnotBuilder minus(int value)
	{
		this.knot = new Subtraction(this.knot, new Value(value));

		return this;
	}

	public KnotBuilder minus(KnotBuilder builder)
	{
		this.knot = new Subtraction(this.knot, builder.getKnot());

		return this;
	}

	public KnotBuilder multiply(int value)
	{
		this.knot = new Multiplication(this.knot, new Value(value));

		return this;
	}

	public KnotBuilder multiply(KnotBuilder builder)
	{
		this.knot = new Multiplication(this.knot, builder.getKnot());

		return this;
	}

	public KnotBuilder divide(int value)
	{
		this.knot = new Division(this.knot, new Value(value));

		return this;
	}

	public KnotBuilder divide(KnotBuilder builder)
	{
		this.knot = new Division(this.knot, builder.getKnot());

		return this;
	}

	public KnotBuilder factorial()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
