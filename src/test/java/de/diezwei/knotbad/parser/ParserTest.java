package de.diezwei.knotbad.parser;

import static de.diezwei.knotbad.tokenizer.Token.numberToken;
import static de.diezwei.knotbad.tokenizer.Token.operatorToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.Test;

import de.diezwei.knotbad.knot.Knot;
import de.diezwei.knotbad.knot.Value;
import de.diezwei.knotbad.operator.Addition;
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
	public void tokenizeLongExpression() throws Exception
	{
		final Parser parser = new Parser();

		final List<Token> output = parser.toPostfix("1+2*3-4*5+6");

		for (final Token token : output)
		{
			System.out.println(token.getLiteral());
		}

		assertThat(output, hasSize(11));
		assertThat(
				output,
				contains(numberToken("1.0"), numberToken("2.0"), numberToken("3.0"), operatorToken("*"), operatorToken("+"), numberToken("4.0"),
						numberToken("5.0"), operatorToken("*"), operatorToken("-"), numberToken("6.0"), operatorToken("+")));
	}

	@Test
	public void parseValue()
	{
		final Parser parser = new Parser();

		//		assertThat(parser.parse("1"), equalTo((Knot) new Value(1)));
		//		assertThat(parser.parse("5"), equalTo((Knot) new Value(5)));

		final Knot addition = new Addition(new Value(1), new Value(2));
		assertThat(parser.parse("1+1"), equalTo(addition));
	}
}
