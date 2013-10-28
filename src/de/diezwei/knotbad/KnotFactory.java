package de.diezwei.knotbad;

import de.diezwei.knotbad.operator.Addition;
import de.diezwei.knotbad.operator.Division;
import de.diezwei.knotbad.operator.Multiplication;
import de.diezwei.knotbad.operator.Subtraction;

public abstract class KnotFactory {
	public static Knot createKnot(final KnotOperator.Type type) {
		if (KnotOperator.Type.ADDITION.equals(type)) {
			return new Addition();
		}
		else if (KnotOperator.Type.SUBTRACTION.equals(type)) {
			return new Subtraction();
		}
		else if (KnotOperator.Type.MULTIPLICATION.equals(type)) {
			return new Multiplication();
		}
		else if (KnotOperator.Type.DIVISION.equals(type)) {
			return new Division();
		}

		return null;
	}

	public static Knot createKnot(final Integer value) {
		return new KnotValue(value);
	}
}