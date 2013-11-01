package de.diezwei.knotbad.parser;

import org.junit.Test;

public class ParserTest
{
    @Test
    public void test() throws Exception
    {
        final Parser parser = new Parser(); 
        parser.buildSyntaxTree("1+2*3*4-5");
    }  
}
