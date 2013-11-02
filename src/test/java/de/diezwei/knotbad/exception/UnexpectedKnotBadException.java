package de.diezwei.knotbad.exception;

public class UnexpectedKnotBadException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public UnexpectedKnotBadException()
	{
		super();
	}

	public UnexpectedKnotBadException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public UnexpectedKnotBadException(String message)
	{
		super(message);
	}

	public UnexpectedKnotBadException(Throwable cause)
	{
		super(cause);
	}
}
