package de.diezwei.knotbad;

public class KnotValue extends Knot {
	private Integer value;

	public KnotValue(final int value) {
		this.value = value;
	}

	@Override
	public Knot getChild1() {
		return null;
	}

	@Override
	public void setChild1(final Knot knot) {
		// no child in KnotValue
	}

	@Override
	public Knot getChild2() {
		return null;
	}

	@Override
	public void setChild2(final Knot knot) {
		// no child in KnotValue
	}

	@Override
	public Integer resolve() {
		return value;
	}
}