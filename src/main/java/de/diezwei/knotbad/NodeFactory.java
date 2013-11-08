package de.diezwei.knotbad;

import java.text.MessageFormat;

import de.diezwei.knotbad.exception.UnexpectedKnotBadException;
import de.diezwei.knotbad.knot.Binary;
import de.diezwei.knotbad.knot.NullNode;
import de.diezwei.knotbad.knot.Node;
import de.diezwei.knotbad.knot.Operator;
import de.diezwei.knotbad.knot.Operators;
import de.diezwei.knotbad.knot.Unary;
import de.diezwei.knotbad.knot.Value;
import de.diezwei.knotbad.operator.Addition;
import de.diezwei.knotbad.operator.Division;
import de.diezwei.knotbad.operator.Factorial;
import de.diezwei.knotbad.operator.Multiplication;
import de.diezwei.knotbad.operator.Subtraction;
import de.diezwei.knotbad.tokenizer.Token;

public abstract class NodeFactory
{

    public static Node toNode(Token token)
    {

        switch (token.getType())
        {
        case NUMBER:

            return toValue(token);

        case OPERATOR_TOKEN:
            return toOperator(token);

        default:
            throw new UnexpectedKnotBadException("Unsupported token type: " + token.getType());
        }
    }

    private static Node toValue(Token token)
    {
        return new Value(Double.parseDouble(token.getLiteral()));
    }

    public static Node toOperator(Token token, Node... arguments)
    {
        if (token == null) { return new NullNode(); }

        final Class<? extends Operator> opType = Operators.getInstance().getOperatorClass(token.getLiteral());

        if (opType == null) { return new NullNode(); }

        if (Unary.class.isAssignableFrom(opType))
        {
            checkArgumentNumber(opType, 1, arguments);
            final Unary instance = (Unary) instantiateOperator(opType);
            instance.setArgument(arguments[0]);

        }
        else if (Binary.class.isAssignableFrom(opType))
        {
            checkArgumentNumber(opType, 2, arguments);
            final Binary instance = (Binary) instantiateOperator(opType);
            instance.setLeftArgument(arguments[0]);
            instance.setRightArgument(arguments[1]);
        }
        else
        {
            throw new UnexpectedKnotBadException("Operator type no supported: " + opType.getCanonicalName());
        }
        return null;
    }

    private static void checkArgumentNumber(final Class<? extends Operator> type, final int expected, Node... arguments)
    {
        if (arguments.length < expected)
        {
            final String messagePattern = "Not enough arguments: Operation {0} expects {1} argument(s), but only {2} arguments passed.";
            throw new UnexpectedKnotBadException(MessageFormat.format(messagePattern, type.getName(), expected, arguments.length));
        }
    }

    private static Operator instantiateOperator(final Class<? extends Operator> opType)
    {
        Operator operator;
        try
        {
            operator = opType.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new UnexpectedKnotBadException("Cannot instantiate operator type " + opType.getCanonicalName(), e);
        }
        return operator;
    }

    public static Node createKnot(final Type type, Node child1, Node child2)
    {
        if (Type.ADDITION.equals(type))
        {
            return new Addition(child1, child2);
        }
        else if (Type.DIVISION.equals(type))
        {
            return new Division(child1, child2);
        }
        else if (Type.FACTORIAL.equals(type))
        {
            return new Factorial(child1);
        }
        else if (Type.SUBTRACTION.equals(type))
        {
            return new Subtraction(child1, child2);
        }
        else if (Type.MULTIPLICATION.equals(type)) { return new Multiplication(child1, child2); }

        return null;
    }

    public static Node createKnot(final Integer value)
    {
        return new Value(value);
    }
}