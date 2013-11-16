package de.diezwei.knotbad.tokenizer;

import static de.diezwei.knotbad.tokenizer.Token.function;
import static de.diezwei.knotbad.tokenizer.Token.number;
import static de.diezwei.knotbad.tokenizer.Token.operator;
import static de.diezwei.knotbad.tokenizer.Token.separator;
import static de.diezwei.knotbad.tokenizer.Token.streamend;
import static de.diezwei.knotbad.tokenizer.Token.unknown;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.diezwei.knotbad.Tokenizer;

public class SimpleTokenizerTest
{

    @Test
    public void testValue()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("1");

        final List<String> tokens = toLiteralList(simpleTokenizer);

        assertThat(tokens, contains("1.0", ""));
    }

    @Test
    public void testSum()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("1+2");

        final List<String> tokens = toLiteralList(simpleTokenizer);

        assertThat(tokens, contains("1.0", "+", "2.0", ""));
    }

    @Test
    public void testDifference()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("1-2");

        final List<String> tokens = toLiteralList(simpleTokenizer);

        assertThat(tokens, contains("1.0", "-", "2.0", ""));
    }

    @Test
    public void testDivision()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("1/2");

        final List<String> tokens = toLiteralList(simpleTokenizer);

        assertThat(tokens, contains("1.0", "/", "2.0", ""));
    }

    @Test
    public void testLongExpression()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("1-2*3/4");

        final List<String> tokens = toLiteralList(simpleTokenizer);

        assertThat(tokens, contains("1.0", "-", "2.0", "*", "3.0", "/", "4.0", ""));
    }

    @Test
    public void testWhitespaces() throws Exception
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer(" 1 + 2 ");

        final List<String> tokens = toLiteralList(simpleTokenizer);

        assertThat(tokens, contains("1.0", "+", "2.0", ""));
    }

    @Test
    public void testBraces() throws Exception
    {
        assertThat(
                new SimpleTokenizer("(1+2)").getTokens(),
                contains(
                        operator("("),
                        number("1.0"),
                        operator("+"),
                        number("2.0"),
                        operator(")"),
                        streamend()));
    }

    private List<String> toLiteralList(final Tokenizer simpleTokenizer)
    {
        final List<String> tokens = new ArrayList<>();
        for (final Token token : simpleTokenizer)
        {
            tokens.add(token.getLiteral());
        }
        return tokens;
    }

    @Test
    public void testGetList()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("1-2*3/4");

        final List<Token> tokens = simpleTokenizer.getTokens();

        final Token token1 = Token.number("1.0");
        final Token token2 = Token.operator("-");
        final Token token3 = Token.number("2.0");
        final Token token4 = Token.operator("*");
        final Token token5 = Token.number("3.0");
        final Token token6 = Token.operator("/");
        final Token token7 = Token.number("4.0");
        final Token token8 = Token.streamend();

        assertThat(tokens, contains(token1, token2, token3, token4, token5, token6, token7, token8));
    }

    @Test
    public void testMethodname()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("min");

        assertThat(simpleTokenizer.getTokens(),
                contains(
                        function("min"),
                        streamend()));
    }

    @Test
    public void testInvalidMethodname()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("fjdkshdf");

        assertThat(simpleTokenizer.getTokens(),
                contains(
                        unknown("fjdkshdf"),
                        streamend()));
    }

    @Test
    public void testMethodMin()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("min(1,2)");

        assertThat(simpleTokenizer.getTokens(),
                contains(
                        function("min"),
                        operator("("),
                        number("1.0"),
                        separator(),
                        number("2.0"),
                        operator(")"),
                        streamend()));
    }

    @Test
    public void test()
    {
        final Tokenizer simpleTokenizer = new SimpleTokenizer("-1");

        System.out.println(simpleTokenizer.getTokens());

        assertThat(simpleTokenizer.getTokens(),
                contains(
                        function("min"),
                        operator("("),
                        number("1.0"),
                        separator(),
                        number("2.0"),
                        operator(")"),
                        streamend()));
    }

    @Test
    public void testFormatter() throws Exception
    {
        assertThat(SimpleTokenizer.format(""), equalTo(""));
        assertThat(SimpleTokenizer.format("1"), equalTo("1"));
        assertThat(SimpleTokenizer.format("1+2"), equalTo("1 + 2"));
        assertThat(SimpleTokenizer.format("1*2"), equalTo("1 * 2"));
        assertThat(SimpleTokenizer.format("1/2"), equalTo("1 / 2"));
        assertThat(SimpleTokenizer.format("1+2*3-4/2"), equalTo("1 + 2 * 3 - 4 / 2"));
        assertThat(SimpleTokenizer.format(" 1+2 "), equalTo("1 + 2"));
        assertThat(SimpleTokenizer.format(" 1        +2 "), equalTo("1 + 2"));
        assertThat(SimpleTokenizer.format("1\t+2"), equalTo("1 + 2"));
    }

}
