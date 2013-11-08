package de.diezwei.knotbad;

import de.diezwei.knotbad.node.Node;
import de.diezwei.knotbad.parser.Parser;

public class Solver
{

    private final String expression;

    private Node node;

    public Solver(String expression)
    {
        this.expression = expression;
    }

    public double solve()
    {
        return getNode().resolve();
    }

    private Node getNode()
    {
        if (this.node == null)
        {
            this.node = new Parser().parse(expression);
        }

        return this.node;
    }

}
