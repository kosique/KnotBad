package de.diezwei.knotbad;

import java.text.MessageFormat;

import de.diezwei.knotbad.exception.UnexpectedKnotBadException;
import de.diezwei.knotbad.knot.Binary;
import de.diezwei.knotbad.knot.Node;
import de.diezwei.knotbad.knot.Unary;
import de.diezwei.knotbad.knot.Value;
import de.diezwei.knotbad.operator.Addition;
import de.diezwei.knotbad.operator.Division;
import de.diezwei.knotbad.operator.Factorial;
import de.diezwei.knotbad.operator.Multiplication;
import de.diezwei.knotbad.operator.Subtraction;

public class KnotBuilder
{
	private Node knot;

	public KnotBuilder(int value)
	{
		this.knot = new Value(value);
	}

	public KnotBuilder(double value)
	{
		this.knot = new Value(value);
	}

	public KnotBuilder(KnotBuilder builder)
	{
		this.knot = builder.getKnot();
	}

	public KnotBuilder knot(Class<? extends Unary> type)
	{
		try
		{
			if (type != null)
			{
				this.knot = type.getConstructor(Node.class).newInstance(this.knot);
			}

			return this;
		}
		catch (final ReflectiveOperationException e)
		{
			throw new UnexpectedKnotBadException(MessageFormat.format("Cannot instantiate knot of type {0}", type), e);
		}
	}

	public KnotBuilder knot(Class<? extends Binary> type, int value)
	{
		try
		{
			if (type != null)
			{
				this.knot = type.getConstructor(Node.class).newInstance(this.knot, new Value(value));
			}

			return this;
		}
		catch (final ReflectiveOperationException e)
		{
			throw new UnexpectedKnotBadException(MessageFormat.format("Cannot instantiate knot of type {0}", type), e);
		}
	}

	public KnotBuilder(Class<? extends Binary> type, KnotBuilder builder)
	{
		try
		{
			if (type != null)
			{
				this.knot = type.getConstructor(Node.class).newInstance(this.knot, builder.getKnot());
			}
		}
		catch (final ReflectiveOperationException e)
		{
			throw new UnexpectedKnotBadException(MessageFormat.format("Cannot instantiate knot of type {0}", type), e);
		}
	}

	public Node getKnot()
	{
		return this.knot;
	}

	public KnotBuilder plus(double value)
	{
		this.knot = new Addition(this.knot, new Value(value));

		return this;
	}

	public KnotBuilder plus(KnotBuilder builder)
	{
		this.knot = new Addition(this.knot, builder.getKnot());

		return this;
	}

	public KnotBuilder minus(double value)
	{
		this.knot = new Subtraction(this.knot, new Value(value));

		return this;
	}

	public KnotBuilder minus(KnotBuilder builder)
	{
		this.knot = new Subtraction(this.knot, builder.getKnot());

		return this;
	}

	public KnotBuilder multiply(double value)
	{
		this.knot = new Multiplication(this.knot, new Value(value));

		return this;
	}

	public KnotBuilder multiply(KnotBuilder builder)
	{
		this.knot = new Multiplication(this.knot, builder.getKnot());

		return this;
	}

	public KnotBuilder divide(double value)
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
		this.knot = new Factorial(this.knot);

		return this;
	}
}
