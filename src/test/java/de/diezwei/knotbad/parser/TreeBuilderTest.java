package de.diezwei.knotbad.parser;

import static de.diezwei.knotbad.tokenizer.Token.function;
import static de.diezwei.knotbad.tokenizer.Token.number;
import static de.diezwei.knotbad.tokenizer.Token.operator;
import static de.diezwei.knotbad.tokenizer.Token.variable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.diezwei.knotbad.function.Min;
import de.diezwei.knotbad.node.Node;
import de.diezwei.knotbad.node.Value;
import de.diezwei.knotbad.node.Variable;
import de.diezwei.knotbad.operator.Addition;
import de.diezwei.knotbad.operator.Factorial;
import de.diezwei.knotbad.operator.Multiplication;
import de.diezwei.knotbad.operator.Subtraction;
import de.diezwei.knotbad.tokenizer.Token;

public class TreeBuilderTest
{

    private final TreeBuilder builder = new TreeBuilder();

    @Test
    public void testSingleValue()
    {
        final List<Token> list = Arrays.asList(new Token[] { number("1") });
        assertThat(builder.toNode(list), equalTo((Node) new Value(1)));
    }

    @Test
    public void testSum()
    {
        // 1+2
        final List<Token> list = Arrays.asList(new Token[] { number("1"), number("2"), operator("+") });
        final Node addition = new Addition(new Value(1), new Value(2));
        assertThat(builder.toNode(list), equalTo(addition));
    }

    @Test
    public void testExpressionWithTwoOperators()
    {
        // 1+2-3
        final List<Token> list = Arrays.asList(new Token[] { number("1"), number("2"), operator("+"), number("3"), operator("-") });

        final Node addition = new Addition(new Value(1), new Value(2));
        final Node subtraction = new Subtraction(addition, new Value(3));
        assertThat(builder.toNode(list), equalTo(subtraction));
    }

    @Test
    public void testExpressionWithThreeOperators()
    {
        // (1+2)*(3-4)
        final List<Token> list = Arrays.asList(new Token[] { number("1"), number("2"), operator("+"), number("3"), number("4"), operator("-"),
                operator("*") });

        final Node addition = new Addition(new Value(1), new Value(2));
        final Node subtraction = new Subtraction(new Value(3), new Value(4));
        final Node product = new Multiplication(addition, subtraction);

        assertThat(builder.toNode(list), equalTo(product));
    }

    @Test
    public void testUnaryOperator()
    {
        // !5
        final List<Token> list = Arrays.asList(new Token[] { number("5"), operator("!") });
        final Node factorial = new Factorial(new Value(5));
        assertThat(builder.toNode(list), equalTo(factorial));
    }

    @Test
    public void testMixingUnaryAndBinaryOperators()
    {
        // !(2+3)
        final List<Token> list = Arrays.asList(new Token[] { number("2"), number("3"), operator("+"), operator("!") });
        final Node node = new Factorial(new Addition(new Value(2), new Value(3)));
        assertThat(builder.toNode(list), equalTo(node));
    }

    @Test
    public void testSimpleFunction()
    {
        // min(2,3)
        final List<Token> list = Arrays.asList(new Token[] { number("2"), number("3"), function("min") });

        final Min function = new Min();
        function.setArgument(0, new Value(3));
        function.setArgument(1, new Value(2));

        final Node node = function;

        assertThat(builder.toNode(list), equalTo(node));
    }

    @Test
    public void testFunctionAsOperand()
    {
        // 1 + min(2,3) * 4
        final List<Token> list = Arrays.asList(new Token[] {
                number("1"),
                number("2"),
                number("3"),
                function("min"),
                number("4"),
                operator("*"),
                operator("+")
        });

        final Min function = new Min();
        function.setArgument(0, new Value(3));
        function.setArgument(1, new Value(2));

        final Multiplication multiplication = new Multiplication(function, new Value(4));
        final Node node = new Addition(new Value(1), multiplication);

        assertThat(builder.toNode(list), equalTo(node));
    }

    @Test
    public void testFunctionOfNodes()
    {
        // min(1+2,3 * (4+5))
        final List<Token> list = Arrays.asList(new Token[] {
                number("1"),
                number("2"),
                operator("+"),
                number("3"),
                number("4"),
                number("5"),
                operator("+"),
                operator("*"),
                function("min"),
        });

        final Node sum1 = new Addition(new Value(1), new Value(2));

        final Node sum2 = new Addition(new Value(4), new Value(5));
        final Node product = new Multiplication(new Value(3), sum2);

        final Min function = new Min();
        function.setArgument(0, product);
        function.setArgument(1, sum1);

        final Node node = function;

        assertThat(builder.toNode(list), equalTo(node));
    }

    @Test
    public void testVariables()
    {
        // x
        final List<Token> list = Arrays.asList(new Token[] { Token.variable("x") });
        final Node node = new Variable("x");

        assertThat(builder.toNode(list), equalTo(node));
    }

    @Test
    public void testVariablesAndNumbers()
    {
        // (x+1)*y
        final List<Token> list = Arrays.asList(new Token[] {
                variable("x1"),
                number("1.0"),
                operator("+"),
                variable("x2"),
                operator("*")
        });

        final Node sum = new Addition(new Variable("x1"), new Value(1));
        final Node product = new Multiplication(sum, new Variable("x2"));

        assertThat(builder.toNode(list), equalTo(product));
    }
}
