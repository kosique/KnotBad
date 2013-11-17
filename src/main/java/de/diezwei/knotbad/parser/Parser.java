package de.diezwei.knotbad.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.diezwei.knotbad.Tokenizer;
import de.diezwei.knotbad.node.Node;
import de.diezwei.knotbad.node.Operators;
import de.diezwei.knotbad.parser.token.AssocType;
import de.diezwei.knotbad.tokenizer.SimpleTokenizer;
import de.diezwei.knotbad.tokenizer.Token;
import de.diezwei.knotbad.tokenizer.TokenType;

public class Parser
{
    private final Stack<Token> stack = new Stack<>();
    private final List<Token> output = new ArrayList<>();

    public Node parse(String input)
    {
        final List<Token> postfix = toPostfix(input);

        final TreeBuilder treeBuilder = new TreeBuilder();

        return treeBuilder.toNode(postfix);
    }

    /*
     * WENN Token IST-Funktion:
     * 
     * Token ZU Stack.
     * 
     * ENDEWENN WENN Token IST-Argumenttrennzeichen:
     * 
     * BIS Stack-Spitze IST öffnende-Klammer:
     * 
     * Stack-Spitze ZU Ausgabe. FEHLER-BEI Stack IST-LEER:
     * 
     * GRUND (1) Ein falsch platziertes Argumenttrennzeichen. GRUND (2) Der
     * schließenden Klammer geht keine öffnende voraus.
     * 
     * ENDEFEHLER
     * 
     * ENDEBIS
     * 
     * ENDEWENN
     */

    List<Token> toPostfix(String input)
    {
        // System.out.println("-------------------------");
        // System.out.println("Parsing: " + input);
        // System.out.println("-------------------------");

        final Tokenizer tokenizer = new SimpleTokenizer(input);

        for (final Token token : tokenizer)
        {
            // System.out.println();
            // System.out.println(" Stack: " + stack);
            // System.out.println("Output: " + output);
            // System.out.println();
            // System.out.println("Processing: " + token);

            switch (token.getType())
            {
            case LINE_END:
            case STREAM_END:
            case UNKNOWN:

                break;

            case NUMBER:

                processValue(token);
                break;

            case VARIABLE:

                processVariable(token);
                break;

            case OPERATOR_TOKEN:

                processOperator(token);
                break;

            case BRACE_CLOSE:

                processClosingBrace();
                break;

            case BRACE_OPEN:

                processOpeningBrace(token);
                break;

            case SEPARATOR:

                while (!"(".equals(stack.lastElement().getLiteral()))
                {
                    output.add(stack.pop());
                }

                /*
                 * BIS Stack-Spitze IST öffnende-Klammer:
                 * 
                 * Stack-Spitze ZU Ausgabe. FEHLER-BEI Stack IST-LEER:
                 * 
                 * GRUND (1) Ein falsch platziertes Argumenttrennzeichen. GRUND
                 * (2) Der schließenden Klammer geht keine öffnende voraus.
                 * 
                 * ENDEFEHLER
                 * 
                 * ENDEBIS
                 */
                break;

            case FUNCTION:

                stack.push(token);

                break;

            }
        }

        while (!stack.isEmpty())
        {
            output.add(stack.pop());
        }

        return output;
    }

    private void processOpeningBrace(final Token token)
    {
        stack.push(token);
    }

    private void processClosingBrace()
    {
        while (!stack.lastElement().getLiteral().equals("("))
        {
            output.add(stack.pop());
        }

        stack.pop();
    }

    private boolean processValue(Token value)
    {
        return output.add(value);
    }

    private boolean processVariable(Token variable)
    {
        return output.add(variable);
    }

    private void processOperator(Token token)
    {
        final Operators operators = Operators.getInstance();

        final String literal = token.getLiteral();

        if (!stack.empty())
        {
            final int tokenPrecedence = operators.getPrecedence(literal);

            while (!stack.empty()
                    && (stack.lastElement().getType() != TokenType.BRACE_CLOSE)
                    && (stack.lastElement().getType() != TokenType.BRACE_OPEN)
                    && (((getStackAssocType() == AssocType.LEFT)
                    && (getStackPrecedence() >= tokenPrecedence))
                    || (getStackPrecedence() > tokenPrecedence)))
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
