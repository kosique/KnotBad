package de.diezwei.knotbad.tokenizer;

import static de.diezwei.knotbad.tokenizer.Token.function;
import static de.diezwei.knotbad.tokenizer.Token.number;
import static de.diezwei.knotbad.tokenizer.Token.operator;
import static java.util.Collections.unmodifiableList;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.diezwei.knotbad.Tokenizer;
import de.diezwei.knotbad.exception.UnexpectedKnotBadException;
import de.diezwei.knotbad.node.Operators;

public class SimpleTokenizer implements Tokenizer
{

    private final String input;

    static class SimpleTokenizerIterator implements Iterator<Token>
    {

        final StreamTokenizer tokenizer;
        private final List<Token> preParsedTokens = new ArrayList<>();
        private Token lastToken;

        public SimpleTokenizerIterator(String input)
        {
            super();
            tokenizer = new StreamTokenizer(new StringReader(input));
            tokenizer.ordinaryChar('/');
        }

        @Override
        public boolean hasNext()
        {
            return (lastToken == null) || (lastToken.getType() != TokenType.STREAM_END);
        }

        @Override
        public Token next()
        {
            int type;

            if (preParsedTokens.size() == 0)
            {
                try
                {
                    type = tokenizer.nextToken();
                }
                catch (final IOException e)
                {
                    throw new UnexpectedKnotBadException("Error while tokenizing", e);
                }
                switch (type)
                {
                case StreamTokenizer.TT_EOF:

                    this.preParsedTokens.add(Token.streamend());
                    break;

                case StreamTokenizer.TT_EOL:

                    this.preParsedTokens.add(Token.lineend());
                    break;

                case StreamTokenizer.TT_NUMBER:

                    final double rawValue = tokenizer.nval;

                    final String value = String.valueOf(rawValue);

                    if ((lastToken != null) && (lastToken.getType() == TokenType.NUMBER))
                    {
                        this.preParsedTokens.add(operator("+"));
                    }

                    this.preParsedTokens.add(number(value));

                    break;

                case StreamTokenizer.TT_WORD:

                    final String word = tokenizer.sval;

                    if (Operators.getInstance().isFunction(word))
                    {
                        this.preParsedTokens.add(function(word));
                    }
                    else
                    {
                        this.preParsedTokens.add(Token.variable(word));
                    }

                    break;

                default:

                    final String operator = String.valueOf((char) tokenizer.ttype);

                    this.preParsedTokens.add(operator(operator));

                    break;
                }
            }

            lastToken = preParsedTokens.remove(0);

            return lastToken;
        }

        @Override
        public void remove()
        {
            // TODO Auto-generated method stub

        }

    }

    public SimpleTokenizer(String input)
    {
        super();
        this.input = input;
    }

    @Override
    public Iterator<Token> iterator()
    {
        return new SimpleTokenizerIterator(input);
    }

    @Override
    public List<Token> getTokens()
    {
        final List<Token> tokens = new ArrayList<>();

        for (final Token token : this)
        {
            tokens.add(token);
        }

        return unmodifiableList(tokens);
    }

}
