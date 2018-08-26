package unit_test;

import static org.junit.Assert.*;
import hammingDistance.Solution1;
import hammingDistance.Solution2;

import org.junit.Test;

public class SolutionTester {

	@Test
	public void test() {
		assertEquals(Solution1.hammingDistance(1, 4),Solution2.hammingDistance(1, 4));
		assertEquals(Solution1.hammingDistance(10, 400),Solution2.hammingDistance(10, 400));
		assertEquals(Solution1.hammingDistance(183, 308),Solution2.hammingDistance(183, 308));
		assertEquals(Solution1.hammingDistance(11427, 20001),Solution2.hammingDistance(11427, 20001));
		assertEquals(Solution1.hammingDistance(20, 256),Solution2.hammingDistance(20, 256));
	}

}
