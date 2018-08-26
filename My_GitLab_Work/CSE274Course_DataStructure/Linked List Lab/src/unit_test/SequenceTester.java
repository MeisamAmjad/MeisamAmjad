package unit_test;
import static org.junit.Assert.*;
import org.junit.Test;

import data_structures.*;


public class SequenceTester {
	
	private Sequence<String> create_sequence(int n) {
		Sequence<String> S = new LinkedList<String>();  // Specifies which Sequence structure is being used.
		for (int i=0; i < n; i++) {
			S.push_back("" + i);
		}
		
		return S;
	}
	
	/**
	 * Does the constructor create an (empty, non-null) Sequence.
	 */
	@Test
	public void constructor() {
		Sequence<String> S = create_sequence(0);
		assertNotNull("S should not be null", S);
		assertTrue("S.size() should be 0", S.size()==0);	
	}
	
	/**
	 * Does Sequence.add(0) work on an empty list?
	 */
	@Test
	public void addToEmpty() {
		Sequence<String> S = create_sequence(0);
		S.add(0, "0");
		assertTrue("S.size() should be 1", S.size()==1);
		assertEquals("S.get() bad output", S.get(0), "" + 0);
	}
	
	/**
	 * Does Sequence.push_back work?
	 */
	@Test
	public void push_back_test() {
		Sequence<String> S = create_sequence(4);
		assertTrue("S.size() should be 10", S.size()==4);
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get(" + i + "): bad value", S.get(i), "" + i);
		}
	}
	
	/**
	 * Does Sequence.push_front work when adding to the beginning of a list?
	 */
	// Test 4: 
	@Test
	public void push_front_test() {
		Sequence<String> S = create_sequence(4);
		S.push_front("4");
		assertTrue("S.size() should be 5", S.size() == 5);
		assertEquals("S.get(0): bad value", S.get(0), "4");
		for (int i=1; i < S.size(); i++) {
			assertEquals("S.get(" + i + "): bad value", S.get(i), "" + (i-1));
		}
	}
	
	/**
	 * Does Sequene.add(i) work when adding to the middle of a list?
	 */
	@Test 
	public void add() {
		Sequence<String> S = create_sequence(4);
		S.add(2, "4");
		assertTrue("S.size() should be 5", S.size() ==5);
		for (int i=0; i < 2; i++)
			assertEquals("S.get(" + i + "): bad value", S.get(i), "" + i);
		assertEquals("S.get(2): bad value", S.get(2), "" + (4));
		for (int i=3; i < 5; i++)
			assertEquals("S.get(" + i + "): bad value", S.get(i), "" + (i-1));
		
	}
	
	/**
	 * Does Sequence.set(i,v) work on each element?
	 */
	@Test
	public void setElement() {
		Sequence<String> S = create_sequence(4);
		for (int i=0; i < S.size(); i++) {
			String t = "" + (i+10);
			String s = S.set(i, t);
			assertEquals("S.set(" + i + ",t): Return value incorrect", "" + i, s);
		}
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get(" + i + "): Not set correctly", S.get(i), "" + (i+10));
		}
	}
		
	/**
	 * Does pop_front?
	 */
	@Test
	public void removeFirstElement() {
		Sequence<String> S = create_sequence(4);
		String v = S.pop_front();
		assertTrue("S.size() should be 3", S.size()==3);
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get incorrect after remove", S.get(i), "" + (i+1));
		}
		assertEquals("Return value wrong", v, "0");
	}

	/**
	 * Does pop_back work?
	 */
	@Test
	public void removeLastElement() {
		Sequence<String> S = create_sequence(4);
		String v = S.pop_back();
		assertTrue("S.size() should be 3", S.size()==3);
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get incorrect after remove", S.get(i), "" + i);
		}
		assertEquals("Return value wrong", v, "3");
	}
	
	/**
	 * Does remove(i) work when 0 < i < S.size()-1?
	 */
	@Test
	public void removeMiddleElement() {
		Sequence<String> S = create_sequence(4);
		String v = S.remove(2);
		assertTrue("S.size() should be 3", S.size()==3);
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get incorrect after remove", S.get(i), "" + (i < 2 ? i : (i+1)));
		}
		assertEquals("Return value wrong", v, "2");
	}
	
	/**
	 * Can we empty a list and still have a list?
	 */
	@Test
	public void removeAllElements() {
		Sequence<String> S = create_sequence(2);
		S.remove(0);
		S.remove(0);
		assertTrue("List should be empty", S.isEmpty());
		S.push_back("0");
		assertEquals("List should have one value", S.size(), 1);
		assertEquals("Get failed", S.get(0), "0");
	}
	
	/**
	 * Does in() work when the list contains the element?
	 */
	@Test
	public void in_positive() {
		Sequence<String> S = create_sequence(4);
		assertTrue("in should return true", S.in("3"));
	}
	
	/**
	 * Does in() work when the list does not contain the element?
	 */
	@Test
	public void in_negative() {
		Sequence<String> S = create_sequence(4);
		assertFalse("in should return false", S.in("10"));
	}
	
	/**
	 * Test the clear() method
	 */
	@Test
	public void clear() {
		Sequence<String> S = create_sequence(4);
		S.clear();
		assertTrue("List should be empty", S.isEmpty());
		S.push_back("0");
		assertEquals("List should have one value", S.size(), 1);
		assertEquals("Get failed", S.get(0), "0");
	}


}
