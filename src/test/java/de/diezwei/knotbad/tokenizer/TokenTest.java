package de.diezwei.knotbad.tokenizer;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TokenTest
{

    @Test
    public void testFactoryMethods()
    {
        assertThat(Token.number("1"), equalTo(new Token("1", TokenType.NUMBER)));
        assertThat(Token.lineend(), equalTo(new Token("", TokenType.LINE_END)));
        assertThat(Token.operator("+"), equalTo(new Token("+", TokenType.OPERATOR_TOKEN)));
        assertThat(Token.operator("("), equalTo(new Token("(", TokenType.BRACE_OPEN)));
        assertThat(Token.operator(")"), equalTo(new Token(")", TokenType.BRACE_CLOSE)));
        assertThat(Token.streamend(), equalTo(new Token("", TokenType.STREAM_END)));
        assertThat(Token.variable("1"), equalTo(new Token("1", TokenType.VARIABLE)));
    }

}
