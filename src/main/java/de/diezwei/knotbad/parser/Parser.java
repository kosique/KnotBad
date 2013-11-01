package de.diezwei.knotbad.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import de.diezwei.knotbad.knot.Knot;
import de.diezwei.knotbad.parser.token.Token;

public class Parser
{
	Pattern numberPattern = Pattern.compile("\\d+\\.{0,1}\\d*");

	final Map<String, Knot> numbers = new HashMap<>();

	Token token;

	void tokenizeNumbers(String input)
	{
		
	}
}
