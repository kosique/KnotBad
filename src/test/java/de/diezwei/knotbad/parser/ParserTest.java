package de.diezwei.knotbad.parser;

import static de.diezwei.knotbad.tokenizer.Token.numberToken;
import static de.diezwei.knotbad.tokenizer.Token.operatorToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.Test;

import de.diezwei.knotbad.tokenizer.Token;

public class ParserTest
{
    @Test
    public void tokenizeNumber() throws Exception
    {
        final Parser parser = new Parser();

        final List<Token> output = parser.toPostfix("1");

        assertThat(output, hasSize(1));
        assertThat(output, contains(numberToken("1.0")));
    }

    @Test
    public void tokenizeSum() throws Exception
    {
        final Parser parser = new Parser();

        final List<Token> output = parser.toPostfix("1+2");

        assertThat(output, hasSize(3));
        assertThat(output, contains(numberToken("1.0"), numberToken("2.0"), operatorToken("+")));
    }

    @Test
    public void tokenizeFactorial() throws Exception
    {
        final Parser parser = new Parser();

        final List<Token> output = parser.toPostfix("10! / 9!");

        assertThat(output, contains(numberToken("10.0"), operatorToken("!"), numberToken("9.0"), operatorToken("!"), operatorToken("/")));
    }

    @Test
    public void tokenizeLongExpression() throws Exception
    {
        final Parser parser = new Parser();

        final List<Token> output = parser.toPostfix("1+2*3-4*5+6");

        assertThat(output, hasSize(11));
        assertThat(
                output,
                contains(numberToken("1.0"), numberToken("2.0"), numberToken("3.0"), operatorToken("*"), operatorToken("+"), numberToken("4.0"),
                        numberToken("5.0"), operatorToken("*"), operatorToken("-"), numberToken("6.0"), operatorToken("+")));
    }

}
