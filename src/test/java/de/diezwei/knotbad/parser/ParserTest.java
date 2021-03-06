package de.diezwei.knotbad.parser;

import static de.diezwei.knotbad.tokenizer.Token.function;
import static de.diezwei.knotbad.tokenizer.Token.number;
import static de.diezwei.knotbad.tokenizer.Token.operator;
import static de.diezwei.knotbad.tokenizer.Token.variable;
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
        assertThat(output, contains(number("1.0")));
    }

    @Test
    public void tokenizeSum() throws Exception
    {
        final Parser parser = new Parser();

        final List<Token> output = parser.toPostfix("1+2");

        assertThat(output, hasSize(3));
        assertThat(output, contains(number("1.0"), number("2.0"), operator("+")));
    }

    @Test
    public void tokenizeFactorial() throws Exception
    {
        final Parser parser = new Parser();

        final List<Token> output = parser.toPostfix("10! / 9!");

        assertThat(
                output,
                contains(
                        number("10.0"),
                        operator("!"),
                        number("9.0"),
                        operator("!"),
                        operator("/")));
    }

    @Test
    public void tokenizeLongExpression() throws Exception
    {
        assertThat(
                new Parser().toPostfix("1+2*3-4*5+6"),
                contains(
                        number("1.0"),
                        number("2.0"),
                        number("3.0"),
                        operator("*"),
                        operator("+"),
                        number("-4.0"),
                        number("5.0"),
                        operator("*"),
                        operator("+"),
                        number("6.0"),
                        operator("+")));
    }

    @Test
    public void tokenizeBraces1() throws Exception
    {
        assertThat(
                new Parser().toPostfix("(1+2)*3"),
                contains(
                        number("1.0"),
                        number("2.0"),
                        operator("+"),
                        number("3.0"),
                        operator("*")
                ));
    }

    @Test
    public void tokenizeBraces2() throws Exception
    {
        assertThat(
                new Parser().toPostfix("(1+2)*(3-4)"),
                contains(
                        number("1.0"),
                        number("2.0"),
                        operator("+"),
                        number("3.0"),
                        number("-4.0"),
                        operator("+"),
                        operator("*")
                ));
    }

    @Test
    public void tokenizeBraces3() throws Exception
    {
        assertThat(
                new Parser().toPostfix("((1+2)*(3-4))+5"),
                contains(
                        number("1.0"),
                        number("2.0"),
                        operator("+"),
                        number("3.0"),
                        number("-4.0"),
                        operator("+"),
                        operator("*"),
                        number("5.0"),
                        operator("+")
                ));
    }

    @Test
    public void tokenizeBraces4() throws Exception
    {
        assertThat(
                new Parser().toPostfix("((1+2)*(3-4))/5"),
                contains(
                        number("1.0"),
                        number("2.0"),
                        operator("+"),
                        number("3.0"),
                        number("-4.0"),
                        operator("+"),
                        operator("*"),
                        number("5.0"),
                        operator("/")
                ));
    }

    @Test
    public void functionToPostfix() throws Exception
    {
        assertThat(new Parser().toPostfix("min"), contains(function("min")));
        assertThat(new Parser().toPostfix("min()"), contains(function("min")));
        assertThat(new Parser().toPostfix("min(1)"), contains(number("1.0"), function("min")));
        assertThat(new Parser().toPostfix("min(1,2)"), contains(number("1.0"), number("2.0"), function("min")));
        assertThat(new Parser().toPostfix("min(1,2) + 2"), contains(number("1.0"), number("2.0"), function("min"), number("2.0"), operator("+")));
    }

    @Test
    public void functionWithNodeArgsToPostfix() throws Exception
    {
        assertThat(new Parser().toPostfix("min(1+2,3*(4+5))"),
                contains(
                        number("1.0"),
                        number("2.0"),
                        operator("+"),
                        number("3.0"),
                        number("4.0"),
                        number("5.0"),
                        operator("+"),
                        operator("*"),
                        function("min")
                ));
    }

    @Test
    public void testVariable() throws Exception
    {
        assertThat(new Parser().toPostfix("x"),
                contains(
                variable("x")
                ));
    }

    @Test
    public void variablesANdValues() throws Exception
    {
        assertThat(new Parser().toPostfix("(x+1)*2/y"),
                contains(
                        variable("x"),
                        number("1.0"),
                        operator("+"),
                        number("2.0"),
                        operator("*"),
                        variable("y"),
                        operator("/")
                ));
    }
}
