package de.diezwei.knotbad.knot;

import de.diezwei.knotbad.parser.token.AssocType;


public abstract class Operator extends Node
{
    @Override
    public abstract AssocType getAssocType();

    @Override
    public abstract int getPrecedence();
    
    public abstract int getArity(); 
}
