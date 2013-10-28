package de.diezwei.knotbad;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class KnotTest {
	@Test
	public void testLeftScalarRightScalar() throws Exception {
		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotA.setChild1(KnotFactory.createKnot(5));
		knotA.setChild2(KnotFactory.createKnot(24));

		assertThat(knotA.resolve(), equalTo(29));
	}

	@Test
	public void testLeftScalarRightKnot() throws Exception{
		Knot knotB = KnotFactory.createKnot(KnotOperator.Type.MULTIPLICATION);
		knotB.setChild1(KnotFactory.createKnot(3));
		knotB.setChild2(KnotFactory.createKnot(8));

		assertThat(knotB.resolve(), equalTo(24));

		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.ADDITION);
		knotA.setChild1(KnotFactory.createKnot(5));
		knotA.setChild2(knotB);

		assertThat(knotA.resolve(), equalTo(29));
	}

	@Test
	public void testLeftKnotRightScalar() throws Exception {
		Knot knotB = KnotFactory.createKnot(KnotOperator.Type.DIVISION);
		knotB.setChild1(KnotFactory.createKnot(4));
		knotB.setChild2(KnotFactory.createKnot(2));

		assertThat(knotB.resolve(), equalTo(2));

		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.SUBTRACTION);
		knotA.setChild1(knotB);
		knotA.setChild2(KnotFactory.createKnot(1));

		assertThat(knotA.resolve(), equalTo(1));
	}

	@Test
	public void testLeftKnotRightKnot() throws Exception {
		Knot knotC = KnotFactory.createKnot(KnotOperator.Type.SUBTRACTION);
		knotC.setChild1(KnotFactory.createKnot(3));
		knotC.setChild2(KnotFactory.createKnot(2));

		assertThat(knotC.resolve(), equalTo(1));

		Knot knotB = KnotFactory.createKnot(KnotOperator.Type.DIVISION);
		knotB.setChild1(KnotFactory.createKnot(4));
		knotB.setChild2(KnotFactory.createKnot(2));

		assertThat(knotB.resolve(), equalTo(2));

		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.SUBTRACTION);
		knotA.setChild1(knotB);
		knotA.setChild2(knotC);

		assertThat(knotA.resolve(), equalTo(1));
	}

	@Test(expected=Exception.class)
	public void testLeftNullRightValue() throws Exception {
		Knot knotB = KnotFactory.createKnot(KnotOperator.Type.DIVISION);
		knotB.setChild1(KnotFactory.createKnot(4));
		knotB.setChild2(KnotFactory.createKnot(2));

		assertThat(knotB.resolve(), equalTo(2));

		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.SUBTRACTION);
		knotA.setChild1(null);
		knotA.setChild2(knotB);

		assertThat(knotA.resolve(), equalTo(1));
	}

	@Test(expected=Exception.class)
	public void testLeftValueRightNull() throws Exception {
		Knot knotB = KnotFactory.createKnot(KnotOperator.Type.DIVISION);
		knotB.setChild1(KnotFactory.createKnot(4));
		knotB.setChild2(KnotFactory.createKnot(2));

		assertThat(knotB.resolve(), equalTo(2));

		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.SUBTRACTION);
		knotA.setChild1(knotB);
		knotA.setChild2(null);

		assertThat(knotA.resolve(), equalTo(1));
	}

//	@Test(expected=Exception.class)
//	public void testLeftNullRightNull() throws Exception {
//		Knot knotB = KnotFactory.createKnot(null);
//
//		assertThat(knotB, null);
//
//		Knot knotA = KnotFactory.createKnot(KnotOperator.Type.SUBTRACTION);
//		knotA.setChild1(knotB);
//		knotA.setChild2(null);
//
//		assertThat(knotA.resolve(), equalTo(1));
//	}
}