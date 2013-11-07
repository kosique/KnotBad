package de.diezwei.knotbad.knot;

public abstract class Ternary extends Operator
{
    private final Knot child2;
    private final Knot child1;
    private final Knot child3;

    public Ternary(Knot child2, Knot child1, Knot child3)
    {
        super();
        this.child2 = child2;
        this.child1 = child1;
        this.child3 = child3;
    }

    public Knot getChild2()
    {
        return child2;
    }

    public Knot getChild1()
    {
        return child1;
    }

    public Knot getChild3()
    {
        return child3;
    }

}
