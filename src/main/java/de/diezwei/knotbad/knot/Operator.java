package de.diezwei.knotbad.knot;

import de.diezwei.knotbad.parser.token.AssocType;


public abstract class Operator extends Knot
{
    public abstract AssocType getAssocType();

    public abstract int getPrecedence();
    
    public abstract String getLiteral();
}
