package de.diezwei.knotbad.parser;

import java.io.StreamTokenizer;
import java.io.StringReader;

import org.junit.Test;

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
		final StreamTokenizer tokenizer = new StreamTokenizer(new StringReader("1+2"));

		for (int tval; (tval = tokenizer.nextToken()) != StreamTokenizer.TT_EOF;)
		{
			switch (tval)
			{
			case StreamTokenizer.TT_NUMBER:

				System.out.println("Nummer: " + tokenizer.nval);

				break;
			case StreamTokenizer.TT_WORD:

				System.out.println("Wort: " + tokenizer.sval);

				break;
			case StreamTokenizer.TT_EOL:

				System.out.println("Ende der Zeile");

				break;
			default:

				System.out.println("Zeichen: " + (char) tokenizer.ttype);

			}
		}
	}
}
