package de.diezwei.knotbad.operator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.diezwei.knotbad.node.Value;

public class SquareRootTest
{

	@Test
	public void testResolve()
	{
		assertThat(new SquareRoot(new Value(1)).resolve(), equalTo(1.0));
		assertThat(new SquareRoot(new Value(4)).resolve(), equalTo(2.0));
		assertThat(new SquareRoot(new Value(100)).resolve(), equalTo(10.0));
	}

}
