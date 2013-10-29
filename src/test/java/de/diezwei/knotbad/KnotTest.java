package de.diezwei.knotbad;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class KnotTest {
	@Test
	public void testOperatorAddition() throws Exception {
		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotA.setChild1(KnotFactory.createKnot(12));
		knotA.setChild2(KnotFactory.createKnot(2));

		assertThat(knotA.resolve(), equalTo(14));
	}

	@Test
	public void testOperatorDivision() throws Exception {
		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.DIVISION);
		knotA.setChild1(KnotFactory.createKnot(12));
		knotA.setChild2(KnotFactory.createKnot(2));

		assertThat(knotA.resolve(), equalTo(6));
	}

	@Test
	public void testOperatorFactorial() throws Exception {
		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.FACTORIAL);
		knotA.setChild1(KnotFactory.createKnot(5));

		assertThat(knotA.resolve(), equalTo(120));
	}

	@Test
	public void testOperatorMultiplication() throws Exception {
		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.MULTIPLICATION);
		knotA.setChild1(KnotFactory.createKnot(12));
		knotA.setChild2(KnotFactory.createKnot(2));

		assertThat(knotA.resolve(), equalTo(24));
	}

	@Test
	public void testOperatorSubtraction() throws Exception {
		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.SUBTRACTION);
		knotA.setChild1(KnotFactory.createKnot(12));
		knotA.setChild2(KnotFactory.createKnot(2));

		assertThat(knotA.resolve(), equalTo(10));
	}

	@Test
	public void testChild1ValueChild2Knot() throws Exception{
		Knot knotB = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotB.setChild1(KnotFactory.createKnot(12));
		knotB.setChild2(KnotFactory.createKnot(2));

		assertThat(knotB.resolve(), equalTo(14));

		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotA.setChild1(KnotFactory.createKnot(12));
		knotA.setChild2(knotB);

		assertThat(knotA.resolve(), equalTo(26));
	}

	@Test
	public void testChild1KnotChild2Value() throws Exception{
		Knot knotB = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotB.setChild1(KnotFactory.createKnot(12));
		knotB.setChild2(KnotFactory.createKnot(2));

		assertThat(knotB.resolve(), equalTo(14));

		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotA.setChild1(knotB);
		knotA.setChild2(KnotFactory.createKnot(2));

		assertThat(knotA.resolve(), equalTo(16));
	}

	@Test
	public void testChild1KnotChild2Knot() throws Exception{
		Knot knotC = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotC.setChild1(KnotFactory.createKnot(12));
		knotC.setChild2(KnotFactory.createKnot(2));

		assertThat(knotC.resolve(), equalTo(14));

		Knot knotB = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotB.setChild1(KnotFactory.createKnot(12));
		knotB.setChild2(KnotFactory.createKnot(2));

		assertThat(knotB.resolve(), equalTo(14));

		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotA.setChild1(knotB);
		knotA.setChild2(knotC);

		assertThat(knotA.resolve(), equalTo(28));
	}
}