

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Luke Skon, John Karro
 */
public class TrendsTest {
	
	
	/**
	 * Testing construction of the object
	 */
	@Test
	public void test01() {
		Trends test = new StudentTrends();
		assertEquals(test.getCount("bogus"), 0);
	}
	
	
	@Test
	public void test02(){
		Trends test = new StudentTrends();
		assertEquals(test.numEntries(), 0);
	}
	

	@Test 
	public void test03() {
		Trends test = new StudentTrends();
		test.increaseCount("one", 1);
		assertEquals(test.getCount("zero"), 0);
		assertEquals(test.getCount("one"), 1);
	}
	
	@Test 
	public void test04() {
		Trends test = new StudentTrends();
		test.increaseCount("one", 2);
		assertEquals(test.getCount("zero"), 0);
		assertEquals(test.getCount("one"), 2);
	}
	
	@Test
	public void test05() {
		Trends test = new StudentTrends();
		test.increaseCount("one", 1);
		test.increaseCount("two", 2);
		assertEquals(test.getCount("one"), 1);
		assertEquals(test.getCount("two"), 2);
	}
	
	@Test
	public void test06() {
		Trends test = new StudentTrends();
		test.increaseCount("one", 1);
		test.increaseCount("two", 1);
		test.increaseCount("one", 1);
		assertEquals(test.getCount("one"), 2);
		assertEquals(test.getCount("two"), 1);
	}
	
	@Test
	public void test07() {
		Trends test = new StudentTrends();
		test.increaseCount("one", 1);
		test.increaseCount("two", 2);
		test.increaseCount("three",  3);
		assertEquals(test.getNthPopular(0), "three");
		assertEquals(test.getNthPopular(1), "two");
		assertEquals(test.getNthPopular(2), "one");
	}
	
	@Test
	public void test08() {
		Trends test = new StudentTrends();
		test.increaseCount("one", 3);
		test.increaseCount("two", 2);
		test.increaseCount("three",  1);
		assertEquals(test.getNthPopular(2), "three");
		assertEquals(test.getNthPopular(1), "two");
		assertEquals(test.getNthPopular(0), "one");
	}
	
	@Test
	public void test09() {
		Trends test = new StudentTrends();
		test.increaseCount("one", 1);
		test.increaseCount("two", 3);
		test.increaseCount("three",  2);
		assertEquals(test.getNthPopular(1), "three");
		assertEquals(test.getNthPopular(0), "two");
		assertEquals(test.getNthPopular(2), "one");
	}
	
	@Test
	public void test10() {
		Trends test = new StudentTrends();
		test.increaseCount("one", 1);
		test.increaseCount("two", 1);
		test.increaseCount("three",  1);
		test.increaseCount("two", 1);
		test.increaseCount("three",  1);
		test.increaseCount("two", 1);
		assertEquals(test.getNthPopular(1), "three");
		assertEquals(test.getNthPopular(0), "two");
		assertEquals(test.getNthPopular(2), "one");
	}
	
	@Test
	public void test11() {
		Trends test = new StudentTrends();
		test.increaseCount("BBB", 1);
		test.increaseCount("AAA",  1);
		assertEquals(test.getNthPopular(0), "AAA");
		assertEquals(test.getNthPopular(1), "BBB");
	}
	
	@Test
	public void test12() {
		Trends test = new StudentTrends();
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		assertEquals(test.getNthPopular(0), "AAA");
		assertEquals(test.getNthPopular(1), "BBB");
	}
	
	@Test
	public void test13() {
		Trends test = new StudentTrends();
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 2);
		test.increaseCount("CCC", 1);
		assertEquals(test.getNthPopular(0), "BBB");
		assertEquals(test.getNthPopular(1), "AAA");
		assertEquals(test.getNthPopular(2), "CCC");
	}
	
	@Test
	public void test14() {
		Trends test = new StudentTrends();
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		test.increaseCount("CCC", 1);
		test.increaseCount("AAA", 1);
		test.increaseCount("CCC", 1);
		test.increaseCount("AAA", 1);
		test.increaseCount("CCC", 1);
		assertEquals(test.getNthPopular(0), "AAA");
		assertEquals(test.getNthPopular(1), "CCC");
		assertEquals(test.getNthPopular(2), "BBB");
	}
	
	@Test
	public void test15() {
		Trends test = new StudentTrends();
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		assertEquals(test.numEntries(), 2);
	}
	
	@Test
	public void test16() {
		Trends test = new StudentTrends();
		test.increaseCount("AAA", 2);
		test.increaseCount("BBB", 2);
		assertEquals(test.numEntries(), 2);
	}
	
	@Test
	public void test17() {
		Trends test = new StudentTrends();
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		assertEquals(test.numEntries(), 2);
	}
    
}
