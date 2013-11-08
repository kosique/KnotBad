package de.diezwei.knotbad;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import de.diezwei.knotbad.knot.Node;
import de.diezwei.knotbad.operator.Factorial;

public class KnotBuilderTest
{
	@Test
	public void testInitBuilder() throws Exception
	{
		final Node knot = new KnotBuilder(10).getKnot();

		assertThat(knot.resolve(), equalTo(10.0));
	}

	@Test
	public void testAdditionOfTwoValues() throws Exception
	{
		final Node knot = new KnotBuilder(10)
				.plus(5)
				.getKnot();

		assertThat(knot.resolve(), equalTo(15.0));
	}

	@Test
	public void testAdditionOfThreeValues() throws Exception
	{
		final Node knot = new KnotBuilder(1)
				.plus(2)
				.plus(3)
				.getKnot();

		assertThat(knot.resolve(), equalTo(6.0));
	}

	@Test
	public void testSubstractionOfThreeValues() throws Exception
	{
		final Node knot = new KnotBuilder(10.0)
				.minus(1)
				.minus(2)
				.getKnot();

		assertThat(knot.resolve(), equalTo(7.0));
	}

	@Test
	public void testMixingAdditionAndSubstraction() throws Exception
	{
		final Node knot = new KnotBuilder(10.0)
				.minus(1)
				.plus(2)
				.getKnot();

		assertThat(knot.resolve(), equalTo(11.0));
	}

	@Test
	public void testMultiplication() throws Exception
	{
		final Node knot = new KnotBuilder(10.0)
				.multiply(3)
				.getKnot();

		assertThat(knot.resolve(), equalTo(30.0));
	}

	@Test
	public void testDivision() throws Exception
	{
		final Node knot = new KnotBuilder(10)
				.divide(4)
				.getKnot();

		assertThat(knot.resolve(), equalTo(2.5));
	}

	@Test
	public void testMixingAllOperations() throws Exception
	{
		final Node knot = new KnotBuilder(10)
				.multiply(2)
				.plus(1)
				.divide(7)
				.getKnot();

		assertThat(knot.resolve(), equalTo(3.0));
	}

	@Test
	public void testAddingValueToKnot() throws Exception
	{
		final KnotBuilder builder = new KnotBuilder(100)
				.plus(20);

		final Node tree = new KnotBuilder(5.0)
				.plus(builder)
				.getKnot();

		assertThat(tree.resolve(), equalTo(125.0));
	}

	@Test
	public void testAddingKnotToKnot() throws Exception
	{
		final KnotBuilder builder1 = new KnotBuilder(6)
				.divide(2);

		final KnotBuilder builder2 = new KnotBuilder(3)
				.minus(1);

		final Node tree = new KnotBuilder(builder1)
				.plus(builder2)
				.getKnot();

		assertThat(tree.resolve(), equalTo(5.0));
	}

	@Test
	public void testMultiplyValueWithKnot() throws Exception
	{
		final KnotBuilder builder = new KnotBuilder(100)
				.plus(20);

		final Node tree = new KnotBuilder(5)
				.multiply(builder)
				.getKnot();

		assertThat(tree.resolve(), equalTo(600.0));
	}

	@Test
	public void testFactorialOf1() throws Exception
	{
		final Node knot = new KnotBuilder(1)
				.factorial()
				.getKnot();

		assertThat(knot.resolve(), equalTo(1.0));
	}

	@Test
	public void testFactorialOf3() throws Exception
	{
		final Node knot = new KnotBuilder(3)
				.knot(Factorial.class)
				.getKnot();

		assertThat(knot.resolve(), equalTo(6.0));
	}

	@Test
	public void testFactorialOfKnot() throws Exception
	{
		final Node knot = new KnotBuilder(2)
				.plus(3)
				.factorial()
				.getKnot();

		assertThat(knot.resolve(), equalTo(120.0));
	}

	@Test
	public void learningTestFactorialTree() throws Exception
	{
		final KnotBuilder builder = new KnotBuilder(1);

		final int rounds = 10;

		for (int i = 2; i <= rounds; i++)
		{
			builder.multiply(i);
		}

		assertThat(builder.getKnot().resolve(), equalTo(3628800.0));
	}
}
