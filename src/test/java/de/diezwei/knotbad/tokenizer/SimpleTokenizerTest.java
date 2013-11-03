package de.diezwei.knotbad.tokenizer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SimpleTokenizerTest
{

    @Test
    public void testValue()
    {
        final SimpleTokenizer simpleTokenizer = new SimpleTokenizer("1");
        
        final List<String> tokens = toLiteralList(simpleTokenizer);
        
        assertThat(tokens, contains("1.0", ""));
    }

    @Test
    public void testSum()
    {
        final SimpleTokenizer simpleTokenizer = new SimpleTokenizer("1+2");
        
        final List<String> tokens = toLiteralList(simpleTokenizer);
        
        assertThat(tokens, contains("1.0", "+", "2.0", ""));
    }
    
    @Test
    public void testDifference()
    {
        final SimpleTokenizer simpleTokenizer = new SimpleTokenizer("1-2");
        
        final List<String> tokens = toLiteralList(simpleTokenizer);
        
        assertThat(tokens, contains("1.0", "-", "2.0", ""));
    }
    
    @Test
    public void testDivision()
    {
        final SimpleTokenizer simpleTokenizer = new SimpleTokenizer("1/2");
        
        final List<String> tokens = toLiteralList(simpleTokenizer);
        
        assertThat(tokens, contains("1.0", "/", "2.0", ""));
    }
    
    @Test
    public void testLongExpression()
    {
        final SimpleTokenizer simpleTokenizer = new SimpleTokenizer("1-2*3/4");
        
        final List<String> tokens = toLiteralList(simpleTokenizer);
        
        assertThat(tokens, contains("1.0", "-", "2.0", "*", "3.0", "/", "4.0", ""));
    }
    
    @Test
    public void testWhitespaces() throws Exception
    {
        final SimpleTokenizer simpleTokenizer = new SimpleTokenizer(" 1 + 2 ");
        
        final List<String> tokens = toLiteralList(simpleTokenizer);
        
        assertThat(tokens, contains("1.0", "+", "2.0", ""));
    }
    
    private List<String> toLiteralList(final SimpleTokenizer simpleTokenizer)
    {
        final List<String> tokens = new ArrayList<>(); 
        for (final Token token : simpleTokenizer)
        {
            System.out.println(token);
            tokens.add(token.getLiteral());
        }
        System.out.println();
        return tokens;
    }

}
