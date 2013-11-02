package de.diezwei.knotbad.parser;

import static java.lang.Math.abs;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.diezwei.knotbad.knot.Operators;
import de.diezwei.knotbad.parser.token.AssocType;

public class Parser
{
    private final Stack<String> stack = new Stack<>();
    private final List<String> output = new ArrayList<>();

    List<String> toPostfix(String input) throws IOException
    {
        final StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(input));

        for (int tval; (tval = tokenizer.nextToken()) != StreamTokenizer.TT_EOF;)
        {
            switch (tval)
            {
            case StreamTokenizer.TT_NUMBER:

                if (tokenizer.nval < 0)
                {
                    processOperator("-");
                }

                processValue(abs(tokenizer.nval));

                break;

            case StreamTokenizer.TT_WORD:

                break;

            case StreamTokenizer.TT_EOL:

                break;

            default:

                final String literal = String.valueOf((char) tokenizer.ttype);

                processOperator(literal);

            }
        }

        while (!stack.isEmpty())
        {
            output.add(stack.pop());
        }
        
        return output;
    }

    private boolean processValue(double value)
    {
        return output.add(String.valueOf(value));
    }

    private void processOperator(String operatorLiteral)
    {
        final Operators operators = Operators.getInstance();

        final String operator = operatorLiteral;
        
        if (!stack.empty())
        {

            final int tokenPrecedence = operators.getPrecedence(operatorLiteral);

            while (!stack.empty() 
                    && (((getStackAssocType() == AssocType.LEFT) && (getStackPrecedence() >= tokenPrecedence)) || (getStackPrecedence() > tokenPrecedence)))
            {
                output.add(stack.pop());
            }
        }

        stack.add(operator);
    }

    private int getStackPrecedence()
    {
        final String literal = stack.lastElement();
        return Operators.getInstance().getPrecedence(literal);
    }

    private AssocType getStackAssocType()
    {
        final String literal = stack.lastElement();
        return Operators.getInstance().getAssocType(literal);
    }

    public List<String> getOutput()
    {
        return output;
    }
}
