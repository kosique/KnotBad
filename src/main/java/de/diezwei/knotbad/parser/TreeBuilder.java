package de.diezwei.knotbad.parser;

import static java.text.MessageFormat.format;

import java.util.List;
import java.util.Stack;

import de.diezwei.knotbad.NodeFactory;
import de.diezwei.knotbad.exception.UnexpectedKnotBadException;
import de.diezwei.knotbad.node.Binary;
import de.diezwei.knotbad.node.Function;
import de.diezwei.knotbad.node.Node;
import de.diezwei.knotbad.node.Operator;
import de.diezwei.knotbad.node.Unary;
import de.diezwei.knotbad.tokenizer.Token;

public class TreeBuilder
{

    public Node toNode(List<Token> list)
    {
        final Stack<Node> nodes = new Stack<>();

        for (final Token token : list)
        {
            switch (token.getType())
            {
            case NUMBER:
            case VARIABLE:

                nodes.push(NodeFactory.toNode(token));

                break;

            case OPERATOR_TOKEN:
                final Operator operator = token.toOperator();

                if (operator instanceof Unary)
                {
                    ((Unary) operator).setArgument(nodes.pop());
                }
                else if (operator instanceof Binary)
                {
                    ((Binary) operator).setRightArgument(nodes.pop());
                    ((Binary) operator).setLeftArgument(nodes.pop());
                }
                else
                {
                    final String pattern = "Unknown operator type. Is not Unary nor Binary: {0}";
                    throw new UnexpectedKnotBadException(format(pattern, operator.getClass().getCanonicalName()));
                }

                nodes.push(operator);

                break;

            case FUNCTION:

                final Function function = token.toFunction();

                if (function.getArity() <= 0)
                {
                    final String pattern = "Function {0} has an invalid operator count of {1}";
                    throw new UnexpectedKnotBadException(format(pattern, function.getClass().getSimpleName(), function.getArity()));
                }

                for (int i = 0; i < function.getArity(); i++)
                {
                    function.setArgument(i, nodes.pop());
                }

                nodes.push(function);

                break;

            case BRACE_CLOSE:
            case BRACE_OPEN:
            case LINE_END:
            case SEPARATOR:
            case STREAM_END:
            case UNKNOWN:
                break;

            default:
                throw new UnexpectedKnotBadException(format("Type {0} not supported.", token.getType()));
            }
        }

        return nodes.pop();
    }
}
