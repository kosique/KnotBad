package de.diezwei.knotbad;

import java.util.List;

import de.diezwei.knotbad.tokenizer.Token;

public interface Tokenizer extends Iterable<Token>
{
	public List<Token> getTokens();
}
