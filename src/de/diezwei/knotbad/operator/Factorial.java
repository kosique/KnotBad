package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.KnotOperator;

public class Factorial extends KnotOperator {
	@Override
	public Integer resolve() throws Exception {
		return factorialRecursive(getChild1().resolve());
	}

	private Integer factorialRecursive(final Integer value) {
		if (value <= 1) {
			return 1;
		}

		return (value * factorialRecursive(value - 1));
	}
}