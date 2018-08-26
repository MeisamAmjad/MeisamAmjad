package unit_test;

import static org.junit.Assert.*;
import javax.management.openmbean.KeyAlreadyExistsException;

//import java.util.Random;
import org.junit.Test;
import data_structures.*;
/**
* @author karroje
*
*/
public class DictionaryTester {
	
	private void create_helper(Dictionary<Integer,Integer> D, int a, int b) {
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
	
	private Dictionary<Integer,Integer> create_dictionary(int n) {
		Dictionary<Integer,Integer> D = new Treap<Integer,Integer>();
		create_helper(D, 0, n);
		return D;
	}
	
	/**
	 * Check that the constructor works.
	 */
	@Test
	public void constructor() {
		Dictionary<Integer,Integer> D = create_dictionary(0);
		assertNotNull("D should not be null", D);
		assertTrue("check_structure failed", D.check_structure());
		assertTrue("Dictionary size should be 0", D.size() == 0);
	}
	
	/**
	 * Check that the first insert doesn't crash,  modifies size correctly,
	 * and leaves the data structure intact.
	 */
	@Test
	public void firstInsert() {
		Dictionary<Integer,Integer> D = create_dictionary(0);
		D.insert(new Integer(5), new Integer(5));
		assertTrue("Wrong size after first insert", D.size() == 1);
		assertTrue("check_structure failed", D.check_structure());
	}
	
	/**
	 * Check that the only inserted element can be bound.
	 */
	@Test
	public void firstFind() {
		Dictionary<Integer,Integer> D = create_dictionary(0);
		D.insert(new Integer(5), new Integer(-1));
		assertEquals("Element not found", D.find(new Integer(5)), new Integer(-1));
	}
	
	/**
	 * Check that multiple inserts don't crash, modifies size correctly,
	 * and leaves the data structure intact.
	 */
	@Test
	public void multiInsert() {
		Dictionary<Integer, Integer> D = create_dictionary(10);
		assertTrue("Wrong size", D.size() == 10);
		assertTrue("check_structure failed", D.check_structure());
	}
	
	/**
	 * Test that find is working when the element is in the list.
	 */
	@Test
	public void testFindPositive() {
		Dictionary<Integer,Integer> D = create_dictionary(10);
		for (int i=0; i < 10; i++) {
		    assertEquals("Not found: " + 2*i, new Integer(2*i+1), D.find(new Integer(2*i)));
			assertTrue("check_structure failed after D.find(" + 2*i + ")", D.check_structure());
		}
	}
	
	/**
	 * Test that find is working when the element is not in the list.
	 */
	@Test
	public void testFindNegitive() {
		Dictionary<Integer,Integer> D = create_dictionary(10);
		for (int i=0; i < 10; i++) {
    		assertNull(D.find(new Integer(2*i+1)));
			assertTrue(D.check_structure());
		}
	}
	
	/**
	 * Make sure we can insert an element at the beginning.
	 */
	@Test
	public void testInsertBegin() {
		Dictionary<Integer,Integer> D = create_dictionary(10);
		D.insert(new Integer(1), new Integer(-4));
		assertTrue(D.size()==11);
		assertEquals(D.find(new Integer(1)), new Integer(-4));
		assertTrue(D.check_structure());
	}
	
	/**
	 * Make sure we can insert an element in the middle.
	 */
	@Test
	public void testInsertMiddle() {
		Dictionary<Integer,Integer> D = create_dictionary(10);
		D.insert(11, 13);
		assertTrue("Size should be 11", D.size()==11);
		assertEquals("1 should be found with value 13", D.find(new Integer(11)), new Integer(13));
		assertTrue("check_structure failed", D.check_structure());
	}	
	
	/**
	 * Make sure we can insert an element at the end.
	 */
	@Test
	public void testInsertEnd() {
		Dictionary<Integer,Integer> D = create_dictionary(10);
		D.insert(new Integer(50), new Integer(51));
		assertTrue("Size should be 11", D.size()==11);
		assertEquals("50 should be found with value 51", D.find(new Integer(50)), new Integer(51));
		assertTrue("check_structure failed", D.check_structure());
	}	
	
	@Test
	public void testRemove() {
		for (int i=0; i < 20; i+=2) {
			Dictionary<Integer,Integer> D = create_dictionary(10);
			D.remove(i);
			for (int j=0; j < 20; j += 2 ){
				if (j != i) {
					assertEquals(D.find(j), new Integer(j+1));
				}
				else {
					assertNull(D.find(j));
				}
			}
			assertEquals(D.size(), 9);
			assertTrue("check_structure failed", D.check_structure());
		}
	}
	
	/**
	 * Test that size is properly adjusted after a remove.
	 */
	@Test
	public void testRemoveSize() {
		Dictionary<Integer,Integer> D = create_dictionary(10);
		//D.print_structure();
		//System.out.println(D.size());
		D.remove(10);
		//System.out.println(D.size());
		assertTrue("Size should be 9, not " + D.size(), D.size() == 9);
	}
	
	
	/**
	 * Test the clear() function.
	 */
	@Test
	public void testClear() {
		Dictionary<Integer,Integer> D = create_dictionary(10);
		D.clear();
		assertTrue("Size should be 0", D.size() == 0);
		assertTrue("check_structure failed after clear", D.check_structure());
	}
	
	
	/**
	 * Test that the same key cannot be inserted twice.
	 */
	@Test(expected = KeyAlreadyExistsException.class) 
	public void testRepeatedKey() {
		Dictionary<Integer,Integer> D = create_dictionary(10);
		D.insert(new Integer(6), new Integer(6));
	}
	
	/**
	 * Test that the same value value be inserted twice.
	 */
	@Test
	public void testRepeatedValue() {
		Dictionary<Integer,Integer> D = create_dictionary(10);
		D.insert(new Integer(7),new Integer(7));
	}
}
