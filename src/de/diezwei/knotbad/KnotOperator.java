package de.diezwei.knotbad;

public abstract class KnotOperator extends Knot {
	public enum Type {
		ADDITION,
		DIVISION,
		MULTIPLICATION,
		SUBTRACTION,
	}

	private Knot child1;
	private Knot child2;

	@Override
	public Knot getChild1() {
		return this.child1;
	}

	@Override
	public void setChild1(final Knot knot) {
		this.child1 = knot;
	}

	@Override
	public Knot getChild2() {
		return this.child2;
	}

	@Override
	public void setChild2(final Knot knot) {
		this.child2 = knot;
	}
}