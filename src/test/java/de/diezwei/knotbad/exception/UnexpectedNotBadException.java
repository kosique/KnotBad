package de.diezwei.knotbad.exception;

public class UnexpectedNotBadException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public UnexpectedNotBadException()
	{
		super();
	}

	public UnexpectedNotBadException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public UnexpectedNotBadException(String message)
	{
		super(message);
	}

	public UnexpectedNotBadException(Throwable cause)
	{
		super(cause);
	}
}
