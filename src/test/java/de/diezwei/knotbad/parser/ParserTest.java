package de.diezwei.knotbad.parser;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import de.diezwei.knotbad.knot.Operators;
import de.diezwei.knotbad.parser.token.AssocType;
import de.diezwei.knotbad.parser.token.OperatorToken;
import de.diezwei.knotbad.parser.token.Token;
import de.diezwei.knotbad.parser.token.ValueToken;

public class ParserTest
{
    @Test
    public void testName() throws Exception
    {
        final Parser parser = new Parser();

        parser.tokenizeNumbers("");

    }

    @Test
    public void test() throws Exception
    {
        final Stack<Token> stack = new Stack<>();
        final List<Token> output = new ArrayList<>();

        final StreamTokenizer tokenizer = new StreamTokenizer(new StringReader("1+2*3*4+5"));

        for (int tval; (tval = tokenizer.nextToken()) != StreamTokenizer.TT_EOF;)
        {
            switch (tval)
            {
            case StreamTokenizer.TT_NUMBER:
                System.out.println("Zahl in Ausgabe: " + tokenizer.nval);

                output.add(new ValueToken(tokenizer.nval));

                break;
            case StreamTokenizer.TT_WORD:

                System.out.println("Wort: " + tokenizer.sval);

                break;
            case StreamTokenizer.TT_EOL:

                System.out.println("Ende der Zeile");

                break;
            default:

                final Token token = new OperatorToken((char) tokenizer.ttype);

                if (!stack.empty())
                {

                    final Token lastElement = stack.lastElement();

                    final String literal = String.valueOf((char) tokenizer.ttype);

                    AssocType lastElementAssocType = Operators.getInstance().getAssocType(stack.lastElement().getRawOp());
                    boolean lastElementIsOperator = stack.lastElement().isOperator();
                    int lastElementPrecedence = Operators.getInstance().getPrecedence(stack.lastElement().getRawOp());

                    final int tokenPrecedence = Operators.getInstance().getPrecedence(String.valueOf((char) tokenizer.ttype));

                    while (!stack.empty() && lastElementIsOperator
                            && (((lastElementAssocType == AssocType.LEFT) && (lastElementPrecedence >= tokenPrecedence))
                            || (lastElementPrecedence > tokenPrecedence)))
                    {
                        final Token pop = stack.pop();

                        System.out.println("Operator von Stack in Ausgabe: " + pop);

                        output.add(pop);

                        System.out.println(lastElement.getRawOp() + " :: " + token.getRawOp());
                        System.out.println(lastElementPrecedence + " :: " + tokenPrecedence);

                        if (!stack.empty())
                        {
                            lastElementAssocType = Operators.getInstance().getAssocType(stack.lastElement().getRawOp());
                            lastElementIsOperator = stack.lastElement().isOperator();
                            lastElementPrecedence = Operators.getInstance().getPrecedence(stack.lastElement().getRawOp());
                        }
                    }
                }

                System.out.println("Operator in Stack: " + token);
                stack.add(token);

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
}
