package de.diezwei.knotbad.operator;

import de.diezwei.knotbad.KnotOperator;

public class Addition extends KnotOperator {
	@Override
	public Integer resolve() throws Exception {
		return getChild1().resolve() + getChild2().resolve();
	}
}