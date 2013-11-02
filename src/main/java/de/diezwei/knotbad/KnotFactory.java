package de.diezwei.knotbad;

import de.diezwei.knotbad.knot.Knot;
import de.diezwei.knotbad.knot.Value;
import de.diezwei.knotbad.operator.Addition;
import de.diezwei.knotbad.operator.Division;
import de.diezwei.knotbad.operator.Factorial;
import de.diezwei.knotbad.operator.Multiplication;
import de.diezwei.knotbad.operator.Subtraction;

public abstract class KnotFactory
{
	public static Knot createKnot(final Type type, Knot child1, Knot child2)
	{
		if (Type.ADDITION.equals(type))
		{
			return new Addition(child1, child2);
		}
		else if (Type.DIVISION.equals(type))
		{
			return new Division(child1, child2);
		}
		else if (Type.FACTORIAL.equals(type))
		{
			return new Factorial(child1);
		}
		else if (Type.SUBTRACTION.equals(type))
		{
			return new Subtraction(child1, child2);
		}
		else if (Type.MULTIPLICATION.equals(type))
		{
			return new Multiplication(child1, child2);
		}

		return null;
	}

	public static Knot createKnot(final Integer value)
	{
		return new Value(value);
	}
}