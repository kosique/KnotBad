package de.diezwei.knotbad;

import java.text.MessageFormat;

import de.diezwei.knotbad.exception.UnexpectedKnotBadException;
import de.diezwei.knotbad.node.Binary;
import de.diezwei.knotbad.node.Node;
import de.diezwei.knotbad.node.Unary;
import de.diezwei.knotbad.node.Value;
import de.diezwei.knotbad.operator.Addition;
import de.diezwei.knotbad.operator.Division;
import de.diezwei.knotbad.operator.Factorial;
import de.diezwei.knotbad.operator.Multiplication;
import de.diezwei.knotbad.operator.Subtraction;

public class NodeBuilder
{
	private Node knot;

	public NodeBuilder(int value)
	{
		this.knot = new Value(value);
	}

	public NodeBuilder(double value)
	{
		this.knot = new Value(value);
	}

	public NodeBuilder(NodeBuilder builder)
	{
		this.knot = builder.getKnot();
	}

	public NodeBuilder knot(Class<? extends Unary> type)
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

	public NodeBuilder knot(Class<? extends Binary> type, int value)
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

	public NodeBuilder(Class<? extends Binary> type, NodeBuilder builder)
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

	public NodeBuilder plus(double value)
	{
		this.knot = new Addition(this.knot, new Value(value));

		return this;
	}

	public NodeBuilder plus(NodeBuilder builder)
	{
		this.knot = new Addition(this.knot, builder.getKnot());

		return this;
	}

	public NodeBuilder minus(double value)
	{
		this.knot = new Subtraction(this.knot, new Value(value));

		return this;
	}

	public NodeBuilder minus(NodeBuilder builder)
	{
		this.knot = new Subtraction(this.knot, builder.getKnot());

		return this;
	}

	public NodeBuilder multiply(double value)
	{
		this.knot = new Multiplication(this.knot, new Value(value));

		return this;
	}

	public NodeBuilder multiply(NodeBuilder builder)
	{
		this.knot = new Multiplication(this.knot, builder.getKnot());

		return this;
	}

	public NodeBuilder divide(double value)
	{
		this.knot = new Division(this.knot, new Value(value));

		return this;
	}

	public NodeBuilder divide(NodeBuilder builder)
	{
		this.knot = new Division(this.knot, builder.getKnot());

		return this;
	}

	public NodeBuilder factorial()
	{
		this.knot = new Factorial(this.knot);

		return this;
	}
}
