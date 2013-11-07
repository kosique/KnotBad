package de.diezwei.knotbad.tokenizer;

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

	private List<String> toLiteralList(final Tokenizer simpleTokenizer)
	{
		final List<String> tokens = new ArrayList<>();
		for (final Token token : simpleTokenizer)
		{
			System.out.println(token);
			tokens.add(token.getLiteral());
		}
		System.out.println();
		return tokens;
	}

	@Test
	public void testGetList()
	{
		final Tokenizer simpleTokenizer = new SimpleTokenizer("1-2*3/4");

		final List<Token> tokens = simpleTokenizer.getTokens();

		final Token token1 = Token.numberToken("1.0");
		final Token token2 = Token.operatorToken("-");
		final Token token3 = Token.numberToken("2.0");
		final Token token4 = Token.operatorToken("*");
		final Token token5 = Token.numberToken("3.0");
		final Token token6 = Token.operatorToken("/");
		final Token token7 = Token.numberToken("4.0");
		final Token token8 = Token.streamEndToken();

		assertThat(tokens, contains(token1, token2, token3, token4, token5, token6, token7, token8));
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
