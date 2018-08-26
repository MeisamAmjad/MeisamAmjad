package unit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import data_structures.BST;


public class BSTTester {
	private void create_helper(BST<Integer,Integer> D, int a, int b) {
		if (a == b-1) {
			D.insert(new Integer(2*a), new Integer(2*a+1));
		}
		else if (a < b-1) {
			int m = (a+b)/2;
			D.insert(new Integer(2*m), new Integer(2*m+1));
			create_helper(D, a, m);
			create_helper(D, m+1, b);
		}
	}
	
	private BST<Integer,Integer> create_BST(int n) {
		BST<Integer,Integer> D = new BST<Integer,Integer>();
		create_helper(D, 0, n);
		return D;
	}
	
	
	@Test
	public void testHeight() {
		BST<Integer,Integer> T = create_BST(64);
		assertTrue(T.check_structure());
		assertEquals("Height should be 6, not " + T.height(), T.height(), 6);
	}
	
	@Test
	public void testMin() {
		BST<Integer,Integer> T = create_BST(64);
		assertTrue(T.check_structure());

		assertEquals("Min should be 0, not " + (int)T.min(), T.min(), new Integer(0));
	}
	
	@Test
	public void testMin2() {
		BST<Integer,Integer> T = new BST<>();
		for (int i=20; i < 40; i++) {
			T.insert(new Integer(i), i);
			assertTrue(T.check_structure());
		}
		assertEquals("Min should be 20, not " + (int)T.min(), T.min(), new Integer(20));
	}
	
	@Test
	public void testMax() {
		BST<Integer,Integer> T = create_BST(64);
		assertTrue(T.check_structure());
		assertEquals("Max should be 126, not " + (int)T.max(), T.max(), new Integer(126));
	}
	
	@Test
	public void testMax2() {
		BST<Integer,Integer> T = new BST<>();
		for (int i=20; i < 40; i++) {
			T.insert(new Integer(40-i), i);
			assertTrue(T.check_structure());
		}
		assertEquals("Max should be 20, not " + (int)T.max(), T.max(), new Integer(20));
	}

}
