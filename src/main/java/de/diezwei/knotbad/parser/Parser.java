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
import de.diezwei.knotbad.parser.token.OperatorToken;
import de.diezwei.knotbad.parser.token.Token;
import de.diezwei.knotbad.parser.token.ValueToken;

public class Parser
{
    private final Stack<Token> stack = new Stack<>();
    private final List<Token> output = new ArrayList<>();

    void buildSyntaxTree(String input) throws IOException
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

        System.out.println("Ergebnis: ");
        for (final Token token : output)
        {
            System.out.println(token);
        }
    }

    private boolean processValue(double value)
    {
        return output.add(new ValueToken(value));
    }

    private void processOperator(String operatorLiteral)
    {
        final Token token = new OperatorToken(operatorLiteral);
        final Operators operators = Operators.getInstance();

        if (!stack.empty())
        {

            final int tokenPrecedence = operators.getPrecedence(operatorLiteral);

            while (!stack.empty() && isOperatorOnStack()
                    && (((getStackAssocType() == AssocType.LEFT) && (getStackPrecedence() >= tokenPrecedence)) || (getStackPrecedence() > tokenPrecedence)))
            {
                output.add(stack.pop());
            }
        }

        stack.add(token);
    }

    private int getStackPrecedence()
    {
        final String literal = stack.lastElement().getLiteral();
        return Operators.getInstance().getPrecedence(literal);
    }

    private AssocType getStackAssocType()
    {
        final String literal = stack.lastElement().getLiteral();
        return Operators.getInstance().getAssocType(literal);
    }

    private boolean isOperatorOnStack()
    {
        return stack.lastElement().isOperator();
    }
}
