package de.diezwei.knotbad.tokenizer;



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

    public static Token streamEndToken()
    {
        return new Token("", TokenType.STREAM_END);
    }

    public static Token lineEndToken()
    {
        return new Token("", TokenType.LINE_END);
    }

    public static Token numberToken(String value)
    {
        return new Token(value, TokenType.NUMBER);
        }

    public static Token operatorToken(String operator)
    {
        return new Token(operator, TokenType.OPERATOR_TOKEN);
    }

    public static Token unknownToken(String word)
    {
        return new Token(word, TokenType.UNKNOWN);
    }

    @Override
    public String toString()
    {
        return  "[" + getLiteral() + "] " + getType();
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
        result = prime * result + ((literal == null) ? 0 : literal.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Token other = (Token) obj;
        if (literal == null)
        {
            if (other.literal != null) return false;
        }
        else if (!literal.equals(other.literal)) return false;
        if (type != other.type) return false;
        return true;
    }
}
