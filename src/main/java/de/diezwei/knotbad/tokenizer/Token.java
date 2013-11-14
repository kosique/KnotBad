package de.diezwei.knotbad.tokenizer;

import static de.diezwei.knotbad.tokenizer.TokenType.BRACE_CLOSE;
import static de.diezwei.knotbad.tokenizer.TokenType.BRACE_OPEN;
import static de.diezwei.knotbad.tokenizer.TokenType.FUNCTION;
import static de.diezwei.knotbad.tokenizer.TokenType.OPERATOR_TOKEN;
import static de.diezwei.knotbad.tokenizer.TokenType.SEPARATOR;
import de.diezwei.knotbad.exception.UnexpectedKnotBadException;
import de.diezwei.knotbad.node.Function;
import de.diezwei.knotbad.node.NullOperator;
import de.diezwei.knotbad.node.Operator;
import de.diezwei.knotbad.node.Operators;

public class Token
{
	private final String literal;
	private final TokenType type;

	protected Token(String literal, TokenType type)
	{
		super();
		this.literal = literal;
		this.type = type;
	}

	public String getLiteral()
	{
		return this.literal;
	}

	public static Token streamend()
	{
		return new Token("", TokenType.STREAM_END);
	}

	public static Token lineend()
	{
		return new Token("", TokenType.LINE_END);
	}

	public static Token number(String value)
	{
		return new Token(value, TokenType.NUMBER);
	}

	public static Token operator(String operator)
	{
		switch (operator)
		{
		case "(":
			return new Token(operator, BRACE_OPEN);

		case ")":
			return new Token(operator, BRACE_CLOSE);

		case ",":
			return separator();

		default:
			return new Token(operator, OPERATOR_TOKEN);
		}
	}

	public static Token unknown(String word)
	{
		return new Token(word, TokenType.UNKNOWN);
	}

	@Override
	public String toString()
	{
		return "[" + getLiteral() + "] " + getType();
	}

	public TokenType getType()
	{
		return type;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((literal == null) ? 0 : literal.hashCode());
		result = (prime * result) + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final Token other = (Token) obj;
		if (literal == null)
		{
			if (other.literal != null)
			{
				return false;
			}
		}
		else if (!literal.equals(other.literal))
		{
			return false;
		}
		if (type != other.type)
		{
			return false;
		}
		return true;
	}

	public Operator toOperator()
	{
		final Class<? extends Operator> opType = Operators.getInstance().getOperatorClass(getLiteral());

		if (opType == null)
		{
			return new NullOperator();
		}

		return instantiateOperator(opType);
	}

	public Function toFunction()
	{
		final Class<? extends Function> type = Operators.getInstance().getFunctionClass(getLiteral());

		return instantiateFunction(type);
	}

	private static Operator instantiateOperator(final Class<? extends Operator> opType)
	{
		Operator operator;
		try
		{
			operator = opType.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new UnexpectedKnotBadException("Cannot instantiate operator type " + opType.getCanonicalName(), e);
		}
		return operator;
	}

	private static Function instantiateFunction(final Class<? extends Function> type)
	{
		Function operator;
		try
		{
			operator = type.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new UnexpectedKnotBadException("Cannot instantiate operator type " + type.getCanonicalName(), e);
		}
		return operator;
	}

	public static Token function(String function)
	{
		return new Token(function, FUNCTION);
	}

	public static Token separator()
	{
		return new Token(",", SEPARATOR);
	}

}
