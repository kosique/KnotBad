package de.diezwei.knotbad.exception;

import static java.text.MessageFormat.format;
import de.diezwei.knotbad.node.Variable;

public class VariableNotBoundException extends UnexpectedKnotBadException
{
    private static final long serialVersionUID = 1L;

    public VariableNotBoundException(Variable variable)
    {
        super(format("Variable ''{0}'' not bound.", variable.getLiteral()));
    }

}
