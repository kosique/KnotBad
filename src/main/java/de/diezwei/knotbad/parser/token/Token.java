package de.diezwei.knotbad.parser.token;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import de.diezwei.knotbad.knot.Knot;

public abstract class Token
{
    private Knot knot; 
    
    public abstract boolean isOperator();

    public abstract AssocType getAssocType();

    public abstract int getPrecedence();
    
    public abstract String getRawOp();
    

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj)
    {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    protected void setKnot(Knot knot) 
    {
        this.knot = knot;
    }
    
    public Knot getKnot()
    {
        return this.knot;
    }
    
    
}
