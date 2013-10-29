package de.diezwei.knotbad;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class KnotBuilderTest
{

	@Test
	public void testInitBuilder() throws Exception
	{
		final Knot knot = new KnotBuilder(10).getKnot();

		assertThat(knot.resolve(), equalTo(10));
	}

	@Test
	public void testAdditionOfTwoValues() throws Exception
	{
		final Knot knot = new KnotBuilder(10)
				.plus(5)
				.getKnot();

		assertThat(knot.resolve(), equalTo(15));
	}

	@Test
	public void testAdditionOfThreeValues() throws Exception
	{
		final Knot knot = new KnotBuilder(1)
				.plus(2)
				.plus(3)
				.getKnot();

		assertThat(knot.resolve(), equalTo(6));
	}

	@Test
	public void testSubstractionOfThreeValues() throws Exception
	{
		final Knot knot = new KnotBuilder(10)
				.minus(1)
				.minus(2)
				.getKnot();

		assertThat(knot.resolve(), equalTo(7));
	}

	@Test
	public void testMixingAdditionAndSubstraction() throws Exception
	{
		final Knot knot = new KnotBuilder(10)
				.minus(1)
				.plus(2)
				.getKnot();

		assertThat(knot.resolve(), equalTo(11));
	}

	@Test
	public void testMultiplication() throws Exception
	{
		final Knot knot = new KnotBuilder(10)
				.multiply(3)
				.getKnot();

		assertThat(knot.resolve(), equalTo(30));
	}

	@Test
	public void testDivision() throws Exception
	{
		final Knot knot = new KnotBuilder(10)
				.divide(4)
				.getKnot();

		assertThat(knot.resolve(), equalTo(2));
	}

	@Test
	public void testMixingAllOperations() throws Exception
	{
		final Knot knot = new KnotBuilder(10)
				.multiply(2)
				.plus(2)
				.divide(7)
				.getKnot();

		assertThat(knot.resolve(), equalTo(3));
	}

	@Test
	public void testAddingValueToKnot() throws Exception
	{
		final KnotBuilder builder = new KnotBuilder(100)
				.plus(20);

		final Knot tree = new KnotBuilder(5)
				.plus(builder)
				.getKnot();

		assertThat(tree.resolve(), equalTo(125));
	}

	@Test
	public void testAddingKnotToKnot() throws Exception
	{
		final KnotBuilder builder1 = new KnotBuilder(6)
				.divide(2);

		final KnotBuilder builder2 = new KnotBuilder(3)
				.minus(1);

		final Knot tree = new KnotBuilder(builder1)
				.plus(builder2)
				.getKnot();

		assertThat(tree.resolve(), equalTo(5));
	}

	@Test
	public void testMultiplyValueWithKnot() throws Exception
	{
		final KnotBuilder builder = new KnotBuilder(100)
				.plus(20);

		final Knot tree = new KnotBuilder(5)
				.multiply(builder)
				.getKnot();

		assertThat(tree.resolve(), equalTo(600));
	}

	@Test
	public void testFactorial() throws Exception
	{
		final KnotBuilder builder = new KnotBuilder(1);

		final int rounds = 10;

		for (int i = 2; i <= rounds; i++)
		{
			builder.multiply(i);
		}

		assertThat(builder.getKnot().resolve(), equalTo(3628800));
	}
}
