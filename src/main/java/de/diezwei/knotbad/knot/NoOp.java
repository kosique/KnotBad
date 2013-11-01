package de.diezwei.knotbad.knot;

import org.apache.commons.lang.NotImplementedException;

public class NoOp extends Knot
{
    @Override
    public double resolve()
    {
        throw new NotImplementedException("This is a NoOp Operation");
    }
}
