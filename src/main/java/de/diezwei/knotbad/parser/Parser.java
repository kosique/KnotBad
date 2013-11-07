package de.diezwei.knotbad.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.diezwei.knotbad.KnotBuilder;
import de.diezwei.knotbad.Tokenizer;
import de.diezwei.knotbad.knot.Binary;
import de.diezwei.knotbad.knot.Knot;
import de.diezwei.knotbad.knot.Operator;
import de.diezwei.knotbad.knot.Operators;
import de.diezwei.knotbad.knot.Ternary;
import de.diezwei.knotbad.knot.Unary;
import de.diezwei.knotbad.parser.token.AssocType;
import de.diezwei.knotbad.tokenizer.SimpleTokenizer;
import de.diezwei.knotbad.tokenizer.Token;
import de.diezwei.knotbad.tokenizer.TokenType;

public class Parser
{
    private final Stack<Token> stack = new Stack<>();
    private final List<Token> output = new ArrayList<>();

    public Knot parse(String input)
    {
        List<Token> postfix;

        try
        {
            postfix = toPostfix(input);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
            return null;
        }

        final Knot result = null;

        for (final Token token : postfix)
        {
            if (token.getType() == TokenType.OPERATOR_TOKEN)
            {
                final Class<? extends Operator> operatorClass = Operators.getInstance().getOperatorClass(token.getLiteral());
                
                int valueCount = 0;
                
                if (Unary.class.isAssignableFrom(operatorClass))
                {
                    new KnotBuilder(1).knot((Class<Unary>)operatorClass);
                    valueCount = 1;
                }
                else if (Binary.class.isAssignableFrom(operatorClass))
                {
                    new KnotBuilder(1).knot((Class<Binary>)operatorClass, 1);
                    valueCount = 2;
                }
                else if (Ternary.class.isAssignableFrom(operatorClass))
                {
                    new KnotBuilder(1).knot((Class<Ternary>)operatorClass, 1, 1);
                    valueCount = 3;
                }
                
                
                
            }
        }

        return result;

    }

    List<Token> toPostfix(String input) throws IOException
    {
        final Tokenizer tokenizer = new SimpleTokenizer(input);
        for (final Token token : tokenizer)
        {
            switch (token.getType())
            {
            case LINE_END:
            case STREAM_END:
            case UNKNOWN:

                break;

            case NUMBER:

                processValue(token);

                break;
            case OPERATOR_TOKEN:

                processOperator(token);

                break;
            }
        }

        while (!stack.isEmpty())
        {
            output.add(stack.pop());
        }

        return output;
    }

    private boolean processValue(Token value)
    {
        return output.add(value);
    }

    private void processOperator(Token token)
    {
        final Operators operators = Operators.getInstance();

        final String literal = token.getLiteral();

        if (!stack.empty())
        {

            final int tokenPrecedence = operators.getPrecedence(literal);

            while (!stack.empty()
                    && (((getStackAssocType() == AssocType.LEFT) && (getStackPrecedence() >= tokenPrecedence)) || (getStackPrecedence() > tokenPrecedence)))
            {
                output.add(stack.pop());
            }
        }

        stack.add(token);
    }

    private int getStackPrecedence()
    {
        return Operators.getInstance().getPrecedence(stack.lastElement().getLiteral());
    }

    private AssocType getStackAssocType()
    {
        return Operators.getInstance().getAssocType(stack.lastElement().getLiteral());
    }

    public List<Token> getOutput()
    {
        return output;
    }
}
