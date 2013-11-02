package de.diezwei.knotbad.parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.Test;

public class ParserTest
{
    @Test
    public void test() throws Exception
    {
        final Parser parser = new Parser();
         
        final List<String> output = parser.toPostfix("1");
        
        assertThat(output, hasSize(1));
        assertThat(output, contains("1.0"));
    }
    
    @Test
    public void testSum() throws Exception
    {
        final Parser parser = new Parser();
        
        final List<String> output = parser.toPostfix("1+2");
        
        assertThat(output, hasSize(3));
        assertThat(output, contains("1.0", "2.0", "+"));
    }  
    
    @Test
    public void test1() throws Exception
    {
        final Parser parser = new Parser();
        
        final List<String> output = parser.toPostfix("1+2*3-4*5+6");
        
        for (final String string : output)
        {
            System.out.println(string);
        }

        assertThat(output, hasSize(11));
        assertThat(output, contains("1.0",  "2.0",  "3.0",  "*",  "+",  "4.0",  "5.0",  "*",  "-",  "6.0",  "+" ));
    }  
}
