package de.diezwei.knotbad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.diezwei.knotbad.node.Node;
import de.diezwei.knotbad.node.Variable;
import de.diezwei.knotbad.parser.Parser;

public class Solver
{

    private final String expression;

    private Node root;

    private Map<String, Node> nodes;

    public Solver(String expression)
    {
        this.expression = expression;
    }

    public double solve()
    {
        return getRoot().resolve();
    }

    private Node getRoot()
    {
        if (this.root == null)
        {
            this.root = new Parser().parse(expression);
            this.nodes = findNodes(this.root);
        }

        return this.root;
    }

    public Map<String, Node> getNodes()
    {
        if (this.nodes == null)
        {
            this.root = new Parser().parse(expression);
            this.nodes = findNodes(this.root);
        }

        return this.nodes;

    }

    private Map<String, Node> findNodes(Node node)
    {
        final Map<String, Node> result = new HashMap<>();

        result.put(node.getLiteral(), node);

        final List<Node> children = node.getChildren();

        for (final Node child : children)
        {
            result.putAll(findNodes(child));
        }

        return result;

    }

    public void bind(String variable, double value)
    {
        final Node node = getNodes().get(variable);

        if (node instanceof Variable)
        {
            final Variable var = (Variable) node;
            var.bind(value);
        }
    }
}
