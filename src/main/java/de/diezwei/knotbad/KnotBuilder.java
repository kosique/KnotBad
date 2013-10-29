package de.diezwei.knotbad;

import de.diezwei.knotbad.operator.Addition;
import de.diezwei.knotbad.operator.Division;
import de.diezwei.knotbad.operator.Multiplication;
import de.diezwei.knotbad.operator.Subtraction;

public class KnotBuilder
{

	private Knot knot;

	public KnotBuilder(int value)
	{
		this.knot = new KnotValue(value);
	}

	public KnotBuilder(KnotBuilder builder)
	{
		this.knot = builder.getKnot();
	}

	public Knot getKnot()
	{
		return this.knot;
	}

	public KnotBuilder plus(int value)
	{
		addOperation(new Addition(), value);

		return this;
	}

	public KnotBuilder plus(KnotBuilder builder)
	{
		addOperation(new Addition(), builder);

		return this;
	}

	public KnotBuilder minus(int value)
	{
		addOperation(new Subtraction(), value);

		return this;
	}

	public KnotBuilder minus(KnotBuilder builder)
	{
		addOperation(new Subtraction(), builder);

		return this;
	}

	public KnotBuilder multiply(int value)
	{
		addOperation(new Multiplication(), value);

		return this;
	}

	public KnotBuilder multiply(KnotBuilder builder)
	{
		addOperation(new Multiplication(), builder);

		return this;
	}

	public KnotBuilder divide(int value)
	{
		addOperation(new Division(), value);

		return this;
	}

	public KnotBuilder divide(KnotBuilder builder)
	{
		addOperation(new Division(), builder);

		return this;
	}

	private void addOperation(final Knot knot, int value)
	{
		knot.setChild1(this.knot);
		knot.setChild2(new KnotValue(value));

		this.knot = knot;
	}

	private void addOperation(final Knot knot, KnotBuilder builder)
	{
		knot.setChild1(this.knot);
		knot.setChild2(builder.getKnot());

		this.knot = knot;
	}

}
