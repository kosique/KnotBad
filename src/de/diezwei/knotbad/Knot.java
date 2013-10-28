package de.diezwei.knotbad;

public abstract class Knot {
	public abstract Knot getChild1();
	public abstract void setChild1(Knot knot);
	public abstract Knot getChild2();
	public abstract void setChild2(Knot knot);

	public abstract Integer resolve() throws Exception;
}